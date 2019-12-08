package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp

public class Gripper extends OpMode {
    private Servo grippy;
    private double open = 0;
    private double WoAhwereHalfwayThere = .5;
    private double grabbed = .3;
    private double almostthere = .7;
    private double ittybittybit = .3;

    @Override
    public void init () {
       grippy = hardwareMap.servo.get("grippy");
    }

    @Override
    public  void loop () {
        if (gamepad2.right_trigger > .1 && gamepad2.right_trigger <.2 ) {
           grippy.setPosition(ittybittybit);
        } else if (gamepad2.right_trigger > .2 && gamepad2.right_trigger < .5) {
            grippy.setPosition(WoAhwereHalfwayThere);
        } else if (gamepad2.right_trigger > .5 && gamepad2.right_trigger < .8) {
            grippy.setPosition(almostthere);
        } else if (gamepad2.right_trigger > .8) {
            grippy.setPosition(grabbed);
        } else {
            grippy.setPosition(open);
        }
        telemetry.addData("Servo Position", grippy.getPosition());
        telemetry.addData("Status", "Running");
        telemetry.update();
    }

}
