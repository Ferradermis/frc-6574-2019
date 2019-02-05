/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
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
  //public static DoubleSolenoid rightSolenoid = new DoubleSolenoid(0, 1);
  //public static DoubleSolenoid leftSolenoid = new DoubleSolenoid(2, 3);

  public static OI oi;
  //public static Spark blinkin = new Spark(0);

  //Command m_autonomousCommand;
  //SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    oi = new OI();
    limelight.ledOff();
   
    /*
    //System.out.println("I N I T");

    //UsbCamera camera = new UsbCamera("USB Camera 5", 1);
    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    //camera.setPixelFormat(PixelFormat.kMJPEG);
    //CameraServer.getInstance().addCamera(camera);
    //UsbCamera backward = CameraServer.getInstance().startAutomaticCapture(RobotMap.camera.BACKWARD_USB_NUM);
    camera.setResolution(160, 120);
    //backward.setResolution(640, 480);
    CvSink forwardSink = CameraServer.getInstance().getVideo();
    //CvSink backwardSink = CameraServer.getInstance().getVideo(backward);
    CvSource outputStream = CameraServer.getInstance().putVideo("Working Camera Feed", 160, 120);

    Mat in = new Mat();
    Mat out = new Mat();

    new Thread(() -> {
      while (!Thread.interrupted()) {
        forwardSink.grabFrame(in);
        Imgproc.blur(in, out, new Size(4, 4));
        outputStream.putFrame(out);
      }
    }).start();
*/
    //new Thread(() -> {
      //while (Thread.interrupted()) {
        //Mat image = new Mat();
        //Mat output = new Mat();
        //forwardSink.grabFrame(image);
        //output = image.clone();
        //outputStream.putFrame(image);
      //}
      //System.out.println("It works here");
		//	while (!Thread.interrupted()) {
				//if (frontCamera) {
          //while(true) {
          //System.out.println(forwardSink.grabFrame(image));
          //output = image;
          //Imgproc.cvtColor(image, output, Imgproc.COLOR_BGR2GRAY);
          //outputStream.putFrame(output);
          //try {
          //Thread.sleep(100);
          //} catch (Exception e) {

          //}
        //}
				//} else {
				//	backwardSink.grabFrame(image);
				//	outputStream.putFrame(image);
				//}
		//	}
		//}).start();

    //CameraServer.getInstance().startAutomaticCapture();
    /*new Thread(() -> {
      CameraServer.getInstance().startAutomaticCapture();
      CvSink cvSink = CameraServer.getInstance().getVideo();
      CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 160, 120);  
    }).start();*/
    //new Thread(() -> {
      

      /*UsbCamera usbCamera = new UsbCamera("USB 1", 0);
      CvSink feed = new CvSink("USB 1 Sink");
      feed.setSource(usbCamera);
      CvSource output = new CvSource("USB 1 Source", PixelFormat.kMJPEG, 160, 120, 30);
      MjpegServer server = new MjpegServer("USB 1 Server", 1181);*/
    //}).start();
    /*UsbCamera usbCamera = new UsbCamera("USB Camera 0", 0);
    MjpegServer mjpegServer1 = new MjpegServer("serve_USB Camera 0", 1181);
    mjpegServer1.setSource(usbCamera); 

    CvSink cvSink = new CvSink("opencv_USB Camera 0");
    cvSink.setSource(usbCamera);
    CvSource outputStream = new CvSource("Blur", PixelFormat.kMJPEG, 640, 480, 30);
    
    MjpegServer mjpegServer2 = new MjpegServer("serve_Blur", 1182);
    mjpegServer2.setSource(outputStream);*/
    //m_chooser.addDefault("Default Auto", new ExampleCommand());
    // chooser.addObject("My Auto", new MyAutoCommand());
    //SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
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
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    //m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    //if (m_autonomousCommand != null) {
    // m_autonomousCommand.start();
    //}
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
    //if (m_autonomousCommand != null) {
    //  m_autonomousCommand.cancel();
    //}
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    //blinkin.set(-0.95);
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    //System.out.println();
    /*compressor.setClosedLoopControl(true);
    compressor.start();
    if (oi.stick.getTrigger()) {
      leftSolenoid.set(DoubleSolenoid.Value.kForward);
      rightSolenoid.set(DoubleSolenoid.Value.kForward);
    } else {
      leftSolenoid.set(DoubleSolenoid.Value.kReverse);
      rightSolenoid.set(DoubleSolenoid.Value.kReverse);
    }*/
  }
} 
