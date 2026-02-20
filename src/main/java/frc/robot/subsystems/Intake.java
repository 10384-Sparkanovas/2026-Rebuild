package frc.robot.subsystems;
//import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.hardware.TalonFX;

//import java.util.function.Supplier;

// import com.ctre.phoenix6.CANBus;
// import com.ctre.phoenix6.SignalLogger;
// import com.ctre.phoenix6.Utils;


// import edu.wpi.first.math.Matrix;
// import edu.wpi.first.math.geometry.Pose2d;
// import edu.wpi.first.math.geometry.Rotation2d;
// import edu.wpi.first.math.numbers.N1;
// import edu.wpi.first.math.numbers.N3;
// import edu.wpi.first.wpilibj.DriverStation;
// import edu.wpi.first.wpilibj.DriverStation.Alliance;
// import edu.wpi.first.wpilibj.motorcontrol.Spark;
import com.revrobotics.spark.SparkFlex;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
//import edu.wpi.first.wpilibj.Notifier;
// import edu.wpi.first.wpilibj.RobotController;
// import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
// import frc.robot.generated.Constants;
// import frc.robot.generated.TunerConstantsSwerve.TunerSwerveDrivetrain;
import frc.robot.generated.Constants.nonDriverConstants;;

public class Intake extends SubsystemBase{

    private  final SparkFlex intakeMotor = new SparkFlex(nonDriverConstants.intakeID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
   // private TalonFX indexMotor;

    public Intake() {
        //this.intakeMotor = intakeMotor;
       
    }

    public void intakeFuel(double speed){
        intakeMotor.set(speed);
        
    }
    
    public void exhaustFuel(double speed){
        intakeMotor.set(speed);
        
    }
    public void stopMotor (double speed){
        intakeMotor.set(speed);
     
    }


    
}
    


