package frc.robot.subsystems;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.generated.Constants;
import frc.robot.generated.Constants.PIDConstants;
import frc.robot.generated.Constants.robotMeasureConstants;

public class HingeMotor extends SubsystemBase{
    private TalonFX hingerMotor;
    private final double dropDown = 1;
    private final double foldUp = 2;
    private  boolean activated = false;
    private final double Fn = 9.81*Constants.robotMeasureConstants.hingeMass;
    private TalonFXConfiguration talonFXConfiguration = new TalonFXConfiguration();
    private Slot0Configs slot0Configs = new Slot0Configs().
    withKG(robotMeasureConstants.maxAccerlation * robotMeasureConstants.hingeMass)
    .withKA(0.01)
    .withKS(Fn * 0.2) //need to find the actual value of μ
    .withKV(0.01) //need actual calcs for this
    .withKP(0.03) //can change later
    .withKI(0) //leave as zero
    .withKD(0.01); //can change later

    public HingeMotor(TalonFX hingerMotor){
        this.hingerMotor = hingerMotor;
        this.hingerMotor = new TalonFX(Constants.nonDriverConstants.hingeID,"rio");

    }
    

    
}
