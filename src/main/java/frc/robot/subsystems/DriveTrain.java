/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * The motors that control the drive base of the robot.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public TalonSRX frontLeft = new TalonSRX(RobotMap.FRONT_LEFT_ID);
  public TalonSRX backLeft = new TalonSRX(RobotMap.BACK_LEFT_ID);
  public TalonSRX frontRight = new TalonSRX(RobotMap.FRONT_RIGHT_ID);
  public TalonSRX backRight = new TalonSRX(RobotMap.BACK_RIGHT_ID);

  @Override
  public void initDefaultCommand() {
    
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void controllerDrive() {

  }

  public void joystickDrive() {
    
  }

}
