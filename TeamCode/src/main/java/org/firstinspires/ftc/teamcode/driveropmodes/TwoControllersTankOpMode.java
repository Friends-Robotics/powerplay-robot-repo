package org.firstinspires.ftc.teamcode.driveropmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.MathsMethods;
import org.firstinspires.ftc.teamcode.hardware.AllMotorsAndSensorsTeamHardwareMap;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Two controllers (tank)", group="Linear Opmode")
public class TwoControllersTankOpMode extends LinearOpMode {

    private AllMotorsAndSensorsTeamHardwareMap teamHardwareMap;

    @Override
    public void runOpMode() {
        teamHardwareMap = new AllMotorsAndSensorsTeamHardwareMap(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        teamHardwareMap.runTime.reset();

        while (opModeIsActive()) {
            // get gamepad1 inputs
            double gamepad1LeftStickY = -gamepad1.left_stick_y * 0.8; // -0.8 = full down and +0.8 = full up
            double gamepad1RightStickY = -gamepad1.right_stick_y * 0.8; // -0.8 = full down and +0.8 = full up

            // get old motor powers
            double oldLeftMotorPower = teamHardwareMap.leftMotor.getPower();
            double oldRightMotorPower = teamHardwareMap.rightMotor.getPower();

            // calculate gradual motor powers, possibly overridden below
            double newLeftMotorPower = MathsMethods.CalculateNewGradualMotorPower(oldLeftMotorPower, gamepad1LeftStickY);
            double newRightMotorPower = MathsMethods.CalculateNewGradualMotorPower(oldRightMotorPower, gamepad1RightStickY);

            if (gamepad1.right_trigger > 0) { // full forwards
                newRightMotorPower = gamepad1.right_trigger;
                newLeftMotorPower = gamepad1.right_trigger;
            }
            if (gamepad1.left_trigger > 0) { // full backwards
                newRightMotorPower = -gamepad1.left_trigger;
                newLeftMotorPower = -gamepad1.left_trigger;
            }

            if (gamepad1.circle) { // emergency stop
                newLeftMotorPower = 0;
                newRightMotorPower = 0;
            }

            // Send calculated power to wheels
            teamHardwareMap.leftMotor.setPower(newLeftMotorPower);
            teamHardwareMap.rightMotor.setPower(newRightMotorPower);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + teamHardwareMap.runTime.toString());
            //telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.addData("Input", "LJ: (%.2f); RJ: (%.2f)", gamepad1LeftStickY, gamepad1RightStickY);
            telemetry.addData("Input", "LT: (%.2f); RT: (%.2f)", gamepad1.left_trigger, gamepad1.right_trigger);
            telemetry.addData("Motors", "Left: (%.2f); Right: (%.2f)", newLeftMotorPower, newRightMotorPower);
            telemetry.update();

            // get gamepad2 inputs
            double gamepad2LeftStickY = -gamepad2.left_stick_y; // -1 = full down and +1 = full up
            double gamepad2RightStickY = -gamepad2.right_stick_y; // -1 = full down and +1 = full up

            if (gamepad2LeftStickY < 0) // drop arm
            {
                teamHardwareMap.hexMotor1.setPower((gamepad2LeftStickY) + 0.1); // faster drop
            }
            else if (gamepad2LeftStickY > 0) // lift arm
            {
                teamHardwareMap.hexMotor1.setPower((gamepad2LeftStickY/3) + 0.1); // slower lift
            }
            else { // hold arm
                teamHardwareMap.hexMotor1.setPower(0.1); // constant power to counteract gravity
            }

            if (gamepad2RightStickY > -0.1 && gamepad2RightStickY < 0.1) { // fix stick drift moving spinner
                teamHardwareMap.hexMotor2.setPower(0);
            }
            else { // move spinner
                if (gamepad2RightStickY > 0) {
                    teamHardwareMap.hexMotor2.setPower(gamepad2RightStickY * 0.5); // slower intake
                } else {
                    teamHardwareMap.hexMotor2.setPower(gamepad2RightStickY); // faster outtake
                }
            }

            if (gamepad2.right_bumper && gamepad2.left_bumper) // don't move duck spinner
            {
                teamHardwareMap.continuousServo1.setPower(0);
            }
            else if (gamepad2.right_bumper) // move duck spinner
            {
                teamHardwareMap.continuousServo1.setPower(1);
            }
            else if (gamepad2.left_bumper) // move duck spinner
            {
                teamHardwareMap.continuousServo1.setPower(-1);
            }
            else // don't move duck spinner
            {
                teamHardwareMap.continuousServo1.setPower(0);
            }

            telemetry.addData("Left Y value", gamepad2LeftStickY);
            telemetry.addData("Right Y value", gamepad2RightStickY);
            telemetry.addData("servo power" , teamHardwareMap.continuousServo1.getPower());
            telemetry.update();
        }
    }

}