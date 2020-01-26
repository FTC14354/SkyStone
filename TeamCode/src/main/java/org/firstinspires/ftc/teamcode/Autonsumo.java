package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.sql.Driver;
import java.util.Map;

@Autonomous
public class Autonsumo extends LinearOpMode {
    private BaseRobot baseRobot;
    private PIDController pidRotate;
    private Orientation lastAngles = new Orientation();
    private final static float OPEN = 0.0f;
    private final static float CLOSED = 1f;
    private final static float UP = 0.0f;
    private final static float DOWN = 1f;

    private Servo gripper;
    private Lift lift;
    private WaffleFoot waffleFoot;

    private double globalAngle;
    private ElapsedTime runtime = new ElapsedTime();

    private static final double COUNTS_PER_MOTOR_REV = 537.6;    // eg: TETRIX Motor Encoder
    private static final double DRIVE_GEAR_REDUCTION = 2.0;     // This is < 1.0 if geared UP
    private static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    private static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    private static final double DRIVE_SPEED = 0.3;
    private static final double TURN_SPEED = 0.3;
    private static final double TIMEOUT_SECONDS = 5.0;

    private double speed = .3;


    @Override
    public void runOpMode() {
        IRobot robot;
        try {
            robot = new Robot(hardwareMap);
            telemetry.addLine("Robot:").addData("Robot", "Real Robot");
        } catch (Exception ex) {
            robot = new TestRobot(hardwareMap);
            telemetry.addLine("Robot:").addData("Robot", "Test Robot");
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
                telemetry.addData(t, telemetryComponent.getTelemetry());
            }
        }
        baseRobot.frontLeft.getCurrentPosition();
//        baseRobot.frontRight.getCurrentPosition();
//        baseRobot.backLeft.getCurrentPosition();
//        baseRobot.backRight.getCurrentPosition();

