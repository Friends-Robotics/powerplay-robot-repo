package org.firstinspires.ftc.teamcode.driveropmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.MathsMethods;
import org.firstinspires.ftc.teamcode.hardware.LinearSlideWithGrabberHardwareMap;


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

@TeleOp(name="Linear Slide With Grabber", group="Linear Opmode")
public class LinearSlideWithGrabberOpMode extends LinearOpMode {

    private LinearSlideWithGrabberHardwareMap teamHardwareMap;

    double previousValue = 0;

    @Override
    public void runOpMode() {
        teamHardwareMap = new LinearSlideWithGrabberHardwareMap(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        teamHardwareMap.runTime.reset();

        while (opModeIsActive()) {
            double gamepadInputY = gamepad1.left_stick_y;
            // Send calculated power to wheels

            //int max = -100;
            //int min = -4000;
            int hardStopTopAbsolute = 4300; // TODO: change this to be appropriate on competition day
            int hardStopBottomAbsolute = 50; // TODO: change this to be appropriate on competition day

            int pos = teamHardwareMap.motor.getCurrentPosition();
            if (MathsMethods.NeedsHardStopped(pos, hardStopTopAbsolute)) {
                teamHardwareMap.motor.setPower(-0.2);
            }
            else if (MathsMethods.NeedsHardStopped(pos, hardStopBottomAbsolute)) {
                teamHardwareMap.motor.setPower(0.7);
            }
            else {
                teamHardwareMap.motor.setPower(gamepadInputY / 2);
            }

            if (gamepad1.circle) {
                teamHardwareMap.servo0.setPosition(1);
                teamHardwareMap.servo1.setPosition(0);
            }
            if (gamepad1.cross) {
                teamHardwareMap.servo0.setPosition(0);
                teamHardwareMap.servo1.setPosition(1);
            }

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + teamHardwareMap.runTime.toString());
            //telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.addData("Input", "Y: (%.2f)", gamepadInputY);
            telemetry.addData("Position", pos);
            telemetry.addData("Motor power", teamHardwareMap.motor.getPower());
            telemetry.update();

        }
    }

}