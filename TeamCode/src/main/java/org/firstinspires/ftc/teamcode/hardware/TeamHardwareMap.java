package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class TeamHardwareMap {

    protected HardwareMap hardwareMap;

    public ElapsedTime runTime = new ElapsedTime();

    public TeamHardwareMap(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        initialise();
    }

    protected abstract void initialise();

}