package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class WaffleFoot implements ITelemetry {
    private final static float UP = 0.0f;
    private final static float DOWN = .7f;

    private Servo waffleFoot;

    WaffleFoot (HardwareMap hardwareMap){
        this.waffleFoot = hardwareMap.servo.get("waffleFoot");
    }
    void setWafflePosition(double wafflePosition){this.waffleFoot.setPosition(wafflePosition);}
    void up (){this.waffleFoot.setPosition(UP);}
    void down (){this.waffleFoot.setPosition(DOWN);}
    public String getTelemetry() {


        double waffleposition = this.waffleFoot.getPosition();
        return String.format("waffleposition: %s", waffleposition);



    }
}
