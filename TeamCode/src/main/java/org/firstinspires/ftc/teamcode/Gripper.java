package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Gripper {
    private final Telemetry telemetry;
    private final Servo grippy;
    private double open = 0;
    private double WoAhwereHalfwayThere = .5;
    private double grabbed = .3;
    private double almostthere = .7;
    private double ittybittybit = .3;
    private double position = 0;

    public Gripper(HardwareMap hardwareMap, Telemetry telemetry) {
        grippy = hardwareMap.servo.get("grippy");
        this.telemetry = telemetry;
    }

    public void grab() {
        grippy.setPosition(ittybittybit);
    }

    public void release() {
        grippy.setPosition(WoAhwereHalfwayThere);
    }

    public void test() {
        position += .1;
        if(position > 1)
            position = 0;
        grippy.setPosition(position);
        telemetry.addData("Servo Position", grippy.getPosition());
        telemetry.update();
    }

    public double getPosition(){
        return grippy.getPosition();
    }
}
