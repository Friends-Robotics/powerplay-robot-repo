package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ServoMotorAdapterHardwareMap extends TeamHardwareMap {

    public ServoMotorAdapterHardwareMap(HardwareMap hardwareMap) {
        super(hardwareMap);
    }

    public CRServo servo1;

    @Override
    protected void initialise() {
        servo1 = hardwareMap.get(CRServo.class, "servo1");
    }
}
