package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Hippo {
    private DcMotor hippo;
    private DigitalChannel backSensor;
    private DigitalChannel frontSensor;
    private static final double STOP = 0.0;
    private static final double SPEED = 0.6;

    public Hippo(HardwareMap hardwareMap) {
        hippo = hardwareMap.dcMotor.get("hungryhippo");
        backSensor = hardwareMap.digitalChannel.get("backsensor");
        frontSensor = hardwareMap.digitalChannel.get("frontsensor");
    }

    void extend () {
        if (frontSensor.getState()) {
            hippo.setPower(SPEED);
        }
    }

    void retract() {
        if (backSensor.getState()) {
            hippo.setPower(SPEED);
        }
    }

    void stop() {
        hippo.setPower(STOP);
    }

    String getTelemetry() {
        return null;
    }
}
