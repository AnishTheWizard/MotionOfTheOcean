package frc.libs.motionoftheocean;

public class Exceptions {

    public static class PathFileDoesNotExit extends Exception {
        public PathFileDoesNotExit(String msg) {
            super(msg);
        }
    }

    public static class MotionOfTheOceanIsNotReady extends Exception {
        public MotionOfTheOceanIsNotReady(String msg) {
            super(msg);
        }
    }
}
