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
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ManipulateCargo;

/**
 * The roller mechanism used to intake cargo game elements.
 */
public class CargoIntake extends Subsystem {

  public static final double MAX_ROTATE = -1000;

  public VictorSPX spinMotor = new VictorSPX(RobotMap.CARGO_INTAKE_CAN_ID);
  public TalonSRX deployMotor = new TalonSRX(RobotMap.CARGO_DEPLOY_CAN_ID);

  public DigitalInput cargoZeroLimit = new DigitalInput(1); //currently unused to my knowledge

  public double lastCargoPosition = 0;
  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ManipulateCargo());
  }
 public void GPControl() {

  //PID constants with feed forward also implemented
  deployMotor.config_kF(1, 1.752 * Math.cos(readCargoDegree()));
  deployMotor.config_kP(0, 2.5);
  //deployMotor.config_kI(0, 0);
  //deployMotor.config_kD(0, 0);

  if (Math.abs(Robot.oi.getXboxRightY()) > 0.2) {
    //lastCargoPosition = cargoIntake.deployMotor.getSelectedSensorPosition();
    deployMotor.set(ControlMode.PercentOutput, Robot.oi.getXboxRightY());
  } else {
    if (Robot.oi.x_bButton.get()) {
      deployMotor.set(ControlMode.Position, 0);
      lastCargoPosition = 0;
    } else if (Robot.oi.x_xButton.get()) { 
      deployMotor.set(ControlMode.Position, -750);
      lastCargoPosition = -750;
    } else if (Robot.oi.x_aButton.get()) { 
      deployMotor.set(ControlMode.Position, -950);
      lastCargoPosition = -950;
    } else {
      deployMotor.set(ControlMode.Position, lastCargoPosition);
    }

    if (!cargoZeroLimit.get()) {
      deployMotor.setSelectedSensorPosition(0);
      lastCargoPosition = 0;
    }
  }
 }


 public void cargoInOut() {
  if (Math.abs(Robot.oi.getXboxLeftTrigger()) > 0.2) {
    spinMotor.set(ControlMode.PercentOutput, 0.5);
  } 
  else if (Robot.oi.l_aButton.get()) {
    spinMotor.set(ControlMode.PercentOutput, 0.5);
  }
  else if (Math.abs(Robot.oi.getXboxRightTrigger()) > 0.2) {
    spinMotor.set(ControlMode.PercentOutput, -1);
  } 
  else {
    spinMotor.set(ControlMode.PercentOutput, 0);
  }
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
  public double readCargoDegree() {
    return deployMotor.getSelectedSensorPosition() / 10.75;
  }

}

//cargoIntake.deployMotor.configMotionCruiseVelocity(51 * 2 * 2 * 3 * 2 * 2);
//cargoIntake.deployMotor.configMotionAcceleration(30 * 2 * 3 * 2 * 2);