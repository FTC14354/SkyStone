package org.firstinspires.ftc.teamcode;

import android.graphics.Path;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp

public class TeleBOP extends OpMode {
    private DcMotor frontleft, frontright, backleft, backright;
    private double DrivePower = .6;
    private double RoadWorkAhead = .3;
    private double IAmSpeed = .8;

    @Override
    public void init() {
      frontleft = hardwareMap.dcMotor.get("frontleft");
      frontright = hardwareMap.dcMotor.get("frontright");
      backleft = hardwareMap.dcMotor.get ("backleft");
      backright = hardwareMap.dcMotor.get ("backright");

      backleft.setDirection(DcMotor.Direction.REVERSE);
      frontleft.setDirection(DcMotor.Direction.REVERSE);
    }


    @Override
    public void loop() {
        if (gamepad1.left_trigger > .1){
            DrivePower = RoadWorkAhead;
        }else if (gamepad1.right_trigger > .1) {
            DrivePower = IAmSpeed;
        } else {
            DrivePower = .6;
        }

        if (gamepad1.left_stick_x < -.1 && gamepad1.left_stick_y > .1) {
            diagonalrightup ();
        } else if (gamepad1.left_stick_x < -.1 && gamepad1.left_stick_y < -.1) {
            diagonalrightdown();
        } else if (gamepad1.left_stick_x > .1 && gamepad1.left_stick_y < -.1){
            diagonalleftdown();
        } else if (gamepad1.left_stick_x > .1 && gamepad1.left_stick_y > .1) {
            diagonalleftup();
        } else if (gamepad1.left_stick_x > .1) {
            leftstrafe ();
        } else if (gamepad1.left_stick_x < -.1) {
           rightstrafe();
        } else if (gamepad1.right_stick_x > .1){
            turninplacetoleft();
        } else if (gamepad1.right_stick_x < -.1){
            turninplacetoright();

        } else if (gamepad1.left_stick_y > .1) {
            go ();
        } else if (gamepad1.left_stick_y < -.1) {
            takeitbacknowyall ();
        } else {
            abort ();

        }
    }

    private void turninplacetoleft() {
        frontleft.setPower (-DrivePower);
        frontright.setPower (DrivePower);
        backleft.setPower (-DrivePower);
        backright.setPower (DrivePower);
    }

    private void leftstrafe() {
        frontleft.setPower(-DrivePower);
        frontright.setPower(DrivePower);
        backleft.setPower(DrivePower);
        backright.setPower(-DrivePower);
    }

    private void diagonalrightdown() {
        frontright.setPower (-DrivePower);
        backleft.setPower (-DrivePower);
    }

    private void diagonalleftdown() {
        frontleft.setPower(-DrivePower);
        backright.setPower(-DrivePower);
    }

    private void abort() {
        frontleft.setPower(0);
        frontright.setPower(0);
        backleft.setPower(0);
        backright.setPower(0);
    }

    private void takeitbacknowyall() {
        frontleft.setPower(-DrivePower);
        frontright.setPower(-DrivePower);
        backright.setPower(-DrivePower);
        backleft.setPower(-DrivePower);
    }

    private void go() {
        frontleft.setPower(DrivePower);
        frontright.setPower(DrivePower);
        backright.setPower(DrivePower);
        backleft.setPower(DrivePower);
    }

    private void turninplacetoright() {
        frontleft.setPower (DrivePower);
        frontright.setPower (-DrivePower);
        backleft.setPower (DrivePower);
        backright.setPower (-DrivePower);
    }

    private void diagonalrightup() {
        frontleft.setPower(DrivePower);
        backright.setPower(DrivePower);
    }

    private void rightstrafe() {
        frontleft.setPower(DrivePower);
        frontright.setPower(-DrivePower);
        backright.setPower(DrivePower);
        backleft.setPower(-DrivePower);
    }

    private void diagonalleftup () {
        frontright.setPower(DrivePower);
        backleft.setPower(DrivePower);
    }
}