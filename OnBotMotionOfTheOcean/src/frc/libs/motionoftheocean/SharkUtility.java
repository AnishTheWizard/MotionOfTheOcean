package frc.libs.motionoftheocean;

public class SharkUtility {
    public static boolean findIn(Object[] arr, Object o) {
        for(Object obj : arr) {
            if(o.equals(obj)) {
                return true;
            }
        }
        return false;
    }
}
