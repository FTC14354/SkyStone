
        package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DigitalChannel;

@TeleOp
public class HallEffectSensor extends OpMode {
    private DigitalChannel backSensor;
    private DigitalChannel frontSensor;
    private DcMotor hippo;
    private double Reach = .7;
    @Override
    public void init() {
        hippo = hardwareMap.dcMotor.get("hungryhippo");
        backSensor = hardwareMap.digitalChannel.get("backsensor");
        frontSensor = hardwareMap.digitalChannel.get("frontsensor");
    }

    @Override
    public void loop() {
        boolean backSensorValue = backSensor.getState();
        boolean frontSensorValue = frontSensor.getState();

        telemetry.addData("backSensor", backSensorValue);
        telemetry.addData("frontSensor", frontSensorValue);
        telemetry.update();

        if (gamepad2.right_stick_y > .1 && frontSensorValue) {
            extend();
        } else if (gamepad2.right_stick_y < -.1 && backSensorValue) {
            retract();
        } else {
            stopMoving();
        }
    }
    private void extend (){
        hippo.setPower(Reach);
    }
    private void retract (){
        hippo.setPower(-Reach);
    }
    private void stopMoving (){
        hippo.setPower(0);
    }
}

