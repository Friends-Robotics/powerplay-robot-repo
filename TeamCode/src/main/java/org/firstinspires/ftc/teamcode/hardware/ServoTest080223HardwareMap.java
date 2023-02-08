package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoTest080223HardwareMap extends TeamHardwareMap {

    public ServoTest080223HardwareMap(HardwareMap hardwareMap) {
        super(hardwareMap);
    }

    public Servo servo0;
    public Servo servo1;

    @Override
    protected void initialise() {
        servo0 = hardwareMap.get(Servo.class, "servo0");
        servo1 = hardwareMap.get(Servo.class, "servo1");
    }
}
