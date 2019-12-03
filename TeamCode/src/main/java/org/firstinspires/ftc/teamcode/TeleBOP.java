package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp

public class TeleBOP extends OpMode {
    private DcMotor frontleft, frontright, backleft, backright;
    private double DrivePower = .6;
    private double RoadWorkAhead = .3;
    private double IAmSpeed = .8;
    private Servo grippy;
    private double open = 0;
    private double WoAhweareHalfwayThere = .5;
    private double grabbed = .3;
    private double almostthere = .7;
    private double ittybittybit = .3;
    private DcMotor liftyboi;
    double LiftPowerStandard = .5;
    double LiftPowerSlow = .2;
    double zeropower = 0;
    double Reach = .7;
    private Hippo hippo;

    @Override
    public void init() {
        frontleft = hardwareMap.dcMotor.get("frontleft");
        frontright = hardwareMap.dcMotor.get("frontright");
        backleft = hardwareMap.dcMotor.get("backleft");
        backright = hardwareMap.dcMotor.get("backright");
        grippy = hardwareMap.servo.get("grippy");
        hippo = new Hippo(hardwareMap);
        backleft.setDirection(DcMotor.Direction.REVERSE);
        frontleft.setDirection(DcMotor.Direction.REVERSE);

        liftyboi = hardwareMap.dcMotor.get("liftyboi");
    }


    @Override
    public void loop() {
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

        if (gamepad2.right_stick_y > .1) {
            hippo.Extend();
        } else if (gamepad2.right_stick_y < -.1) {
            hippo.retract();
        } else {
            hippo.EndGame();
        }

        if (gamepad2.left_bumper = true) {
            LiftPowerStandard = LiftPowerSlow;
        } else {
            LiftPowerStandard = .5;
        }

        if (gamepad2.left_stick_y > .1) {
            GoUp();
        } else if (gamepad2.left_stick_y < -.1) {
            GoDown();
        } else {
            stopLift();
        }

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
        telemetry.addData("Servo Position", grippy.getPosition());
        telemetry.addData("Status", "Running");
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

    private void diagonalleftup() {
        frontright.setPower(DrivePower);
        backleft.setPower(DrivePower);
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