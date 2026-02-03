package frc.robot.subsystems;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
// Import the Constants class
import frc.robot.generated.Constants.ShooterConstants;
import frc.robot.generated.Constants;
public class Shooter extends SubsystemBase {
private final TalonFX shooterMotor = new TalonFX(Constants.nonDriverConstants.operatorID);
// Create request once to save memory
private final VelocityVoltage request = new VelocityVoltage(0).
withSlot(0);
public Shooter() {
TalonFXConfiguration config = new TalonFXConfiguration();
// Tuning for Velocity
config.Slot0.kS = ShooterConstants.kS;
config.Slot0.kV = ShooterConstants.kV;
config.Slot0.kP = ShooterConstants.kP;
config.Slot0.kI = ShooterConstants.kI;
config.Slot0.kD = ShooterConstants.kD;
// Optional: If you want to put current limit in constants too
// config.CurrentLimits.StatorCurrentLimit = ShooterConstants.kCurrentLimit;
config.CurrentLimits.StatorCurrentLimit = 40;
config.CurrentLimits.StatorCurrentLimitEnable = true;
config.MotorOutput.NeutralMode = NeutralModeValue.Coast; // Coast
            shooterMotor.getConfigurator().apply(config);
    }   
public void runAtVelocity(double rps) {
shooterMotor.setControl(request.withVelocity(rps));
}
public void stop() {
shooterMotor.set(0);
}
// Command Factory
// Uses startEnd: Runs on start, Stops on end.
    public Command runShooterCmd(double rps) {
        return this.startEnd(
            ()-> runAtVelocity(rps),
            ()-> stop()
        );
    }
}
