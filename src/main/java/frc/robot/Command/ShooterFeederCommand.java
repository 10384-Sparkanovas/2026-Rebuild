// package frc.robot.Command;

// import com.ctre.phoenix6.hardware.TalonFX;

// import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.subsystems.FeederSubystem;
// import frc.robot.subsystems.Shooter;

// public class ShooterFeederCommand extends Command{

//     private Shooter shooter;
//     private FeederSubystem feederSubystem;

//     public ShooterFeederCommand(FeederSubystem 
//              feederSubystem,
//             Shooter shooter,
//              TalonFX feederMotor 
//             ){
//         this.feederSubystem = feederSubystem;
//         //this.shooter = shooter;

//         feederSubystem = new FeederSubystem(feederMotor);
//         shooter = new Shooter();

//         addRequirements((feederSubystem), (shooter));

//     }
    
//     @Override
//     public void initialize(){
//     }

//     @Override
//     public void execute(){
//         shooter.runShooterCmd(40);
//         feederSubystem.runMotor(30);
//     }

//     @Override
//     public void end(boolean interrupted){
//         shooter.stop();
//         feederSubystem.stop();
//     }

//     @Override
//     public boolean isFinished(){
//         return false;
//     }


    
// }
