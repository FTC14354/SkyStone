package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;

@Config
public class RobotConstants {
    public static double COUNTS_PER_MOTOR_REV = 350;    // eg: TETRIX Motor Encoder
    public static double DRIVE_GEAR_REDUCTION = 2.0;     // This is < 1.0 if geared UP
    public static double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    public static double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    public static double DRIVE_SPEED = 0.3;
    public static double LIFT_SPEED = 0.3;
    public static double TIMEOUT_SECONDS = 5.0;
}
