package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubby extends SubsystemBase{

    private final CANSparkMax primary = new CANSparkMax(9, MotorType.kBrushless);
    private final CANSparkMax follower = new CANSparkMax(10, MotorType.kBrushless);

    private final RelativeEncoder encoder = primary.getAlternateEncoder(8192);

    private final ArmFeedforward feedforwardController = new ArmFeedforward(0, 0, 0);
    private final SparkPIDController pidfController = primary.getPIDController();
    private double position;
    
    public ArmSubby(){
        position = 0;

        primary.setIdleMode(IdleMode.kBrake);
        follower.setIdleMode(IdleMode.kBrake);
        follower.follow(primary, true);

        pidfController.setP(0);
        pidfController.setI(0);
        pidfController.setD(0);
        pidfController.setFF(feedforwardController.calculate(0, 0));

        pidfController.setOutputRange(-0.8, 0.8);

        SmartDashboard.putString("position", String.valueOf(getPosition()));
    }

    public boolean atSetpoint() {
        return encoder.getPosition() >= position - 0.01 && encoder.getPosition() <= position + 0.01;
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