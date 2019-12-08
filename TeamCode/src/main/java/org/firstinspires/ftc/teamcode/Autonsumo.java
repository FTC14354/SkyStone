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

        backleft.setDirection(DcMotor.Direction.REVERSE);
        frontleft.setDirection(DcMotor.Direction.REVERSE);

        doStuff ();

    }

    private void doStuff() {
frontleft.setPower(1);
sleep(1000);

    }
}
