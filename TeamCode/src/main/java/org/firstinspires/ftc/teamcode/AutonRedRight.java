package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import static org.firstinspires.ftc.teamcode.BaseAuton.DRIVE_SPEED;
import static org.firstinspires.ftc.teamcode.RobotConstants.AUTO_FORWARD_1;
import static org.firstinspires.ftc.teamcode.RobotConstants.AUTO_FORWARD_2;
import static org.firstinspires.ftc.teamcode.RobotConstants.AUTO_STRAFE_1;

@Autonomous
public class AutonRedRight extends LinearOpMode {
    public void runOpMode() {
        BaseAuton ba = new BaseAuton(this);
        ba.stopAndResetAll();

        waitForStart();
        ba.encoderStrafeRight(DRIVE_SPEED, AUTO_STRAFE_1);
        sleep(100);
        ba.driveForward(DRIVE_SPEED, AUTO_FORWARD_1);
        ba.dragWaffle();
        ba.driveForward(-DRIVE_SPEED, AUTO_FORWARD_2);
    }
}
