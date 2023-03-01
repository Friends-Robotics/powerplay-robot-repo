package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LinearSlideHardwareMap extends TeamHardwareMap {

    public LinearSlideHardwareMap(HardwareMap hardwareMap) {
        super(hardwareMap);
    }

    public DcMotor motor;

    @Override
    protected void initialise() {
        //config for motor
        motor = hardwareMap.get(DcMotor.class, "Motor0");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
