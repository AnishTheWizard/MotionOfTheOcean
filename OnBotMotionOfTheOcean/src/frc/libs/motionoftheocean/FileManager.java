package frc.libs.motionoftheocean;

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

        private static final String DEFAULT_PATH_FILE = "recording.wave";


        //TODO finish this
        public static void export(String filename, ArrayList<State> recording) {
            System.out.println("for system file: "+ filename);
            System.out.println(recording.toString());
        }

        public static void export(ArrayList<State> recording) {
            export(DEFAULT_PATH_FILE, recording);
        }
    }

    public class Parser {}

}
