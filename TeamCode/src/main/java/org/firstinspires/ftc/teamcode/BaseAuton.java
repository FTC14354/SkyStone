package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.Map;

import static android.os.SystemClock.sleep;
import static java.lang.Math.abs;

public class BaseAuton {
    private LinearOpMode parent;
    private BaseRobot baseRobot;
    private PIDController pidRotate;
    private Orientation lastAngles = new Orientation();
//    private final static float OPEN = 0.0f;
//    private final static float CLOSED = 1f;
//    private final static float UP = 0.0f;
//    private final static float DOWN = 1f;

//    private Servo gripper;
//    private Lift lift;
//    private WaffleFoot waffleFoot;

    private double globalAngle;
    private ElapsedTime runtime = new ElapsedTime();

    private static final double COUNTS_PER_MOTOR_REV = 350;    // eg: TETRIX Motor Encoder
    private static final double DRIVE_GEAR_REDUCTION = 2.0;     // This is < 1.0 if geared UP
    private static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    private static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.6;
    //    private static final double TURN_SPEED = 0.3;
    private static final double TIMEOUT_SECONDS = 5.0;

    //    private double speed = .3;
    IRobot robot;

    BaseAuton(LinearOpMode parent) {
        this.parent = parent;

        IRobot robot;
        try {
            robot = new Robot(parent.hardwareMap);
            parent.telemetry.addLine("Robot:").addData("Robot", "Real Robot");
        } catch (Exception ex) {
            robot = new TestRobot(parent.hardwareMap);
            parent.telemetry.addLine("Robot:").addData("Robot", "Test Robot");
        }
        baseRobot = (BaseRobot) robot;
        // Set PID proportional value to start reducing power at about 50 degrees of rotation.
        // P by itself may stall before turn completed so we add a bit of I (integral) which
        // causes the PID controller to gently increase power if the turn is not completed.
        pidRotate = new PIDController(.003, .00003, 0);

        Map<String, ITelemetry> telemetryMap = robot.getTelemetryMap();
        for (String t : telemetryMap.keySet()) {
            ITelemetry telemetryComponent = telemetryMap.get(t);
            if (telemetryComponent != null) {
                parent.telemetry.addData(t, telemetryComponent.getTelemetry());
            }
        }
        parent.telemetry = new MultipleTelemetry(parent.telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    void stopAndResetAll() {
        baseRobot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        baseRobot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        baseRobot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        baseRobot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        baseRobot.frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        baseRobot.frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        baseRobot.backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        baseRobot.backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    //    private void grabblock() {
//        raiselift(speed);
//        openGrip();
//        lowerlift(speed);
//        sleep(30);
//        closeGrip();
//    }
//
//    private void placeBlock() {
//        raiselift(speed);
//        sleep(50);
//        openGrip();
//        lowerlift(speed);
//        sleep(30);
//
//    }
//
//    private void raiselift(double speed) {
//
//
//    }
//
//    private void lowerlift(double speed) {
//
//        lift.lower(speed);
//
//    }
//
//    private void openGrip() {
//        this.gripper.setPosition(OPEN);
//
//    }
//
//    private void closeGrip() {
//        this.gripper.setPosition(CLOSED);
//    }
//
    void dragWaffle() {
//        lowerWaffleFoot();
        sleep(2000);
        driveForward(-DRIVE_SPEED, 18);
        encoderStrafeLeft(DRIVE_SPEED, 3);
        rotate(-90, DRIVE_SPEED);
//        raiseWaffleFoot();
        sleep(200);

    }

    private void lowerWaffleFoot() {
        robot.waffleDown();
    }

    void raiseWaffleFoot() {
        robot.waffleUp();
    }

    //
    void driveForward(double driveSpeed, double target) {
        baseRobot.frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        baseRobot.frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        baseRobot.backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        baseRobot.backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        encoderDrive(driveSpeed, target);
        baseRobot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        baseRobot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        baseRobot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        baseRobot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }


//    private void strafeLeft(double driveSpeed, double target) {
//        getAngle();
//
//        while (opModeIsActive() && (currentRobotPosition() > target)) {
//
//            if (globalAngle < -.1) {
//                baseRobot.frontLeft.setPower((-.4));
//                baseRobot.frontRight.setPower(.3);
//                baseRobot.backRight.setPower((-.4));
//                baseRobot.backLeft.setPower((.3));
//                updateRotationalTelemetry((int) globalAngle);
//            }else if (globalAngle > .1){
//                baseRobot.frontLeft.setPower((-.3));
//                baseRobot.frontRight.setPower(.4);
//                baseRobot.backRight.setPower((-.3));
//                baseRobot.backLeft.setPower((.4));
//                updateRotationalTelemetry((int) globalAngle);
//            }else if ( -.1 <globalAngle && globalAngle < .1){
//                encoderStrafeLeft(driveSpeed, -1);
//                updateRotationalTelemetry((int) globalAngle);
//            }  else {updateRotationalTelemetry((int) globalAngle);
//                baseRobot.frontLeft.getCurrentPosition();
//            }
//
//
//
//
//
//        }
//
//
//    }


    void encoderStrafeLeft(double speed, double target) {
        target *= COUNTS_PER_INCH;

        while (currentRobotPosition() > target) {
            baseRobot.frontLeft.setPower((-speed));
            baseRobot.frontRight.setPower(speed);
            baseRobot.backRight.setPower((-speed));
            baseRobot.backLeft.setPower((speed));

        }
        baseRobot.frontLeft.setPower(0);
        baseRobot.frontRight.setPower(0);
        baseRobot.backLeft.setPower(0);
        baseRobot.backRight.setPower(0);
    }

    void encoderStrafeRight(double speed
            , double target

    ) {

        baseRobot.frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        baseRobot.frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        baseRobot.backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        baseRobot.backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        target *= COUNTS_PER_INCH;

        while (currentRobotPosition() < target) {
            baseRobot.frontLeft.setPower((speed));
            baseRobot.frontRight.setPower((-speed));
            baseRobot.backRight.setPower((speed));
            baseRobot.backLeft.setPower((-speed));

        }
        baseRobot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        baseRobot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        baseRobot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        baseRobot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }


    private double currentRobotPosition() {
        return (
                ((Math.abs(baseRobot.frontLeft.getCurrentPosition())) +
                        (Math.abs(baseRobot.frontRight.getCurrentPosition())) +
                        (Math.abs(baseRobot.backLeft.getCurrentPosition())) +
                        (Math.abs(baseRobot.backRight.getCurrentPosition()))) / 4.0);
    }


    private void encoderDrive(double speed, double target) {

        target *= COUNTS_PER_INCH;

        while (currentRobotPosition() < abs(target)) {
            baseRobot.frontLeft.setPower((speed));
            baseRobot.frontRight.setPower((speed));
            baseRobot.backRight.setPower((speed));
            baseRobot.backLeft.setPower((speed));

        }



        // keep looping while we are still active, and there is time left, and both motors are running.
        // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
        // its target position, the motion will stop.  This is "safer" in the event that the robot will
        // always end the motion as soon as possible.
        // However, if you require that BOTH motors have finished their moves before the robot continues
        // onto the next step, use (isBusy() || isBusy()) in the loop test.
        while (parent.opModeIsActive() &&
                (runtime.seconds() < TIMEOUT_SECONDS) &&
                (baseRobot.frontLeft.isBusy() && baseRobot.frontRight.isBusy() && baseRobot.backLeft.isBusy() && baseRobot.backRight.isBusy())) {

            // Display it for the driver.
//                telemetry.addData("Path1", "Running to %7d :%7d", newBackLeftTarget, newBackRightTarget, newFrontLeftTarget, newFrontRightTarget);
            parent.telemetry.addData("Path2", "Running at %7d :%7d",
                    baseRobot.frontLeft.getCurrentPosition(),
                    baseRobot.frontRight.getCurrentPosition());
            baseRobot.backRight.getCurrentPosition();
            baseRobot.backLeft.getCurrentPosition();
            parent.telemetry.addData("value", "Current: %s", currentRobotPosition());
            parent.telemetry.update();
        }

        // Stop all motion;
        baseRobot.frontLeft.setPower(0);
        baseRobot.frontRight.setPower(0);
        baseRobot.backLeft.setPower(0);
        baseRobot.backRight.setPower(0);

//             Turn off RUN_TO_POSITION
        baseRobot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        baseRobot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        baseRobot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        baseRobot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        parent.sleep(250);   // optional pause after each move
//        }
    }


    /**
     * Resets the cumulative angle tracking to zero.
     */
    private void resetAngle() {
        lastAngles = baseRobot.IMU.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        globalAngle = 0;
    }

    /**
     * Get current cumulative angle rotation from last reset.
     *
     * @return Angle in degrees. + = left, - = right from zero point.
     */
    private double getAngle() {
        // We experimentally determined the Z axis is the axis we want to use for heading angle.
        // We have to process the angle because the imu works in euler angles so the Z axis is
        // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
        // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

        Orientation angles = baseRobot.IMU.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        globalAngle += deltaAngle;

        lastAngles = angles;

        return globalAngle;
    }

    /**
     * Rotate left or right the number of degrees. Does not support turning more than 359 degrees.
     *
     * @param degrees Degrees to turn, + is left - is right
     */
    public void rotate(int degrees, double power) {
        // restart imu angle tracking.
        resetAngle();

        baseRobot.frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        baseRobot.frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        baseRobot.backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        baseRobot.backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // if degrees > 359 we cap at 359 with same sign as original degrees.
        if (abs(degrees) > 359) degrees = (int) Math.copySign(359, degrees);

        // start pid controller. PID controller will monitor the turn angle with respect to the
        // target angle and reduce power as we approach the target angle. This is to prevent the
        // robots momentum from overshooting the turn after we turn off the power. The PID controller
        // reports onTarget() = true when the difference between turn angle and target angle is within
        // 1% of target (tolerance) which is about 1 degree. This helps prevent overshoot. Overshoot is
        // dependant on the motor and gearing configuration, starting power, weight of the robot and the
        // on target tolerance. If the controller overshoots, it will reverse the sign of the output
        // turning the robot back toward the setpoint value.

        pidRotate.reset();
        pidRotate.setSetpoint(degrees);
        pidRotate.setInputRange(0, degrees);
        pidRotate.setOutputRange(0, power);
        pidRotate.setTolerance(1);
        pidRotate.enable();

        // getAngle() returns + when rotating counter clockwise (left) and - when rotating
        // clockwise (right).

        // rotate until turn is completed.

        if (degrees < 0) {
            // On right turn we have to get off zero first.
            while (parent.opModeIsActive() && getAngle() == 0) {
                baseRobot.frontLeft.setPower(-power);
                baseRobot.backLeft.setPower(-power);
                baseRobot.frontRight.setPower(power);
                baseRobot.backRight.setPower(power);
                parent.sleep(100);
                updateRotationalTelemetry(degrees);
            }

            do {
                power = pidRotate.performPID(getAngle()); // power will be - on right turn.
                baseRobot.frontLeft.setPower(power);
                baseRobot.backLeft.setPower(power);
                baseRobot.frontRight.setPower(-power);
                baseRobot.backRight.setPower(-power);
                updateRotationalTelemetry(degrees);

            } while (parent.opModeIsActive() && !pidRotate.onTarget());
        } else    // left turn.
            do {
                power = pidRotate.performPID(getAngle()); // power will be + on left turn.
                baseRobot.frontLeft.setPower(power);
                baseRobot.backLeft.setPower(power);
                baseRobot.frontRight.setPower(-power);
                baseRobot.backRight.setPower(-power);
                updateRotationalTelemetry(degrees);

            } while (parent.opModeIsActive() && !pidRotate.onTarget());

        // turn the motors off.
        baseRobot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        baseRobot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        baseRobot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        baseRobot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // wait for rotation to stop.
        parent.sleep(500);

        // reset angle tracking on new heading.
        resetAngle();
    }

    private void updateRotationalTelemetry(int degrees) {
        parent.telemetry.addData("Rotating:", "Target angle: %s, Current angle: %s", degrees, getAngle());
        parent.telemetry.addData("Current Position", "Current Position: %s", currentRobotPosition());
        parent.telemetry.update();
    }
}
