package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.Range;

public class MathsMethods {
    static final int ticksPerRotation = 720;
    static final double diameterOfWheel = 9;
    static final double centimetresPerInch = 2.54;
    static final double centimetresBetweenBackWheels = 36;
    static final double gradualIncreaseRate = 0.05;
    static final double millisecondsToPerformGradualIncreaseOver = 50;

    public static double[] JoystickToDifferential(double x, double y) {
        if (x == 0 && y == 0) {
            return (new double[]{0, 0});
        }

        double minJoyStick = -1;
        double maxJoyStick = 1;
        double minSpeed = -1;
        double maxSpeed = 1;

        //find hypotenuse
        double hyp = Math.sqrt(x * x + y * y);

        //find angle in radians
        double ang = Math.acos(Math.abs(x) / y);

        double degAng = ang * 180 / Math.PI;

     /* Now angle indicates the measure of turn
	    Along a straight line, with an angle o, the turn co-efficient is same
	    this applies for angles between 0-90, with angle 0 the coeff is -1
	    with angle 45, the co-efficient is 0 and with angle 90, it is 1 */

        double turnCE = -1 + (degAng / 90) * 2;
        double turn = turnCE * Math.abs(Math.abs(y) - Math.abs(x));
        // java cant round to a certain dp :(
        turn *= 100;
        turn = Math.round(turn);
        turn /= 100;

        double movement = Math.max(Math.abs(y), Math.abs(x));

        double rawLeft;
        double rawRight;

        if ((x >= 0 && y >= 0) || (x < 0 && y < 0)) {
            rawLeft = turn;
            rawRight = movement;
        } else {
            rawRight = turn;
            rawLeft = movement;
        }


        //Reverse polarity
        if (y < 0) {
            rawLeft = 0 - rawLeft;
            rawRight = 0 - rawRight;
        }

        return( new double[] {rawRight, rawLeft});
    }

    /**
     *
     * @param centimetres The amount of centimetres the robot as a whole should move
     * @return The amount of ticks needed to be applied to all driving motors for the robot to move the provided number of centimetres
     */
    public static int CentimetresToMainMotorTicks(int centimetres) {
        return (int)(centimetres / (diameterOfWheel * Math.PI) * ticksPerRotation);
    }

    /**
     *
     * @param inches The amount of inches the robot as a whole should move
     * @return The amount of ticks needed to be applied to all driving motors for the robot to move the provided number of inches
     */
    public static int InchesToMainMotorTicks(int inches) {
        return CentimetresToMainMotorTicks((int)(inches * centimetresPerInch));
    }

    /**
     *
     * @param degrees The amount of degrees the robot as a whole should turn
     * @return The amount of ticks needed to be applied to one side's driving motors for the robot to rotate the provided number of degrees
     */
    public static int DegreesToMainMotorTicks(int degrees) {
        return MathsMethods.CentimetresToMainMotorTicks((int) (centimetresBetweenBackWheels * Math.PI * 2 / (360 / degrees)));
    }

    /**
     *
     * @param oldMotorPower The current power being applied to the motor. (value between -1 and +1)
     * @param targetMotorPower The target power being indicated by the joystick. You will need to apply the negative inversion. (value between -1 and +1)
     * @return The new power to be applied to the motor. (value between -1 and +1)
     */
    public static double CalculateNewGradualMotorPower(double oldMotorPower, double targetMotorPower, double millisecondsSinceLastLoopStarted) {
        if (targetMotorPower == 0) // don't move motor
        {
            return 0;
        }
        else if (oldMotorPower < targetMotorPower) { // increase motor speed
            return Range.clip(oldMotorPower + gradualIncreaseRate * (millisecondsSinceLastLoopStarted / millisecondsToPerformGradualIncreaseOver), -1, 1);
        }
        else if (oldMotorPower > targetMotorPower) { // decrease motor speed
            return Range.clip(oldMotorPower - gradualIncreaseRate * (millisecondsSinceLastLoopStarted / millisecondsToPerformGradualIncreaseOver), -1, 1);
        }
        else if (oldMotorPower == targetMotorPower) {
            return oldMotorPower;
        }
        throw new IllegalArgumentException("MathsMethods.java line 110 --- targetMotorSpeed = " + targetMotorPower + ", oldMotorPower = " + oldMotorPower);
    }
}
