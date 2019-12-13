package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {
    Gripper gripper;
    Hippo hippo;
    Lift lift;

    private DcMotor frontLeft, frontRight, backLeft, backRight;
    Robot(HardwareMap hardwareMap) {
        frontLeft = hardwareMap.dcMotor.get("frontleft");
        frontRight = hardwareMap.dcMotor.get("frontright");
        backLeft = hardwareMap.dcMotor.get ("backleft");
        backRight = hardwareMap.dcMotor.get ("backright");

        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);

        this.hippo = new Hippo(hardwareMap);
        this.lift = new Lift(hardwareMap);
        this.gripper = new Gripper(hardwareMap);
    }

    void setHippo(Hippo h) { this.hippo = h;  }
    void setLift(Lift l) {
        this.lift = l;
    }
    void setGripper(Gripper g) {
        this.gripper = g;
    }

    void moveForward(double speed) {
        frontLeft.setPower(speed);
        frontRight.setPower(speed);
        backRight.setPower(speed);
        backLeft.setPower(speed);
    }

    void moveBackwards(double speed) {
        frontLeft.setPower(-speed);
        frontRight.setPower(-speed);
        backRight.setPower(-speed);
        backLeft.setPower(-speed);
    }

    void moveLeft(double speed) {
        frontLeft.setPower(speed);
        frontRight.setPower(speed);
        backLeft.setPower(speed);
        backRight.setPower(speed);
    }

    void moveRight(double speed) {
        frontLeft.setPower (speed);
        frontRight.setPower (-speed);
        backLeft.setPower (speed);
        backRight.setPower (-speed);
    }

    void turnLeft(double speed) {
        frontLeft.setPower (-speed);
        frontRight.setPower (speed);
        backLeft.setPower (-speed);
        backRight.setPower (speed);
    }

    void turnRight(double speed) {
        frontLeft.setPower(speed);
        frontRight.setPower(-speed);
        backRight.setPower(speed);
        backLeft.setPower(-speed);
    }

    void moveNW(double speed) {
        frontRight.setPower(speed);
        backLeft.setPower(speed);
    }

    void moveNE(double speed) {
        frontLeft.setPower(speed);
        backRight.setPower(speed);
    }

    void moveSE(double speed) {
        frontRight.setPower (-speed);
        backLeft.setPower (-speed);
    }

    void moveSW(double speed) {
        frontLeft.setPower(-speed);
        backRight.setPower(-speed);
    }

    void stop() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
}

