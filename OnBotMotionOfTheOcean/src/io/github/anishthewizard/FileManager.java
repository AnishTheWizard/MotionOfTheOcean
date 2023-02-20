package io.github.anishthewizard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

    private static String directory;
    private static String[] pathNames = null;

    public static void configureWorkingDirectory(String dir) {
        directory = dir;
    }

    public static String[] locateAllPaths() {
        if(pathNames == null) {
            //TODO REPLACE WITH PROPER LOCATION TACTIC
            pathNames = new String[1];
        }
        return pathNames;
    }

    public static class Encoder {

        private static final String DEFAULT_PATH_FILE = "recording.csv";


        public static void export(String filename, ArrayList<State> recording, String headers) throws IOException {
            new File(filename).createNewFile();
            FileWriter writer = new FileWriter(filename);
            writer.write(headers + "\n");
            for(State state : recording) {
                writer.write(state.toString());
            }
            writer.flush();
            writer.close();
        }

        public static void export(ArrayList<State> recording, String headers) throws IOException {
            export(DEFAULT_PATH_FILE, recording, headers);
        }
    }

    public class Parser {}

}
