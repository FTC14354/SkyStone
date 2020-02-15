package org.firstinspires.ftc.teamcode;

import java.util.Map;

interface IRobot {
    void moveForward(double speed);
    void moveBackwards(double speed);
    void moveLeft(double speed);
    void moveRight(double speed);
    void turnLeft(double speed);
    void turnRight(double speed);
    @SuppressWarnings("unused")
    void moveNW(double speed);
    @SuppressWarnings("unused")
    void moveNE(double speed);
    @SuppressWarnings("unused")
    void moveSE(double speed);
    @SuppressWarnings("unused")
    void moveSW(double speed);
    void stop();

    void raiseLift(double speed);
    void lowerLift(double speed);
    void stopLift();

    void extendHippo();
    void retractHippo();
    void stopHippo();

    void setGripperPosition(double position);
    void openGripper();
    void closeGripper();

    @SuppressWarnings("unused")
    void setWafflePosition (double wafflePosition);
    void waffleDown();
    void waffleUp();

    Map<String, ITelemetry> getTelemetryMap();
}
