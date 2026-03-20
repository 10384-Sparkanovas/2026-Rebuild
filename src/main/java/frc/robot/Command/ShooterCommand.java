 package frc.robot.Command;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.LeftShooter;
import frc.robot.subsystems.RightShooter;

public class ShooterCommand extends Command{

private final LeftShooter LeftShooterSubsystem;
private final RightShooter RightShooterSubsystem;
private double targetShooterRPS = 50;


   public ShooterCommand(LeftShooter LeftShooterSubsystem, RightShooter RightShooterSubsystem, double targetShooterRPS){
        this.LeftShooterSubsystem = LeftShooterSubsystem;
        this.RightShooterSubsystem = RightShooterSubsystem;
        this.targetShooterRPS = targetShooterRPS;

        addRequirements(LeftShooterSubsystem, RightShooterSubsystem);


    }

    @Override
    public void initialize(){

    }

@Override
    public void execute(){
        LeftShooterSubsystem.runAtVelocity(targetShooterRPS);
        RightShooterSubsystem.runAtVelocity(-targetShooterRPS);
    }

    @Override
    public void end(boolean interrupted){
        LeftShooterSubsystem.runAtVelocity(0);
        RightShooterSubsystem.runAtVelocity(0);
    }

    @Override
    public boolean isFinished(){
        return false;
    }













}
