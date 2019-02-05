/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class FrontLift extends Subsystem {

  /**
   * Possible positions the lift can take.
   */
  public static final double LOW = 0;
  public static final double MID = 0;
  public static final double HIGH = 0;

  //public TalonSRX liftMotor = new TalonSRX(RobotMap.FRONT_LIFT_CAN_ID);
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  /**
   * Sets the position of the front lift mechanism.
   * 
   * @param newPosition a position value indicating intended position
   */
  public void setPosition(double newPosition) {
    //liftMotor.set(ControlMode.Position, newPosition);
  }

  public void set(double speed) {
    //liftMotor.set(ControlMode.PercentOutput, speed);
  }

}
