package org.firstinspires.ftc.teamcode.driveropmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.encoderTestHardwareMap;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;


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

@TeleOp(name="EncodersTestMode", group="Linear Opmode")
public class EncodersTestMode extends LinearOpMode {

    private encoderTestHardwareMap teamHardwareMap;

    double previousValue = 0;
    ElapsedTime timer =  new ElapsedTime();

    @Override
    public void runOpMode() {
        teamHardwareMap = new encoderTestHardwareMap(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        teamHardwareMap.runTime.reset();

        boolean[] previousState = new boolean[]{teamHardwareMap.Encoder1.getState(), teamHardwareMap.Encoder2.getState()};
        boolean[] currentState = new boolean[]{teamHardwareMap.Encoder1.getState(), teamHardwareMap.Encoder2.getState()};

        int prevState = 0;
        int curState = 0;

        prevState = ConvertBoolArrayToInt(previousState);
        curState = ConvertBoolArrayToInt(currentState);

        int currentpos = 0;


        while (opModeIsActive()) {
            timer.reset();
            double gamepadInputY;
            gamepadInputY = gamepad1.left_stick_y;
            // Send calculated power to wheels

            if (curState != prevState)
            {
                switch(prevState)
                {
                    case 0:
                        switch (curState)
                        {
                            case 1:
                                currentpos--;
                                break;
                            case 2:
                                currentpos++;
                                break;
                        }
                        break;
                    case 1:
                        switch (curState)
                        {
                            case 0:
                                currentpos--;
                                break;
                            case 3:
                                currentpos++;
                                break;
                        }
                        break;
                    case 2:
                        switch (curState)
                        {
                            case 3:
                                currentpos--;
                                break;
                            case 0:
                                currentpos++;
                                break;
                        }
                        break;
                    case 3:
                        switch (curState)
                        {
                            case 2:
                                currentpos--;
                                break;
                            case 1:
                                currentpos++;
                                break;
                        }
                        break;

                }
            }

            prevState = curState;
            currentState = new boolean[]{teamHardwareMap.Encoder1.getState(), teamHardwareMap.Encoder2.getState()};
            curState = ConvertBoolArrayToInt(currentState);



            teamHardwareMap.motor.setPower(gamepadInputY/2);


            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + teamHardwareMap.runTime.toString());
            telemetry.addData("Logging 1", teamHardwareMap.Encoder1.getState());
            telemetry.addData("Logging 2", teamHardwareMap.Encoder2.getState());
            telemetry.addData("CurrentPos:", currentpos);
            telemetry.addData("Timer:", timer.time(TimeUnit.MILLISECONDS));



            //telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.addData("Input", "Y: (%.2f)", gamepadInputY);
            telemetry.update();

        }
    }

    public int ConvertBoolArrayToInt(boolean[] state)
    {
        int total = 0;
        if (state[0])
        {
            total += 2;
        }
        if (state[1])
        {
            total += 1;
        }
        return total;
    }

}