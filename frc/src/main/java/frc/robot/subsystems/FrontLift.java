/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import frc.robot.OI;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ElevatorControl;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.*;

/**
 * Add your docs here.
 */
public class FrontLift extends Subsystem {
  public DigitalInput liftZeroLimit = new DigitalInput(0);
  public DigitalInput liftTopLimit = new DigitalInput(3);

  

  public TalonSRX liftMotor = new TalonSRX(RobotMap.FRONT_LIFT_CAN_ID);
  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ElevatorControl());
  }


  public void controlElevator() {
    if (Robot.oi.getXboxLeftY() > 0.2 && liftTopLimit.get()) {
      liftMotor.set(ControlMode.PercentOutput, Robot.oi.getXboxLeftY() * 0.85 + 0.15);
    } else if (Robot.oi.getXboxLeftY() < -0.2 && liftZeroLimit.get()) {
    
      liftMotor.set(ControlMode.PercentOutput, Robot.oi.getXboxLeftY() * 0.5);
    } else {
      liftMotor.set(ControlMode.PercentOutput, 0.15);
    }
  
    if (!liftZeroLimit.get()) {
      liftMotor.setSelectedSensorPosition(0);
    }
  }

/**
   * Sets the position of the front lift mechanism.
   * 
   * @param newPosition a position value indicating intended position
   */

  public void setPosition(double newPosition) {
    liftMotor.set(ControlMode.Position, newPosition);
  }

  //public void set(double speed) {
    //liftMotor.set(ControlMode.PercentOutput, speed);
  //}

  public double getPosition() {
    //return 0;
    return liftMotor.getSelectedSensorPosition();
  }

}

//Need time to test before adding
    

    //frontLift.liftMotor.config_kF(0, 0.25); //1.8
    
    //frontLift.liftMotor.config_kF(0, 0);
    //frontLift.liftMotor.config_kP(0, 3);
    //frontLift.liftMotor.config_kI(0, 0);
    //frontLift.liftMotor.config_kD(0, 0);

    //frontLift.liftMotor.configAllowableClosedloopError(0, 25);

    //frontLift.liftMotor.setSelectedSensorPosition(0);