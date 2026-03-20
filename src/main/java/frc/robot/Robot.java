// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.estimator.PoseEstimator;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
// import frc.robot.subsystems.LimelightHelpers;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.CommandSwerveDrivetrain;


public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  // private Pigeon2 m_gyro;
  
  private final RobotContainer m_robotContainer;
  // private final PoseEstimator m_poseEstimator;
  

  public Robot() {
    m_robotContainer = new RobotContainer();
    // m_gyro = new Pigeon2(30);
    // m_gyro.setYaw(0);
    // m_poseEstimator = new PoseEstimator<>(null, null, null, null);
    
  
  }

  
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run(); 
    // double yawDegrees = m_gyro.getYaw().getValueAsDouble();
    // LimelightHelpers.SetRobotOrientation("novocane",yawDegrees,0,0,0,0,0);
    // LimelightHelpers.PoseEstimate mt2 = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2("limelight");
    // LimelightHelpers.SetRobotOrientation("novocane", m_poseEstimator.getEstimatedPosition().getRotation().getDegrees(), 0, 0, 0, 0, 0);
    
  
    // m_poseEstimator.setVisionMeasurementStdDevs(VecBuilder.fill(.5,.5,9999999));
    // m_poseEstimator.addVisionMeasurement(
    //     mt2.pose,
    //     mt2.timestampSeconds);
  
  }

  @Override
  public void disabledInit() {}

  @Override 
  public void disabledPeriodic(){
  //   LimelightHelpers.SetIMUMode("novocane", 1);
  }

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
    // LimelightHelpers.SetIMUAssistAlpha("novocane", 0.4);
  }

  @Override
  public void autonomousPeriodic() {
    // LimelightHelpers.SetIMUMode("novocane", 4);
  
  }

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    // LimelightHelpers.SetIMUAssistAlpha("novocane", 0.4);
  }

  @Override
  public void teleopPeriodic() {
    // LimelightHelpers.SetIMUMode("novocane", 4);
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

