package org.usfirst.frc.team2609.robot.commands;

import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LaunchMP extends Command {

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
        }catch(Throwable t){
        	System.out.println("LaunchMP error");
        	System.out.println(t.getMessage());
        	System.out.println(t.getStackTrace());
        }
    	Robot.driveTrain.initMP();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}