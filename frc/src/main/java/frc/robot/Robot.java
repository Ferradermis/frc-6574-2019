/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import javax.print.attribute.standard.PrinterName;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
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
  public static boolean toggleOn = false;
  public static boolean togglePressed = false;

  UsbCamera camera1;
  UsbCamera camera2;

  VideoSink cameraServer;
  

  public static OI oi;
  public static Spark blinkin = new Spark(0);

  // Command m_autonomousCommand;
  // SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    oi = new OI(); // import all joystick buttons
    limelight.ledOff(); // turn the lime_light off to not blind people
    camera1 = CameraServer.getInstance().startAutomaticCapture(0); // start camera feed 1
    camera2 = CameraServer.getInstance().startAutomaticCapture(1); // start camera feed 2
    cameraServer = CameraServer.getInstance().getServer();

    compressor.start(); //compressor init code
    compressor.setClosedLoopControl(true);
  }


  boolean prevTrigger = false;

  public double lastValueLift = 0;
  public double lastValueCargo = 0;
  public double lastValueWedge = 0;

  public DigitalInput liftZero = new DigitalInput(0);
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

    compressor.start();
    compressor.setClosedLoopControl(true);

    if (oi.rightStickTrigger.get()) { // right joystick is shifting control
      driveTrain.shifter.set(DoubleSolenoid.Value.kForward);
    } else if (oi.rightStickSideButton.get()) {
      driveTrain.shifter.set(DoubleSolenoid.Value.kReverse);
    }

    if (oi.leftStickTrigger.get()) { // left joystick is camera feed control
      cameraServer.setSource(camera1);
    } else if (oi.leftStickSideButton.get()) {
      cameraServer.setSource(camera2);
    }

    if (lastValueLift != frontLift.liftMotor.getSelectedSensorPosition()) {
      lastValueLift = frontLift.liftMotor.getSelectedSensorPosition();
      System.out.println("Lift: " + lastValueLift);
    }
    if (lastValueCargo != cargoIntake.deployMotor.getSelectedSensorPosition()) {
      lastValueCargo = cargoIntake.deployMotor.getSelectedSensorPosition();
      System.out.println("Cargo: " + lastValueCargo);
    }
    if (lastValueWedge != wedges.wedgeMotor.getSelectedSensorPosition()) {
      lastValueWedge = wedges.wedgeMotor.getSelectedSensorPosition();
      System.out.println("Wedge: " + lastValueWedge);
    }
    if (!liftZero.get()) {
      //System.out.println("Bottom reached");
      frontLift.liftMotor.setSelectedSensorPosition(0);
    }

    double rawLiftSpeed = Functions.deadband(oi.getGamepadLeftY());
    double rawClimberSpeed = Functions.deadband(oi.getGamepadRightY());
    if (oi.bButton.get()) {
      if (!liftZero.get()) {
        if (rawLiftSpeed < 0) {
          frontLift.liftMotor.set(ControlMode.PercentOutput, 0);
        } else {
          frontLift.liftMotor.set(ControlMode.PercentOutput, rawLiftSpeed);
        }
      } else {
        if (frontLift.liftMotor.getSelectedSensorPosition() < 26000) {
          frontLift.liftMotor.set(ControlMode.PercentOutput, rawLiftSpeed);
        }
      }
      frontLift.liftMotor.set(ControlMode.PercentOutput, rawLiftSpeed);
      System.out.println(rawClimberSpeed);
      climber.extender.set(ControlMode.PercentOutput, rawClimberSpeed * 0.85);
    } else {
      frontLift.liftMotor.set(ControlMode.PercentOutput, 0);
      climber.extender.set(ControlMode.PercentOutput, 0);
    }

    if (oi.xButton.get()) {
      wedges.wedgeMotor.set(ControlMode.PercentOutput, 0.9);// needs to be .85 - Allison
      //frontLift.liftMotor.set(ControlMode.Position, 1300);
      //cargoIntake.spin(-0.3);
    } else if (oi.aButton.get()) {
      wedges.wedgeMotor.set(ControlMode.PercentOutput, -0.9);
      //frontLift.liftMotor.set(ControlMode.Position, 400);
      //cargoIntake.spin(0.3);
    } else {
      wedges.wedgeMotor.set(ControlMode.PercentOutput, 0);
      //cargoIntake.spin(0);
    }

    if (oi.yButton.get()) {
      climber.wheel.set(ControlMode.PercentOutput, -0.8);
    } else {
      climber.wheel.set(ControlMode.PercentOutput, 0);
    }

    //if (oi.gamepad.get)

    /*if (oi.stick.getTrigger() && !prevTrigger) {
      cameraServer.setSource(camera2);
    } else if (!oi.stick.getTrigger() && prevTrigger) {
      cameraServer.setSource(camera1);
    }
    prevTrigger = oi.stick.getTrigger();

    if (oi.stick.getRawButton(7)) {
      blinkin.set(-0.87); //confetti
    } else if (oi.stick.getRawButton(8)) {
      blinkin.set(-0.25); //red
    } else if (oi.stick.getRawButton(9)) {
      blinkin.set(-0.23); //blue
    } else if (oi.stick.getRawButton(10)) {
      blinkin.set(0.53);
    }*/
  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
    limelight.ledOff();
    compressor.stop();
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

  @Override
  public void testPeriodic() {
    
    updateToggle();
    
    /*if (oi.rightStickTrigger.get()) { // right joystick is shifting control
      driveTrain.shifter.set(DoubleSolenoid.Value.kForward);
      pressed = 
      System.out.println("pressed: " +pressed);
    } else if (oi.rightStickSideButton.get()) {
      driveTrain.shifter.set(DoubleSolenoid.Value.kReverse);
    }*/

    if(toggleOn){
      driveTrain.shifter.set(DoubleSolenoid.Value.kForward);
      System.out.println("toggle on");
  }
  else
  {
    driveTrain.shifter.set(DoubleSolenoid.Value.kReverse);
    System.out.println("toggle off");   
  }   

  }

    public void updateToggle()
    {
    if(oi.rightStickTrigger.get()){
        if(!togglePressed)
        {
            toggleOn = !toggleOn;
            togglePressed = true;
         
        }
        else if(togglePressed)
        {
          
            togglePressed = false;
  
        }
        try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
   
  

    // System.out.println();
    /*
     * compressor.setClosedLoopControl(true); compressor.start(); if
     * (oi.stick.getTrigger()) { leftSolenoid.set(DoubleSolenoid.Value.kForward);
     * rightSolenoid.set(DoubleSolenoid.Value.kForward); } else {
     * leftSolenoid.set(DoubleSolenoid.Value.kReverse);
     * rightSolenoid.set(DoubleSolenoid.Value.kReverse); }
     */

    // Another version of raw lifter control, hold b to activate, left gamepad joy
    // is speed

    /*
    double val = Functions.deadband(oi.getGamepadRightY());
    if (val != 0) {
      climber.wheel.set(ControlMode.PercentOutput, val);
    } else {
      climber.wheel.set(ControlMode.PercentOutput, 0);
    }*/

    /*
     * 
     * if (oi.aButton.get()) {// really rough code for lifter, y button up, a button
     * down - Allison frontLift.set(ControlMode.PercentOutput, 1); } else if
     * (oi.yButton.get()) { frontLift.set(ControlMode.PercentOutput, -1); } else {
     * frontLift.set(ControlMode.PercentOutput, 0); }
     */
  }
}
