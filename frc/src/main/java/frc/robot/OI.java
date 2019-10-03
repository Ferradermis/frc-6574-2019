/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.commands.VisionTrack;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public CANSparkMax frontLeft = new CANSparkMax(RobotMap.FRONT_LEFT_CAN_ID, MotorType.kBrushless);
  public CANSparkMax backLeft = new CANSparkMax(RobotMap.BACK_LEFT_CAN_ID, MotorType.kBrushless);
  public CANSparkMax frontRight = new CANSparkMax(RobotMap.FRONT_RIGHT_CAN_ID, MotorType.kBrushless);
  public CANSparkMax backRight = new CANSparkMax(RobotMap.BACK_RIGHT_CAN_ID, MotorType.kBrushless);

  public Joystick logitech = new Joystick(0);
  public Joystick xbox = new Joystick(1);

  // Logitech Variables
  public Button l_xButton = new JoystickButton(logitech, 3);
  public Button l_aButton = new JoystickButton(logitech, 1);
  public Button l_bButton = new JoystickButton(logitech, 2);
  public Button l_yButton = new JoystickButton(logitech, 4);
  public Button l_leftBumper = new JoystickButton(logitech, 5);
  public Button l_rightBumper = new JoystickButton(logitech, 6);
  //public Button l_leftTrigger = new JoystickButton(logitech, 7);
  public Button l_backButton = new JoystickButton(logitech, 7);
  //public Button l_startButton = new JoystickButton(logitech, 8);
  public Button l_rightTrigger = new JoystickButton(logitech, 8);
  //public Button l_backButton = new JoystickButton(logitech, 9);
  //public Button l_startButton = new JoystickButton(logitech, 10);
  public POVButton l_upDpad = new POVButton(logitech, 0);
  public POVButton l_rightDpad = new POVButton(logitech, 90);
  public POVButton l_downDpad = new POVButton(logitech, 180);
  public POVButton l_leftDpad = new POVButton(logitech, 270);

  public double getLogitechLeftX() {
    return -logitech.getRawAxis(0);
  }

  public double getLogitechLeftY() {
    return -logitech.getRawAxis(1);
  }

  public double getLogitechRightX() {
    return -logitech.getRawAxis(2);
  }

  public double getLogitechRightY() {
    return -logitech.getRawAxis(5);
  }

  public double getLogitechLeftTrigger() {
    return logitech.getRawAxis(2);
  }

  public double getLogitechRightTrigger() {
    return logitech.getRawAxis(3);
  }

  public boolean getLogitechLeftBumper() {
    return xbox.getRawButton(5);
  }

  public boolean getLogitechRightBumper() {
    return xbox.getRawButton(6);
  }

  // Xbox Variables
  public Button x_xButton = new JoystickButton(xbox, 3);
  public Button x_aButton = new JoystickButton(xbox, 1);
  public Button x_bButton = new JoystickButton(xbox, 2);
  public Button x_yButton = new JoystickButton(xbox, 4);
  public Button x_leftBumper = new JoystickButton(xbox, 5);
  public Button x_rightBumper = new JoystickButton(xbox, 6);
  public Button x_backButton = new JoystickButton(xbox, 7);
  public Button x_startButton = new JoystickButton(xbox, 8);
  //public Button x_leftTrigger = new JoystickButton(xbox, 7);
  //public Button x_rightTrigger = new JoystickButton(xbox, 8);
  //public Button x_backButton = new JoystickButton(xbox, 9);
  //public Button x_startButton = new JoystickButton(xbox, 10);
  public POVButton x_upDpad = new POVButton(xbox, 0);
  public POVButton x_rightDpad = new POVButton(xbox, 90);
  public POVButton x_downDpad = new POVButton(xbox, 180);
  public POVButton x_leftDpad = new POVButton(xbox, 270);

  public double getXboxLeftX() {
    return -xbox.getRawAxis(0);
  }

  public double getXboxLeftY() {
    return -xbox.getRawAxis(1);
  }

  public double getXboxRightX() {
    return -xbox.getRawAxis(2);
  }

  public double getXboxRightY() {
    return -xbox.getRawAxis(5);
  }

  public double getXboxLeftTrigger() {
    return xbox.getRawAxis(2);
  }

  public double getXboxRightTrigger() {
    return xbox.getRawAxis(3);
  }

  public boolean getXboxLeftBumper() {
    return xbox.getRawButton(5);
  }

  public boolean getXboxRightBumper() {
    return xbox.getRawButton(6);
  }

  public OI() {
    
    l_rightTrigger.whileHeld(new VisionTrack());
    //leftStickButton3.whenPressed(new Shift());
  }

}
