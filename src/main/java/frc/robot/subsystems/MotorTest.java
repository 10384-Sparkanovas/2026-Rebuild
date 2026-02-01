package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Telemetry;
import frc.robot.generated.Constants;




public class MotorTest extends SubsystemBase{


    private TalonFX motor1;
    private TalonFX motor2;
   // private double lastEncoderVal1 = 0;
    private double lastEncoderVal2 ;
    


    public MotorTest(TalonFX motor1, TalonFX motor2){
        this.motor1 = motor1;
        this.motor1 = new TalonFX(18, "rio");
        DataLogManager.start();
      
    }

    public void runMotor(double speed){
        motor1.set(speed);
        //motor2.set(speed);
    }

    public void stopMotor(){
        motor1.set(0);
    
    }
    public double readEncoderVals(){
        
       return motor1.getPosition().getValueAsDouble();
       
    }


    
    
}
