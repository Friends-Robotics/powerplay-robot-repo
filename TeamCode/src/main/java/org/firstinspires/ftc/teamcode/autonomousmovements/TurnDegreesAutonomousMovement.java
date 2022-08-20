package org.firstinspires.ftc.teamcode.autonomousmovements;

import org.firstinspires.ftc.teamcode.enums.TurningDirection;
import org.firstinspires.ftc.teamcode.hardware.AllMotorsAndSensorsTeamHardwareMap;
import org.firstinspires.ftc.teamcode.hardware.TeamHardwareMap;

public class TurnDegreesAutonomousMovement extends AutonomousMovement {
    public int degrees;
    public double speed;
    public TurningDirection turningDirection;

    public TurnDegreesAutonomousMovement(AllMotorsAndSensorsTeamHardwareMap teamHardwareMap, int _degrees, double _speed, TurningDirection _turningDirection) {
        super(teamHardwareMap);
        degrees = _degrees;
        speed = _speed;
        turningDirection = _turningDirection;
    }
}
