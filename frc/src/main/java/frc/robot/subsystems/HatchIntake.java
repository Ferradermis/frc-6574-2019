/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ManipulateHatch;

//Something is off here, pretty sure we either don't need the extend() and retract() methods or should rework hatchInOut()

/**
 * The extending mechanism used to collect hatch panels.
 */
public class HatchIntake extends Subsystem {
  //Solenoid extender = new Solenoid(RobotMap.HATCH_EXTENDER_ID);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ManipulateHatch());
  }

  public void hatchInOut() {
    if (Robot.oi.getOperatorLeftBumper()) {
      //extender.set(true);
    } else if (Robot.oi.getOperatorRightBumper()) {
      //extender.set(false);
    }
  }
  public void extend() {
    //extender.set(true);
  }

  public void retract() {
    //extender.set(false);
  }

}
