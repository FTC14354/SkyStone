package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

class TestRobot extends BaseRobot implements IRobot {
    private WaffleFoot waffleFoot;
    public TestRobot(HardwareMap hardwareMap) {
        super(hardwareMap, "BNO055IMUCalibration.json");

        WaffleFoot waffleFoot = new WaffleFoot(hardwareMap);
        this.waffleFoot = waffleFoot;
        telemetryMap.put("waffleFoot", waffleFoot);
    }

    @Override
    public void raiseLift(double speed) {

    }

    @Override
    public void lowerLift(double speed) {

    }

    @Override
    public void stopLift() {

    }

    @Override
    public void extendHippo() {

    }

    @Override
    public void retractHippo() {

    }

    @Override
    public void stopHippo() {

    }

    @Override
    public void setGripperPosition(double position) {

    }

    @Override
    public void openGripper() {

    }

    @Override
    public void closeGripper() {

    }
    @Override
    public void setWafflePosition (double wafflePosition){

    }
    @Override
    public void waffleUp (){
    }
    @Override
    public void waffleDown (){

    }

}
