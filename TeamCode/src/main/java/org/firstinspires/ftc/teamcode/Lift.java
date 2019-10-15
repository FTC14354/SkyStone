package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Lift extends OpMode {
    private DcMotor liftyboi;
    double LiftPowerStandard = .5;
    double LiftPowerSlow = .2;
    double HandsToTheSky = 0;
    public void init (){
    liftyboi = hardwareMap.dcMotor.get("liftyboi");
    }
    public void loop () {
        if (gamepad2.left_bumper = true) {
            LiftPowerStandard = LiftPowerSlow;
        } else {
            LiftPowerStandard = .5;
        }

        if (gamepad2.left_stick_y > .1) {
            GoUp();
        }else if (gamepad2.left_stick_y < .1) {
        GoDown();
        }else {
            ItIsHighNoon ();
        }
    }
    private void GoUp(){
        liftyboi.setPower(LiftPowerStandard);
    }
    private void GoDown(){
        liftyboi.setPower(-LiftPowerStandard);
    }
    private void ItIsHighNoon (){
        liftyboi.setPower(HandsToTheSky);
    }
}
