package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.Camera;

public class CameraTestHardwareMap extends TeamHardwareMap {

    public CameraTestHardwareMap(HardwareMap hardwareMap) {
        super(hardwareMap);
    }

    public Camera camera;

    @Override
    protected void initialise() {
        //config for motor
        camera = hardwareMap.get(Camera.class, "Camera1");
    }
}
