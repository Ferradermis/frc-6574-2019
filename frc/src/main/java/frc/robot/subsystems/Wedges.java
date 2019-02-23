/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * The wedge subsystem used for climbing positioning.
 */
public class Wedges extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static final double MAX_ROTATE = 0;
  public TalonSRX wedgeMotor = new TalonSRX(RobotMap.WEDGE_MOTOR_CAN_ID);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  /**
   * Deploys the wedges into the position necessary for climbing.
   */
  public void deploy() {
    wedgeMotor.set(ControlMode.Position, MAX_ROTATE);
  }

  /**
   * Retracts the wedges into the starting position within the frame perimeter.
   */
  public void retract() {
    wedgeMotor.set(ControlMode.Position, 0);
  }
  
}
