package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.generated.Constants;

public class MotorTest extends SubsystemBase{

    private TalonFX motor1;
    private TalonFX motor2;

    public MotorTest(TalonFX motor1, TalonFX motor2){
        this.motor1 = motor1;
        this.motor2 = motor2;

        this.motor1 = new TalonFX(18, "rio");
        this.motor2 = new TalonFX(19, "rio" );
    }

    public void runMotor(double speed){
        motor1.set(speed);
        motor2.set(speed);
    }

    
    
}
