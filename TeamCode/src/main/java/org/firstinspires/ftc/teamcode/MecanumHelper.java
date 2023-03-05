package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class MecanumHelper {
    private final DcMotor frontRightMotor;
    private final DcMotor backRightMotor;
    private final DcMotor backLeftMotor;
    private final DcMotor frontLeftMotor;

    private enum Motor {
        FRW, // front right wheel
        BRW, // back right wheel
        BLW, // back left wheel
        FLW // front left wheel
    }

    public double speed;

    public MecanumHelper(DcMotor frontRightMotor, DcMotor backRightMotor, DcMotor backLeftMotor, DcMotor frontLeftMotor) {
        this.frontRightMotor = frontRightMotor;
        this.backRightMotor = backRightMotor;
        this.backLeftMotor = backLeftMotor;
        this.frontLeftMotor = frontLeftMotor;
        this.speed = 1;
    }

    public void SetMotorPower(Motor motor, double power) {
        switch (motor) {
            case FRW:
                frontRightMotor.setPower(-power * speed);
                break;
            case BRW:
                backRightMotor.setPower(power * speed);
                break;
            case BLW:
                backLeftMotor.setPower(-power * speed);
                break;
            case FLW:
                frontLeftMotor.setPower(power * speed);
                break;
        }
    }

    public void move(double x, double y) {
        if (x < 0 || y < 0) return;
        // currently only works for positive x and positive y values

        double angleThetaDegrees = Math.toDegrees(Math.atan(y / x));
        double hypotenuseLength = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        double magnitude = hypotenuseLength / Math.sqrt(2);
        double power = 0;
        if (angleThetaDegrees < 45) {
            double rangeAngle = 45 - angleThetaDegrees;
            power = -rangeAngle / 45;
        }
        if (angleThetaDegrees > 45) {
            double rangeAngle = angleThetaDegrees - 45;
            power = rangeAngle / 45;
        }
        SetMotorPower(Motor.FRW, power * magnitude);
        SetMotorPower(Motor.BLW, power * magnitude);
        SetMotorPower(Motor.FLW, magnitude);
        SetMotorPower(Motor.BRW, magnitude);
    }

    public void rotate(double y) {
        SetMotorPower(Motor.FLW, y);
        SetMotorPower(Motor.BLW, y);
        SetMotorPower(Motor.FRW, -y);
        SetMotorPower(Motor.BRW, -y);
    }
}
