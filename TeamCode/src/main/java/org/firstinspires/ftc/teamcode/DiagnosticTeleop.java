package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class DiagnosticTeleop extends OpMode {
    TestRobot robot;

    @Override
    public void init() {
        robot = new TestRobot(hardwareMap);
    }

    @Override
    public void loop() {
        final double POWER = 0.3;
        double power = 0;

        if (gamepad1.left_stick_y > 0.1) {
            power = POWER;
        }
        else if (gamepad1.left_stick_y < 0.1) {
            power = -POWER;
        }

        DcMotor[] motors = { robot.frontLeft, robot.frontRight, robot.backLeft, robot.backRight };
        double[] powers = { 0, 0, 0, 0};

        if (gamepad1.a) {
            powers[0] = power;
        } else if (gamepad1.b) {
            powers[1] = power;
        } else if (gamepad1.x) {
            powers[2] = power;
        } else if (gamepad1.y) {
            powers[3] = power;
        }

        for (int index = 0; index < motors.length; index++) {
            motors[index].setPower(powers[index]);
        }
    }
}
