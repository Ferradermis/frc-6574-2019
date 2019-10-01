/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ClimbPickControl;

/**
 * The wedge subsystem used for climbing positioning.
 */

public class Wedges extends Subsystem {

  public static final double MAX_ROTATE = 0;
  public TalonSRX wedgeMotor = new TalonSRX(RobotMap.WEDGE_MOTOR_CAN_ID);
  public DigitalInput climbTopLimit = new DigitalInput(2);
  public boolean endgame = false;

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ClimbPickControl());
  }

  public void WedgeControl() {
    if (Robot.getEndgame()) {

      Robot.blinkin.set(-0.91); 
      Robot.driveTrain.shifter.set(DoubleSolenoid.Value.kReverse);
      
      //Wedge controls
      if (Robot.oi.x_aButton.get()) {
        wedgeMotor.set(ControlMode.PercentOutput, 0.7); //Wedges down
      } 
      else if (Robot.oi.x_bButton.get()) {
        wedgeMotor.set(ControlMode.PercentOutput, -0.9); //Wedges up
      } 
      else {
        wedgeMotor.set(ControlMode.PercentOutput, 0);
      }

      //Climber controls
      if (Robot.oi.getXboxRightTrigger() > 0.2) {
        Robot.climber.wheel.set(ControlMode.PercentOutput, -0.8);
      } else {
        Robot.climber.wheel.set(ControlMode.PercentOutput, 0);
      }

      if (Robot.oi.getXboxLeftY() > 0.2 && climbTopLimit.get()) {
        Robot.climber.extender.set(ControlMode.PercentOutput, -Robot.oi.getXboxLeftY());
      } 
      else if (Robot.oi.getXboxLeftY() < -0.2) {

        Robot.climber.extender.set(ControlMode.PercentOutput, -Robot.oi.getXboxLeftY());
      } 
      else {
        Robot.climber.extender.set(ControlMode.PercentOutput, 0);
      }
    }
    else {
      Robot.blinkin.set(-0.57); //-.91 (green/forest palette), -.67(party palette)
    }
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
