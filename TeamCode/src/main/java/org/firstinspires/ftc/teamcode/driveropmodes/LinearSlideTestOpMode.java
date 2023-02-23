package org.firstinspires.ftc.teamcode.driveropmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.MathsMethods;
import org.firstinspires.ftc.teamcode.hardware.AllMotorsAndSensorsTeamHardwareMap;
import org.firstinspires.ftc.teamcode.hardware.LinearSlideHardwareMap;


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

@TeleOp(name="Linear Slide Test", group="Linear Opmode")
public class LinearSlideTestOpMode extends LinearOpMode {

    private LinearSlideHardwareMap teamHardwareMap;

    double previousValue = 0;

    @Override
    public void runOpMode() {
        teamHardwareMap = new LinearSlideHardwareMap(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        teamHardwareMap.runTime.reset();


        while (opModeIsActive()) {
            double gamepadInputY;
            gamepadInputY = gamepad1.left_stick_y;
            // Send calculated power to wheels

            int max = -100;
            int min = -4000;

            int pos = teamHardwareMap.motor.getCurrentPosition();
            if (teamHardwareMap.motor.getCurrentPosition() < max || teamHardwareMap.motor.getCurrentPosition() > min)
                teamHardwareMap.motor.setPower(gamepadInputY);
            else
                teamHardwareMap.motor.setPower(0);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + teamHardwareMap.runTime.toString());
            //telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.addData("Input", "Y: (%.2f)", gamepadInputY);
            telemetry.addData("Position ", Integer.toString(pos));
            telemetry.update();

        }
    }

}