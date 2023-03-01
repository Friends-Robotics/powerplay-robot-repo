package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class LinearSlideWithGrabberHardwareMap extends TeamHardwareMap {

    public LinearSlideWithGrabberHardwareMap(HardwareMap hardwareMap) {
        super(hardwareMap);
    }

    public DcMotor motor;
    public Servo servo0;
    public Servo servo1;

    @Override
    protected void initialise() {
        //config for motor
        motor = hardwareMap.get(DcMotor.class, "Motor0");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        servo0 = hardwareMap.get(Servo.class, "servo0");
        servo1 = hardwareMap.get(Servo.class, "servo1");
    }
}
