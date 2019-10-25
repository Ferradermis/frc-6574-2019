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
import frc.robot.OI;
import frc.robot.commands.ArcadeDrive;

/**
 * The motors that control the drive base of the robot.
 */
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

  public void controllerDrive() {
    /*if (Robot.oi.getLeftStick() > 0.2) {
      frontLeft.set(ControlMode.PercentOutput, Functions.map(Robot.oi.getLeftStick(), 0.2, 1, 0, 1));
      backLeft.set(ControlMode.PercentOutput, Functions.map(Robot.oi.getLeftStick(), 0.2, 1, 0, 1));
    } else if (Robot.oi.getLeftStick() < -0.2) {
      frontLeft.set(ControlMode.PercentOutput, Functions.map(Robot.oi.getLeftStick(), -0.2, -1, 0, -1));
      backLeft.set(ControlMode.PercentOutput, Functions.map(Robot.oi.getLeftStick(), -0.2, -1, 0, -1));
    } else {
      frontLeft.set(ControlMode.PercentOutput, 0);
      backLeft.set(ControlMode.PercentOutput, 0);
    }
    if (Robot.oi.getRightStick() > 0.2) {
      frontRight.set(ControlMode.PercentOutput, Functions.map(Robot.oi.getRightStick(), 0.2, 1, 0, 1));
      backRight.set(ControlMode.PercentOutput, Functions.map(Robot.oi.getRightStick(), 0.2, 1, 0, 1));
    } else if (Robot.oi.getRightStick() < -0.2) {
      frontRight.set(ControlMode.PercentOutput, Functions.map(Robot.oi.getRightStick(), -0.2, -1, 0, -1));
      backRight.set(ControlMode.PercentOutput, Functions.map(Robot.oi.getRightStick(), -0.2, -1, 0, -1));
    } else {
      frontRight.set(ControlMode.PercentOutput, 0);
      backRight.set(ControlMode.PercentOutput, 0);
    }*/
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

<<<<<<< HEAD
<<<<<<< HEAD
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
    }

    /*
=======
>>>>>>> parent of b361a68... Post-Milwaukee working copy
=======
>>>>>>> parent of b361a68... Post-Milwaukee working copy
    double left = Robot.oi.getLogitechLeftY();
    double right = Robot.oi.getLogitechRightY();
    double lt = Robot.oi.getLogitechLeftTrigger();
    double rt = Robot.oi.getLogitechRightTrigger();

    if (lt > 0.2) {
      lt = 0.5;
    } else {
      lt = 1;
    }


    if (Robot.oi.l_xButton.get()) {
      spinLeft(Robot.oi.getLogitechLeftY());
      spinRight(Robot.oi.getLogitechLeftY());
    } else {
    if (left > 0.2) {
      spinLeft((left * 0.7 + rt * 0.3) * lt);
    } else if (left < -0.2) {
      spinLeft((left * 0.7 - rt * 0.3) * lt);
    } else {
      stopLeft();
    }

    if (right > 0.2) {
      spinRight((right * 0.7 + rt * 0.3) * lt);
    } else if (right < -0.2) {
      spinRight((right * 0.7 - rt * 0.3) * lt);
    } else {
      stopRight();
    }
  }

    /*
    double left = -Robot.oi.leftStick.getY();
    double right = -Robot.oi.rightStick.getY();
    
    if (Math.abs(left) > 0.2) {
      spinLeft(left);
    } else {
      stopLeft();
    }

    if (Math.abs(right) > 0.2) {
      spinRight(right);
    } else {
      stopRight();
    }*/

    /*
    double y = -Robot.oi.stick.getLeftStickY();
    double twist = Robot.oi.stick.getZ();
    if (y > 0.2 || y < -0.2) {
      spinLeft(y);
      spinRight(y);
    } else {
      if (twist < -0.2 || twist > 0.2) {
        spinLeft(twist * 0.4);
        spinRight(-twist * 0.4);
      } else {
        stop();
      }
    }*/


    /*double throt = Functions.deadband(Robot.oi.getLeftStickY() * 0.7);
    double turn = Functions.deadband(Robot.oi.getX() * 0.3);

    spinLeft(throt + turn);
    spinRight(throt - turn);

    System.out.println("Left: " + (throt + turn) + " Right: " + (throt - turn));*/
    //if (throt > 0) {
      //if (throt < 0)
       // turn = -turn;
      //spinLeft(turn + throt);
      //spinRight(-turn + throt);
    /*} else if (throt < 0) {
      turn = -turn;
      frontLeft.set(ControlMode.PercentOutput, turn + throt);
      backLeft.set(ControlMode.PercentOutput, turn + throt);
      frontRight.set(ControlMode.PercentOutput, turn - throt);
      backRight.set(ControlMode.PercentOutput, turn - throt);
    } else {
      frontLeft.set(ControlMode.PercentOutput, 0);
      backLeft.set(ControlMode.PercentOutput, 0);
      frontRight.set(ControlMode.PercentOutput, 0);
      backRight.set(ControlMode.PercentOutput, 0);
    }*/


    
    /*if (Math.abs(Robot.oi.getX()) > 0.1 || Math.abs(Robot.oi.getLeftStickY()) > 0.1) {
      frontLeft.set(ControlMode.PercentOutput, Robot.oi.getLeftStickY() - Robot.oi.getX());
      backLeft.set(ControlMode.PercentOutput, Robot.oi.getLeftStickY() - Robot.oi.getX());
      frontRight.set(ControlMode.PercentOutput, Robot.oi.getLeftStickY() + Robot.oi.getX());
      backRight.set(ControlMode.PercentOutput, Robot.oi.getLeftStickY() + Robot.oi.getX());
    } else {
      frontLeft.set(ControlMode.PercentOutput, 0);
      backLeft.set(ControlMode.PercentOutput, 0);
      frontRight.set(ControlMode.PercentOutput, 0);
      backRight.set(ControlMode.PercentOutput, 0);
    }
  }*/

  /**
   * Spins the two left side motors of the robot's drive base.
   * 
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
