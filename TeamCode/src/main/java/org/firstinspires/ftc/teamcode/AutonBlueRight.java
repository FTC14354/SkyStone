package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import static org.firstinspires.ftc.teamcode.BaseAuton.DRIVE_SPEED;

@Autonomous
public class AutonBlueRight extends LinearOpMode {
    @Override
    public void runOpMode() {
        BaseAuton ba = new BaseAuton(this);
        ba.stopAndResetAll();

        ba.raiseWaffleFoot();
        waitForStart();
        ba.encoderStrafeRight(DRIVE_SPEED, 1);
        ba.driveForward(DRIVE_SPEED, 65);
        sleep(100);
        ba.rotate(-90, DRIVE_SPEED);
        ba.driveForward(DRIVE_SPEED, 15);
        ba.dragWaffle();
        ba.encoderStrafeRight(DRIVE_SPEED, 30);
        ba.driveForward(DRIVE_SPEED, 18);
    }
}