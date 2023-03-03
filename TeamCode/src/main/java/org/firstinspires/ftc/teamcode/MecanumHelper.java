package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class MecanumHelper {
    public DcMotor frontRightMotor;
    public DcMotor backRightMotor;
    public DcMotor backLeftMotor;
    public DcMotor frontLeftMotor;

    public double speed;

    public MecanumHelper(DcMotor frontRightMotor, DcMotor backRightMotor, DcMotor backLeftMotor, DcMotor frontLeftMotor) {
        this.frontRightMotor = frontRightMotor;
        this.backRightMotor = backRightMotor;
        this.backLeftMotor = backLeftMotor;
        this.frontLeftMotor = frontLeftMotor;
        this.speed = 1;
    }

    public void forward(double power) {
        frontLeftMotor.setPower(power * speed);
        frontRightMotor.setPower(-power * speed);
        backLeftMotor.setPower(-power * speed);
        backRightMotor.setPower(power * speed);
    }

    public void right(double power) {
        frontLeftMotor.setPower(power * speed);
        frontRightMotor.setPower(power * speed);
        backLeftMotor.setPower(power * speed);
        backRightMotor.setPower(power * speed);
    }

    public void go(double x, double y) {
        if (Math.abs(x) > Math.abs(y)) {
            right(x);
        }
        else if (Math.abs(y) > Math.abs(x)) {
            forward(y);
        }
        else {
            forward(0);
        }
    }
}
