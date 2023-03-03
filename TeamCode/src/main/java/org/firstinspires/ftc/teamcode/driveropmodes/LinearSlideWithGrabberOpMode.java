package org.firstinspires.ftc.teamcode.driveropmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.MecanumHelper;
import org.firstinspires.ftc.teamcode.hardware.MecanumMotorsLinearSlideGrabberHardwareMap;


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

    private MecanumMotorsLinearSlideGrabberHardwareMap teamHardwareMap;

    @Override
    public void runOpMode() {
        teamHardwareMap = new MecanumMotorsLinearSlideGrabberHardwareMap(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        teamHardwareMap.runTime.reset();

        MecanumHelper mecanumHelper = new MecanumHelper(teamHardwareMap.frontRightMotor, teamHardwareMap.backRightMotor, teamHardwareMap.backLeftMotor, teamHardwareMap.frontLeftMotor);

        boolean dpadUpPressed = false;
        boolean dpadDownPressed = false;

        while (opModeIsActive()) {
            double leftStickYInput = -gamepad1.left_stick_y;
            double leftStickXInput = gamepad1.left_stick_x;
            double rightStickYInput = -gamepad1.right_stick_y;

            //mecanumHelper.forward(leftStickYInput);
            mecanumHelper.go(leftStickXInput, leftStickYInput);

            teamHardwareMap.linearSlide.setPower(rightStickYInput / 1.5);
            if (rightStickYInput == 0) teamHardwareMap.linearSlide.setPower(0.1);

            if (gamepad1.circle) { // in
                teamHardwareMap.grabberLeft.setPosition(1);
                teamHardwareMap.grabberRight.setPosition(0);
            }
            if (gamepad1.cross) { // out
                teamHardwareMap.grabberLeft.setPosition(0.54);
                teamHardwareMap.grabberRight.setPosition(0.48);
            }

            if (gamepad1.dpad_up && !dpadUpPressed) {
                mecanumHelper.speed += 0.1;
                if (mecanumHelper.speed > 1) mecanumHelper.speed = 1;
                dpadUpPressed = true;
            }
            if (!gamepad1.dpad_up) dpadUpPressed = false;
            if (gamepad1.dpad_down && !dpadDownPressed) {
                mecanumHelper.speed -= 0.1;
                if (mecanumHelper.speed < 0) mecanumHelper.speed = 0;
                dpadDownPressed = true;
            }
            if (!gamepad1.dpad_down) dpadDownPressed = false;

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + teamHardwareMap.runTime.toString());
            telemetry.addData("Left Stick X", leftStickXInput);
            telemetry.addData("Left Stick Y", leftStickYInput);
            telemetry.addData("FLW", teamHardwareMap.frontLeftMotor.getPower());
            telemetry.addData("FRW", teamHardwareMap.frontRightMotor.getPower());
            telemetry.addData("BLW", teamHardwareMap.backLeftMotor.getPower());
            telemetry.addData("BRW", teamHardwareMap.backRightMotor.getPower());
            telemetry.addData("Speed", mecanumHelper.speed);
            telemetry.update();

        }
    }
}