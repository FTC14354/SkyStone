package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Objects;

public class Hippo {
    private final Telemetry telemetery;
    private DigitalChannel backsensor;
    private DigitalChannel frontsensor;
    private DcMotor hippo;
    private double HandsToTheSky = 0;
    private double Reach = .7;

    public Hippo(HardwareMap hardwareMap, Telemetry telemetry) {
        hippo = hardwareMap.dcMotor.get("hungryhippo");
        backsensor = hardwareMap.digitalChannel.get("backsensor");
        frontsensor = hardwareMap.digitalChannel.get("frontsensor");
        this.telemetery = telemetry;
    }


    public void Extend() {
        if (frontsensor.getState()) {
            telemetery.addLine("Hippo fully extended!");
            EndGame();
        } else {
            hippo.setPower(Reach);
        }
    }

    public void retract() {
        if (backsensor.getState()) {
            telemetery.addLine("Hippo fully retracted!");
            EndGame();
        } else {
            hippo.setPower(-Reach);
        }
    }

    public void EndGame() {
        hippo.setPower(HandsToTheSky);
    }
}
