package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import java.util.Objects;

@TeleOp
public class HallEffectSensor extends OpMode {
    private DigitalChannel backsensor;
    private DigitalChannel frontsensor;
    private DcMotor hippo;
    private double HandsToTheSky = 0;
    private double Reach = .7;
    @Override
    public void init() {
        hippo = hardwareMap.dcMotor.get("hungryhippo");
        backsensor = hardwareMap.digitalChannel.get("backsensor");
        frontsensor = hardwareMap.digitalChannel.get("frontsensor");
    }

    @Override
    public void loop() {
        boolean CanIRun = backsensor.getState() && frontsensor.getState();

        String switchState;
        if (CanIRun){
            switchState = "Run";
        }else {
            switchState = "Stop";
        }


        if (Objects.equals(switchState, "Stop")){
            EndGame();

        }else {

            if (gamepad2.right_stick_y > .1) {
                Extend();
            } else if (gamepad2.right_stick_y < -.1) {
                condese();
            } else {
                EndGame();
            }
        }


        telemetry.addData("state", ": " + switchState);
        telemetry.update();
    }
    private void Extend (){
        hippo.setPower(Reach);
    }
    private void condese (){
        hippo.setPower(-Reach);
    }
    private void EndGame (){
        hippo.setPower(HandsToTheSky);
    }
}
