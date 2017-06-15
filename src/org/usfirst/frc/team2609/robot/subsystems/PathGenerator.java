package org.usfirst.frc.team2609.robot.subsystems;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class PathGenerator {
	Trajectory.Config configFast = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_FAST, 0.01, 7, 5, 120); 
	// TODO: Fix hardcoding of max_vel, max_acc,max_jerk, etc. setProperties(double max_vel, double max_acc, double max_jerk...) perhaps?
	public Waypoint[] FiveFeetForwardPoints = new Waypoint[]{
            new Waypoint(0, 0, Pathfinder.d2r(0)),
            new Waypoint(5, 0, Pathfinder.d2r(0))
	};
	public Waypoint[] TenFeetForwardPoints = new Waypoint[]{
            new Waypoint(0, 0, Pathfinder.d2r(0)),
            new Waypoint(10, 0, Pathfinder.d2r(0))
	};
	public Waypoint[] TheoreticalGearSwerve = new Waypoint[]{
            new Waypoint(0, 0, Pathfinder.d2r(0)),
            new Waypoint(105/12, -70/12, Pathfinder.d2r(-60)) // in front of the peg
	};
	public Waypoint[] TheoreticalGearSwerve2 = new Waypoint[]{
            new Waypoint(0, 0, Pathfinder.d2r(0)),
            new Waypoint(111/12, -82/12, Pathfinder.d2r(-60)) // HITTING THE WALL IN HAMILTON
	};
	public Waypoint[] TheoreticalGearSwervePractice = new Waypoint[]{
            new Waypoint(0, 0, Pathfinder.d2r(0)),
//            new Waypoint(133/12, 45/12, Pathfinder.d2r(60)) // HITTING THE WALL IN HAMILTON
//            new Waypoint(108/12, 59/12, Pathfinder.d2r(60)) // 2.5fps 3fps2
//            new Waypoint(107/12, 57.5/12, Pathfinder.d2r(60)) // HITTING THE WALL IN HAMILTON
            new Waypoint(50/12, 60/12, Pathfinder.d2r(60)) // HITTING THE WALL IN HAMILTON
	};
	// TODO: Fix hardcoding of Waypoints SetWaypoints(arg0,arg1,arg3...) perhaps?
//  Reid measured hamilton: Rightside: x=87 y=131; Leftside: X=92 Y=131 Middle: X=0 Y= 111
    long fastGenTime;
	public TankModifier trajectoryGenerator(Waypoint[] points){
		fastGenTime = System.currentTimeMillis();
        Trajectory FastTrajectory = Pathfinder.generate(points, configFast);
        fastGenTime = System.currentTimeMillis() - fastGenTime;
        TankModifier FastModifier = new TankModifier(FastTrajectory).modify(27.75/12); // TODO: Fix hardcoding of wheelbase width
        return FastModifier;
	}
}
