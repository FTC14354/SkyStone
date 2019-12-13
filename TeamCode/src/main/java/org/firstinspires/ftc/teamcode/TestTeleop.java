package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TestTeleop extends OpMode {
    private static final double MOVE_FAST = 0.8;
    private static final double MOVE_NORMAL = 0.6;
    private static final double MOVE_SLOW = 0.3;

    private static final double LIFT_POWER_NORMAL = 0.7;
    private static final double LIFT_POWER_SLOW = 0.2;

    private Robot robot;

    @Override
    public void init() {

        this.robot = new Robot(hardwareMap);

        telemetry.addData("Lift",robot.lift.getTelemetry());
        telemetry.addData("Hippo",robot.hippo.getTelemetry());
        telemetry.addData("Gripper", robot.gripper.getTelemetry());
    }

    @Override
    public void loop() {
        movementControls(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x,
                gamepad1.left_trigger > 0.1, gamepad1.right_trigger < 0.1);
        hippoControls(gamepad2.right_stick_y);
        liftControls(gamepad2.left_stick_y, gamepad2.left_bumper);
        gripperControls(gamepad2.right_trigger);
    }

    private void gripperControls(float controlValue) {
        double almostThere = 0.7;
        double ittyBittyBit = 0.3;

        if (controlValue > .1 && controlValue <.2 ) {
            this.robot.gripper.setPosition(ittyBittyBit);
        } else if (controlValue > .2 && controlValue < .5) {
            double woahWeAreHalfwayThere = .5;
            this.robot.gripper.setPosition(woahWeAreHalfwayThere);
        } else if (controlValue > .5 && controlValue < .8) {
            this.robot.gripper.setPosition(almostThere);
        } else if (controlValue > .8) {
            robot.gripper.close();
        } else {
            robot.gripper.open();
        }
    }

    private void liftControls(float controlValue, boolean slowMode) {
        double speed = LIFT_POWER_NORMAL;

        if (slowMode) {
            speed = LIFT_POWER_SLOW;
        }

        if (controlValue > .1) {
            robot.lift.raise(speed);
        } else if (controlValue < -.1) {
            robot.lift.lower(speed);
        } else {
            robot.lift.stop();
        }
    }

    private void hippoControls(float controlValue) {
        if (controlValue > 0.1){
            robot.hippo.extend();
        } else if (controlValue < -0.1){
            robot.hippo.retract();
        } else {
            robot.hippo.stop();
        }
    }

    private void movementControls(float x, float y, float turn, boolean slowMode, boolean fastMode) {
        double speed = MOVE_NORMAL;

        if (slowMode){
            speed = MOVE_SLOW;
        } else if (fastMode) {
            speed = MOVE_FAST;
        }

        if (x < -.1 && y > .1) {
            robot.moveNE(speed);

        } else if (x < -.1 && y < -.1) {
            robot.moveSE(speed);

        } else if (x > .1 && y < -.1){
            robot.moveSW(speed);

        } else if (x > .1 && y > .1) {
            robot.moveNW(speed);

        } else if (x > .1) {
            robot.moveRight(speed);

        } else if (x < -.1) {
            robot.moveLeft(speed);

        } else if (turn > .1){
            robot.turnLeft(speed);

        } else if (turn < -.1){
            robot.turnRight(speed);

        } else if (y > .1) {
            robot.moveForward(speed);

        } else if (y < -.1) {
            robot.moveBackwards(speed);

        } else {
            robot.stop();

        }
    }
}