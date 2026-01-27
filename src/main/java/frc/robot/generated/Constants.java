package frc.robot.generated;

import com.ctre.phoenix6.CANBus;

import edu.wpi.first.wpilibj.XboxController;

public class Constants {
    
    public static final class nonDriverConstants{
        public static final int intakeID = 9;
        public static final int hingeID = 16;
        public static final CANBus canivore = new CANBus("put name here");
        public static final int operatorID = 0;
        public static final int driverID = 1;
        

    }
    public static final class PIDConstants{
        public static final double hingeKp = 0.1;
        public static final double hingeKd = 0.01;
        public static final double hingeKi = 0;
    }
    public static final class robotMeasureConstants{
        public static final double robotMass = 100;
        public static final double maxAccerlation = 0; //need to fill this in
    }

    
}
