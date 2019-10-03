/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
//import frc.robot.OI;
import frc.robot.commands.ArcadeDrive;

public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public CANSparkMax frontLeft = new CANSparkMax(RobotMap.FRONT_LEFT_CAN_ID, MotorType.kBrushless);
  public CANSparkMax backLeft = new CANSparkMax(RobotMap.BACK_LEFT_CAN_ID, MotorType.kBrushless);
  public CANSparkMax frontRight = new CANSparkMax(RobotMap.FRONT_RIGHT_CAN_ID, MotorType.kBrushless);
  public CANSparkMax backRight = new CANSparkMax(RobotMap.BACK_RIGHT_CAN_ID, MotorType.kBrushless);
  
  public DoubleSolenoid shifter = new DoubleSolenoid(RobotMap.SHIFTER_ID_BACK, RobotMap.SHIFTER_ID_FORWARD);
  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ArcadeDrive());
  }

  /**
   * Controls the robot based on identified vision targets, orienting
   * itself and allowing forward and backward movement when a target
   * is identified, otherwise allow normal movement.
   */
  public void trackVision() {
    double margin = 5;
    if (Robot.limelight.hasTarget()) {
      double target = Robot.limelight.targetX();
      if (target < -margin) {
        spinLeft(0.23);
        spinRight(-0.23);
      } else if (target > margin) {
        spinLeft(-0.23);
        spinRight(0.23);
      } else {
        //if (Robot.oi.getLeftStickY() > 0.2 || Robot.oi.getLeftStickY() < 0.2) {
        spinLeft(0.4);
        spinRight(0.4);
        //} else {
        //  stop();
        //}
      }
    } else {
      arcadeDrive();
    }
  }

  /**
   * Drives the robot using the arcade drive style, where forward and
   * backward movement is controlled by the Y axis of the joystick
   * and rotation is controlled by the X axis.
   */
  public void arcadeDrive() {

    double rampRate = 0.2;
    int currentLimit = 30; //int because .setSmartCurrentLimit takes only ints, not doubles. Which makes sense

    frontRight.setOpenLoopRampRate(rampRate);
    frontRight.setSmartCurrentLimit(currentLimit);

    backLeft.setOpenLoopRampRate(rampRate);
    backLeft.setSmartCurrentLimit(currentLimit);

    frontLeft.setOpenLoopRampRate(rampRate);
    frontLeft.setSmartCurrentLimit(currentLimit);

    backRight.setOpenLoopRampRate(rampRate);
    backRight.setSmartCurrentLimit(currentLimit);

    double y = -Robot.oi.logitech.getRawAxis(1);
    double x = Robot.oi.logitech.getRawAxis(0);
 
    y = Math.pow(y, 3);
    x = 0.5 * Math.pow(x, 3);

    double left = y + x;
    double right = y - x;

    if (left > 1) {
      left = 1;
    } else if (left < -1) {
      left = -1;
    }

    if (right > 1) {
      right = 1;
    } else if (right < -1) {
      right = -1;
    }

    if (Math.abs(x) > 0.1 || Math.abs(y) > 0.1) {
      if (Robot.oi.getLogitechLeftTrigger() > 0.1) {
        spinLeft(left * 0.5);
        spinRight(right * 0.5);
      } else {
        spinLeft(left);
        spinRight(right);
      }
    } else {
      stopLeft();
      stopRight();
      }
      
      if (Robot.oi.l_leftBumper.get()) {
        shifter.set(DoubleSolenoid.Value.kForward); //WRITE WHICH GEAR THIS IS
      } else if (Robot.oi.l_rightBumper.get()) {
        shifter.set(DoubleSolenoid.Value.kReverse); //WRITE WHICH GEAR THIS IS
      }
    }

  /**
   * Spins the two left side motors of the robot's drive base.
   * @param speed a double in the range of -1 to 1
   */
  public void spinLeft(double speed) {
    frontLeft.set(-speed);
    backLeft.set(-speed);
  }

  /**
   * Stops the two left side motors of the robot's drive base.
   */
  public void stopLeft() {
    spinLeft(0);
  }

  /**
   * Spins the two right side motors of the robot's drive base.
   * 
   * @param speed a double in the range of -1 to 1
   */
  public void spinRight(double speed) {
    frontRight.set(speed);
    backRight.set(speed);
  }

  /**
   * Stops the two right side motors of the robot's drive base.
   */
  public void stopRight() {
    spinRight(0);
  }

  /**
   * Stops the four motors of the robot's drive base.
   */
  public void stop() {
    stopLeft();
    stopRight();
  }

}
