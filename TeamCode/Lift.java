package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import java.util.Objects;

@TeleOp
public class Lift extends OpMode {
    private DcMotor liftyboi;
    private DcMotor hippo;
    private DigitalChannel backsensor;
    private DigitalChannel frontsensor;
   private double LiftPowerStandard = .5;
    private double MAXIMUMOVERDRIVE = 1;
   private  double LiftPowerSlow = .2;
    private double HandsToTheSky = 0;
    private double Reach = .7;
    public void init (){
    liftyboi = hardwareMap.dcMotor.get("liftyboi");
    hippo = hardwareMap.dcMotor.get("hungryhippo");
        backsensor = hardwareMap.digitalChannel.get("backsensor");
        frontsensor = hardwareMap.digitalChannel.get("frontsensor");
    }
    public void loop () {

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

        if (gamepad2.left_bumper = true) {
            LiftPowerStandard = LiftPowerSlow;
        } else {
            LiftPowerStandard = .5;
        }

        if (gamepad2.left_stick_y > .1) {
            GoUp();
        }else if (gamepad2.left_stick_y < -.1) {
        GoDown();
        }else {
            ItIsHighNoon ();
        }

    }
    private void GoUp(){
        liftyboi.setPower(-MAXIMUMOVERDRIVE);
    }
    private void GoDown(){
        liftyboi.setPower(MAXIMUMOVERDRIVE);
    }
    private void ItIsHighNoon (){
        liftyboi.setPower(HandsToTheSky);
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
