package org.usfirst.frc.team2609.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	public static CANTalon left1;
	public static CANTalon left2;
	public static CANTalon right1;
	public static CANTalon right2;
	public static AHRS ahrs;
	public static void init(){
		left1 = new CANTalon(1);
		left1.configEncoderCodesPerRev(611);
		left2 = new CANTalon(2);
		left2.changeControlMode(TalonControlMode.Follower);
		left2.set(1);
		right1 = new CANTalon(3);
		right1.configEncoderCodesPerRev(611);
		right1.setInverted(true);
		right2 = new CANTalon(4);
		right2.changeControlMode(TalonControlMode.Follower);
		right2.set(3);
		try {
            ahrs = new AHRS(SPI.Port.kMXP);
            LiveWindow.addSensor("Drivetrain", "AHRS", ahrs);
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }
	}
}
