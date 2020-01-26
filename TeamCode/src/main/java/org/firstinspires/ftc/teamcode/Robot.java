package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/*
    Extend the base robot (motion) with a Gripper, Lift, and Hippo
    systems that are only found on the competition robot.
 */
public class Robot extends BaseRobot implements IRobot {
    private Gripper gripper;
    private Hippo hippo;
    private Lift lift;
    public WaffleFoot waffleFoot;

    Robot(HardwareMap hardwareMap) {
        super(hardwareMap, "CompGryo");

        Hippo hippo = new Hippo(hardwareMap);
        this.hippo = hippo;
        telemetryMap.put("hippo", hippo);
        Lift lift = new Lift(hardwareMap);
        this.lift = lift;
        telemetryMap.put("lift", lift);
        Gripper gripper = new Gripper(hardwareMap);
        this.gripper = gripper;
        telemetryMap.put("gripper", gripper);
        WaffleFoot waffleFoot = new WaffleFoot(hardwareMap);
        this.waffleFoot = waffleFoot;
                telemetryMap.put("waffleFoot", waffleFoot);
    }

    public void raiseLift(double speed) {
        lift.raise(speed);
    }

    public void lowerLift(double speed) {
        lift.lower(speed);
    }

    public void stopLift() {
        lift.stop();
    }

    public void extendHippo() {
        hippo.extend();
    }

    public void retractHippo() {
        hippo.retract();
    }

    public void stopHippo() {
        hippo.stop();
    }

    public void setGripperPosition(double position) {
        gripper.setPosition(position);
    }

    public void openGripper() {
        gripper.open();
    }

    public void closeGripper() {
        gripper.close();
    }


    public void setWafflePosition(double wafflePosition) {
        waffleFoot.setWafflePosition(wafflePosition);
    }
    public void waffleUp (){waffleFoot.up();}
    public void waffleDown (){waffleFoot.down();}
}
