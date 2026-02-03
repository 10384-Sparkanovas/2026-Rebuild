// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.sim.TalonFXSimState.MotorType;
import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.math.geometry.Rotation2d;
import com.revrobotics.spark.SparkFlex;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.RobotModeTriggers;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import frc.robot.generated.Constants;
import frc.robot.generated.TunerConstantsSwerve;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.MotorTest;
import frc.robot.subsystems.HingeMotor;

public class RobotContainer {
    private double MaxSpeed = TunerConstantsSwerve.kSpeedAt12Volts.in(MetersPerSecond) * 0.4; // kSpeedAt12Volts desired
                                                                                              // top speed
    private double MaxAngularRate = RotationsPerSecond.of(0.1).in(RadiansPerSecond) * 0.4; // 3/4 of a rotation per
                                                                                           // second max angular
                                                                                           // velocity

    /* Setting up bindings for necessary control of the swerve drive platform */
    private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
            .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
            .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // Use open-loop control for drive motors
    private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
    private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();

    private final Telemetry logger = new Telemetry(MaxSpeed);

    private final CommandXboxController driveJoystick = new CommandXboxController(Constants.nonDriverConstants.driverID);
    private final CommandXboxController operatorJoystick = new CommandXboxController(Constants.nonDriverConstants.operatorID);

    //Object declerations
    private  SparkFlex intakeMotor = new SparkFlex(Constants.nonDriverConstants.intakeID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
    private  TalonFX motor1; 
    private  TalonFX motor2;

    // Subsystems
    public final CommandSwerveDrivetrain drivetrain = TunerConstantsSwerve.createDrivetrain();
    public final MotorTest motorTest = new MotorTest(motor1, motor2);
    
    
    public final Intake intake = new Intake(intakeMotor);

    public final HingeMotor hinge = new HingeMotor();

    public RobotContainer() {
        configureDriveBindings();
        configureOperatorBindings();
    }

    private void configureOperatorBindings() {
        //operatorJoystick.b().whileTrue(Commands.run(intake::intakeFuel, intake));
        operatorJoystick.b().whileTrue(Commands.run(() -> {
            intake.intakeFuel(0.25);
        }, intake));
        operatorJoystick.x().whileTrue(Commands.run(() -> {
            intake.exhaustFuel(-0.25);
        }, intake));
        operatorJoystick.b().whileFalse(Commands.run(() -> {
            intake.stopMotor(0);
        }, intake));
        operatorJoystick.x().whileFalse(Commands.run(() -> {
            intake.stopMotor(0);
        }, intake));

        
        operatorJoystick.povDown().onTrue(Commands.run(() -> {
            hinge.down(0);
        }, hinge));
        operatorJoystick.povUp().onTrue(Commands.run(() -> {
            hinge.down(0.25);
        }, hinge));
       
         

    }


    private void configureDriveBindings() {
        // Note that X is defined as forward according to WPILib convention,
        // and Y is defined as to the left according to WPILib convention.
        drivetrain.setDefaultCommand(
                // Drivetrain will execute this command periodically
                drivetrain.applyRequest(() -> drive.withVelocityX(-driveJoystick.getLeftY() * MaxSpeed) // Drive forward
                                                                                                        // with negative
                                                                                                        // Y (forward)
                        .withVelocityY(-driveJoystick.getLeftX() * MaxSpeed) // Drive left with negative X (left)
                        .withRotationalRate(-driveJoystick.getRightX() * MaxAngularRate) // Drive counterclockwise with
                                                                                         // negative X (left)
                ));

        // Idle while the robot is disabled. This ensures the configured
        // neutral mode is applied to the drive motors while disabled.
        final var idle = new SwerveRequest.Idle();
        RobotModeTriggers.disabled().whileTrue(
                drivetrain.applyRequest(() -> idle).ignoringDisable(true));

        driveJoystick.a().whileTrue(drivetrain.applyRequest(() -> brake));
        driveJoystick.b().whileTrue(drivetrain.applyRequest(
                () -> point.withModuleDirection(new Rotation2d(-driveJoystick.getLeftY(), -driveJoystick.getLeftX()))));

        // Run SysId routines when holding back/start and X/Y.
        // Note that each routine should be run exactly once in a single log.
        driveJoystick.back().and(driveJoystick.y()).whileTrue(drivetrain.sysIdDynamic(Direction.kForward));
        driveJoystick.back().and(driveJoystick.x()).whileTrue(drivetrain.sysIdDynamic(Direction.kReverse));
        driveJoystick.start().and(driveJoystick.y()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kForward));
        driveJoystick.start().and(driveJoystick.x()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kReverse));

        // reset the field-centric heading on left bumper press
        driveJoystick.leftBumper().onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldCentric()));

        drivetrain.registerTelemetry(logger::telemeterize);
    }

    public Command getAutonomousCommand() {
        return Commands.print("No autonomous command configured");
    }
}
