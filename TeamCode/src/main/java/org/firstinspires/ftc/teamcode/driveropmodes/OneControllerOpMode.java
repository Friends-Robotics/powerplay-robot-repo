package org.firstinspires.ftc.teamcode.driveropmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.MathsMethods;
import org.firstinspires.ftc.teamcode.hardware.AllMotorsAndSensorsTeamHardwareMap;
import org.firstinspires.ftc.teamcode.hardware.DrivetrainOnlyTeamHardwareMap;


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

@TeleOp(name="One controller (tank)", group="Linear Opmode")
public class OneControllerOpMode extends LinearOpMode {

    private DrivetrainOnlyTeamHardwareMap teamHardwareMap;
    private double totalMillisecondsAtLastLoop;
    private double millisecondsSinceLastLoopStarted;

    @Override
    public void runOpMode() {
        teamHardwareMap = new DrivetrainOnlyTeamHardwareMap(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        teamHardwareMap.runTime.reset();
        totalMillisecondsAtLastLoop = 0;
        millisecondsSinceLastLoopStarted = 0;

        while (opModeIsActive()) {
            millisecondsSinceLastLoopStarted = teamHardwareMap.runTime.milliseconds() - totalMillisecondsAtLastLoop;
            totalMillisecondsAtLastLoop = teamHardwareMap.runTime.milliseconds();

            double gamepad1LeftStickY = -gamepad1.left_stick_y;
            double gamepad1RightStickY = -gamepad1.right_stick_y;
            double gamepad1LeftTrigger = gamepad1.left_trigger;
            double gamepad1RightTrigger = gamepad1.right_trigger;

            double oldLeftMotorPower = teamHardwareMap.leftMotor.getPower();
            double oldRightMotorPower = teamHardwareMap.rightMotor.getPower();

            // calculate gradual motor powers, possibly overridden below
            double newLeftMotorPower = gamepad1LeftStickY;
            double newRightMotorPower = gamepad1RightStickY;

            if (gamepad1RightTrigger > 0) {
                newRightMotorPower = MathsMethods.CalculateMotorOutputOnCurveFromControllerInput(gamepad1RightTrigger);
                newLeftMotorPower = MathsMethods.CalculateMotorOutputOnCurveFromControllerInput(gamepad1RightTrigger);
            }
            if (gamepad1LeftTrigger > 0) {
                newRightMotorPower = MathsMethods.CalculateMotorOutputOnCurveFromControllerInput(-gamepad1LeftTrigger);
                newLeftMotorPower = MathsMethods.CalculateMotorOutputOnCurveFromControllerInput(-gamepad1LeftTrigger);
            }

            // Send calculated power to wheels
            teamHardwareMap.leftMotor.setPower(newLeftMotorPower);
            teamHardwareMap.rightMotor.setPower(newRightMotorPower);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + teamHardwareMap.runTime.toString());
            //telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.addData("Input", "L stick: (%.2f); R stick: (%.2f)", gamepad1LeftStickY, gamepad1RightStickY);
            telemetry.addData("Input", "L trigger: (%.2f); R trigger: (%.2f)", gamepad1LeftTrigger, gamepad1RightTrigger);
            telemetry.addData("Motors", "Left: (%.2f); Right: (%.2f)", newLeftMotorPower, newRightMotorPower);
            telemetry.update();

            ////////////

            /*if (gamepad1.right_bumper)
            {
                teamHardwareMap.hexMotor1.setPower(1);
            }
            if (gamepad1.left_bumper)
            {
                teamHardwareMap.hexMotor1.setPower(-1);
            }
            else
            {
                teamHardwareMap.hexMotor1.setPower(0.1);
            }

            if (gamepad1.triangle) {
                teamHardwareMap.hexMotor2.setPower(1);
            }
            else if (gamepad1.cross) {
                teamHardwareMap.hexMotor2.setPower(-1);
            }
            else {
                teamHardwareMap.hexMotor2.setPower(0);
            }

            ////////////

            if (gamepad1.circle) {
                teamHardwareMap.continuousServo1.setPower(1);
            }
            else if (gamepad1.square) {
                teamHardwareMap.continuousServo1.setPower(-1);
            }
            else {
                teamHardwareMap.continuousServo1.setPower(0);
            }

            ////////////

            telemetry.update();*/
        }
    }

}