package frc.libs.motionoftheocean;

public class SharkState {
    private double time;

    private double x;
    private double y;
    private double heading;

    private double linearVelocity;
    private double angularVelocity;

    public SharkState(double t, double x, double y, double lV, double heading, double aV) {
        time = t;

        this.x = x;
        this.y = y;

        linearVelocity = lV;
        angularVelocity = aV;

        this.heading = heading;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getLinearVelocity() {
        return linearVelocity;
    }

    public void setLinearVelocity(double linearVelocity) {
        this.linearVelocity = linearVelocity;
    }

    public double getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(double angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    public double[] getAsArray() {
        return new double[]{x, y, heading, linearVelocity, angularVelocity};
    }

    @Override
    public String toString() {
        return "SharkState{" +
                "time=" + time +
                ", x=" + x +
                ", y=" + y +
                ", heading=" + heading +
                ", linearVelocity=" + linearVelocity +
                ", angularVelocity=" + angularVelocity +
                '}';
    }

    public double getHeading() {
        return heading;
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }
}
