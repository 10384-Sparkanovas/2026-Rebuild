 package frc.robot.Command;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.FeederSubystem;
import frc.robot.subsystems.Indexerx44;
import frc.robot.subsystems.Shooter;

public class ShooterFeederCommandReverse extends Command{

    private final FeederSubystem feedersubystem;
    private final Shooter shooterSubsystem;
    private final Indexerx44 indexerSubsytem;
    private double targetShooterRPS = 75;
    private final double tolerance = 1;


    public ShooterFeederCommandReverse( FeederSubystem feederSubystem, Shooter shooterSubsystem, Indexerx44 indexersubsystem, double targetShooterRPS ){
        this.shooterSubsystem = shooterSubsystem;
        this.feedersubystem = feederSubystem;
        this.indexerSubsytem = indexersubsystem;
        this.targetShooterRPS = targetShooterRPS;

        addRequirements(shooterSubsystem, feederSubystem, indexersubsystem);


    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        shooterSubsystem.runAtVelocity(targetShooterRPS);
         if(shooterSubsystem.atTargetRps(-targetShooterRPS, tolerance)){
                feedersubystem.runAtVelocity(-65);
                indexerSubsytem.runAtVelocity(40);
         }//method in shooter which returns a boolean val when shooter has reached target velocity
        
    }

    @Override
    public void end(boolean interrupted){
        shooterSubsystem.runAtVelocity(0);
        feedersubystem.runAtVelocity(0);
        indexerSubsytem.runAtVelocity(0);
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}