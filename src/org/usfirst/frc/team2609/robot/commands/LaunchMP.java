package org.usfirst.frc.team2609.robot.commands;

import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

/**
 *
 */
public class LaunchMP extends Command {

	EncoderFollower left = Robot.driveTrain.left;
	EncoderFollower right = Robot.driveTrain.right;
    public LaunchMP() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("Starting init LaunchMP");
        try{
        	RobotMap.plannedPath = Robot.pathGenerator.trajectoryGenerator(Robot.pathGenerator.TheoreticalGearSwervePractice); // Generate the path

			
        	RobotMap.generatedEh = true;
        	Robot.driveTrain.initMP();
        }catch(Throwable t){
        	System.out.println("LaunchMP error");
        	System.out.println(t.getMessage());
        	System.out.println(t.getStackTrace());
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if(RobotMap.generatedEh){
//    		double leftOutput = left.calculate(RobotMap.left1.getEncPosition());
//    		double rightOutput = right.calculate(RobotMap.right1.getEncPosition());
//    		if(leftOutput != 0 && rightOutput!=0){
//    			Trajectory.Segment rightSegment = right.getSegment();
//    			Trajectory.Segment leftSegment = left.getSegment();
//    			Robot.logger.logMP(leftSegment, rightSegment);
//    		}
//    		RobotMap.left1.set(leftOutput);
//    		RobotMap.right1.set(rightOutput);
//    	}
    	// This timing of this loop runs anywhere between 100hz and 7hz. DO NOT USE
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return RobotMap.drivetrainMPActiveEh;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}