package frc.libs.motionoftheocean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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


        public static void export(String filename, ArrayList<State> recording) throws IOException {
            new File(filename).createNewFile();
            FileWriter writer = new FileWriter(filename);
            for(State state : recording) {
                writer.write(state.toString());
            }
            writer.flush();
            writer.close();
        }

        public static void export(ArrayList<State> recording) throws IOException {
            export(DEFAULT_PATH_FILE, recording);
        }
    }

    public class Parser {}

}
