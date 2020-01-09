package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.sql.Driver;
import java.util.Map;

@Autonomous
public class Autonsumo extends LinearOpMode {
    BaseRobot baseRobot;
    double driveSpeed = .5;
    private ElapsedTime runtime = new ElapsedTime();
    static final double COUNTS_PER_MOTOR_REV = 1440;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 2.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.6;
    static final double TURN_SPEED = 0.5;
    double globalAngle, power = .30, correction, rotation;
    PIDController pidRotate, pidDrive;


    Orientation lastAngles = new Orientation();


    @Override
    public void runOpMode() throws InterruptedException {
        IRobot robot;
        try {
            robot = new Robot(hardwareMap);
            telemetry.addLine("Robot:").addData("Robot", "Real Robot");
        } catch (Exception ex) {
            robot = new TestRobot(hardwareMap);
            telemetry.addLine("Robot:").addData("Robot", "Test Robot");
        }
        baseRobot = (BaseRobot) robot;

        Map<String, ITelemetry> telemetryMap = robot.getTelemetryMap();
        for (String t : telemetryMap.keySet()) {
            ITelemetry telemetryComponent = telemetryMap.get(t);
            if (telemetryComponent != null) {
                telemetry.addData(t, telemetryComponent.getTelemetry());
            }
        }
        pidRotate = new PIDController(0, 0, 0);
        pidDrive = new PIDController(.05, 0, 0);

        telemetry.addData("Mode", "calibrating...");
        telemetry.update();
        baseRobot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        baseRobot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        baseRobot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        baseRobot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        baseRobot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        baseRobot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        baseRobot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        baseRobot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        telemetry.addData("1 imu heading", lastAngles.firstAngle);
        telemetry.addData("2 global heading", globalAngle);
        telemetry.addData("3 correction", correction);
        telemetry.addData("4 turn rotation", rotation);
        telemetry.update();

        driveForward();
        sleep(5000);

        turnToRight();
        sleep(5000);
        strafeleft();
    }

    private void driveForward() {

        encoderDrive(driveSpeed, 6, 6, 6, 6, 5);
        telemetry.addData("done 1", "done");
    }

    private void turnToRight() {
        rotate(90, DRIVE_SPEED);

        telemetry.addData("done 2", "done");
    }

    private void strafeleft() {

        telemetry.addData("done 3", "done");
        telemetry.update();
    }

    public void encoderDrive(double speed,
                             double FrontleftInches, double FrontrightInches, double BackleftInches, double BackRightInches,
                             double timeoutS) {
        int newFrontLeftTarget;
        int newFrontRightTarget;
        int newBackLeftTarget;
        int newBackRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newFrontLeftTarget = baseRobot.frontLeft.getCurrentPosition() + (int) (FrontleftInches * COUNTS_PER_INCH);
            newFrontRightTarget = baseRobot.frontRight.getCurrentPosition() + (int) (FrontrightInches * COUNTS_PER_INCH);
            newBackLeftTarget = baseRobot.frontLeft.getCurrentPosition() + (int) (BackleftInches * COUNTS_PER_INCH);
            newBackRightTarget = baseRobot.frontRight.getCurrentPosition() + (int) (BackRightInches * COUNTS_PER_INCH);
            baseRobot.frontLeft.setTargetPosition(newFrontLeftTarget);
            baseRobot.frontRight.setTargetPosition(newFrontRightTarget);
            baseRobot.backLeft.setTargetPosition(newBackLeftTarget);
            baseRobot.backRight.setTargetPosition(newBackRightTarget);

            // Turn On RUN_TO_POSITION
            baseRobot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            baseRobot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            baseRobot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            baseRobot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            baseRobot.frontLeft.setPower(Math.abs(speed));
            baseRobot.frontRight.setPower(Math.abs(speed));
            baseRobot.backRight.setPower(Math.abs(speed));
            baseRobot.backLeft.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (baseRobot.frontLeft.isBusy() && baseRobot.frontRight.isBusy() && baseRobot.backLeft.isBusy() && baseRobot.backRight.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newBackLeftTarget, newBackRightTarget, newFrontLeftTarget, newFrontRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        baseRobot.frontLeft.getCurrentPosition(),
                        baseRobot.frontRight.getCurrentPosition());
                baseRobot.backRight.getCurrentPosition();
                baseRobot.backLeft.getCurrentPosition();
                telemetry.update();
            }

            // Stop all motion;
            baseRobot.frontLeft.setPower(0);
            baseRobot.frontRight.setPower(0);
            baseRobot.backLeft.setPower(0);
            baseRobot.backRight.setPower(0);

            // Turn off RUN_TO_POSITION
            baseRobot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            baseRobot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            baseRobot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            baseRobot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }

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

    private void rotate(int degrees, double power)
    {
        // restart imu angle tracking.
        resetAngle();

        // If input degrees > 359, we cap at 359 with same sign as input.
        if (Math.abs(degrees) > 359) degrees = (int) Math.copySign(359, degrees);

        // start pid controller. PID controller will monitor the turn angle with respect to the
        // target angle and reduce power as we approach the target angle. We compute the p and I
        // values based on the input degrees and starting power level. We compute the tolerance %
        // to yield a tolerance value of about 1 degree.
        // Overshoot is dependant on the motor and gearing configuration, starting power, weight
        // of the robot and the on target tolerance.

        pidRotate.reset();

        double p = Math.abs(power/degrees);
        double i = p / 100.0;
        pidRotate.setPID(p, i, 0);

        pidRotate.setSetpoint(degrees);
        pidRotate.setInputRange(0, degrees);
        pidRotate.setOutputRange(0, power);
        pidRotate.setTolerance(1.0 / Math.abs(degrees) * 100.0);
        pidRotate.enable();

        // getAngle() returns + when rotating counter clockwise (left) and - when rotating
        // clockwise (right).

        // rotate until turn is completed.

        if (degrees < 0)
        {
            // On right turn we have to get off zero first.
            while (opModeIsActive() && getAngle() == 0)
            {
            }
                baseRobot.frontLeft.setPower(power);
                baseRobot.backLeft.setPower(power);
                baseRobot.backRight.setPower(-power);
            baseRobot.frontRight.setPower(-power);
                sleep(100);
            }

            do {
                power = pidRotate.performPID(getAngle()); // power will be - on right turn.
                baseRobot.frontLeft.setPower(-power);
                baseRobot.backLeft.setPower(-power);
                baseRobot.backRight.setPower(power);
                baseRobot.frontRight.setPower(power);

            } while (opModeIsActive() && !pidRotate.onTarget());
        } else    // left turn.
                {
                power = pidRotate.performPID(getAngle()); // power will be + on left turn.
                baseRobot.frontLeft.setPower(-power);
                baseRobot.backLeft.setPower(-power);
                baseRobot.backRight.setPower(power);
                baseRobot.frontRight.setPower(power);
            }while (opModeIsActive() && !pidRotate.onTarget());


        private void resetAngle() {
        lastAngles = baseRobot.IMU.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);


    }
}
