package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
public class TestTeleop extends OpMode {
    private DcMotor frontLeft, frontRight, backLeft, backRight;
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
    private DcMotor hippo;
    double LiftPowerStandard = .5;
    double LiftPowerSlow = .2;
    double HandsToTheSky = 0;
    double Reach = .7;

    @Override
    public void init() {
        frontLeft = hardwareMap.dcMotor.get("frontleft");
        frontRight = hardwareMap.dcMotor.get("frontright");
        backLeft = hardwareMap.dcMotor.get ("backleft");
        backRight = hardwareMap.dcMotor.get ("backright");
        //  grippy = hardwareMap.servo.get("grippy");

        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);

        liftyboi = hardwareMap.dcMotor.get("liftyboi");
        hippo = hardwareMap.dcMotor.get("hungryhippo");
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
            Totheleft();
        } else if (gamepad1.right_stick_x > .1){
            turninplacetoleft();
        } else if (gamepad1.right_stick_x < -.1){
            turninplacetoright();
        } else if (gamepad1.left_stick_y > .1) {
            notmejkunless ();
        } else if (gamepad1.left_stick_y < -.1) {
            takeitbacknowyall ();
        } else {
            abort ();
        }

        if (gamepad2.right_stick_y >.1){
            Extend ();
        }else if (gamepad2.right_stick_y < -.1){
            condese();
        }else {
            EndGame();
        }

        if (gamepad2.left_bumper = true) {
            LiftPowerStandard = LiftPowerSlow;
        } else {
            LiftPowerStandard = .5;
        }

        if (gamepad2.left_stick_y > .1) {
            GoUp();
        }else if (gamepad2.left_stick_y < -.1) {
            GoDown();
        }else {
            ItIsHighNoon ();
        }

//        if (gamepad2.right_trigger > .1 && gamepad2.right_trigger <.2 ) {
//            grippy.setPosition(ittybittybit);
//        } else if (gamepad2.right_trigger > .2 && gamepad2.right_trigger < .5) {
//            grippy.setPosition(WoAhweareHalfwayThere);
//        } else if (gamepad2.right_trigger > .5 && gamepad2.right_trigger < .8) {
//            grippy.setPosition(almostthere);
//        } else if (gamepad2.right_trigger > .8) {
//            grippy.setPosition(grabbed);
//        } else {
//            grippy.setPosition(open);
//        }
//        telemetry.addData("Servo Position", grippy.getPosition());
//        telemetry.addData("Status", "Running");
//        telemetry.update();
    }

    private void turninplacetoleft() {
        frontLeft.setPower (-DrivePower);
        frontRight.setPower (DrivePower);
        backLeft.setPower (-DrivePower);
        backRight.setPower (DrivePower);
    }

    private void leftstrafe() {
        frontLeft.setPower(-DrivePower);
        frontRight.setPower(DrivePower);
        backLeft.setPower(DrivePower);
        backRight.setPower(-DrivePower);
    }

    private void diagonalrightdown() {
        frontRight.setPower (-DrivePower);
        backLeft.setPower (-DrivePower);
    }

    private void diagonalleftdown() {
        frontLeft.setPower(-DrivePower);
        backRight.setPower(-DrivePower);
    }

    private void abort() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    private void takeitbacknowyall() {
        frontLeft.setPower(-DrivePower);
        frontRight.setPower(-DrivePower);
        backRight.setPower(-DrivePower);
        backLeft.setPower(-DrivePower);
    }

    private void notmejkunless() {
        frontLeft.setPower(DrivePower);
        frontRight.setPower(DrivePower);
        backRight.setPower(DrivePower);
        backLeft.setPower(DrivePower);
    }

    private void turninplacetoright() {
        frontLeft.setPower (DrivePower);
        frontRight.setPower (-DrivePower);
        backLeft.setPower (DrivePower);
        backRight.setPower (-DrivePower);
    }

    private void diagonalrightup() {
        frontLeft.setPower(DrivePower);
        backRight.setPower(DrivePower);
    }

    private void Totheleft() {
        frontLeft.setPower(DrivePower);
        frontRight.setPower(-DrivePower);
        backRight.setPower(DrivePower);
        backLeft.setPower(-DrivePower);
    }

    private void diagonalleftup () {
        frontRight.setPower(DrivePower);
        backLeft.setPower(DrivePower);
    }
    private void GoUp(){
        liftyboi.setPower(LiftPowerStandard);
    }
    private void GoDown(){
        liftyboi.setPower(-LiftPowerStandard);
    }
    private void ItIsHighNoon (){
        liftyboi.setPower(HandsToTheSky);
    }
    private void Extend (){ hippo.setPower(Reach); }
    private void condese (){ hippo.setPower(-Reach); }
    private void EndGame (){ hippo.setPower(HandsToTheSky); }

}