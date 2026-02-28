package frc.robot.subsystems;
import static edu.wpi.first.units.Units.Seconds;
import static edu.wpi.first.units.Units.Volts;
import static edu.wpi.first.units.Units.Rotations;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import static edu.wpi.first.units.Units.Second;

import com.ctre.phoenix6.SignalLogger;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.sysid.SysIdRoutineLog;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
// Import the Constants class
import frc.robot.generated.Constants.ShooterConstants;
import frc.robot.generated.Constants;

public class Shooter extends SubsystemBase {
       
    private final TalonFX shooterMotor = new TalonFX(Constants.ShooterConstants.shooterID, Constants.nonDriverConstants.canivore);
    // Create request once to save memory
    private final VelocityVoltage request = new VelocityVoltage(0).withSlot(0);
   
    // SysId Control Request (Voltage)
    // SysId requires direct voltage control, ignoring PID constants
    private final VoltageOut sysIdControl = new VoltageOut(0);
    // The SysId Routine Object
    private final SysIdRoutine sysIdRoutine;
   

    public Shooter() {
        //this.shooterMotor = shooterMotor;
       
        TalonFXConfiguration config = new TalonFXConfiguration();
        // Tuning for Velocity
        config.Slot0.kS = ShooterConstants.kS;
        config.Slot0.kV = ShooterConstants.kV;
        config.Slot0.kP = ShooterConstants.kP;
        config.Slot0.kI = ShooterConstants.kI;
        config.Slot0.kD = ShooterConstants.kD;
        config.Slot0.kA = ShooterConstants.kA;
        // Optional: If you want to put current limit in constants too
        // config.CurrentLimits.StatorCurrentLimit = ShooterConstants.kCurrentLimit;
        config.CurrentLimits.StatorCurrentLimit = 80;
        config.CurrentLimits.StatorCurrentLimitEnable = true;
        config.MotorOutput.NeutralMode = NeutralModeValue.Brake; // Coast?
        shooterMotor.getConfigurator().apply(config);

        sysIdRoutine = new SysIdRoutine(
            new SysIdRoutine.Config(
                Volts.of(1).per(Second),
                Volts.of(7),            
                Seconds.of(10)          
            ),
            new SysIdRoutine.Mechanism(
                (Voltage volts) -> {
                    shooterMotor.setControl(sysIdControl.withOutput(volts.in(Volts)));
                },
                (SysIdRoutineLog log) -> {
                    log.motor("Shooter-Motor")
                    .voltage(Volts.of(shooterMotor.getMotorVoltage().getValueAsDouble()))
                    .angularPosition(Rotations.of(shooterMotor.getPosition().getValueAsDouble()))
                    .angularVelocity(RotationsPerSecond.of(shooterMotor.getVelocity().getValueAsDouble()));
                },
                this
            )
        );
    }  

    public void runAtVelocity(double rps) {
        shooterMotor.setControl(request.withVelocity(rps)); //rps in this case would be the target
    }
    public void stop() {
        shooterMotor.set(0);
    }
    public boolean atTargetRps(double targetRPS, double tolerance){
        return Math.abs(shooterMotor.getVelocity().getValueAsDouble() - targetRPS) < tolerance;
    }
   
    public Command sysIdQuasistatic(SysIdRoutine.Direction direction){
        return sysIdRoutine.quasistatic(direction);
    }
    public Command sysIDDynamic(SysIdRoutine.Direction direction){
        return sysIdRoutine.dynamic(direction);
    }
}
