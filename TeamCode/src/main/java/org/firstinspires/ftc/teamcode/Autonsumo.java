package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class Autonsumo extends LinearOpMode {
   private DcMotor frontleft, frontright, backleft,backright;
   private double DrivePower = .6;
   private double RoadWorkAhead = .3;
   private double IAmSpeed = .8;
   private double open = 0;
   private double WoAhweareHalfwayThere = .5;
   private double grabbed = .3;
   private double almostThere = .7;
   private double ittybittybit = .3;
   private Servo grippy;
    private DcMotor liftyboi;
    @Override
    public void runOpMode(){
        while (!opModeIsActive() && !isStopRequested()){
         telemetry.addData("Status", "Waiting in Init");
         telemetry.update(); }

        frontleft = hardwareMap.dcMotor.get("frontleft");
        frontright = hardwareMap.dcMotor.get("frontright");
        backleft = hardwareMap.dcMotor.get ("backleft");
        backright = hardwareMap.dcMotor.get ("backright");
         grippy = hardwareMap.servo.get("grippy");
         liftyboi = hardwareMap.dcMotor.get("liftyboi");
        backleft.setDirection(DcMotor.Direction.REVERSE);
        frontleft.setDirection(DcMotor.Direction.REVERSE);

        doStuff ();

    }

    private void doStuff() {
        driftLeft ();
        sleep (50);
    }
private void driftLeft (){
        frontright.setPower (-1);
        frontleft.setPower(1);
        backleft.setPower(1);
        backright.setPower(-1);
}
    private void forward (){
        frontleft.setPower(.5);
        frontright.setPower(.5);
        backleft.setPower(.5);
        backright.setPower(.5);
    }
    private void goUp (){
        liftyboi.setPower(.5);
    }
    private void goDown(){
        liftyboi.setPower(-.5);
    }
}
