/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode; //added by Allison
import com.ctre.phoenix.motorcontrol.can.TalonSRX; //added by Allison
//import frc.robot.OI;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.CargoIntake;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.FrontLift;
import frc.robot.subsystems.HatchIntake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Wedges;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static DriveTrain driveTrain = new DriveTrain();
  public static Wedges wedges = new Wedges();
  public static FrontLift frontLift = new FrontLift();
  public static Climber climber = new Climber();
  public static HatchIntake hatchIntake = new HatchIntake();
  public static CargoIntake cargoIntake = new CargoIntake();
  public static Limelight limelight = new Limelight();

  public static Compressor compressor = new Compressor();

  UsbCamera camera1;
  UsbCamera camera2;

  VideoSink cameraServer;
  // public static DoubleSolenoid rightSolenoid = new DoubleSolenoid(0, 1);
  // public static DoubleSolenoid leftSolenoid = new DoubleSolenoid(2, 3);

  public static OI oi;
  // public static Spark blinkin = new Spark(0);

  // Command m_autonomousCommand;
  // SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    oi = new OI();
    limelight.ledOff();
    camera1 = CameraServer.getInstance().startAutomaticCapture(0);
    camera2 = CameraServer.getInstance().startAutomaticCapture(1);
    cameraServer = CameraServer.getInstance().getServer();

    compressor.setClosedLoopControl(false);
  }


  boolean prevTrigger = false;
  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    if (oi.stick.getTrigger() && !prevTrigger) {
      cameraServer.setSource(camera2);
    } else if (!oi.stick.getTrigger() && prevTrigger) {
      cameraServer.setSource(camera1);
    }
    prevTrigger = oi.stick.getTrigger();
  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
    limelight.ledOff();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString code to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    // m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
     * switch(autoSelected) { case "My Auto": autonomousCommand = new
     * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
     * ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    // if (m_autonomousCommand != null) {
    // m_autonomousCommand.start();
    // }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    // if (m_autonomousCommand != null) {
    // m_autonomousCommand.cancel();
    // }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    // blinkin.set(-0.95);
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */

  public double lastValue = 0;

  public DigitalInput liftZero = new DigitalInput(0);

  @Override
  public void testPeriodic() {

    if (lastValue != driveTrain.frontLeft.getSelectedSensorPosition()) {
      lastValue = driveTrain.frontLeft.getSelectedSensorPosition();
      System.out.println(lastValue);
    }
    if (!liftZero.get()) {
      //System.out.println("Bottom reached");
      driveTrain.frontLeft.setSelectedSensorPosition(0);
    }

    // System.out.println();
    /*
     * compressor.setClosedLoopControl(true); compressor.start(); if
     * (oi.stick.getTrigger()) { leftSolenoid.set(DoubleSolenoid.Value.kForward);
     * rightSolenoid.set(DoubleSolenoid.Value.kForward); } else {
     * leftSolenoid.set(DoubleSolenoid.Value.kReverse);
     * rightSolenoid.set(DoubleSolenoid.Value.kReverse); }
     */

    TalonSRX frontLift = new TalonSRX(RobotMap.FRONT_LEFT_CAN_ID);// change this when we plug more talons in - Allison
    // Another version of raw lifter control, hold b to activate, left gamepad joy
    // is speed
    double rawLiftSpeed = Functions.deadband(oi.getGamepadLeftY());
    if (oi.bButton.get()) {
      if (!liftZero.get()) {
        if (rawLiftSpeed < 0) {
          frontLift.set(ControlMode.PercentOutput, 0);
        } else {
          frontLift.set(ControlMode.PercentOutput, rawLiftSpeed);
        }
      } else {
        frontLift.set(ControlMode.PercentOutput, rawLiftSpeed);
      }
      // System.out.println("Left y is... " +
      // driveTrain.deadband(oi.getGamepadLeftY()));
      frontLift.set(ControlMode.PercentOutput, rawLiftSpeed);
    } else {
      frontLift.set(ControlMode.PercentOutput, 0);
    }

    if (oi.xButton.get()) {
      frontLift.set(ControlMode.Position, 1300);
    } else if (oi.aButton.get()) {
      frontLift.set(ControlMode.Position, 400);
    }

    /*
     * 
     * if (oi.aButton.get()) {// really rough code for lifter, y button up, a button
     * down - Allison frontLift.set(ControlMode.PercentOutput, 1); } else if
     * (oi.yButton.get()) { frontLift.set(ControlMode.PercentOutput, -1); } else {
     * frontLift.set(ControlMode.PercentOutput, 0); }
     */
  }
}
