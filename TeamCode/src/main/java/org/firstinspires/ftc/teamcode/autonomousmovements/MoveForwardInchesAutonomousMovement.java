package org.firstinspires.ftc.teamcode.autonomousmovements;

import org.firstinspires.ftc.teamcode.hardware.AllMotorsAndSensorsTeamHardwareMap;
import org.firstinspires.ftc.teamcode.hardware.TeamHardwareMap;

public class MoveForwardInchesAutonomousMovement extends AutonomousMovement {
    public int inches;
    public double speed;

    public MoveForwardInchesAutonomousMovement(AllMotorsAndSensorsTeamHardwareMap teamHardwareMap, int _inches, double _speed) {
        super(teamHardwareMap);
        inches = _inches;
        speed = _speed;
    }
}
