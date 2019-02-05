/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The extending mechanism used to collect hatch panels.
 */
public class HatchIntake extends Subsystem {
  //Solenoid extender = new Solenoid(RobotMap.HATCH_EXTENDER_ID);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void extend() {
    //extender.set(true);
  }

  public void retract() {
    //extender.set(false);
  }

}
