package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.linearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.components.Climber3;
import org.firstinspires.ftc.teamcode.components.Drivetrain;
import org.firstinspires.ftc.teamcode.components.Grab;
import org.firstinspires.ftc.teamcode.components.MotorGroup;

@TeleOp
public class MainOpIdea3 {
    @Override
    public void runOpMode() {
        MotorGroup leftClimbMotor = new MotorGroup(
                hardwareMap.get(DcMotorEx.class, "leftClimbMotor1"),
                hardwareMap.get(DcMotorEx.class, "leftClimbMotor2"),
                false
        );
        MotorGroup rightClimbMotor = new MotorGroup(
                hardwareMap.get(DcMotorEx.class, "rightClimbMotor1"),
                hardwareMap.get(DcMotorEx.class, "rightClimbMotor2"),
                true
        );
        Drivetrain drivetrain = new Drivetrain(
                hardwareMap.get(DcMotorEx.class, "leftDriveMotor"),
                hardwareMap.get(DcMotorEx.class, "rightDriveMotor")
        );
        Climber3 climber = new Climber3(
                leftClimbMotor,
                rightClimbMotor,
                hardwareMap.get(Servo.class, "leftDeployServo"),
                hardwareMap.get(Servo.class, "rightDeployServo")
        );
        Grab grab = new Grab(
                hardwareMap.get(Servo.class, "leftGripServo"),
                hardwareMap.get(Servo.class, "rightGripServo"),
                hardwareMap.get(Servo.class, "leftDeployServo"),
                hardwareMap.get(Servo.class, "rightDeployServo")
        );
        linearOpMode.waitForStart();
        if(linearOpMode.opModeIsActive()) {
            while(linearOpMode.opModeIsActive()) {
                drivetrain.run();
                climber.run();
                grab.run();
            }
        }
    }
}
