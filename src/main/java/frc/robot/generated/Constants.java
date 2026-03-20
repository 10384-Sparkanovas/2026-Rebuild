package frc.robot.generated;

import com.ctre.phoenix6.CANBus;

public class Constants {
    
    public static final class nonDriverConstants{
        public static final int intakeID = 17;
       
        public static final CANBus canivore = new CANBus("CANivore");
        public static final int operatorID = 0;
        public static final int driverID = 1;
        

    }
    public static final class HingeConstants{
        public static final double hingeMass = 0; //need to adjust 
        //public static final double kmaxAcceleration = 0; //need to fill this in
        //public static final double kmaxVelocity = 0; //in RPS
        //public static final double kMaxVelocityRPS = 80;
        public static final double KG = 0.3; //need to increase with weight (estimate protoype weight at 3lb)
        public static final double KS = 0.1; //need to increase with weight 
        public static final double KV = 0.12; //leave alone
        public static final double KP = 3.0; //when there is something attached, needs to be increased
        public static final double KD = 0.5; //may need to change 
        public static final double CruiseVelocity = 6; //RPS
        public static final double Acceleration = 12; // RPS/S may move up to 24
        public static final double Jerk = 0;
        public static final double MaxCurrent = 40;
        public static final int leftHingeID = 19;
        public static final int rightHingeID = 18;
        public static final int encoderID = 20; 
        

    }
    public static final class LeftShooterConstants{
        public static final int LeftShooterID = 43; //43
        public static final double kS = 0.078212;
        public static final double kA = 0.01326; //0.01226
        public static final double kV = 0.11639;
        public static final double kP = 0.066857;
        public static final double kI = 0;
        public static final double kD = 0.0;
    }
    public static final class RightShooterConstants{
        public static final int RightShooterID = 45; //45
        public static final double kS = 0;
        public static final double kA = 0; 
        public static final double kV = 0.12;
        public static final double kP = 0;
        public static final double kI = 0;
        public static final double kD = 0;
        
    }

    public static final class feederConstants{
        public static final int feederID = 42; //actually 42
        public static final double kS = 0.073278;
        public static final double kV = 0.11498;
        public static final double kA = 0.0017407;
        public static final double kP = 0.0078322;
        public static final double kI = 0;
        public static final double kD = 0;
    }

    
    public static final class ClimbConstants{
        public static final int ClimbID = 45;
        public static final double kS = 0;
        public static final double kA = 0.02;
        public static final double kV = 0.12;
        public static final double kP = 0.5;
        public static final double kI = 0;
        public static final double kD = 0.01;
    
    }

    public static final class limelightConstants{
        public static final int [] validLimelightIDs = {9,10,25,26}; 
    }
    
}
