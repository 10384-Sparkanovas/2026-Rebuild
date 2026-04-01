 package frc.robot.Command;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.FeederSubystem;
import frc.robot.subsystems.LeftShooter;
import frc.robot.subsystems.RightShooter;

public class ShooterFeederCommand extends Command{

    private final FeederSubystem feedersubystem;
    private final LeftShooter LeftShooterSubsystem;
    private final RightShooter RightShooterSubsystem;
    private double targetShooterRPS = 60;
    private final double tolerance = 1;


    public ShooterFeederCommand( FeederSubystem feederSubystem, LeftShooter LeftShooterSubsystem, RightShooter RightShooterSubsystem, double targetShooterRPS ){
        this.LeftShooterSubsystem = LeftShooterSubsystem;
        this.RightShooterSubsystem = RightShooterSubsystem;
        this.feedersubystem = feederSubystem;
        this.targetShooterRPS = targetShooterRPS;

        addRequirements(LeftShooterSubsystem, feederSubystem, RightShooterSubsystem);


    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
       LeftShooterSubsystem.runAtVelocity(targetShooterRPS);
       RightShooterSubsystem.runAtVelocity(-targetShooterRPS);
         if(LeftShooterSubsystem.atTargetRps(targetShooterRPS, tolerance)){
                feedersubystem.runAtVelocity(55);
         }//method in shooter which returns a boolean val when shooter has reached target velocity
        
    }

    @Override
    public void end(boolean interrupted){
        LeftShooterSubsystem.runAtVelocity(0);
        RightShooterSubsystem.runAtVelocity(0);
        feedersubystem.runAtVelocity(0);
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}