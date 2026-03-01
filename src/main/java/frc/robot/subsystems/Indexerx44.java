package frc.robot.subsystems ;
import com.ctre.phoenix6.configs. TalonFXConfiguration ;
import com.ctre.phoenix6.hardware.TalonFX ;
import com.ctre.phoenix6.controls.VelocityVoltage ;
import com.ctre.phoenix6.signals.NeutralModeValue ;
import edu.wpi.first.wpilibj2.command.Command ;
import edu.wpi.first.wpilibj2.command.SubsystemBase ;
import frc.robot.generated.Constants;
// Import the Constants class
import frc.robot.generated.Constants.indexerConstants;
public class Indexerx44 extends SubsystemBase {
    //private final TalonFX motor = new TalonFX ( IndexerConstants . kMotorID ) ;
    private TalonFX indexMotor = new TalonFX(indexerConstants.indexMotorID,Constants.nonDriverConstants.canivore);
    // Create request once to save memory
    private final VelocityVoltage request = new VelocityVoltage (0).withSlot (0) ;

    public Indexerx44() {
        TalonFXConfiguration config = new TalonFXConfiguration () ;
        // Tuning for Velocity
        
        
        config.Slot0.kS = Constants.indexerConstants.kS ;
        config.Slot0.kV = Constants.indexerConstants.kV ;
        config.Slot0.kP = Constants.indexerConstants.kP ;
        config.Slot0.kI = Constants.indexerConstants.kI ;
        config.Slot0.kD = Constants.indexerConstants.kD ;
        config.Slot0.kA = Constants.indexerConstants.kA ;
        // Optional : If you want to put current limit in constants too
        // config . CurrentLimits . StatorCurrentLimit = ShooterConstants .
        
        config.CurrentLimits.StatorCurrentLimit = 85;
        config.CurrentLimits.StatorCurrentLimitEnable = true ;
        config.MotorOutput.NeutralMode = NeutralModeValue . Coast ; // Coast
        
        indexMotor.getConfigurator (). apply(config) ;
    }
    public void runAtVelocity(double rps) {
    indexMotor.setControl (request.withVelocity(rps)) ;
    }
    

    public void stop () {
    indexMotor.set (0);
    }
    // Command Factory
    // Uses startEnd : Runs on start , Stops on end .
    public Command runIndexerCmd(double rps) {
        return this.startEnd(
        () -> runAtVelocity (rps),
        () -> stop()
        );
    }
    public boolean atSetpoint ( double targetRPS , double tolerance ) {
    return Math . abs ( indexMotor. getVelocity () . getValueAsDouble () - targetRPS
    ) < tolerance ;
    }
}
