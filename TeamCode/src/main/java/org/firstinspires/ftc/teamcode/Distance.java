
package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.hardware.DistanceSensor;
        import com.qualcomm.robotcore.hardware.HardwareMap;

        import org.firstinspires.ftc.robotcore.external.Telemetry;
        import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
public class Distance {

    private DistanceSensor sensorRange;

    public Distance(HardwareMap hardwareMap) {
        sensorRange = hardwareMap.get(DistanceSensor.class, "sensor_range");
    }

    public boolean testRange(int distance, Telemetry telemetry) {
        telemetry.addData("deviceName",sensorRange.getDeviceName() );
        telemetry.addData("range", String.format("%.01f mm", sensorRange.getDistance(DistanceUnit.MM)));
        telemetry.addData("range", String.format("%.01f cm", sensorRange.getDistance(DistanceUnit.CM)));
        telemetry.addData("range", String.format("%.01f m", sensorRange.getDistance(DistanceUnit.METER)));
        telemetry.addData("range", String.format("%.01f in", sensorRange.getDistance(DistanceUnit.INCH)));
        telemetry.update();

        return distance < sensorRange.getDistance(DistanceUnit.MM);

    }

}