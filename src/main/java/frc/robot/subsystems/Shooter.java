package frc.robot.subsystems;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.SignalLogger;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
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
    
    private final VoltageOut voltageOut = new VoltageOut(2.0);
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
        config.CurrentLimits.StatorCurrentLimit = Constants.HingeConstants.MaxCurrent;
        config.CurrentLimits.StatorCurrentLimitEnable = true;
        config.MotorOutput.NeutralMode = NeutralModeValue.Brake; // Coast
        shooterMotor.getConfigurator().apply(config);
        }   
    public void runAtVelocity(double rps) {
        shooterMotor.setControl(request.withVelocity(rps)); //rps in this case would be the target error?
    }
    public void stop() {
        shooterMotor.set(0);
    }
   // private final SysIdRoutine shooterIdRoutine = new SysIdRoutine(SysIdRoutine.Config(null, Volts.of(2.0), null), 
              //  SysIdRoutine.Mechanism((volts) -> shooterMotor.setControl(voltageOut.withOut)));
    // Command Factory
    // Uses startEnd: Runs on start, Stops on end.()
        // public Command runShooterCmd(double rps) {
        //     return this.startEnd(
        //         ()-> runAtVelocity(rps),
        //         ()-> stop()
        //     );
        // }
        // // temporary for testing
        // public Command reverseShooterCmd(double rps) {
        //     return this.startEnd(
        //         ()-> runAtVelocity(-rps),
        //         ()-> stop()
        //     );
        // }

}
