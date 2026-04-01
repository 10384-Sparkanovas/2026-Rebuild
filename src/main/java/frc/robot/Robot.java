// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.Degrees;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.generated.Constants.limelightConstants;


public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  
  private final RobotContainer m_robotContainer;

  public Robot() {
    m_robotContainer = new RobotContainer();
  }

  
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run(); 
    
    LimelightHelpers.SetRobotOrientation("limelight-novas", m_robotContainer.drivetrain.getRotation3d().getMeasureZ().in(Degrees), 0, 0, 0, 0, 0);

    LimelightHelpers.PoseEstimate mt2 = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2("limelight-novas");  
    m_robotContainer.drivetrain.addVisionMeasurement(mt2.pose, mt2.timestampSeconds);

    var combinedPosition = m_robotContainer.drivetrain.samplePoseAt(Timer.getFPGATimestamp());
    System.out.println(combinedPosition);
    if (combinedPosition.isPresent()){
      var angleToHub = limelightConstants.blueHubPosition.minus(combinedPosition.get().getTranslation()).getAngle();
      System.out.println(angleToHub);
    }
  }

  @Override
  public void disabledInit() {}

  @Override 
  public void disabledPeriodic(){
    LimelightHelpers.SetIMUMode("limelight-novas", 1);
  }

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }

    LimelightHelpers.SetIMUMode("limelight-novas", 4);
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    LimelightHelpers.SetIMUMode("limelight-novas", 4);
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}

  @Override
  public void simulationPeriodic() {}
}

