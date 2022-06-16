package org.firstinspires.ftc.teamcode.driveropmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.armOnlyHardwareMap;


@TeleOp(name="Motor Encoder Testing", group="Linear Opmode")
@Disabled
public class MotorEncodingTestingOpMode extends LinearOpMode {


    private armOnlyHardwareMap teamHardwareMap;

    double previousValue = 0;

    @Override
    public void runOpMode() {
        teamHardwareMap = new armOnlyHardwareMap(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        teamHardwareMap.runTime.reset();

        teamHardwareMap.hexMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Encoder start Value" , teamHardwareMap.hexMotor1.getCurrentPosition());
        telemetry.update();
        while(opModeIsActive())
        {
            // left joystick y axis
            double gamepadinputLeft_Y = gamepad1.left_stick_y;
            double gamepadinputRight_Y = gamepad1.right_stick_y;

            if (gamepadinputLeft_Y > 0)
            {
                try {
                    teamHardwareMap.hexMotor1.setPower((gamepadinputLeft_Y) + 0.1);
                }
                catch(Exception ex)
                {
                    teamHardwareMap.hexMotor1.setPower(gamepadinputLeft_Y);
                }
            }
            if (gamepadinputLeft_Y < 0)
            {
                try {
                    teamHardwareMap.hexMotor1.setPower((gamepadinputLeft_Y/4) + 0.1);
                }
                catch(Exception ex)
                {
                    teamHardwareMap.hexMotor1.setPower(gamepadinputLeft_Y/4);
                }
            }
            else
            {
                teamHardwareMap.hexMotor1.setPower(0.08);
            }


            teamHardwareMap.hexMotor2.setPower(gamepadinputRight_Y);


            telemetry.addData("Left Y value", gamepadinputLeft_Y);
            telemetry.addData("Right Y value", gamepadinputRight_Y);
            telemetry.addData("Encoder  Value" , teamHardwareMap.hexMotor1.getCurrentPosition());
            telemetry.update();

        }





    }
}

