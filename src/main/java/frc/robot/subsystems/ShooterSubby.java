package frc.robot.subsystems;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;



public class ShooterSubby extends SubsystemBase{
    private CANSparkMax intakeMotor = new CANSparkMax(11, MotorType.kBrushless);
    private CANSparkMax shootMotor = new CANSparkMax(11, MotorType.kBrushless);

    public void shoot(){
        new SequentialCommandGroup(
            new InstantCommand(() -> intakeMotor.set(-0.05)),
            new WaitCommand(0.1),
            new InstantCommand(() -> intakeMotor.set(0.8)),
            new InstantCommand(() -> shootMotor.set(0.8)),
            new WaitCommand(0.5)
        );
    }
    public void stop(){
        intakeMotor.stopMotor();
        shootMotor.stopMotor();
    }
}
