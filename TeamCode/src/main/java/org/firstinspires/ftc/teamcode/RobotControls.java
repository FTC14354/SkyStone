package org.firstinspires.ftc.teamcode;

class RobotControls {
    private static final double MOVE_FAST = 0.8;
    private static final double MOVE_NORMAL = 0.6;
    private static final double MOVE_SLOW = 0.3;

    private static final double LIFT_POWER_NORMAL = 0.7;
    private static final double LIFT_POWER_SLOW = 0.2;

    private IRobot robot;

    RobotControls(IRobot robot) {
        this.robot = robot;
    }

    void gripperControls(float controlValue) {
        double almostThere = 0.7;
        double ittyBittyBit = 0.3;

        if (controlValue > .1 && controlValue <.2 ) {
            robot.setGripperPosition(ittyBittyBit);
        } else if (controlValue > .2 && controlValue < .5) {
            double woahWeAreHalfwayThere = .5;
            robot.setGripperPosition(woahWeAreHalfwayThere);
        } else if (controlValue > .5 && controlValue < .8) {
            this.robot.setGripperPosition(almostThere);
        } else if (controlValue > .8) {
            robot.closeGripper();
        } else {
            robot.openGripper();
        }
    }

    void liftControls(float controlValue, boolean slowMode) {
        double speed = LIFT_POWER_NORMAL;

        if (slowMode) {
            speed = LIFT_POWER_SLOW;
        }

        if (controlValue > .1) {
            robot.raiseLift(speed);
        } else if (controlValue < -.1) {
            robot.lowerLift(speed);
        } else {
            robot.stopLift();
        }
    }

    void hippoControls(float controlValue) {
        if (controlValue > 0.1){
            robot.extendHippo();
        } else if (controlValue < -0.1){
            robot.retractHippo();
        } else {
            robot.stopHippo();
        }
    }

    void movementControls(float x, float y, float turn, boolean slowMode, boolean fastMode) {
        double speed = MOVE_NORMAL;

        if (slowMode){
            speed = MOVE_SLOW;
        } else if (fastMode) {
            speed = MOVE_FAST;
        }

        if (x < -0.1 && y > 0.1) {
            robot.moveNW(speed);

        } else if (x < -0.1 && y < -0.1) {
            robot.moveSW(speed);

        } else if (x > 0.1 && y < -0.1){
            robot.moveSE(speed);

        } else if (x > 0.1 && y > 0.1) {
            robot.moveNE(speed);

        } else if (x > 0.1) {
            robot.moveRight(speed);

        } else if (x < -0.1) {
            robot.moveLeft(speed);

        } else if (turn > 0.1){
            robot.turnRight(speed);

        } else if (turn < -0.1){
            robot.turnLeft(speed);

        } else if (y > .1) {
            robot.moveForward(speed);

        } else if (y < -.1) {
            robot.moveBackwards(speed);

        } else {
            robot.stop();

        }
    }
}
