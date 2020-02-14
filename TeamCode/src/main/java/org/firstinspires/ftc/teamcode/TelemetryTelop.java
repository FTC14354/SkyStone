package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Map;

@TeleOp
public class TelemetryTelop extends OpMode {
    private RobotControls robotControls;
    private IRobot robot;

    @Override
    public void init() {
        // TODO Use the config name instead of relying on the exception
        try {
            robot = new Robot(hardwareMap);
            telemetry.addLine("Robot:").addData("Robot", "Real Robot");
        }
        catch(Exception ex) {
            robot = new TestRobot(hardwareMap);
            telemetry.addLine("Robot:").addData("Robot", "Test Robot");
        }
        this.robotControls = new RobotControls(robot);
        ((BaseRobot)robot).frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        ((BaseRobot)robot).frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        Map<String, ITelemetry> telemetryMap = robot.getTelemetryMap();
        for (String t : telemetryMap.keySet()) {
            ITelemetry telemetryComponent = telemetryMap.get(t);
            if (telemetryComponent != null) {
                telemetry.addData(t, telemetryComponent.getTelemetry());
            }
        }
    }

    @Override
    public void loop() {
        robotControls.movementControls(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x,
                gamepad1.left_trigger > 0.1, gamepad1.right_trigger < 0.1);
        robotControls.hippoControls(gamepad2.right_stick_y);
        robotControls.liftControls(gamepad2.left_stick_y, gamepad2.left_bumper);
        robotControls.gripperControls(gamepad2.right_trigger);
        robotControls.waffleControls (gamepad2.a);

        Map<String, ITelemetry> telemetryMap = robot.getTelemetryMap();
        for (String t : telemetryMap.keySet()) {
            ITelemetry telemetryComponent = telemetryMap.get(t);
            if (telemetryComponent != null) {
                telemetry.addData(t, telemetryComponent.getTelemetry());
                telemetry.addData("Encodervalues", "Encoder Fl : %s",

                        ((BaseRobot) robot).frontLeft.getCurrentPosition());
//                telemetry.addData("Encodervalues", "Encoder FR : %s",
//                ((BaseRobot) robot).frontRight.getCurrentPosition());
//                telemetry.addData("Encodervalues", "Encoder Bl : %s",
//                        ((BaseRobot) robot).backLeft.getCurrentPosition());
//                telemetry.addData("Encodervalues", "Encoder BR : %s",
//                        ((BaseRobot) robot).backRight.getCurrentPosition());
                telemetry.addData("Waffle foot: %s", ((Robot)robot).waffleFoot.getTelemetry());

                telemetry.update();
            }
        }
    }
}

