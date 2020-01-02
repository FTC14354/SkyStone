package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

class Lift implements ITelemetry {
    private DcMotor lift;
    private static final double STOP = 0.0;

    Lift (HardwareMap hardwareMap) {
        lift = hardwareMap.dcMotor.get("liftyboi");
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    void raise(double speed) {
        lift.setPower(speed);
    }

    void lower(double speed) {
        lift.setPower(-speed);
    }

    void stop() {
        lift.setPower(STOP);
    }

    public String getTelemetry() {
        return null;
    }
}
