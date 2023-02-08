package org.firstinspires.ftc.teamcode.driveropmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.hardware.ServoMotorAdapterHardwareMap;
import org.firstinspires.ftc.teamcode.hardware.ServoTest080223HardwareMap;


@TeleOp(name="Servo Test 08-02-23", group="Linear Opmode")
public class ServoTest080223OpMode extends LinearOpMode {

    private ServoTest080223HardwareMap teamHardwareMap;


    @Override
    public void runOpMode() {
        teamHardwareMap = new ServoTest080223HardwareMap(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        teamHardwareMap.runTime.reset();


        while(opModeIsActive())
        {
            teamHardwareMap.servo0.setPosition(gamepad1.left_stick_x);
            teamHardwareMap.servo1.setPosition(-gamepad1.left_stick_x);
        }
    }
}
