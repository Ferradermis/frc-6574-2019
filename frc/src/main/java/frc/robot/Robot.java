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
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.CargoIntake;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.FrontLift;
import frc.robot.subsystems.HatchIntake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Wedges;
//import edu.wpi.first.wpilibj.Timer;


public class Robot extends TimedRobot {
  public static DriveTrain driveTrain = new DriveTrain();
  public static Wedges wedges = new Wedges();
  public static FrontLift frontLift = new FrontLift();
  public static Climber climber = new Climber();
  public static HatchIntake hatchIntake = new HatchIntake();
  public static CargoIntake cargoIntake = new CargoIntake();
  public static Limelight limelight = new Limelight();
  public static Compressor compressor = new Compressor();
  public static boolean  endgame = false;

  UsbCamera camera1;
  //UsbCamera camera2;

  VideoSink cameraServer;

  PowerDistributionPanel pdp = new PowerDistributionPanel();
  public static OI oi;
  public static Spark blinkin = new Spark(0);

  //public static Timer timer = new Timer();
  // Command m_autonomousCommand;
  // SendableChooser<Command> m_chooser = new SendableChooser<>();

  public static boolean getEndgame() {
    return endgame;
  }

  @Override
  public void robotInit() {
    oi = new OI(); // import all joystick buttons
    limelight.ledOff(); // turn the Limelight off to not blind people
    camera1 = CameraServer.getInstance().startAutomaticCapture(0); // start camera feed 1
    //camera2 = CameraServer.getInstance().startAutomaticCapture(1); // start camera feed 2
    cameraServer = CameraServer.getInstance().getServer();
    cameraServer.setSource(camera1);
    compressor.start(); //compressor init code
    compressor.setClosedLoopControl(true);

    endgame = false;
    cargoIntake.deployMotor.setSelectedSensorPosition(0);
  }

  public boolean prevTrigger = false;

  public double lastValueLift = 0;
  public double lastValueCargo = 0;
  public double lastValueWedge = 0;

  @Override
  public void robotPeriodic() {
    
    /*
    PDP CHANNELS ARE NOT ACCURATE, NUMBERS INPUT SO IT COMPILES. 
    DOESN'T HURT ANYTHING BUT NEED TO GET THESE ON THE PROPER CHANNELS
    We can use these to determine used current during normal operation for mechanisms
    that failed routinely due to stalling or running at high current for extended periods of time
    due to either user failure or poorly managed closed loop control
    */
    double totalCurrent = pdp.getTotalCurrent();
    double frontLiftCurrent = pdp.getCurrent(0);
    double wedgesCurrent = pdp.getCurrent(1);
    double cargoIntakeCurrent = pdp.getCurrent(2);
    double climbCurrent = pdp.getCurrent(3);
    double GPCurrent = pdp.getCurrent(4);

    SmartDashboard.putNumber("Total Current", totalCurrent);    
    SmartDashboard.putNumber("Front Lift Current", frontLiftCurrent);
    SmartDashboard.putNumber("Wedges Current", wedgesCurrent);
    SmartDashboard.putNumber("Cargo Intake Current", cargoIntakeCurrent);
    SmartDashboard.putNumber("Climbing Elevator Current", climbCurrent);
    SmartDashboard.putNumber("Game Piecer Current", GPCurrent);

    compressor.start();
    compressor.setClosedLoopControl(true);

    //It pains me, but for now it seems most efficient to keep this chunk here. 
    if (Robot.oi.x_startButton.get()) {
      endgame = true;
      Robot.cargoIntake.deployMotor.set(ControlMode.Position, 0);
      //frontLift.liftMotor.set(ControlMode.PercentOutput, 0);
    } else if (Robot.oi.x_backButton.get()) {
      endgame = false;
    }  

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
      
      
    }

  @Override
  public void disabledInit() {
    limelight.ledOff();
    compressor.stop();
    endgame = false;
    //cargoIntake.deployMotor.setSelectedSensorPosition(0);
    //hoping this just resets the game piecer to position zero upon disabling the robot instead of it drooping sadly
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    frontLift.liftMotor.set(ControlMode.PercentOutput, 0);
    frontLift.liftMotor.setSelectedSensorPosition(0);
    cargoIntake.deployMotor.setSelectedSensorPosition(0);
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() { 
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}
