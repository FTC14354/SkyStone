package org.firstinspires.ftc.teamcode;

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

    BaseRobot(HardwareMap hardwareMap) {
        frontLeft = hardwareMap.dcMotor.get("frontleft");
        frontRight = hardwareMap.dcMotor.get("frontright");
        backLeft = hardwareMap.dcMotor.get ("backleft");
        backRight = hardwareMap.dcMotor.get ("backright");

        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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
        frontLeft.setPower(-speed);
        frontRight.setPower(speed);
        backLeft.setPower(speed);
        backRight.setPower(-speed);
    }

    public void moveRight(double speed) {
        frontLeft.setPower (speed);
        frontRight.setPower (-speed);
        backLeft.setPower (speed);
        backRight.setPower (-speed);
    }

    public void turnLeft(double speed) {
        frontLeft.setPower (-speed);
        frontRight.setPower (speed);
        backLeft.setPower (-speed);
        backRight.setPower (speed);
    }

    public void turnRight(double speed) {
        frontLeft.setPower(speed);
        frontRight.setPower(-speed);
        backRight.setPower(speed);
        backLeft.setPower(-speed);
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
        frontRight.setPower (-speed);
        backLeft.setPower (-speed);
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
