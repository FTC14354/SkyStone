package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;

public class Constants {
    @Config
    public class RobotConstants {
        private static final double COUNTS_PER_MOTOR_REV = 350;    // eg: TETRIX Motor Encoder
        private static final double DRIVE_GEAR_REDUCTION = 2.0;     // This is < 1.0 if geared UP
        private static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
        private static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
                (WHEEL_DIAMETER_INCHES * 3.1415);
        static final double DRIVE_SPEED = 0.3;
        //    private static final double TURN_SPEED = 0.3;
        private static final double TIMEOUT_SECONDS = 5.0;
        private static final double MOVE_FAST = 0.8;
        private static final double MOVE_NORMAL = 0.6;
        private static final double MOVE_SLOW = 0.3;

        private static final double LIFT_POWER_NORMAL = 0.7;
        private static final double LIFT_POWER_SLOW = 0.2;

    }
}
