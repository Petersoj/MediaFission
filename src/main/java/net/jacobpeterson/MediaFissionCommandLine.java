package net.jacobpeterson;

import com.google.gson.*;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class MediaFissionCommandLine {

    private String[] args;
    private File temporaryDataDirectory;
    private File mediaDataFile;
    private File destinationDirectory;
    private String songInfoURL;
    private String[] songGenres;
    private String[] youtubeDLJSONCommand;
    private String[] youtubeDLCommand;
    private String[] ffmpegConvertCommand;

    public MediaFissionCommandLine(String[] args) {
        this.args = args;
        // Create random-named directory to allow for multiple MediaFission instances
        this.temporaryDataDirectory = new File(System.getProperty("user.home"), ".tempMediaFission/" + String.valueOf(Math.random()).substring(5));
        this.mediaDataFile = new File(temporaryDataDirectory, "mediaData.json");

        this.songInfoURL = "http://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key=bc67636b1f06495cdee8b3dbcc49f477&format=json"; // + "&artist=<ARTIST>&track=<TRACK>"

        this.songGenres = new String[]{
                "Alternative",
                "Blues",
                "Classical",
                "Country",
                "Dance",
                "Electronic",
                "Hip-Hop/Rap",
                "Indie",
                "Inspirational",
                "Instrumental",
                "Jazz",
                "Latino",
                "Opera",
                "Pop",
                "R&B/Soul",
                "Reggae",
                "Rock",
                "Soundtrack",
                "Vocal",
                "World"
        };

        this.youtubeDLJSONCommand = new String[]{
                "<youtube-dl placeholder>",
                "-f",
                "bestaudio",
                "--no-playlist",
                "--playlist-items",
                "1",
                "--dump-json",
                "<URL placeholder>"
        };
        this.youtubeDLCommand = new String[]{
                "<youtube-dl placeholder>",
                "-f",
                "bestaudio",
                "--no-playlist",
                "--playlist-items",
                "1",
                "--load-info-json",
                "<json file placeholder>",
        };
        this.ffmpegConvertCommand = new String[]{
                "<ffmpeg placeholder>",
                "-i",
                "<file placeholder>",
                "-i",
                "<album cover placeholder>",
                "-y",
                "-map", "0",
                "-map", "1",
                "-metadata", "title=<title placeholder>",
                "-metadata", "artist=<artist placeholder>",
                "-metadata", "album=<album placeholder>",
                "-metadata", "album_artist=<album_artist placeholder>",
                "-metadata", "genre=<genre placeholder>",
                "-metadata:s:1", "title=Cover (front)",
                "<output path placeholder>"
        };
    }

    public void execute() {

        String url = this.setupEnvironmentAndGetURL();
        JsonObject mediaJSON = this.downloadJSONData(url);
        File mediaFile = this.downloadMedia(mediaJSON);
        JsonObject songMetadata = this.fetchSongMetadata(mediaJSON);
        File finalMediaFile = this.applyMetadataAndCreateFinal(mediaFile, songMetadata);
        this.cleanUpAndMove(finalMediaFile);

        System.out.println();
        System.out.println("Successfully downloaded: " + finalMediaFile.getName());

    }

    private String setupEnvironmentAndGetURL() {
        // Create temporaryDataDirectory
        if (!temporaryDataDirectory.exists() & !temporaryDataDirectory.mkdirs()) {
            throwError("Could not create temporaryDataDirectory!", false, true);
        }

        String returnURL = "";

        // Check proper args -  java -jar MediaFission.jar --youtube-dl <path> --ffmpeg <path> --out <path> --url <url>
        if (args.length != 8) {
            throwError("No URL in args! Use: command <url>", false, true);
        }
        for (int index = 0; index < args.length; index++) {
            String arg = args[index];
            if (arg.equals("--youtube-dl")) {
                this.youtubeDLJSONCommand[0] = args[index + 1];
                this.youtubeDLCommand[0] = args[index + 1];
            } else if (arg.equals("--ffmpeg")) {
                this.ffmpegConvertCommand[0] = args[index + 1];
            } else if (arg.equals("--out")) {
                this.destinationDirectory = new File(args[index + 1]);
            } else if (arg.equals("--url")) {
                returnURL = args[index + 1];
            }
        }

        // Check if youtube-dl, FFMPEG, and out directory exist
        if (!new File(youtubeDLJSONCommand[0]).exists()) {
            throwError("youtube-dl executable does not exist!", false, true);
        }
        if (!new File(ffmpegConvertCommand[0]).exists()) {
            throwError("ffmpeg executable does not exist!", false, true);
        }
        if (!destinationDirectory.exists()) {
            throwError("Out Directory does not exist!", false, true);
        }

        System.out.println("Setup - Temporary data directory obtained and arguments are valid!");

        return returnURL;
    }

    private JsonObject downloadJSONData(String url) {
        System.out.println("Downloading JSON - Fetching JSON data from: " + url + " ...");

        this.replacePlaceholder(youtubeDLJSONCommand, youtubeDLJSONCommand.length - 1, url);

        try {
            Process youtubeDLJSONProcess = this.createCommandProcess(youtubeDLJSONCommand, temporaryDataDirectory);
            if (youtubeDLJSONProcess == null) {
                throwError("Could not create Process for youtubeDLCommand.", true, true);
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(youtubeDLJSONProcess.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("{")) { // Start of main JSON object
                    break; // Use 'line' variable for JSON
                }
            }
            bufferedReader.close();
            youtubeDLJSONProcess.destroy(); // We don't need the process anymore after we have the JSON

            if (line == null) {
                throwError("Could not get media JSON from youtube-dl!", true, true);
            }
            System.out.println("Downloaded JSON - Finished fetching JSON and creating JSON file...");

            // Write the JSON data created from youtube-dl so we can get some metadata from that before downloading the actual file
            Files.write(mediaDataFile.toPath(), line.getBytes()); // Default StandardOpenOptions are fine here (see javadocs)
            System.out.println("Created JSON file - Wrote JSON metadata file to temporary data directory.");

            JsonElement metadataElement = new JsonParser().parse(line); // Parse metadata from youtube-dl so we can get the name and artists as well as the destination file location
            if (!(metadataElement instanceof JsonObject)) {
                throwError("Could not cast JsonElement as JsonObject!", true, true);
            }
            return (JsonObject) metadataElement;
        } catch (JsonParseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private File downloadMedia(JsonObject mediaData) {
        System.out.println("Downloading Media - downloading media using JSON...");

        this.replacePlaceholder(youtubeDLCommand, youtubeDLCommand.length - 1, mediaDataFile.getAbsolutePath());

        try {
            Process youtubeDLProcess = this.createCommandProcess(youtubeDLCommand, temporaryDataDirectory);
            if (youtubeDLProcess == null) {
                throwError("Could not create Process for youtubeDLCommand.", true, true);
            }
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(youtubeDLProcess.getInputStream()));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.contains("[download")) { // Start of main JSON object
                        System.out.println(line);
                    }
                }
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            youtubeDLProcess.waitFor();
            youtubeDLProcess.destroy(); // We don't need the process anymore after we have the downloaded media (in case it doesn't end)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new File(temporaryDataDirectory, mediaData.get("_filename").getAsString());
    }

    private JsonObject fetchSongMetadata(JsonObject mediaData) {
        String artistKey = "&artist=";
        String trackKey = "&track=";

        String artistGuess = null;
        String trackGuess = null;

        String extractor = mediaData.get("extractor").getAsString();
        String title = mediaData.get("title").getAsString();
        if (extractor.equals("youtube") && title.contains(" - ")) { // " - " means that this could be a song so we'll try to parse out the artist and track names
            String[] splitTitle = title.split(" - ");
            artistGuess = splitTitle[0];
            trackGuess = splitTitle[1];
        } else if (extractor.equals("soundcloud")) {
            artistGuess = mediaData.get("uploader").getAsString();
            trackGuess = title;
        }

        System.out.print("\n\n");

        Scanner inputScanner = new Scanner(System.in);

        boolean conclude = false;
        int loopStatus = 0; // Tells whether to print confirmation, edit artist, or edit track information
        while (!conclude) {
            if (loopStatus == 0) {
                System.out.println("Please validate or edit the artist and track name...");
                System.out.println();
                System.out.println("1: Artist -  " + artistGuess);
                System.out.println("2: Track - " + trackGuess);
                System.out.println();
                System.out.println("Press '1' to edit the Artist name");
                System.out.println("Press '2' to edit the Track name");
                System.out.println("Press the 'Return' key to continue with these names");
                System.out.println("Enter an option here: ");

                String input = inputScanner.nextLine();
                if (input.isEmpty()) { // Carriage Return key
                    conclude = true;
                } else {
                    int inputASCIICharacter = input.charAt(0); // Get first character
                    if (inputASCIICharacter == 0x31) { // '1' Key
                        loopStatus = 1;
                    } else if (inputASCIICharacter == 0x32) { // '2' Key
                        loopStatus = 2;
                    } else {
                        System.out.println("Invalid Character!");
                    }
                }
            } else if (loopStatus == 1) { // Artist Name
                System.out.print("Type the new Artist name here: ");
                artistGuess = inputScanner.nextLine();
                loopStatus = 0;
            } else if (loopStatus == 2) { // Track Name
                System.out.print("Type the new Track name here: ");
                trackGuess = inputScanner.nextLine();
                loopStatus = 0;
            }
        }

        this.songInfoURL += artistKey + artistGuess + trackKey + trackGuess;
        System.out.println(songInfoURL);

        String songInfoString = this.makeHTTPRequest(songInfoURL);
        if (songInfoString == null) {
            throwError("HTTP Request empty!", false, true);
        }
        JsonElement songInfoJsonElement = new JsonParser().parse(songInfoString);
        if (!(songInfoJsonElement instanceof JsonObject)) {
            throwError("Could not cast JsonElement as JsonObject!", true, false);
            return null;
        }
        JsonObject songInfoJsonObject = (JsonObject) songInfoJsonElement;
        if (songInfoJsonObject.has("error")) {
            throwError("Error in JSON response " + songInfoJsonObject.get("message").getAsString(), false, false);
            return null;
        }

        System.out.println("Fetched Song Info - Successfully fetched song info from last.fm service!");

        return songInfoJsonObject.get("track").getAsJsonObject();
    }

    private File applyMetadataAndCreateFinal(File mediaFile, JsonObject songInfoJsonObject) {
        System.out.println("Parsing Metadata - reading from JSON into objects...");

        File albumArtFile = null;
        String trackName = "\"\"";
        String artistName = "\"\"";
        String albumName = "\"\"";
        String genreName = "\"\"";

        try {
            trackName = songInfoJsonObject.get("name").getAsString();

            if (songInfoJsonObject.has("artist")) {
                artistName = songInfoJsonObject.get("artist").getAsJsonObject().get("name").getAsString();
            }

            if (songInfoJsonObject.has("album")) {
                JsonObject albumJsonObject = songInfoJsonObject.get("album").getAsJsonObject();
                albumName = albumJsonObject.get("title").getAsString();

                JsonArray albumImageArray = albumJsonObject.get("image").getAsJsonArray();
                String albumArtURL = albumImageArray.get(albumImageArray.size() - 1).getAsJsonObject().get("#text").getAsString();
                albumArtFile = new File(temporaryDataDirectory, albumArtURL.substring(albumArtURL.lastIndexOf('/')));
                try (InputStream in = new URL(albumArtURL).openStream()) {
                    Files.copy(in, albumArtFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (songInfoJsonObject.has("toptags")) {
                genreName = "";
                JsonArray songTagsArray = songInfoJsonObject.get("toptags").getAsJsonObject().get("tag").getAsJsonArray();
                for (JsonElement tagElement : songTagsArray) {
                    if (tagElement instanceof JsonObject) {
                        JsonObject tagObject = (JsonObject) tagElement;
                        String tagName = tagObject.get("name").getAsString();
                        for (String songGenre : songGenres) {
                            if (songGenre.toLowerCase().contains(tagName)) {
                                genreName = songGenre;
                                break;
                            }
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        if (albumArtFile == null) {
            System.out.println("Could not find album art for this song!");
            System.out.println("Input an Image file URL manually here: ");
            Scanner scanner = new Scanner(System.in);
            String url = scanner.nextLine();
            if (url != null && !url.isEmpty()) {
                albumArtFile = new File(url);
            } else {
                throwError("No album art included!", false, true);
            }
            System.out.println("Album art located at: " + albumArtFile.getAbsolutePath());
        }


        File finalMediaFile = new File(temporaryDataDirectory, artistName + " - " + trackName + ".mp3");

        this.replacePlaceholder(ffmpegConvertCommand, 2, mediaFile.getAbsolutePath());
        this.replacePlaceholder(ffmpegConvertCommand, 4, albumArtFile.getAbsolutePath());
        this.replacePlaceholder(ffmpegConvertCommand, 11, trackName);
        this.replacePlaceholder(ffmpegConvertCommand, 13, artistName);
        this.replacePlaceholder(ffmpegConvertCommand, 15, albumName);
        this.replacePlaceholder(ffmpegConvertCommand, 17, artistName);
        this.replacePlaceholder(ffmpegConvertCommand, 19, genreName);
        this.replacePlaceholder(ffmpegConvertCommand, 22, finalMediaFile.getAbsolutePath());

        System.out.println("FFMPEG - Converting and applying metadata...");
        try {
            Process ffmpegConvertProcess = this.createCommandProcess(ffmpegConvertCommand, temporaryDataDirectory);
            if (ffmpegConvertProcess == null) {
                throwError("Could not create Process for youtubeDLCommand.", true, true);
            }
            ffmpegConvertProcess.waitFor();
            ffmpegConvertProcess.destroy(); // We don't need the process anymore after we have the downloaded media (in case it doesn't end)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Applied Metadata - successfully attached metadata to media.");

        return finalMediaFile;
    }

    private void cleanUpAndMove(File finalMediaFile) {
        System.out.println("Moving and cleaning - Moving media file to " + " and cleaning temporary data directory...");

        // Move media file
        try {
            Files.move(finalMediaFile.toPath(), destinationDirectory.toPath().resolve(finalMediaFile.getName()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Clean temp data directory
        File[] dataFiles = temporaryDataDirectory.listFiles();
        if (dataFiles == null) {
            throwError("Could not list data files!", true, true);
        }
        for (File file : dataFiles) {
            if (!file.delete()) {
                throwError("Could not clean up file: " + file.getName(), true, true);
            }
        }
        if (!temporaryDataDirectory.delete()) {
            throwError("Could not clean up temporaryDataDirectory!", true, true);
        }

        System.out.println("Moved and cleaned - Successfully moved media and cleaned temp data directory!");
    }


    private void replacePlaceholder(String[] command, int index, String argument) {
        int indexOfLessThan = command[index].indexOf("<");
        int indexOfGreaterThan = command[index].indexOf(">");

        String newArgument = command[index].substring(0, indexOfLessThan) + argument + command[index].substring(indexOfGreaterThan + 1);
        command[index] = newArgument;
    }

    private Process createCommandProcess(String[] command, File workingDirectory) {
        try {
            return Runtime.getRuntime().exec(command, null, workingDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String makeHTTPRequest(String url) {
        try {
            URL httpURL = new URL(url);
            URLConnection urlConnection = httpURL.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();

            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void throwError(String message, boolean includeStacktrace, boolean fatal) {
        if (message != null) {
            if (includeStacktrace) {
                new RuntimeException(message).printStackTrace();
            } else {
                System.err.println(message);
            }
        }
        if (fatal) {
            System.err.println("Fatal error - exiting!");
            System.exit(1);
        }
    }

    private void debugOutput(Process process) {
        final Thread ioThread = new Thread(() -> {
            try {
                final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ioThread.start();
    }
}
