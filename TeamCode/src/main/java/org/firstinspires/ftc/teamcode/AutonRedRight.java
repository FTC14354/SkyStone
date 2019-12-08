package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class AutonRedRight extends LinearOpMode {
    private DcMotor frontleft, frontright, backleft, backright;

    private double DrivePower = .6;
    private double RoadWorkAhead = .3;
    private double IAmSpeed = .8;
    private double open = 0;
    private double WoAhweareHalfwayThere = .5;
    private double grabbed = .3;
    private double almostThere = .7;
    private double ittybittybit = .3;
    private Servo grippy;

    public void runOpMode (){
        while (!opModeIsActive() && !isStopRequested()){
            telemetry.addData("Status", "Waiting in Init");
            telemetry.update();
        }
        frontleft = hardwareMap.dcMotor.get ("frontleft");
        frontright = hardwareMap.dcMotor.get ("frontright");
        backleft = hardwareMap.dcMotor.get ("backleft");
        backright = hardwareMap.dcMotor.get ("backright");

        backleft.setDirection(DcMotor.Direction.REVERSE);
        frontleft.setDirection(DcMotor.Direction.REVERSE);

        GetReady();
        ScanStone();
        HeadToWaffle ();
        PlaceStone ();
        ParkUnderZone ();
    }
    private void GetReady (){
goForward1();
left1();
    }
    private void ScanStone (){

    }
    private void HeadToWaffle (){
right1();
right1andahalf();
    }
    private void PlaceStone (){
    }
    private void ParkUnderZone(){
left2();
    }
    private void goForward1() {
            frontleft.setPower(1);
        backleft.setPower(1);
        frontright.setPower(1);
        backright.setPower(1);
        sleep(264);
        //get measure
    }
    private void left1 () {
        frontleft.setPower(1);
        frontright.setPower(-1);
        backleft.setPower(-1);
        backright.setPower(1);
        sleep(101);
        //get measure

    }
    private void left2(){
        frontleft.setPower(1);
        frontright.setPower(-1);
        backleft.setPower(-1);
        backright.setPower(1);
        sleep(1597);
        //get measure

    }
    private void takeItBackNowYall (){
        frontleft.setTargetPosition(-4000);
        frontright.setTargetPosition(-4000);
        backleft.setTargetPosition(-4000);
        backright.setTargetPosition(-4000);
    }
    private void right1 (){
        frontleft.setPower(-1);
        frontright.setPower(1);
        backleft.setPower(-1);
        backright.setPower(1);
        sleep(1983);
        //get measure

    }
    private void right1andahalf (){
        frontleft.setPower(-1);
        frontright.setPower(1);
        backleft.setPower(-1);
        backright.setPower(1);
        sleep(1349);
        //get measure

    }
}
