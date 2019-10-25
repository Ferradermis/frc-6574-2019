/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

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
import edu.wpi.first.wpilibj.Timer;

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
  //UsbCamera camera2;

  VideoSink cameraServer;

  boolean endgame = false;
  
  public static OI oi;
  public static Spark blinkin = new Spark(0);

  //public static Timer timer = new Timer();

public double readCargoDegree() {
  return cargoIntake.deployMotor.getSelectedSensorPosition() / 10.75;
}

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
    //camera2 = CameraServer.getInstance().startAutomaticCapture(1); // start camera feed 2
    cameraServer = CameraServer.getInstance().getServer();

    cameraServer.setSource(camera1);

    compressor.start(); //compressor init code
    compressor.setClosedLoopControl(true);

    cargoIntake.deployMotor.config_kF(0, 0);
    cargoIntake.deployMotor.config_kP(0, 2.5);
    cargoIntake.deployMotor.config_kI(0, 0);
    cargoIntake.deployMotor.config_kD(0, 0);

<<<<<<< HEAD
<<<<<<< HEAD
    //timer.start();

    //Need time to test before adding
    //cargoIntake.deployMotor.configMotionCruiseVelocity(51 * 2 * 2 * 3 * 2 * 2);
    //cargoIntake.deployMotor.configMotionAcceleration(30 * 2 * 3 * 2 * 2);

    //frontLift.liftMotor.config_kF(0, 0.25); //1.8
    
    //frontLift.liftMotor.config_kF(0, 0);
    //frontLift.liftMotor.config_kP(0, 3);
    //frontLift.liftMotor.config_kI(0, 0);
    //frontLift.liftMotor.config_kD(0, 0);

    //frontLift.liftMotor.configAllowableClosedloopError(0, 25);

    //frontLift.liftMotor.setSelectedSensorPosition(0);

    driveTrain.frontRight.setOpenLoopRampRate(0.2);
    driveTrain.backLeft.setOpenLoopRampRate(0.2);
    driveTrain.frontLeft.setOpenLoopRampRate(0.2);
    driveTrain.backRight.setOpenLoopRampRate(0.2);
=======
=======
>>>>>>> parent of b361a68... Post-Milwaukee working copy
    frontLift.liftMotor.config_kF(0, 1.8); //1.8
    frontLift.liftMotor.config_kP(0, 0.000025);
    frontLift.liftMotor.config_kI(0, 0);
    frontLift.liftMotor.config_kD(0, 0);
    
    
    //frontLift.liftMotor.set(ControlMode.PercentOutput, 0);
<<<<<<< HEAD
>>>>>>> parent of b361a68... Post-Milwaukee working copy
=======
>>>>>>> parent of b361a68... Post-Milwaukee working copy

    endgame = false;

    //frontLift.liftMotor.configAllowableClosedloopError(1, 10);

    //frontLift.liftMotor.configOpenloopRamp(1);

    //frontLift.liftMotor.config_kP(1, 0.1);
    //frontLift.liftMotor.config_kI(1, 0.0);
    //frontLift.liftMotor.config_kD(1, 0.0);
    //frontLift.liftMotor.config_kF(1, 2.1);
  }

  boolean prevTrigger = false;

  public double lastValueLift = 0;
  public double lastValueCargo = 0;
  public double lastValueWedge = 0;

<<<<<<< HEAD
<<<<<<< HEAD
  public double lastCargoPosition = 0;
  public double targetLiftPosition = 0;

