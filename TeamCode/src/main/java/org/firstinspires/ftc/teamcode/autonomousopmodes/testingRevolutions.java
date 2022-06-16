package org.firstinspires.ftc.teamcode.autonomousopmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.AllMotorsAndSensorsTeamHardwareMap;

@Autonomous(name = "testing Revoultions", group = "tests")
@Disabled
public class testingRevolutions extends LinearOpMode {

    private AllMotorsAndSensorsTeamHardwareMap teamHardwareMap;

    @Override
    public void runOpMode() throws InterruptedException {

        teamHardwareMap = new AllMotorsAndSensorsTeamHardwareMap(hardwareMap);

        ElapsedTime timer = new ElapsedTime();
        waitForStart();
        timer.reset();
        while (opModeIsActive()) {
            teamHardwareMap.leftMotor.setPower(0.6);
            teamHardwareMap.rightMotor.setPower(-0.6);
            Thread.sleep(2510);
            teamHardwareMap.leftMotor.setPower(0);
            teamHardwareMap.rightMotor.setPower(0);
            Thread.sleep(10000);
        }


    }

}

