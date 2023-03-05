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

        SetMotorPower(Motor.FLW, 1);
        SetMotorPower(Motor.BRW, 1);
        double angleThetaDegrees = Math.toDegrees(Math.atan(y / x));
        if (angleThetaDegrees < 45) {
            double rangeAngle = 45 - angleThetaDegrees;
            double power = -rangeAngle / 45;
            SetMotorPower(Motor.FRW, power);
            SetMotorPower(Motor.BLW, power);
        }
        if (angleThetaDegrees > 45) {
            double rangeAngle = angleThetaDegrees - 45;
            double power = rangeAngle / 45;
            SetMotorPower(Motor.FRW, power);
            SetMotorPower(Motor.BLW, power);
        }
    }

    public void rotate(double y) {
        SetMotorPower(Motor.FLW, y);
        SetMotorPower(Motor.BLW, y);
        SetMotorPower(Motor.FRW, -y);
        SetMotorPower(Motor.BRW, -y);
    }
}
