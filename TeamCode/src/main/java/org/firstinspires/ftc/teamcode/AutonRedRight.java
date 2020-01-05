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

    public void runOpMode() {
        while (!opModeIsActive() && !isStopRequested()) {
            telemetry.addData("Status", "Waiting in Init");
            telemetry.update();
        }
        frontleft = hardwareMap.dcMotor.get("frontleft");
        frontright = hardwareMap.dcMotor.get("frontright");
        backleft = hardwareMap.dcMotor.get("backleft");
        backright = hardwareMap.dcMotor.get("backright");

        backright.setDirection(DcMotor.Direction.REVERSE);
        frontright.setDirection(DcMotor.Direction.REVERSE);

        GetReady();
        PlaceStone();
        HeadToWaffle();
        ParkUnderZone();
    }

    private void GetReady() {
        goForward();
        sleep(201);
        driftleft();
        sleep(454);
    }

    private void PlaceStone() {

    }

    private void HeadToWaffle() {
        driftright();
        sleep (1211);
    }

    private void ParkUnderZone() {
        driftleft();
        sleep (755);
    }

    private void goForward() {
        frontleft.setPower(1);
        backleft.setPower(1);
        frontright.setPower(1);
        backright.setPower(1);

        //get measure
    }

    private void driftleft() {
        frontleft.setPower(-1);
        frontright.setPower(1);
        backleft.setPower(1);
        backright.setPower(-1);

        //get measure

    }
    private void driftright(){
        frontleft.setPower(1);
        frontright.setPower(-1);
        backleft.setPower(-1);
        backright.setPower(1);
    }
}