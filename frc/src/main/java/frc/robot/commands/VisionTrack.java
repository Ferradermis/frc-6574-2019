/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class VisionTrack extends Command {
  boolean tracking;
  public VisionTrack() {
    requires(Robot.driveTrain);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //Robot.limelight.ledOn();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    tracking = true;
    Robot.limelight.ledOn();
    //Robot.driveTrain.trackVision();
    final double STEER_K = 0.1;
    final double DRIVE_K = 0.15;
    final double DESIRED_TARGET_AREA = 13.0;
    final double MAX_DRIVE = 0.4;

    double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

    if (tv < 1.0) {
      Robot.driveTrain.arcadeDrive(0, 0);
      return;
    }

    double steer_cmd = tx * STEER_K;
    double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;

    if (drive_cmd > MAX_DRIVE) {
      Robot.driveTrain.arcadeDrive(MAX_DRIVE, steer_cmd);
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (tracking == true) {
      return false;
    }
    else if (tracking == false) {
      return true;
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.limelight.ledOff();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    //Robot.limelight.ledOff();
  }
}
