// package frc.robot.subsystems;

// import com.revrobotics.CANSparkBase.IdleMode;
// import com.revrobotics.CANSparkBase;
// import com.revrobotics.CANSparkMax;
// import com.revrobotics.RelativeEncoder;
// import com.revrobotics.SparkPIDController;
// import com.revrobotics.CANSparkLowLevel.MotorType;

// import edu.wpi.first.math.controller.ArmFeedforward;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;

// public class ArmSubby extends SubsystemBase{

//     private final CANSparkMax primary = new CANSparkMax(9, MotorType.kBrushless);
//     private final CANSparkMax follower = new CANSparkMax(10, MotorType.kBrushless);

//     private final RelativeEncoder encoder = primary.getAlternateEncoder(8192);

//     private final ArmFeedforward feedforwardController = new ArmFeedforward(0, 0, 0);
//     private final SparkPIDController pidfController = primary.getPIDController();
//     private double position, kP, kI, kD, kG, kV;
    
//     public ArmSubby(){
//         kP = 0;
//         kI = 0;
//         kD = 0;
//         kG = 0;
//         kV = 0;

//         position = 0;

//         primary.setIdleMode(IdleMode.kBrake);
//         follower.setIdleMode(IdleMode.kBrake);
//         follower.follow(primary, true);

//         pidfController.setP(kP);
//         pidfController.setI(kI);
//         pidfController.setD(kD);
//         pidfController.setFF((new ArmFeedforward(0, kG, kV)).calculate(0, 1));//REPLACE 0 WITH ABS OFFSET

//         pidfController.setOutputRange(-0.8, 0.8);

//         SmartDashboard.putNumber("kP", kP);
//         SmartDashboard.putNumber("kI", kI);
//         SmartDashboard.putNumber("kD", kD);
//         SmartDashboard.putNumber("kG", kG);
//         SmartDashboard.putNumber("kV", kV);
//         SmartDashboard.putNumber("pos", position);
//         // SmartDashboard.putN("PID Controller", new PIDF);
//     }

//     public boolean atSetpoint() {
//         return encoder.getPosition() >= position - 0.01 && encoder.getPosition() <= position + 0.01;
//     }

//     public double getPosition() {
//         return encoder.getPosition();
//     }

//     public void setPosition(double targetPosition) {
//         position = targetPosition;
//     }

//     @Override
//     public void periodic() {
//         double p = SmartDashboard.getNumber("kP", 0);
//         double i = SmartDashboard.getNumber("kI", 0);
//         double d = SmartDashboard.getNumber("kD", 0);
//         double g = SmartDashboard.getNumber("kG", 0);
//         double v = SmartDashboard.getNumber("kV", 0);
//         double pos = SmartDashboard.getNumber("pos", 0);

//     // if PID coefficients on SmartDashboard have changed, write new values to controller
//         if((p != kP)) { pidfController.setP(p); kP = p; }
//         if((i != kI)) { pidfController.setI(i); kI = i; }
//         if((d != kD)) { pidfController.setD(d); kD = d; }
//         if(g != kG || v != kV){
//             pidfController.setFF((new ArmFeedforward(0, g, v)).calculate(0, 1));//REPLACE 0 WITH ABSOLUTE OFFSET
//         }
//         if(pos != position){position = pos;}
//         pidfController.setReference(position, CANSparkBase.ControlType.kPosition);
//     }

// }