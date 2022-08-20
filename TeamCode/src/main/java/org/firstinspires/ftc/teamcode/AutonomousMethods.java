package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.enums.TurningDirection;
import org.firstinspires.ftc.teamcode.hardware.AllMotorsAndSensorsTeamHardwareMap;

public class AutonomousMethods {
    /**
     * Assumes encoder ticks are at 0 for original call, and that the RUN_WITHOUT_ENCODER motor mode is set for all calls. Does not set any motor modes.
     * @param teamHardwareMap The team hardware map variable
     * @param inches Then amount of inches to move the robot forward
     * @param speed The speed at which to turn the motors to complete the movement
     * @return False if robot has not completed movement, true if robot has completed movement. (value between 0 and 1)
     */
    public static boolean MoveForwardInches(AllMotorsAndSensorsTeamHardwareMap teamHardwareMap, int inches, double speed) {
        if (teamHardwareMap.leftMotor.getCurrentPosition() >= MathsMethods.InchesToMainMotorTicks(inches)) {
            teamHardwareMap.leftMotor.setPower(0);
            teamHardwareMap.rightMotor.setPower(0);
            return true;
        } else {
            teamHardwareMap.leftMotor.setPower(speed);
            teamHardwareMap.rightMotor.setPower(speed);
            return false;
        }
    }

    /**
     * Assumes encoder ticks are at 0 for original call, and that the RUN_WITHOUT_ENCODER motor mode is set for all calls. Does not set any motor modes.
     * @param teamHardwareMap The team hardware map variable
     * @param degrees Then amount of degrees to turn the robot
     * @param speed The speed at which to turn the motors to complete the movement. (value between 0 and 1)
     * @param turningDirection The direction to turn the robot
     * @return False if robot has not completed movement, true if robot has completed movement
     */
    public static boolean TurnDegrees(AllMotorsAndSensorsTeamHardwareMap teamHardwareMap, int degrees, double speed, TurningDirection turningDirection) {
        DcMotor motorToGetEncoderTicksFromAndMove;
        if (turningDirection == TurningDirection.Left) {
            motorToGetEncoderTicksFromAndMove = teamHardwareMap.rightMotor;
        }
        else if (turningDirection == TurningDirection.Right){
            motorToGetEncoderTicksFromAndMove = teamHardwareMap.leftMotor;
        }
        else {
            throw new IllegalArgumentException();
        }

        if (motorToGetEncoderTicksFromAndMove.getCurrentPosition() >= MathsMethods.DegreesToMainMotorTicks(degrees)) {
            teamHardwareMap.leftMotor.setPower(0);
            teamHardwareMap.rightMotor.setPower(0);
            return true;
        } else {
            motorToGetEncoderTicksFromAndMove.setPower(speed);
            return false;
        }
    }
}
