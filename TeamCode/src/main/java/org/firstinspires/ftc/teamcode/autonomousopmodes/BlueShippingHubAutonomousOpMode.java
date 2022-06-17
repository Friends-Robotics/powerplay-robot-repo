package org.firstinspires.ftc.teamcode.autonomousopmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.AutonomousMethods;
import org.firstinspires.ftc.teamcode.autonomousmovements.AutonomousMovement;
import org.firstinspires.ftc.teamcode.autonomousmovements.MoveForwardInchesAutonomousMovement;
import org.firstinspires.ftc.teamcode.autonomousmovements.TurnDegreesAutonomousMovement;
import org.firstinspires.ftc.teamcode.enums.TurningDirection;
import org.firstinspires.ftc.teamcode.hardware.AllMotorsAndSensorsTeamHardwareMap;

import java.util.ArrayList;

@Autonomous(name = "Blue Shipping Hub", group = "tests")
public class BlueShippingHubAutonomousOpMode extends LinearOpMode {

    private AllMotorsAndSensorsTeamHardwareMap teamHardwareMap;

    @Override
    public void runOpMode() throws InterruptedException {

        teamHardwareMap = new AllMotorsAndSensorsTeamHardwareMap(hardwareMap);

        ElapsedTime timer = new ElapsedTime();
        waitForStart();

        timer.reset();
        final int numOfPreStages = 1;
        int lastStageFinished = 0;
        ArrayList<AutonomousMovement> autonomousMovements = new ArrayList<>();
        autonomousMovements.add(new MoveForwardInchesAutonomousMovement(teamHardwareMap, 24, 0.5));
        autonomousMovements.add(new TurnDegreesAutonomousMovement(teamHardwareMap, 90, 0.5, TurningDirection.Right));
        autonomousMovements.add(new MoveForwardInchesAutonomousMovement(teamHardwareMap, 27, 0.5));
        while (opModeIsActive()) {
            teamHardwareMap.leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            teamHardwareMap.rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            if (lastStageFinished == 0) {
                if (timer.milliseconds() < 500) { // raise arm for start of autonomous to get over pipes
                    teamHardwareMap.hexMotor1.setPower(0.5);
                } else {
                    teamHardwareMap.hexMotor1.setPower(0.05);
                    lastStageFinished++;
                }
            }

            if (lastStageFinished == numOfPreStages) {
                AutonomousMovement currentAutonomousMovement = autonomousMovements.get(lastStageFinished - numOfPreStages);
                boolean finished = false;
                if (currentAutonomousMovement instanceof MoveForwardInchesAutonomousMovement) {
                    MoveForwardInchesAutonomousMovement moveForwardInchesAutonomousMovement = (MoveForwardInchesAutonomousMovement) currentAutonomousMovement;
                    finished = AutonomousMethods.MoveForwardInches(currentAutonomousMovement.teamHardwareMap, moveForwardInchesAutonomousMovement.inches, moveForwardInchesAutonomousMovement.speed);
                }
                else if (currentAutonomousMovement instanceof TurnDegreesAutonomousMovement) {
                    TurnDegreesAutonomousMovement turnDegreesAutonomousMovement = (TurnDegreesAutonomousMovement) currentAutonomousMovement;
                    finished = AutonomousMethods.TurnDegrees(currentAutonomousMovement.teamHardwareMap, turnDegreesAutonomousMovement.degrees, turnDegreesAutonomousMovement.speed, turnDegreesAutonomousMovement.turningDirection);
                }
                if (finished) {
                    teamHardwareMap.leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    teamHardwareMap.rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    lastStageFinished++;
                }
            }
        }
    }
}