package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class AutonRedLeft extends LinearOpMode {
    private DcMotor frontleft, frontright, backleft, backright;
    private double DrivePower = .6;
    private double RoadWorkAhead =.3;
    private double IAmSpeed = .8;
    private double open = 0;
    private double WoAhweareHalfwayThere = .5;
    private double ittybittybit = .3;
    private Servo grippy;

    @Override
    public void runOpMode () {
        while (!opModeIsActive() && !isStopRequested()) {
            telemetry.addData("Status", "Waiting in Init");
            telemetry.update();
        }
        frontleft = hardwareMap.dcMotor.get("frontleft");
        frontright = hardwareMap.dcMotor.get("frontright");
        backleft = hardwareMap.dcMotor.get("backleft");
        backright = hardwareMap.dcMotor.get("backright");
        grippy = hardwareMap.servo.get("grippy");

        backleft.setDirection(DcMotor.Direction.REVERSE);
        frontleft.setDirection(DcMotor.Direction.REVERSE);
        ImReady();
        ScanforStone();
        ScadoodleToWaffle();
        PlaceStone();
        BringWaffleToZone();
        ParkUnderZone();
    }
           private void ImReady (){
left1();
goForward1();
           }
           private void ScanforStone(){

           }
           private void ScadoodleToWaffle (){
takeItBackNowYall();
right1();
right2();
goForward2();
           }
           private void PlaceStone (){

           }
           private void BringWaffleToZone (){
takeItBackNowYall1();
           }
           private void ParkUnderZone(){
left2();
                 }
    private void goForward1() {
        frontleft.setPower(1);
        backleft.setPower(1);
        frontright.setPower(1);
        backright.setPower(1);
        sleep (234);//get numbers
    }
    private void goForward2() {
        frontleft.setPower(1);
        backleft.setPower(1);
        frontright.setPower(1);
        backright.setPower(1);
        sleep(234);//get numbers
    }
    private void left1 () {
        frontleft.setPower(1);
        frontright.setPower(-1);
        backleft.setPower(-1);
        backright.setPower(1);
        sleep(347);
//get numbers
    }
    private void left2 (){
        frontleft.setPower(1);
        frontright.setPower(-1);
        backleft.setPower(-1);
        backright.setPower(1);
        sleep(347);
//get numbers
    }
    private void takeItBackNowYall (){
        frontleft.setPower(-1);
        frontright.setPower(-1);
        backleft.setPower(-1);
        backright.setPower(-1);
        sleep(234);
        //get numbers
    }
    private void right1 (){
        frontleft.setPower(-1);
        frontright.setPower(1);
        backleft.setPower(-1);
        backright.setPower(1);
        sleep (342);
    }//get numbers
    private void right2 (){
        frontleft.setPower(-1);
        frontright.setPower(1);
        backleft.setPower(-1);
        backright.setPower(1);
        sleep (342); //ger numbers
    }

    private void takeItBackNowYall1 (){
        frontleft.setPower(-1);
        frontright.setPower(-1);
        backleft.setPower(-1);
        backright.setPower(-1);
        sleep(234);
        //get numbers
    }
}
