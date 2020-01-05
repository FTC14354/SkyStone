package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Base64;
import java.util.Objects;


@TeleOp
public class TeleBOP extends OpMode {
    private DcMotor frontleft, frontright, backleft, backright, hippo, liftyboi;
    private double DrivePower = .6;
    private double RoadWorkAhead = .3;
    private double IAmSpeed = .8;
    private Servo grippy;
    private double HandsToTheSky = 0;
    private double MAXIMUMOVERDRIVE = 1;
    private double open = 0;
    private double WoAhweareHalfwayThere = .5;
    private double grabbed = 1;
    private double almostthere = .7;
    private double ittybittybit = .3;
    double LiftPowerStandard = 1;
    double LiftPowerSlow = .2;
    double zeropower = 0;
    double Reach = .7;
    private DigitalChannel backSensor;
    private DigitalChannel frontSensor;
    private Gripper gripper;

    @Override
    public void init() {

        frontleft = hardwareMap.dcMotor.get("frontleft");
        frontright = hardwareMap.dcMotor.get("frontright");
        backleft = hardwareMap.dcMotor.get("backleft");
        backright = hardwareMap.dcMotor.get("backright");
        hippo = hardwareMap.dcMotor.get("hungryhippo");
        grippy = hardwareMap.servo.get("grippy");
        backleft.setDirection(DcMotor.Direction.REVERSE);
        frontleft.setDirection(DcMotor.Direction.REVERSE);
        backSensor = hardwareMap.digitalChannel.get("backsensor");
        frontSensor = hardwareMap.digitalChannel.get("frontsensor");
        liftyboi = hardwareMap.dcMotor.get("liftyboi");


    }


    @Override
    public void loop() {
        boolean backSensorValue = backSensor.getState();
        boolean frontSensorValue = frontSensor.getState();

        telemetry.addData("backSensor", backSensorValue);
        telemetry.addData("frontSensor", frontSensorValue);
        telemetry.update();

        if (gamepad1.left_trigger > .1) {
            DrivePower = RoadWorkAhead;
        } else if (gamepad1.right_trigger > .1) {
            DrivePower = IAmSpeed;
        } else {
            DrivePower = .6;
        }

        if (gamepad1.left_stick_x < -.1 && gamepad1.left_stick_y > .1) {
            diagonalrightup();
        } else if (gamepad1.left_stick_x < -.1 && gamepad1.left_stick_y < -.1) {
            diagonalrightdown();
        } else if (gamepad1.left_stick_x > .1 && gamepad1.left_stick_y < -.1) {
            diagonalleftdown();
        } else if (gamepad1.left_stick_x > .1 && gamepad1.left_stick_y > .1) {
            diagonalleftup();
        } else if (gamepad1.left_stick_x > .1) {
            leftstrafe();
        } else if (gamepad1.left_stick_x < -.1) {
            Totheleft();
        } else if (gamepad1.right_stick_x > .1) {
            turninplacetoleft();
        } else if (gamepad1.right_stick_x < -.1) {
            turninplacetoright();
        } else if (gamepad1.left_stick_y > .1) {
            forward();
        } else if (gamepad1.left_stick_y < -.1) {
            backwards();
        } else {
            abort();
        }

        if (gamepad2.right_stick_y < -.1 &&backSensorValue) {
            retract();
        } else if (gamepad2.right_stick_y > .1 &&  frontSensorValue) {
            extend();
        } else {
            stopMoving();
        }

        if (gamepad2.left_bumper) {
            LiftPowerStandard = LiftPowerSlow;
        } else {
            LiftPowerStandard = .5;
        }

        if (gamepad2.left_bumper && gamepad2.left_stick_y > .1) {
            GoUpSlow();
        } else if (gamepad2.left_bumper && gamepad2.left_stick_y < -.1) {
            GoDownSlow();
        } else if (gamepad2.left_stick_y > .1) {
            GoDown();
        } else if (gamepad2.left_stick_y < -.1) {
            GoUp();
        } else {
            ItIsHighNoon();
        }
/*
        if (gamepad2.right_trigger > 0) {
            gripper.grab();
        } else {
            gripper.release();
        }
*/
        if (gamepad2.right_trigger > .1 && gamepad2.right_trigger < .2) {
            grippy.setPosition(ittybittybit);
        } else if (gamepad2.right_trigger > .2 && gamepad2.right_trigger < .5) {
            grippy.setPosition(WoAhweareHalfwayThere);
        } else if (gamepad2.right_trigger > .5 && gamepad2.right_trigger < .8) {
            grippy.setPosition(almostthere);
        } else if (gamepad2.right_trigger > .8) {
            grippy.setPosition(grabbed);
        } else {
            grippy.setPosition(open);
        }
        telemetry.addData("Status", "Running");
        telemetry.addData("Servo Position", grippy.getPosition());
        telemetry.update();


        telemetry.addData("Status", "Running");
        telemetry.addData("encoderValueFL", frontleft.getCurrentPosition());
        telemetry.addData("encoderValueFR", frontright.getCurrentPosition());
        telemetry.addData("encoderValueBR", backright.getCurrentPosition());
        telemetry.addData("encoderValueBL", backleft.getCurrentPosition());
       telemetry.update();

    }


    private void turninplacetoleft() {
        frontleft.setPower(-DrivePower);
        frontright.setPower(DrivePower);
        backleft.setPower(-DrivePower);
        backright.setPower(DrivePower);
    }

    private void leftstrafe() {
        frontleft.setPower(-DrivePower);
        frontright.setPower(DrivePower);
        backleft.setPower(DrivePower);
        backright.setPower(-DrivePower);
    }

    private void diagonalrightdown() {
        frontright.setPower(-DrivePower);
        backleft.setPower(-DrivePower);
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

    private void backwards() {
        frontleft.setPower(-DrivePower);
        frontright.setPower(-DrivePower);
        backright.setPower(-DrivePower);
        backleft.setPower(-DrivePower);
    }

    private void forward() {
        frontleft.setPower(DrivePower);
        frontright.setPower(DrivePower);
        backright.setPower(DrivePower);
        backleft.setPower(DrivePower);
    }

    private void turninplacetoright() {
        frontleft.setPower(DrivePower);
        frontright.setPower(-DrivePower);
        backleft.setPower(DrivePower);
        backright.setPower(-DrivePower);
    }

    private void diagonalrightup() {
        frontleft.setPower(DrivePower);
        backright.setPower(DrivePower);
    }

    private void Totheleft() {
        frontleft.setPower(DrivePower);
        frontright.setPower(-DrivePower);
        backright.setPower(DrivePower);
        backleft.setPower(-DrivePower);
    }
    private void extend (){
        hippo.setPower(Reach);
    }
    private void retract (){
        hippo.setPower(-Reach);
    }
    private void stopMoving (){
        hippo.setPower(0);
    }

    private void diagonalleftup() {
        frontright.setPower(DrivePower);
        backleft.setPower(DrivePower);
    }
    private void GoDownSlow () {
        liftyboi.setPower(LiftPowerSlow);
    }

    private void GoUpSlow () {
        liftyboi.setPower(-LiftPowerSlow);
    }


    private void ItIsHighNoon () {
        liftyboi.setPower(HandsToTheSky);
    }

    private void GoUp() {
        liftyboi.setPower(LiftPowerStandard);
    }

    private void GoDown() {
        liftyboi.setPower(-LiftPowerStandard);
    }

    private void stopLift() {
        liftyboi.setPower(zeropower);
    }


}