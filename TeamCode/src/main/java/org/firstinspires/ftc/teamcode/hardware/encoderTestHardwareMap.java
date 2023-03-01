package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchSimple;

public class encoderTestHardwareMap extends TeamHardwareMap {

    public encoderTestHardwareMap(HardwareMap hardwareMap) {
        super(hardwareMap);
    }

    public DcMotor motor;
    public DigitalChannel Encoder1;
    public DigitalChannel Encoder2;

    @Override
    protected void initialise() {
        //config for motor
        motor = hardwareMap.get(DcMotor.class, "Motor0");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Encoder1 = hardwareMap.get(DigitalChannel.class, "Encoder1" );
        Encoder2 = hardwareMap.get(DigitalChannel.class, "Encoder2" );
    }
}