        baseRobot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        baseRobot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        baseRobot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        baseRobot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        baseRobot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        baseRobot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        baseRobot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        baseRobot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        baseRobot.backLeft.setPower(DRIVE_SPEED);
        sleep(1000);
        baseRobot.backLeft.setPower(0);
        baseRobot.backRight.setPower(DRIVE_SPEED);
        sleep(1000);
        baseRobot.backRight.setPower(0);
        baseRobot.frontRight.setPower(DRIVE_SPEED);
        sleep(1000);
        baseRobot.frontRight.setPower(0);
        baseRobot.frontLeft.setPower(DRIVE_SPEED);
        sleep(1000);
        baseRobot.frontLeft.setPower(0);

    }

    private void grabblock() {
        raiselift(speed);
        sleep(50);
        openGrip();
        lowerlift(speed);
        sleep(30);
        closeGrip();
    }

    private void placeBlock() {
        raiselift(speed);
        sleep(50);
        openGrip();
        lowerlift(speed);
        sleep(30);

    }

    private void raiselift(double speed) {

        lift.raise(speed);

    }

    private void lowerlift(double speed) {

        lift.lower(speed);

    }

    private void openGrip() {
        this.gripper.setPosition(OPEN);

    }

    private void closeGrip() {
        this.gripper.setPosition(CLOSED);
    }

    private void dragWaffle() {
        lowerWaffleFoot();
        driveForward(DRIVE_SPEED);
        raiseWaffleFoot();

    }

    private void lowerWaffleFoot() {
        this.waffleFoot.setWafflePosition(DOWN);
    }

    private void raiseWaffleFoot() {
        this.waffleFoot.setWafflePosition(UP);
    }

    private void driveForward(double driveSpeed) {
        encoderDrive(driveSpeed, 6);
        telemetry.addData("done 1", "done");
    }

    private void strafeleft(double driveSpeed) {

    }

    private void straferight(double driveSpeed) {


    }
    private double currentRobotPosition (){
        return  (baseRobot.frontLeft.getCurrentPosition()) ;
    }



    private void encoderDrive(double speed
            ,double target

    ) {

        target *= COUNTS_PER_INCH;

        while (currentRobotPosition() < target) {
            baseRobot.frontLeft.setPower(Math.abs(speed));
            baseRobot.frontRight.setPower(Math.abs(speed));
            baseRobot.backRight.setPower(Math.abs(speed));
            baseRobot.backLeft.setPower(Math.abs(speed));

        }

//        int newFrontLeftTarget;
//        int newFrontRightTarget;
//        int newBackLeftTarget;
//        int newBackRightTarget;

        // Ensure that the opmode is still active
//        if (opModeIsActive()) {
//
//            // Determine new target position, and pass to motor controller
//            newFrontLeftTarget = baseRobot.frontLeft.getCurrentPosition() + (int) (frontLeftInches * COUNTS_PER_INCH);
//            newFrontRightTarget = baseRobot.frontRight.getCurrentPosition() + (int) (frontRightInches * COUNTS_PER_INCH);
//            newBackLeftTarget = baseRobot.backLeft.getCurrentPosition() + (int) (backLeftInches * COUNTS_PER_INCH);
//            newBackRightTarget = baseRobot.backRight.getCurrentPosition() + (int) (backRightInches * COUNTS_PER_INCH);
//
//
//            baseRobot.frontLeft.setTargetPosition(newFrontLeftTarget);
//            baseRobot.frontRight.setTargetPosition(newFrontRightTarget);
//            baseRobot.backLeft.setTargetPosition(newBackLeftTarget);
//            baseRobot.backRight.setTargetPosition(newBackRightTarget);
//
//            // Turn On RUN_TO_POSITION
//            baseRobot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            baseRobot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            baseRobot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            baseRobot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//            // reset the timeout time and start motion.
//            runtime.reset();
//            baseRobot.frontLeft.setPower(Math.abs(speed));
//            baseRobot.frontRight.setPower(Math.abs(speed));
//            baseRobot.backRight.setPower(Math.abs(speed));
//            baseRobot.backLeft.setPower(Math.abs(speed));

        // keep looping while we are still active, and there is time left, and both motors are running.
        // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
        // its target position, the motion will stop.  This is "safer" in the event that the robot will
        // always end the motion as soon as possible.
        // However, if you require that BOTH motors have finished their moves before the robot continues
        // onto the next step, use (isBusy() || isBusy()) in the loop test.
        while (opModeIsActive() &&
                (runtime.seconds() < TIMEOUT_SECONDS) &&
                (baseRobot.frontLeft.isBusy() && baseRobot.frontRight.isBusy() && baseRobot.backLeft.isBusy() && baseRobot.backRight.isBusy())) {

            // Display it for the driver.
//                telemetry.addData("Path1", "Running to %7d :%7d", newBackLeftTarget, newBackRightTarget, newFrontLeftTarget, newFrontRightTarget);
            telemetry.addData("Path2", "Running at %7d :%7d",
                    baseRobot.frontLeft.getCurrentPosition(),
                    baseRobot.frontRight.getCurrentPosition());
            baseRobot.backRight.getCurrentPosition();
            baseRobot.backLeft.getCurrentPosition();
            telemetry.addData ("value", "Current: %s", currentRobotPosition());
            telemetry.update();
        }

        // Stop all motion;
        baseRobot.frontLeft.setPower(0);
        baseRobot.frontRight.setPower(0);
        baseRobot.backLeft.setPower(0);
        baseRobot.backRight.setPower(0);

//             Turn off RUN_TO_POSITION
        baseRobot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        baseRobot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        baseRobot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        baseRobot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        sleep(250);   // optional pause after each move
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
    private void rotate(int degrees, double power) {
        // restart imu angle tracking.
        resetAngle();

        // if degrees > 359 we cap at 359 with same sign as original degrees.
        if (Math.abs(degrees) > 359) degrees = (int) Math.copySign(359, degrees);

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
            while (opModeIsActive() && getAngle() == 0) {
                baseRobot.frontLeft.setPower(-power);
                baseRobot.backLeft.setPower(-power);
                baseRobot.frontRight.setPower(power);
                baseRobot.backRight.setPower(power);
                sleep(100);
                updateRotationalTelemetry(degrees);
            }

            do {
                power = pidRotate.performPID(getAngle()); // power will be - on right turn.
                baseRobot.frontLeft.setPower(power);
                baseRobot.backLeft.setPower(power);
                baseRobot.frontRight.setPower(-power);
                baseRobot.backRight.setPower(-power);
                updateRotationalTelemetry(degrees);

            } while (opModeIsActive() && !pidRotate.onTarget());
        } else    // left turn.
            do {
                power = pidRotate.performPID(getAngle()); // power will be + on left turn.
                baseRobot.frontLeft.setPower(power);
                baseRobot.backLeft.setPower(power);
                baseRobot.frontRight.setPower(-power);
                baseRobot.backRight.setPower(-power);
                updateRotationalTelemetry(degrees);

            } while (opModeIsActive() && !pidRotate.onTarget());

        // turn the motors off.
        baseRobot.frontLeft.setPower(0);
        baseRobot.backLeft.setPower(0);
        baseRobot.frontRight.setPower(0);
        baseRobot.backRight.setPower(0);

        // wait for rotation to stop.
        sleep(500);

        // reset angle tracking on new heading.
        resetAngle();
    }

    private void updateRotationalTelemetry(int degrees) {
        telemetry.addData("Rotating:", "Target angle: %s, Current angle: %s", degrees, getAngle());
        telemetry.update();
    }
}
