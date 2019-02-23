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
 * The roller mechanism used to intake cargo game elements.
 */
public class CargoIntake extends Subsystem {

  public static final double MAX_ROTATE = -1000;

  public VictorSPX spinMotor = new VictorSPX(RobotMap.CARGO_INTAKE_CAN_ID);
  public TalonSRX deployMotor = new TalonSRX(RobotMap.CARGO_DEPLOY_CAN_ID);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  /**
   * 
   * @param value (0.3) a value in the range -0.5 (full inward) to 0.5 (full outward)
   */
  public void spin(double speed) {
    spinMotor.set(ControlMode.PercentOutput, speed);
  }

  public void deploy() {
    deployMotor.set(ControlMode.Position, MAX_ROTATE);
  }

  public void retract() {
    deployMotor.set(ControlMode.Position, 0);
  }

}
