package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Objects;

public class Hippo {
    private DigitalChannel backsensor;
    private DigitalChannel frontsensor;
    private DcMotor hippo;
    private double HandsToTheSky = 0;
    private double Reach = .7;

    public Hippo(HardwareMap hardwareMap) {
        hippo = hardwareMap.dcMotor.get("hungryhippo");
        backsensor = hardwareMap.digitalChannel.get("backsensor");
        frontsensor = hardwareMap.digitalChannel.get("frontsensor");
    }


    public void Extend() {
        if (frontsensor.getState()) {
            EndGame();
        } else {
            hippo.setPower(Reach);
        }
    }

    public void retract() {
        if (backsensor.getState()) {
            EndGame();
        } else {
            hippo.setPower(-Reach);
        }
    }

    public void EndGame() {
        hippo.setPower(HandsToTheSky);
    }
}
