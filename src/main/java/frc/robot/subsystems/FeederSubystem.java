package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Seconds;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;

import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import frc.robot.generated.Constants;
import frc.robot.generated.Constants.feederConstants;
import frc.robot.subsystems.Shooter;


public class FeederSubystem extends SubsystemBase{

    private final TalonFX feederMotor = new TalonFX(feederConstants.feederID, Constants.nonDriverConstants.canivore);
    private final VelocityVoltage request = new VelocityVoltage(0).withSlot(0);
    private final VoltageOut voltageOut = new VoltageOut(2.0);
    public final VoltageOut sysIDOut = new VoltageOut(0);
    public final Shooter shooterSubsystem = new Shooter();

    public FeederSubystem(){
        //this.feederMotor = feederMotor;

        //feederMotor = new TalonFX(0, Constants.nonDriverConstants.canivore);
        
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
        config.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        feederMotor.getConfigurator().apply(config);

    }
    public void runAtVelocity(double rps) {
    feederMotor.setControl(request.withVelocity(rps)); 
    }
    public void stop() {
        feederMotor.set(0);
    }

    
    

    // public void runMotor(double rps){
    //     feederMotor.set(rps);
    
    // }
    // public void stop(){
    //     feederMotor.set(0);
    // }
    //SysId stuff


     public SysIdRoutine sysIdRoutine = new SysIdRoutine(new SysIdRoutine.Config(
        null,
        Volts.of(7),
        Seconds.of(10)
    ), new SysIdRoutine.Mechanism((Voltage volts) -> {feederMotor.setControl(sysIDOut.withOutput(volts.in(Volts)));}, null , this)
    );

    public Command sysIdQuasistatic(SysIdRoutine.Direction direction){
        return sysIdRoutine.quasistatic(direction);
    }
    public Command sysIDDynamic(SysIdRoutine.Direction direction){
        return sysIdRoutine.dynamic(direction);
    }
    
}
