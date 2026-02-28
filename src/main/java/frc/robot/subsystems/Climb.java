// package.frc.robot.subsystems;
// import com.ctre.phoenix6.configs.TalonFXConfigurations;
// import com.ctre.phoenix6.hardware.TalonFX;
// import com.ctre.phoenix6.controls.MotionMagicVoltage;
// import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// //import frc.robot.Constants.ClimbConstants;
// import frc.robot.generated.Constants;
 
// public class Climb extends SubsystemBase {
//     private TalonFX ClimbMotor = new TalonFX(Climb.Constants.ClimbMotorID,ClimbConstants.canivore);
   
//     private final MotionMagicVoltage request = new MotionMagicVoltage(0).
//         withSlot(0)
 
// public Climb Subsystem(){
//     TalonFXConfiguration config = new TalonFXConfiguration();
//     //Tuning for Positions
 
//     config.Slot0.kP = Constants.ClimbConstants.kP;
//     config.Slot0.kI = Constants.ClimbConstants.kI;
//     config.Slot0.kD = Constants.ClimbConstants.kD;
//     config.Slot0.kS = Constants.ClimbConstants.kS;
//     config.Slot0.kV = Constants.ClimbConstants.kV;
//    // config.Slot0.kG = Constants.ClimbConstants.kG;
 
//     //Motion Magic settings
//     config.MotionMagic.MotionMagicCruiseVelocity = Climb.Constants.kCruiseVelocity;
 
//     config.MotionMagic.MotionMagicAcceleration = ArmConstants.kAcceleration;
 
//     config.MotionMagic.MotionMagicJerk = ArmConstants.kJerk;
 
//     config.CurrentLimits.StatorCurrentLimit = 60;
//     config.CurrentLimits.StatorCurrentLimitEnable = true;
//     config.MotorOutput.NuetralMode = NuetralModeValue.Brake;
//     ClimbMotor.getConfigurator().apply(config);
//     ClimbMotor.setPosition(0);
 
//     ClimbMotor.getConfigurator().apply(config);
   
// }
 
// public void moveToPosition(double rotations){
//     ClimbMotor.setControl(request.withPosition(rotations));
// }
 
// public Command moveToPositionCmd(double rotations){
//     return this.runOnce(() -> moveToPosition(rotations));
// }
 
// public boolean atSetpoint(double target, double tolerance){
//     return Math.abs(ClimbMotor.getPosition().getValueAsDouble() - target) <
//        tolerance;
// }
   
   
// }
 