package org.firstinspires.ftc.teamcode.autonomousopmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.AutonomousMethods;
import org.firstinspires.ftc.teamcode.MathsMethods;
import org.firstinspires.ftc.teamcode.hardware.AllMotorsAndSensorsTeamHardwareMap;

@Autonomous(name = "Blue Shipping Hub", group = "tests")
public class BlueShippingHubAutonomousOpMode extends LinearOpMode {

    private AllMotorsAndSensorsTeamHardwareMap teamHardwareMap;

    @Override
    public void runOpMode() throws InterruptedException {

        teamHardwareMap = new AllMotorsAndSensorsTeamHardwareMap(hardwareMap);

        ElapsedTime timer = new ElapsedTime();
        waitForStart();

        timer.reset();
        boolean stageArmFin = false;
        boolean stage1Fin = false;
        boolean stage2Fin = false;
        boolean stage3Fin = false;
        while (opModeIsActive()) {
            if (!stageArmFin) {
                if (timer.milliseconds() < 500) { // raise arm for start of autonomous to get over pipes
                    teamHardwareMap.hexMotor1.setPower(0.5);
                } else {
                    teamHardwareMap.hexMotor1.setPower(0.05);
                    stageArmFin = true;
                }
            }

            telemetry.addData("Encoder (left)", teamHardwareMap.leftMotor.getCurrentPosition());
            telemetry.addData("Encoder (right)", teamHardwareMap.rightMotor.getCurrentPosition());
            telemetry.update();

            teamHardwareMap.leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            teamHardwareMap.rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            if (!stage1Fin) {
                stage1Fin = AutonomousMethods.MoveForwardInches(teamHardwareMap, 24, 0.5);
                if (stage1Fin) {
                    teamHardwareMap.leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    teamHardwareMap.rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                }
            }

            teamHardwareMap.leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            teamHardwareMap.rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            if (!stage2Fin) {
                stage2Fin = AutonomousMethods.TurnDegrees(teamHardwareMap, 90, 0.5, false);
                if (stage2Fin) {
                    teamHardwareMap.leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    teamHardwareMap.rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                }
            }

            teamHardwareMap.leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            teamHardwareMap.rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            if (!stage3Fin) {
                stage3Fin = AutonomousMethods.MoveForwardInches(teamHardwareMap, 27, 0.5);
                if (stage3Fin) {
                    teamHardwareMap.leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    teamHardwareMap.rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                }
            }
        }
    }
}