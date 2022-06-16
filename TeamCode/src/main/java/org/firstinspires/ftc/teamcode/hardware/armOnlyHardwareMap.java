package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class armOnlyHardwareMap extends TeamHardwareMap {

    public armOnlyHardwareMap(HardwareMap hardwareMap) {
        super(hardwareMap);
    }

    public DcMotor hexMotor1;
    public DcMotor hexMotor2;

    @Override
    protected void initialise() {
        //config for motor
        hexMotor1 = hardwareMap.get(DcMotor.class, "Core_Hex_Motor_1");
        hexMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        hexMotor2 = hardwareMap.get(DcMotor.class, "Core_Hex_Motor_2");
        hexMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
