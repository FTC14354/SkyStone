package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.HashMap;
import java.util.Map;

class BaseRobot {

    public DcMotor frontLeft, frontRight, backLeft, backRight;
    private Orientation lastAngles = new Orientation();
    private double globalAngle, correction, rotation, power = .30;

    protected Map<String, ITelemetry> telemetryMap = new HashMap<>();
    public BNO055IMU IMU;

    BaseRobot(HardwareMap hardwareMap, String configurationfile) {
        frontLeft = hardwareMap.dcMotor.get("frontleft");
        frontRight = hardwareMap.dcMotor.get("frontright");
        backLeft = hardwareMap.dcMotor.get("backleft");
        backRight = hardwareMap.dcMotor.get("backright");

        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = configurationfile; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        IMU = hardwareMap.get(BNO055IMU.class, "imu");
        IMU.initialize(parameters);
    }


    public void moveForward(double speed) {
        frontLeft.setPower(speed);
        frontRight.setPower(speed);
        backRight.setPower(speed);
        backLeft.setPower(speed);
    }

    public void moveBackwards(double speed) {
        frontLeft.setPower(-speed);
        frontRight.setPower(-speed);
        backRight.setPower(-speed);
        backLeft.setPower(-speed);
    }

    public void moveLeft(double speed) {
        frontLeft.setPower(speed);
        frontRight.setPower(-speed);
        backLeft.setPower(-speed);
        backRight.setPower(speed);
    }

    public void moveRight(double speed) {
        frontLeft.setPower(-speed);
        frontRight.setPower(speed);
        backLeft.setPower(speed);
        backRight.setPower(-speed);
    }

    public void turnLeft(double speed) {
        frontLeft.setPower(-speed);
        frontRight.setPower(speed);
        backLeft.setPower(-speed);
        backRight.setPower(speed);
    }

    public void turnRight(double speed) {
        frontLeft.setPower(speed);
        frontRight.setPower(-speed);
        backRight.setPower(-speed);
        backLeft.setPower(speed);
    }

    public void moveNW(double speed) {
        frontRight.setPower(speed);
        backLeft.setPower(speed);
    }

    public void moveNE(double speed) {
        frontLeft.setPower(speed);
        backRight.setPower(speed);
    }

    public void moveSE(double speed) {
        frontRight.setPower(-speed);
        backLeft.setPower(-speed);
    }

    public void moveSW(double speed) {
        frontLeft.setPower(-speed);
        backRight.setPower(-speed);
    }

    public void stop() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    public Map<String, ITelemetry> getTelemetryMap() {
        return this.telemetryMap;
    }
}
