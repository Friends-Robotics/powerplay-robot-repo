package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

public class MecanumMotorsLinearSlideGrabberHardwareMap extends TeamHardwareMap {
    public MecanumMotorsLinearSlideGrabberHardwareMap(HardwareMap hardwareMap) {
        super(hardwareMap);
    }

    public DcMotor frontRightMotor;
    public DcMotor backRightMotor;
    public DcMotor backLeftMotor;
    public DcMotor frontLeftMotor;

    public CRServo linearSlide;
    public Servo grabberRight;
    public Servo grabberLeft;

    @Override
    protected void initialise() {
        frontRightMotor = hardwareMap.get(DcMotor.class, "FRW");
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightMotor = hardwareMap.get(DcMotor.class, "BRW");
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor = hardwareMap.get(DcMotor.class, "BLW");
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeftMotor = hardwareMap.get(DcMotor.class, "FLW");
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        linearSlide = hardwareMap.get(CRServo.class, "LSD");
        grabberRight = hardwareMap.get(Servo.class, "RG");
        grabberLeft = hardwareMap.get(Servo.class, "LG");
    }
}
