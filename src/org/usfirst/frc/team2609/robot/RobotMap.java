package org.usfirst.frc.team2609.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.modifiers.TankModifier;

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
	public static boolean drivetrainMPActiveEh,generatedEh = false;
	public static CANTalon left1,left2,right1,right2;
	public static AHRS ahrs;
	public static int leftEncPerUnit = 611;
	public static int rightEncPerUnit = 611;
	public static double cruiseVelocity = 6; //6.6
	public static TankModifier plannedPath;
	public static void init(){
		left1 = new CANTalon(1);
		left2 = new CANTalon(2);
		right1 = new CANTalon(3);
		right2 = new CANTalon(4);
		try {
            ahrs = new AHRS(SPI.Port.kMXP);
            LiveWindow.addSensor("Drivetrain", "AHRS", ahrs);
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }

		RobotMap.right1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		RobotMap.right1.configEncoderCodesPerRev(RobotMap.rightEncPerUnit);
		RobotMap.right1.setStatusFrameRateMs(CANTalon.StatusFrameRate.QuadEncoder, 10);
		RobotMap.right1.setInverted(false);
		RobotMap.right1.reverseSensor(false);
		RobotMap.right1.reverseOutput(false);
		RobotMap.left1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		RobotMap.left1.setStatusFrameRateMs(CANTalon.StatusFrameRate.QuadEncoder, 10);
		RobotMap.left1.configEncoderCodesPerRev(RobotMap.leftEncPerUnit);
		RobotMap.left1.setInverted(true);
		RobotMap.left1.reverseSensor(true);
		RobotMap.left1.reverseOutput(false);
		RobotMap.left1.setVoltageRampRate(10000);
    	RobotMap.right1.setVoltageRampRate(10000);
		right2.changeControlMode(TalonControlMode.Follower);
		right2.set(3);
		left2.changeControlMode(TalonControlMode.Follower);
		left2.set(1);
		
	}
}
