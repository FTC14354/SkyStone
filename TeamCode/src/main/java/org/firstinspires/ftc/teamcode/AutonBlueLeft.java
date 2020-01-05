package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class AutonBlueLeft extends LinearOpMode {
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
    public void runOpMode (){
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

        GetReady ();
        ScanStone ();
        HeadToWaffle ();
        PlaceStone ();
        ParkUnderZone ();

    }

     private void GetReady() {
        goForward1 ();
        turnToRight1 ();
        goForward1andhalf();
        turnToLeft1();

    }



    private void ScanStone (){

    }
     private void HeadToWaffle (){
        turnToLeft2();
        goForward2();
        goForward2andhalf();
        turnToRight2();
    }
      private void PlaceStone (){

    }
     private void ParkUnderZone(){
turnToLeft3();
turnToLeft3andhalf();
goForward3();
    }


        private void goForward1() {
        frontleft.setPower(1);
        backleft.setPower(1);
        frontright.setPower(1);
        backright.setPower(1);
        sleep(234);
    } //get numbers
    private void goForward1andhalf(){
        frontleft.setPower(1);
        backleft.setPower(1);
        frontright.setPower(1);
        backright.setPower(1);
        sleep(234); //get numbers
    }
    private void goForward2 (){
        frontleft.setPower(1);
        backleft.setPower(1);
        frontright.setPower(1);
        backright.setPower(1);
        sleep(234);//get numbers
    }
    private void goForward2andhalf(){
        frontleft.setPower(1);
        backleft.setPower(1);
        frontright.setPower(1);
        backright.setPower(1);
        sleep(234);//get numbers
    }
    private void goForward3 (){
        frontleft.setPower(1);
        backleft.setPower(1);
        frontright.setPower(1);
        backright.setPower(1);
        sleep(234);//get numbers
    }
        private void turnToRight1(){
        frontleft.setPower(1);
        backright.setPower(.5);
        backleft.setPower(1);
        frontright.setPower(.5);
        sleep(234);//get numbers
        }
        private void turnToRight2 (){
            frontleft.setPower(1);
            backright.setPower(.5);
            backleft.setPower(1);
            frontright.setPower(.5);
            sleep(234);//get numbers
        }
        private void turnToLeft1(){
        frontleft.setPower(.5);
        backleft.setPower(.5);
        frontright.setPower(1);
        backright.setPower(1);
        sleep(235);//get numbers
        }
        private void turnToLeft2 (){
            frontleft.setPower(.5);
            backleft.setPower(.5);
            frontright.setPower(1);
            backright.setPower(1);
            sleep(235);//get numbers
        }
        private void turnToLeft3 (){
            frontleft.setPower(.5);
            backleft.setPower(.5);
            frontright.setPower(1);
            backright.setPower(1);
            sleep(235);//get numbers
        }
        private void turnToLeft3andhalf (){
    frontleft.setPower(.5);
    backleft.setPower(.5);
    frontright.setPower(1);
    backright.setPower(1);
    sleep(235);//get numbers
}
}
