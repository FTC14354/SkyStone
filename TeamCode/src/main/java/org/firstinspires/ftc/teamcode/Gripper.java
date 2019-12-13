package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

class Gripper {
    private final static float OPEN = 0.0f;
    private final static float CLOSED = 0.3f;

    private Servo gripper;

    Gripper(HardwareMap hardwareMap) {
       this.gripper = hardwareMap.servo.get("grippy");
    }

    void setPosition(double position) {
        this.gripper.setPosition(position);
    }

    void open() {
        this.gripper.setPosition(OPEN);
    }

    void close() {
        this.gripper.setPosition(CLOSED);
    }

    String getTelemetry() {
        double position = this.gripper.getPosition();
        return Double.toString(position);
    }
}
