package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.linearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.components.Climber1;
import org.firstinspires.ftc.teamcode.components.Drivetrain;

@TeleOp
public class MainOpIdea1 {
    @Override
    public void runOpMode() {
        Drivetrain drivetrain = new Drivetrain(
                hardwareMap.get(DcMotorEx.class, "leftDriveMotor"),
                hardwareMap.get(DcMotorEx.class, "rightDriveMotor")
        );
        Climber1 climber = new Climber1(
                hardwareMap.get(DcMotorEx.class, "leftClimbMotor"),
                hardwareMap.get(Servo.class, "leftDeployServo"),
                hardwareMap.get(DcMotorEx.class, "rotateArmMotor")
        );

        linearOpMode.waitForStart();
        if(linearOpMode.opModeIsActive()) {
            while(linearOpMode.opModeIsActive()) {
                drivetrain.run();
                climber.run();
            }
        }
    }
}
