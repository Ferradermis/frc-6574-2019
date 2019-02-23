/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {

  public static final double MAX_EXTEND = 0;
  public TalonSRX extender = new TalonSRX(RobotMap.CLIMB_EXTENDER_CAN_ID);
  public VictorSPX wheel = new VictorSPX(RobotMap.CLIMB_WHEEL_CAN_ID);
  public double speed = 0.5;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  } 

  public void lowerClimber() {
    extender.set(ControlMode.Position, MAX_EXTEND);
  }

  public void raiseClimber() {
    extender.set(ControlMode.Position, 0);
  }

  public void driveForward() {
    wheel.set(ControlMode.PercentOutput, speed);
  }

  public void driveBackward() {
    wheel.set(ControlMode.PercentOutput, -speed);
  }

}
