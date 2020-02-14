package org.firstinspires.ftc.teamcode;

import java.util.Map;

interface IRobot {
    void moveForward(double speed);
    void moveBackwards(double speed);
    void moveLeft(double speed);
    void moveRight(double speed);
    void turnLeft(double speed);
    void turnRight(double speed);
    void moveNW(double speed);
    void moveNE(double speed);
    void moveSE(double speed);
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

    void setWafflePosition (double wafflePosition);
    void waffleDown();
    void waffleUp();


    Map<String, ITelemetry> getTelemetryMap();
}
