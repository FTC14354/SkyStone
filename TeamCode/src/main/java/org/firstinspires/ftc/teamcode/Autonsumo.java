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
    BNO055IMU imu;
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

        baseRobot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        baseRobot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        baseRobot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        baseRobot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        baseRobot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        baseRobot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        baseRobot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        baseRobot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        driveForward();
        sleep(5000);
        while ( < 90){
        turnToRight();}
        sleep(5000);
        strafeleft();
    }

    private void driveForward() {
        encoderDrive(driveSpeed, 6, 6, 6, 6, 5);
        telemetry.addData("done 1", "done");
    }

    private void turnToRight() {

        encoderDrive(driveSpeed, 2, -2, 2, -2, 5);

        telemetry.addData("done 2", "done");
    }

    private void strafeleft() {
        encoderDrive(driveSpeed, -12, 12, 12, -12, 5);

        telemetry.addData("done 3", "done");
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

    private void rotate(int degrees, double power) {
        resetAngle();
        if (Math.abs(degrees) > 359) degrees = (int) Math.copySign(359, degrees);
    }
    private void resetAngle()
    {
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        globalAngle = 0;
    }
}
