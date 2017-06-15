package org.usfirst.frc.team2609.robot.subsystems;
import org.usfirst.frc.team2609.loops.Loop;
import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

public class Drivetrain extends Subsystem {
	private static double drivePIDOutputLeft = 0;
	private static double drivePIDOutputRight = 0;
	private static double steerPIDOutput = 0;
	private static double throttle = 0;
	private static double turnValue = 0;
	private static double leftMtr = 0;
	private static double rightMtr = 0;
	private static double turningGain = 0;  // 0 is no change, larger has greater effect
	private static double deadZone = 0.15; //Deadband for the controller
	double[] defaultval = new double[0];
	EncoderFollower left,right;
	private final Loop mLoop = new Loop() {
		@Override
		public void onStart(){
			System.out.println("Starting DriveTrain loop");
		}
		@Override
		public void onLoop(){
			synchronized (Drivetrain.this){
				if(RobotMap.drivetrainMPActiveEh){
					// Run motion profiler
			    	double leftOutput = left.calculate(RobotMap.left1.getPosition());
			    	double rightOutput = right.calculate(RobotMap.right1.getPosition());
//			    	if(rightOutput != 0){
//			    		rightOutput = rightSegment.velocity * (1/(RobotMap.cruiseVelocity));
//			    	}
			    	double angleError = (Pathfinder.r2d(left.getHeading()) - (-RobotMap.ahrs.getYaw())); // I know it is the same as + getyaw
			    	
			    	double turn = 0.03*Pathfinder.boundHalfDegrees(angleError);
			    	if (turn>0){
			    		turn = Math.min(turn, 0.4);
			    	}
			    	else if (turn<0){
			    		turn = Math.max(turn, -0.4);
			    	}
			    	else{
			    		turn = 0;
			    	}
//			    	System.out.println(Pathfinder.boundHalfDegrees(angleError));
//			    	leftOutput = leftOutput+turn;
//			    	rightOutput = rightOutput-turn;
			    	
			    	RobotMap.left1.set(leftOutput);
			    	RobotMap.right1.set(rightOutput);
			    	
			    	if(left.isFinished() && right.isFinished()){
			    		RobotMap.drivetrainMPActiveEh = false;
				    	RobotMap.left1.set(0);
				    	RobotMap.right1.set(0);
			    		System.out.println("Both trajectories finished");
			    	}
			    	else{

				    	Trajectory.Segment rightSegment = right.getSegment();
				    	Trajectory.Segment leftSegment = left.getSegment();
				    	Robot.logger.logMP(leftSegment, rightSegment);
			    	}
			    	
				}
				else{
//					Robot.logger.logAll();
				}
			}
		}
		@Override
		public void onStop(){
			System.out.println("Ending DriveTrain loop");
		}
	};
	
	public Loop getLooper(){
		return mLoop;
	}
	public void resetDriveEncoders(){
		RobotMap.left1.setEncPosition(0);
		RobotMap.right1.setEncPosition(0);
	}
	
    public void initMP(){
		if (RobotMap.generatedEh) { // The point of this is to make sure that the trajectory has been generated.

			this.resetDriveEncoders();
			left = new EncoderFollower(RobotMap.plannedPath.getLeftTrajectory());
			left.configureEncoder(RobotMap.left1.getEncPosition(), 3840, 6 / 12);
			left.configurePIDVA( 0.0, 0.0, 0.0, 1 / (RobotMap.cruiseVelocity), 1/12);

			right = new EncoderFollower(RobotMap.plannedPath.getRightTrajectory());
			right.configureEncoder(RobotMap.right1.getEncPosition(), 3840, 6 / 12);
			right.configurePIDVA(0.0, 0, 0.0, 1 / (RobotMap.cruiseVelocity), 1/12);

			RobotMap.left1.changeControlMode(TalonControlMode.PercentVbus);
			RobotMap.right1.changeControlMode(TalonControlMode.PercentVbus);
			RobotMap.drivetrainMPActiveEh = true;
			System.out.println("Starting MP loops");
		}
    }

    public void initDefaultCommand() {
    }
   
    public double nativeVelToFPSL(double nativeVel){
    	return ((nativeVel*10)/4)/RobotMap.leftEncPerUnit;
    }
    public double nativeVelToFPSR(double nativeVel){
    	return ((nativeVel*10)/4)/RobotMap.rightEncPerUnit;
    }
}

