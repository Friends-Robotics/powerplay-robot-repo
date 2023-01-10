package org.firstinspires.ftc.teamcode.driveropmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.ServoMotorAdapterHardwareMap;


@TeleOp(name="Servo Motor Adapter", group="Linear Opmode")
public class ServoMotorAdapterOpMode extends LinearOpMode {

    private ServoMotorAdapterHardwareMap teamHardwareMap;


    @Override
    public void runOpMode() {
        teamHardwareMap = new ServoMotorAdapterHardwareMap(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        teamHardwareMap.runTime.reset();


        while(opModeIsActive())
        {
            if (gamepad1.cross) {
                teamHardwareMap.servo1.setPower(0.5);
            }
            if (gamepad1.circle) {
                teamHardwareMap.servo1.setPower(1);
            }
        }
    }
}