=======
>>>>>>> parent of b361a68... Post-Milwaukee working copy
=======
>>>>>>> parent of b361a68... Post-Milwaukee working copy
  public DigitalInput liftZero = new DigitalInput(0);
  public DigitalInput cargoZero = new DigitalInput(1);
  public DigitalInput climbTop = new DigitalInput(2);
  public DigitalInput liftTop = new DigitalInput(3);

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

    //double ff = getCargoDegrees()

    cargoIntake.deployMotor.config_kF(1, 1.752 * Math.cos(readCargoDegree()));

    if (oi.l_aButton.get()) {
      cargoIntake.deployMotor.set(ControlMode.Position, 0);
    } else if (oi.l_bButton.get()) {
      cargoIntake.deployMotor.set(ControlMode.Position, -968);
    }

    if (oi.l_xButton.get()) {
      frontLift.liftMotor.set(ControlMode.Position, 2000);
    } else if (oi.l_yButton.get()) {
      frontLift.liftMotor.set(ControlMode.Position, 5000);
    }

    /*if (oi.l_aButton.get()) {
      cargoIntake.deployMotor.setSelectedSensorPosition(0);
    }*/

    compressor.start();
    compressor.setClosedLoopControl(true);

    if (oi.x_startButton.get()) {
      endgame = true;
<<<<<<< HEAD
<<<<<<< HEAD
      cargoIntake.deployMotor.set(ControlMode.Position, 0);
      //frontLift.liftMotor.set(ControlMode.PercentOutput, 0);
=======
>>>>>>> parent of b361a68... Post-Milwaukee working copy
=======
>>>>>>> parent of b361a68... Post-Milwaukee working copy
    } else if (oi.x_backButton.get()) {
      endgame = false;
    }
      
    if (oi.l_leftBumper.get()) { // right joystick is shifting control
      driveTrain.shifter.set(DoubleSolenoid.Value.kForward);
    } else if (oi.l_rightBumper.get()) {
      driveTrain.shifter.set(DoubleSolenoid.Value.kReverse);
    }

    //if (!liftZero.get()) {
      //System.out.println("Bottom reached");
    //  frontLift.liftMotor.setSelectedSensorPosition(0);
    //}

    //System.out.println(climbTop.get());

    /*if (oi.x_leftBumper.get()) { // left joystick is camera feed control
      cameraServer.setSource(camera1);
    } else if (oi.x_rightBumper.get()) {
      cameraServer.setSource(camera2);
    }*/

<<<<<<< HEAD
<<<<<<< HEAD
    if (!liftZero.get()) {
      frontLift.liftMotor.setSelectedSensorPosition(0);
    }

    if (!cargoZero.get()) {
      cargoIntake.deployMotor.setSelectedSensorPosition(0);
      lastCargoPosition = 0;
    }
