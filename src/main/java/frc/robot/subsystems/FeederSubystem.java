package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.generated.Constants;
import frc.robot.generated.Constants.feederConstants;;

public class FeederSubystem extends SubsystemBase{

    private TalonFX feederMotor;

    public FeederSubystem(TalonFX feederMotor){
        this.feederMotor = feederMotor;

        feederMotor = new TalonFX(0, Constants.nonDriverConstants.canivore);
        
        //Create TalonFX configuration

        TalonFXConfiguration config = new TalonFXConfiguration();

         // Tuning for Velocity
        config.Slot0.kS = feederConstants.kS;
        config.Slot0.kV = feederConstants.kV;
        config.Slot0.kP = feederConstants.kP;
        config.Slot0.kI = feederConstants.kI;
        config.Slot0.kD = feederConstants.kD;

        config.CurrentLimits.StatorCurrentLimit = Constants.HingeConstants.MaxCurrent;
        config.CurrentLimits.StatorCurrentLimitEnable = true;
        config.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        feederMotor.getConfigurator().apply(config);

    }

    public void runMotor(double rps){
        feederMotor.set(rps);
    
    }
    public void stop(){
        feederMotor.set(0);
    }


    
}
