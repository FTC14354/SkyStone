package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Intake extends OpMode {
    private DcMotor thunder;
    private DcMotor lightening;
    double cronchin = .6;
    public void init (){

        thunder = hardwareMap.dcMotor.get("thunder");
        lightening = hardwareMap.dcMotor.get("lightening");
    }
    public void loop (){
        if (gamepad2.a){
            cronch();
        } else if (gamepad2.b){
            purge ();
        } else {
           abandonShip();
       }
    }
    private void cronch(){
        thunder.setPower(cronchin);
        lightening.setPower(-cronchin);
    }
    private void purge (){
        thunder.setPower(-cronchin);
        lightening.setPower(cronchin);
    }
    private void abandonShip(){
        thunder.setPower(0);
        lightening.setPower(0);
    }
}
