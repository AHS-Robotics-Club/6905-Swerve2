package frc.robot.subsystems;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class IntakeSubby extends SubsystemBase{
    private CANSparkMax intakeMotor = new CANSparkMax(11, MotorType.kBrushless);

    public void intake(){
        intakeMotor.set(0.45);
    }
    public void stop(){
        intakeMotor.stopMotor();
    }
    public void outtake(){
        intakeMotor.stopMotor();
    }
}
