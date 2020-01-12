package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

class Hippo implements ITelemetry {
    private DcMotor hippo;
    private DigitalChannel backSensor;
    private DigitalChannel frontSensor;
    private static final double STOP = 0.0;
    private static final double SPEED = 0.6;

    Hippo(HardwareMap hardwareMap) {
        hippo = hardwareMap.dcMotor.get("hungryhippo");
        backSensor = hardwareMap.digitalChannel.get("backsensor");
        frontSensor = hardwareMap.digitalChannel.get("frontsensor");

        hippo.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    void extend () {
        if (frontSensor.getState()) {
            hippo.setPower(SPEED);
        }
    }

    void retract() {
        if (backSensor.getState()) {
            hippo.setPower(-SPEED);
        }
    }

    void stop() {
        hippo.setPower(STOP);
    }

    public String getTelemetry() {
        return String.format("BackSensor: %s, FrontSensor: %s",backSensor.getState(),frontSensor.getState());
    }
}
