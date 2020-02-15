package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import static org.firstinspires.ftc.teamcode.BaseAuton.DRIVE_SPEED;

@Autonomous
public class AutonRedRight extends LinearOpMode {
    public void runOpMode() {
        BaseAuton ba = new BaseAuton(this);
        ba.stopAndResetAll();

        waitForStart();
        ba.encoderStrafeRight(DRIVE_SPEED, 25);
        sleep(100);
        ba.driveForward(DRIVE_SPEED, 25);
        ba.dragWaffle();
        ba.driveForward(-DRIVE_SPEED, 40);
    }
}
