// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.sim.TalonFXSimState.MotorType;
import com.ctre.phoenix6.swerve.SwerveRequest;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.spark.SparkFlex;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.RobotModeTriggers;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import frc.robot.Command.ShooterFeederCommand;
import frc.robot.generated.Constants;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.FeederSubystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.HingeMotor;
import frc.robot.subsystems.Indexerx44;

public class RobotContainer {
    private double MaxSpeed = 0.4 * TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed
    private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); // 3/4 of a rotation per second max angular velocity

    // private final SendableChooser<Command> autoChooser;
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
    //private  SparkFlex intakeMotor = new SparkFlex(Constants.nonDriverConstants.intakeID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);

    
    // Subsystems
     public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();
    public final Shooter shooterSubsystem = new Shooter();
    public final FeederSubystem feederSubsystem = new FeederSubystem();
    public final Indexerx44 indexer = new Indexerx44();
    //public final IndexerNeo indexer = new IndexerNeo();
    //Command 
    public final ShooterFeederCommand shooterFeederCommand = new ShooterFeederCommand(feederSubsystem, shooterSubsystem, indexer, 60);
    //public final ShooterFeederCommand shooterFeederCmd = new ShooterFeederCommand(feederSubsystem, shooterSubsystem, feederMotor);
    public final Intake intake = new Intake();
    //public final Shooter shooter = new Shooter()

    public final HingeMotor hinge = new HingeMotor();
 
    private  SendableChooser<Command>autoChooser;

    public RobotContainer() {

        //DEFAULT AUTO IS THE MIDDLE AUTO
        // boolean notMiddle = false; //to change auto change these two booleans
        // boolean notRight = false;
        
        //autoChooser = AutoBuilder.buildAutoChooser("Test Auto"); // builds an auto chooser and defults command.chose() to none
        
        // //COMMENTED OUT FOR AUTO FAILURE
        // if(notMiddle && notRight){
        //     autoChooser = AutoBuilder.buildAutoChooser("Right Shooting");

        // }else if (notMiddle == true && notRight){
        //     autoChooser = AutoBuilder.buildAutoChooser("Left Shooting");
        // }else{
        //     autoChooser = AutoBuilder.buildAutoChooser("Center start - middle shooting");
        // }
        

        //SmartDashboard.putData("auto chosen", autoChooser);
        //Web cam stuff:
        CameraServer.startAutomaticCapture(0); //starts the camera
        configureOperatorBindings();
        configureDriveBindings();
    }

    private void configureOperatorBindings() {
        //operatorJoystick.b().whileTrue(Commands.run(intake::intakeFuel, intake));

        //Intake bindings
        operatorJoystick.b().whileTrue(Commands.run(() -> {
            intake.intakeFuel(0.5);
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
   
        
        //Shooter bindings
        
                                                //this joystick binding will be changed later
        //operatorJoystick.a().whileTrue(Commands.run(() -> {shooterSubsystem.runAtVelocity(30); feederSubsystem.runAtVelocity(30); indexer.runAtVelocity(-30); }));
        //operatorJoystick.a().whileFalse(Commands.run(() -> {shooterSubsystem.runAtVelocity(0); feederSubsystem.runAtVelocity(0); indexer.runAtVelocity(0); }));
        //operatorJoystick.y().whileTrue(Commands.run(() -> {shooterSubsystem.runAtVelocity(-30); feederSubsystem.runAtVelocity(-30); indexer.runAtVelocity(30); }));
        //operatorJoystick.y().whileFalse(Commands.run(() -> {shooterSubsystem.runAtVelocity(0); feederSubsystem.runAtVelocity(0); indexer.runAtVelocity(0); }));
        operatorJoystick.a().whileTrue(shooterFeederCommand);

    
       
        //operatorJoystick.a().whileTrue(Commands.run(() -> {shooterSubsystem.runAtVelocity(65); feederSubsystem.runAtVelocity(30);}));

        //operatorJoystick.a().whileFalse(Commands.run(() -> {feederSubsystem.stop(); shooterSubsystem.stop(); indexer.stop();}));

         operatorJoystick.rightBumper().whileTrue(Commands.run(() -> {indexer.runAtVelocity(-30);}));
         operatorJoystick.rightBumper().whileFalse(Commands.run(() -> {indexer.runAtVelocity(0);}));
         operatorJoystick.leftBumper().whileTrue(Commands.run(() -> {shooterSubsystem.runAtVelocity(65);}));
         operatorJoystick.leftTrigger().whileTrue(Commands.run(() -> {feederSubsystem.runAtVelocity(45);}));
         operatorJoystick.leftTrigger().whileFalse((Commands.run(() -> {feederSubsystem.runAtVelocity(0);})));
         //operatorJoystick.leftBumper().whileFalse(Commands.run(() -> {shooterSubsystem.runAtVelocity(0);}));
        // }));
        //operatorJoystick.rightTrigger.whileTrueCommands.run(() -> {
        //     indexer.IndexOut(0.25);
        // }));
        // operatorJoystick.rightBumper().whileFalse(Commands.run(() -> {
        //     indexer.stopMotor(0);
        // }));
        //operatorJoystick.rightTrigger.whileFalseCommands.run(() -> {
        //     indexer.stopMotor(0);
        // }));


        

        //Hinge bindings
        operatorJoystick.povDown().onTrue(Commands.run(() -> {
            hinge.down(0);
        }, hinge));
        operatorJoystick.povUp().onTrue(Commands.run(() -> {
            hinge.down(0.25);
        }, hinge));
        //operatorJoystick.a().onTrue(Commands.run(() -> {
        //   hinge.down(0.125);
        //}, hinge));   
       
    }


    private void configureDriveBindings() {
        // Note that X is defined as forward according to WPILib convention,
        // and Y is defined as to the left according to WPILib convention.

        //COMMENTED FPR SYSID
         drivetrain.setDefaultCommand(
           // Drivetrain will execute this command periodically
                drivetrain.applyRequest(() -> drive.withVelocityX(driveJoystick.getLeftY() * MaxSpeed) // Drive forward
                                                                                                        // with negative
                                                                                                        // Y (forward)
                       .withVelocityY(driveJoystick.getLeftX() * MaxSpeed) // Drive left with negative X (left)
                        .withRotationalRate(-driveJoystick.getRightX() * MaxAngularRate) // Drive counterclockwise with
                                                                                         // negative X (left)
               ));

        // Idle while the robot is disabled. This ensures the configured
        // neutral mode is applied to the drive motors while disabled.

        //COMMENTED FOR SYSID
        // final var idle = new SwerveRequest.Idle();
        // RobotModeTriggers.disabled().whileTrue(
        //         drivetrain.applyRequest(() -> idle).ignoringDisable(true));

        // driveJoystick.a().whileTrue(drivetrain.applyRequest(() -> brake));
        driveJoystick.b().whileTrue(drivetrain.applyRequest(
                () -> point.withModuleDirection(new Rotation2d(driveJoystick.getLeftY(), driveJoystick.getLeftX())) ));

        // Run SysId routines when holding back/start and X/Y.
        // Note that each routine should be run exactly once in a single log.
        driveJoystick.back().and(driveJoystick.y()).whileTrue(drivetrain.sysIdDynamic(Direction.kForward));
        driveJoystick.back().and(driveJoystick.x()).whileTrue(drivetrain.sysIdDynamic(Direction.kReverse));
        driveJoystick.start().and(driveJoystick.y()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kForward));
        driveJoystick.start().and(driveJoystick.x()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kReverse));

        //Shooter SysId routines
        // driveJoystick.start().and(driveJoystick.a()).whileTrue(shooterSubsystem.sysIdQuasistatic(Direction.kForward));
        // driveJoystick.start().and(driveJoystick.b()).whileTrue(shooterSubsystem.sysIdQuasistatic(Direction.kReverse));
        // driveJoystick.start().and(driveJoystick.x()).whileTrue(shooterSubsystem.sysIDDynamic(Direction.kForward));
        // driveJoystick.start().and(driveJoystick.y()).whileTrue(shooterSubsystem.sysIDDynamic(Direction.kReverse));

        //feeder SysId routines
        // driveJoystick.back().and(driveJoystick.povUp().whileTrue(feederSubsystem.sysIdQuasistatic(Direction.kForward)));
        // driveJoystick.back().and(driveJoystick.povDown().whileTrue(feederSubsystem.sysIDDynamic(Direction.kReverse)));
        // driveJoystick.start().and(driveJoystick.povUp().whileTrue(feederSubsystem.sysIdQuasistatic(Direction.kForward)));
        // driveJoystick.start().and(driveJoystick.povDown().whileTrue(feederSubsystem.sysIDDynamic(Direction.kReverse)));

        //hinge SysId routines

        // reset the field-centric heading on left bumper press

        //COMMENTED FOR SYSID
        driveJoystick.leftBumper().onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldCentric()));

        drivetrain.registerTelemetry(logger::telemeterize);
    }

    public Command getAutonomousCommand() {


        //return Commands.print("No autonomous command configured");
        return new PathPlannerAuto("Test Auto");
        //return autoChooser.getSelected(); //will select the default which is Center start - middle shooter
    }
}
