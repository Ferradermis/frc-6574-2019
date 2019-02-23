/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.commands.Shift;
import frc.robot.commands.VisionTrack;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  //assign all joysticks to names
  public Joystick leftStick = new Joystick(0);
  public Joystick rightStick = new Joystick(1);
  public Joystick gamepad = new Joystick(2);


  // Left Stick Variables
  public Button leftStickTrigger = new JoystickButton(leftStick, 1);
  public Button leftStickSideButton = new JoystickButton(leftStick, 2);
  public Button leftStickButton3 = new JoystickButton(leftStick, 3);
  public Button leftStickButton4 = new JoystickButton(leftStick, 4);
  public Button leftStickButton5 = new JoystickButton(leftStick, 5);
  public Button leftStickButton6 = new JoystickButton(leftStick, 6);
  public Button leftStickButton7 = new JoystickButton(leftStick, 7);
  public Button leftStickButton8 = new JoystickButton(leftStick, 8);
  public Button leftStickButton9 = new JoystickButton(leftStick, 9);
  public Button leftStickButton10 = new JoystickButton(leftStick, 10);
  public Button leftStickButton11 = new JoystickButton(leftStick, 11);
  public Button leftStickButton12 = new JoystickButton(leftStick, 12);

  public double getLeftStickX() {
    return -leftStick.getRawAxis(0);
  }

  public double getLeftStickY() {
    return -leftStick.getRawAxis(1);
  }

  // Right Stick Variables
  public Button rightStickTrigger = new JoystickButton(rightStick, 1);
  public Button rightStickSideButton = new JoystickButton(rightStick, 2);
  public Button rightStickButton3 = new JoystickButton(rightStick, 3);
  public Button rightStickButton4 = new JoystickButton(rightStick, 4);
  public Button rightStickButton5 = new JoystickButton(rightStick, 5);
  public Button rightStickButton6 = new JoystickButton(rightStick, 6);
  public Button rightStickButton7 = new JoystickButton(rightStick, 7);
  public Button rightStickButton8 = new JoystickButton(rightStick, 8);
  public Button rightStickButton9 = new JoystickButton(rightStick, 9);
  public Button rightStickButton10 = new JoystickButton(rightStick, 10);
  public Button rightStickButton11 = new JoystickButton(rightStick, 11);
  public Button rightStickButton12 = new JoystickButton(rightStick, 12);

  public double getRightStickX() {
    return -rightStick.getRawAxis(0);
  }

  public double getRightStickY() {
    return -rightStick.getRawAxis(1);
  }






  // Gamepad Variables
  public Button xButton = new JoystickButton(gamepad, 1);
  public Button aButton = new JoystickButton(gamepad, 2);
  public Button bButton = new JoystickButton(gamepad, 3);
  public Button yButton = new JoystickButton(gamepad, 4);
  public Button leftBumper = new JoystickButton(gamepad, 5);
  public Button rightBumper = new JoystickButton(gamepad, 6);
  public Button leftTrigger = new JoystickButton(gamepad, 7);
  public Button rightTrigger = new JoystickButton(gamepad, 8);
  public Button backButton = new JoystickButton(gamepad, 9);
  public Button startButton = new JoystickButton(gamepad, 10);
  public POVButton upDpad = new POVButton(gamepad, 0);
  public POVButton rightDpad = new POVButton(gamepad, 90);
  public POVButton downDpad = new POVButton(gamepad, 180);
  public POVButton leftDpad = new POVButton(gamepad, 270);

  public double getGamepadLeftX() {
    return -gamepad.getRawAxis(0);
  }

  public double getGamepadLeftY() {
    return -gamepad.getRawAxis(1);
  }

  public double getGamepadRightX() {
    return -gamepad.getRawAxis(2);
  }

  public double getGamepadRightY() {
    return -gamepad.getRawAxis(3);
  }


  public OI() {
    leftStickTrigger.whileHeld(new VisionTrack());
    leftStickButton3.whenPressed(new Shift());
  }

  // Button button = new JoystickButton(leftStick, 0);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
