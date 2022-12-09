package org.firstinspires.ftc.teamcode.hardware;

import android.graphics.Camera;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

public class AllMotorsAndSensorsTeamHardwareMap extends TeamHardwareMap {

    public AllMotorsAndSensorsTeamHardwareMap(HardwareMap hardwareMap) {
        super(hardwareMap);
    }

    public DcMotor hexMotor1;
    public DcMotor hexMotor2;

    public DcMotor rightMotor;
    public DcMotor leftMotor;

    public CRServo continuousServo1;
    public CRServo continuousServo2;

    public Servo servo1;
    public Servo servo2;

    public DigitalChannel touchSensor;

    public NormalizedColorSensor colourSensor;

    @Override
    protected void initialise() {
        rightMotor = hardwareMap.get(DcMotor.class, "HD_Hex_Motor_1");
        leftMotor = hardwareMap.get(DcMotor.class, "HD_Hex_Motor_2");

        // Setup motor direction as positive power = forwards
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setDirection(DcMotor.Direction.FORWARD);

        // Set zero power behavior: means when there is no power going to motor it breaks instead of free wheeling
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        hexMotor1 = hardwareMap.get(DcMotor.class, "Core_Hex_Motor_1");
        hexMotor2 = hardwareMap.get(DcMotor.class, "Core_Hex_Motor_2");

        // Setup motor direction as positive power = forwards/(in/out)
        hexMotor1.setDirection(DcMotor.Direction.FORWARD);
        hexMotor2.setDirection(DcMotor.Direction.FORWARD);

        // Set zero power behavior: means when there is no power going to motor it breaks instead of free wheeling
        hexMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hexMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Set default power to motors as zero
        rightMotor.setPower(0);
        leftMotor.setPower(0);

        // Set motor mode: reset the motor ticks to 0
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set motors to not use encoder
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);



        continuousServo1 = hardwareMap.get(CRServo.class, "Continuous_Rotation_Servo_1");

        //continuousServo2 = hardwareMap.get(CRServo.class, "Continuous_Rotation_Servo_2");

        //servo1 = hardwareMap.get(Servo.class, "Servo_1");
        //servo2 = hardwareMap.get(Servo.class, "Servo_2");

        //touchSensor = hardwareMap.get(DigitalChannel.class, "Touch_Sensor_1");
        //colourSensor = hardwareMap.get(NormalizedColorSensor.class, "Colour_Sensor_1");
    }
}