=======
>>>>>>> parent of b361a68... Post-Milwaukee working copy
=======
>>>>>>> parent of b361a68... Post-Milwaukee working copy
    
    //Encoder value printing

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


    /*
      ENDGAME v. MATCH OPERATOR CONTROLS
    */
    //Wedges, climber, emergency game piecer lifting
    if (endgame) {
<<<<<<< HEAD
<<<<<<< HEAD

      blinkin.set(-0.91); 

=======
>>>>>>> parent of b361a68... Post-Milwaukee working copy
=======
>>>>>>> parent of b361a68... Post-Milwaukee working copy
      driveTrain.shifter.set(DoubleSolenoid.Value.kReverse);
      cargoIntake.deployMotor.configOpenloopRamp(2);
      
      cargoIntake.deployMotor.configClosedloopRamp(2);
      
      //Wedge controls
<<<<<<< HEAD
<<<<<<< HEAD
      if (oi.x_aButton.get()) {
        //Need to find position
        wedges.wedgeMotor.set(ControlMode.PercentOutput, 0.7); //Wedge down
      } else if (oi.x_bButton.get()) {
        //Need to find position
=======
      if (oi.x_xButton.get()) {
        wedges.wedgeMotor.set(ControlMode.PercentOutput, 0.9); //Wedge down
      } else if (oi.x_aButton.get()) {
>>>>>>> parent of b361a68... Post-Milwaukee working copy
=======
      if (oi.x_xButton.get()) {
        wedges.wedgeMotor.set(ControlMode.PercentOutput, 0.9); //Wedge down
      } else if (oi.x_aButton.get()) {
>>>>>>> parent of b361a68... Post-Milwaukee working copy
        wedges.wedgeMotor.set(ControlMode.PercentOutput, -0.9); //Wedge up
      } else {
        wedges.wedgeMotor.set(ControlMode.PercentOutput, 0);
      }

      //Climber controls
      if (oi.getXboxRightTrigger() > 0.2) {
        climber.wheel.set(ControlMode.PercentOutput, -0.8);
      } else {
        climber.wheel.set(ControlMode.PercentOutput, 0);
      }

      if (oi.getXboxLeftY() > 0.2 && climbTop.get()) {
        climber.extender.set(ControlMode.PercentOutput, -oi.getXboxLeftY());
      } else if (oi.getXboxLeftY() < -0.2) {

        climber.extender.set(ControlMode.PercentOutput, -oi.getXboxLeftY());
      } else {
        climber.extender.set(ControlMode.PercentOutput, 0);
      }

      //Game piecer lifting
      /*if (oi.getXboxLeftTrigger() > 0.2) {
        cargoIntake.deployMotor.set(ControlMode.PercentOutput, 0.8);
      } else {
        cargoIntake.deployMotor.set(ControlMode.PercentOutput, 0);
      }*/
    //Front lift, game piecer
    } else {
<<<<<<< HEAD
<<<<<<< HEAD

      
      blinkin.set(-0.57); //-.91 (green/forest palette), -.67(party palette)

      if (Math.abs(oi.getXboxRightY()) > 0.2) {
        //lastCargoPosition = cargoIntake.deployMotor.getSelectedSensorPosition();
        cargoIntake.deployMotor.set(ControlMode.PercentOutput, oi.getXboxRightY());
      } else {
        if (oi.x_bButton.get()) {
          cargoIntake.deployMotor.set(ControlMode.Position, 0);
          lastCargoPosition = 0;
        } else if (oi.x_xButton.get()) { //x
          cargoIntake.deployMotor.set(ControlMode.Position, -750);
          lastCargoPosition = -750;
        } else if (oi.x_aButton.get()) { //a
          cargoIntake.deployMotor.set(ControlMode.Position, -950);
          lastCargoPosition = -950;
        } else {
          cargoIntake.deployMotor.set(ControlMode.Position, lastCargoPosition);
        }
      }
=======
      cargoIntake.deployMotor.configOpenloopRamp(0);
>>>>>>> parent of b361a68... Post-Milwaukee working copy
=======
      cargoIntake.deployMotor.configOpenloopRamp(0);
>>>>>>> parent of b361a68... Post-Milwaukee working copy
      
      cargoIntake.deployMotor.configClosedloopRamp(0);
      //Front lift controls
      /*if (oi.getXboxRightY() > 0.2) {
        System.out.println(oi.getXboxRightY());
        frontLift.liftMotor.set(ControlMode.PercentOutput, oi.getXboxRightY() * 0.85 + 0.15);
      } else if (oi.getXboxRightY() < -0.2) {
        frontLift.liftMotor.set(ControlMode.PercentOutput, -0.25);
      } else {
        frontLift.liftMotor.set(ControlMode.PercentOutput, 0.15); //0.21
      }*/

<<<<<<< HEAD
<<<<<<< HEAD
      /* PUT THIS BACK FOR MATCH
      */
      if (oi.getXboxLeftY() > 0.2 && liftTop.get()) {
        /*System.out.println("Going up");
        System.out.println("Stick: " + oi.getXboxLeftY());
        System.out.println("Sensor: " + frontLift.liftMotor.getSelectedSensorPosition());
        System.out.println("New target: " + (frontLift.liftMotor.getSelectedSensorPosition() + oi.getXboxLeftY() * 300));
        frontLift.liftMotor.config_kF(0, 0.25);
        frontLift.liftMotor.set(ControlMode.Position, frontLift.liftMotor.getSelectedSensorPosition() + oi.getXboxLeftY() * 300);*/
        frontLift.liftMotor.set(ControlMode.PercentOutput, oi.getXboxLeftY() * 0.85 + 0.15);
      } else if (oi.getXboxLeftY() < -0.2 && liftZero.get()) {
        /*System.out.println("Going down");
        System.out.println("Stick: " + oi.getXboxLeftY());
        System.out.println("Sensor: " + frontLift.liftMotor.getSelectedSensorPosition());
        System.out.println("New target: " + (frontLift.liftMotor.getSelectedSensorPosition() + oi.getXboxLeftY() * 300));
        frontLift.liftMotor.config_kF(0, -0.25);
        frontLift.liftMotor.set(ControlMode.Position, frontLift.liftMotor.getSelectedSensorPosition() + oi.getXboxLeftY() * 300);*/
        frontLift.liftMotor.set(ControlMode.PercentOutput, oi.getXboxLeftY() * 0.5);
      } else {
        frontLift.liftMotor.set(ControlMode.PercentOutput, 0.15);
      }



      //Game piecer controls
      
      if (oi.logitech.getRawButton(7)) {
        cargoIntake.deployMotor.setSelectedSensorPosition(0);
      }

      if (Math.abs(oi.getXboxLeftTrigger()) > 0.2) {
        cargoIntake.spinMotor.set(ControlMode.PercentOutput, 0.5);
      } else if (Math.abs(oi.getXboxRightTrigger()) > 0.2) {
        cargoIntake.spinMotor.set(ControlMode.PercentOutput, -1);
=======
      /*
      if (oi.getXboxRightY() > 0.2 && liftTop.get()) {
        frontLift.liftMotor.set(ControlMode.PercentOutput, oi.getXboxRightY() * 0.85 + 0.15);
      } else if (oi.getXboxRightY() < -0.2) {
        frontLift.liftMotor.set(ControlMode.PercentOutput, -0.25);
=======
      /*
      if (oi.getXboxRightY() > 0.2 && liftTop.get()) {
        frontLift.liftMotor.set(ControlMode.PercentOutput, oi.getXboxRightY() * 0.85 + 0.15);
      } else if (oi.getXboxRightY() < -0.2) {
        frontLift.liftMotor.set(ControlMode.PercentOutput, -0.25);
      } else {
        frontLift.liftMotor.set(ControlMode.PercentOutput, 0.15);
      }*/

      //Game piecer controls
      
      if (oi.getXboxLeftTrigger() > 0.2) {
        cargoIntake.spinMotor.set(ControlMode.PercentOutput, 0.5 * oi.getXboxLeftTrigger());
      } else if (oi.getXboxRightTrigger() > 0.2) {
        cargoIntake.spinMotor.set(ControlMode.PercentOutput, -oi.getXboxRightTrigger());
>>>>>>> parent of b361a68... Post-Milwaukee working copy
      } else {
        frontLift.liftMotor.set(ControlMode.PercentOutput, 0.15);
      }*/

<<<<<<< HEAD
      //Game piecer controls
      
      if (oi.getXboxLeftTrigger() > 0.2) {
        cargoIntake.spinMotor.set(ControlMode.PercentOutput, 0.5 * oi.getXboxLeftTrigger());
      } else if (oi.getXboxRightTrigger() > 0.2) {
        cargoIntake.spinMotor.set(ControlMode.PercentOutput, -oi.getXboxRightTrigger());
>>>>>>> parent of b361a68... Post-Milwaukee working copy
      } else {
        cargoIntake.spinMotor.set(ControlMode.PercentOutput, 0);
      }

=======
>>>>>>> parent of b361a68... Post-Milwaukee working copy
      //System.out.println(cargoIntake.deployMotor.getMotorOutputPercent());

      /*if (Math.abs(oi.getXboxLeftY()) > 0.2) {
        cargoIntake.deployMotor.set(ControlMode.PercentOutput, -oi.getXboxLeftY() * 0.5);
      } else {
        cargoIntake.deployMotor.set(ControlMode.PercentOutput, 0);
      }*/
    }

    /*if (oi.stick.getTrigger() && !prevTrigger) {
      cameraServer.setSource(camera2);
    } else if (!oi.stick.getTrigger() && prevTrigger) {
      cameraServer.setSource(camera1);
    }
    prevTrigger = oi.stick.getTrigger();


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
    endgame = false;
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
    frontLift.liftMotor.set(ControlMode.PercentOutput, 0);
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
    //frontLift.liftMotor.set(ControlMode.PercentOutput, 0);
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
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */

  @Override
  public void testPeriodic() {

  }

}
