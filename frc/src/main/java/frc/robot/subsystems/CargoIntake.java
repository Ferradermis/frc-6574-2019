/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The roller mechanism used to intake cargo game elements.
 */
public class CargoIntake extends Subsystem {

  public static final double MAX_ROTATE = 0;

  //public void VictorSPX spinMotor = new VictorSPX(RobotMap.CARGO_INTAKE_CAN_ID);
  //public void TalonSRX deployMotor = new TalonSRX(RobotMap.CARGO_DEPLOY_CAN_ID);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  /**
   * 
   * @param value a value in the range -1 (full inward) to 1 (full outward)
   */
  public void spin(double speed) {
    //spinMotor.set(ControlMode.PercentOutput, speed);
  }

  public void deploy() {
    //deployMotor.set(ControlMode.Position, MAX_ROTATE);
  }

  public void retract() {
    //deployMotor.set(ControlMode.Position, 0);
  }

}
