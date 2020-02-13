package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


import static org.firstinspires.ftc.teamcode.BaseAuton.DRIVE_SPEED;

@Autonomous
public class AutonRedRight extends LinearOpMode {



    @Override
    public void runOpMode() {

        BaseAuton ba = new BaseAuton(this);
        ba.stopAndResetAll();

        waitForStart();
        ba.driveForward(DRIVE_SPEED, 30);
        sleep(100);
        ba.rotate(-90, DRIVE_SPEED);
        ba.driveForward(DRIVE_SPEED, 33);



    }

}
