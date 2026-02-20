package frc.robot.subsystems;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.generated.Constants;
import frc.robot.generated.Constants.HingeConstants;

public class HingeMotor extends SubsystemBase{
    private final TalonFX leftHinge = new TalonFX(HingeConstants.leftHingeID, Constants.nonDriverConstants.canivore);
    private final TalonFX rightHinge = new TalonFX(HingeConstants.rightHingeID, Constants.nonDriverConstants.canivore);
    private final MotionMagicVoltage request = new MotionMagicVoltage(0).withSlot(0);
    

    //private final double Fn = 9.81*Constants.HingeConstants.hingeMass; 

    public HingeMotor(){
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.MotionMagic.MotionMagicAcceleration = Constants.HingeConstants.Acceleration;
        config.MotionMagic.MotionMagicCruiseVelocity = Constants.HingeConstants.CruiseVelocity;
        config.MotionMagic.MotionMagicJerk = Constants.HingeConstants.Jerk;
        config.Slot0.kG = HingeConstants.KG;
        config.Slot0.kS = HingeConstants.KS;
        config.Slot0.kV = HingeConstants.KV;
        config.Slot0.kP = HingeConstants.KP;
        config.Slot0.kD = HingeConstants.KD;
        config.Feedback.FeedbackRemoteSensorID = HingeConstants.encoderID;
        config.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.RemoteCANcoder;
        config.Feedback.RotorToSensorRatio = 12;
        config.CurrentLimits.StatorCurrentLimit = HingeConstants.MaxCurrent;
        config.CurrentLimits.StatorCurrentLimitEnable = true; 
        config.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        leftHinge.getConfigurator().apply(config);
        rightHinge.getConfigurator().apply(config);

        rightHinge.setControl(new Follower(HingeConstants.leftHingeID, MotorAlignmentValue.Opposed)); 
    }

    public void down(double rotation){
        leftHinge.setControl(request.withPosition(rotation));
        //rightHinge.setControl(request.withPosition(rotation));
        
    }
   



    
}
