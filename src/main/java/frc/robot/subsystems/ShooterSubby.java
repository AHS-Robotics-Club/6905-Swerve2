package frc.robot.subsystems;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;



public class ShooterSubby extends SubsystemBase{
    private CANSparkMax loadMotor = new CANSparkMax(14   , MotorType.kBrushless);
    private CANSparkMax shootMotor = new CANSparkMax(15, MotorType.kBrushless);
    

    public void shoot(){
        new SequentialCommandGroup(
            new InstantCommand(() -> loadMotor.set(-0.05)),
            new WaitCommand(0.1),
            new InstantCommand(() -> loadMotor.set(0.8)),
            new InstantCommand(() -> shootMotor.set(0.8)),
            new WaitCommand(1),
            new InstantCommand(() -> loadMotor.stopMotor()),
            new InstantCommand(() -> shootMotor.stopMotor())
        );
    }
    public void stop(){
        loadMotor.stopMotor();
        shootMotor.stopMotor();
    }
    public void load(){
        new SequentialCommandGroup(
            new InstantCommand(() -> loadMotor.set(0.15)),
            new WaitCommand(0.5),
            new InstantCommand(() -> loadMotor.stopMotor())
        );
    }
}
