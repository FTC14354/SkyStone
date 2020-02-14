package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


import static org.firstinspires.ftc.teamcode.BaseAuton.DRIVE_SPEED;

@Autonomous
public class AutonRedRight extends LinearOpMode {


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
