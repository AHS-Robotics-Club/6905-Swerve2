package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;

// import edu.wpi.first.math.controller.ArmFeedforward;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubby extends SubsystemBase{

    private final CANSparkMax primary = new CANSparkMax(9, MotorType.kBrushless);
    private final CANSparkMax follower = new CANSparkMax(10, MotorType.kBrushless);

    private final RelativeEncoder encoder = primary.getAlternateEncoder(8192);

    // private final ArmFeedforward feedforwardController = new ArmFeedforward(0, 0, 0);
    private final SparkPIDController pidfController = primary.getPIDController();
    private double position, kP, kI, kD, kF;
    
    public ArmSubby(){
        kP = 0;
        kI = 0;
        kD = 0;
        kF = 0;
        // kV = 0;

        position = 0;

        primary.setIdleMode(IdleMode.kBrake);
        follower.setIdleMode(IdleMode.kBrake);
        follower.follow(primary, true);

        pidfController.setP(kP);
        pidfController.setI(kI);
        pidfController.setD(kD);
        pidfController.setFF(kF);//REPLACE 0 WITH ABS OFFSET

        pidfController.setOutputRange(-0.8, 0.8);    }

    public boolean atSetpoint() {
        return encoder.getPosition() >= position - 0.1 && encoder.getPosition() <= position + 0.1;
    }

    public double getPosition() {
        return encoder.getPosition();
    }

    public void setPosition(double targetPosition) {
        position = targetPosition;
    }

    @Override
    public void periodic() {
        pidfController.setReference(position, CANSparkBase.ControlType.kPosition);
    }

}