package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.linearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.components.ArmHook;
import org.firstinspires.ftc.teamcode.components.Climber2;
import org.firstinspires.ftc.teamcode.components.Drivetrain;

@TeleOp
public class MainOpIdea2 {
    @Override
    public void runOpMode() {
        Drivetrain drivetrain = new Drivetrain(
                hardwareMap.get(DcMotorEx.class, "leftDriveMotor"),
                hardwareMap.get(DcMotorEx.class, "rightDriveMotor")
        );
        Climber2 climber = new Climber2(
                hardwareMap.get(DcMotorEx.class, "leftClimbMotor"),
                hardwareMap.get(DcMotorEx.class, "rightClimbMotor")
        );
        ArmHook armHook = new ArmHook(
                hardwareMap.get(DcMotorEx.class, "armMotor"),
                hardwareMap.get(DcMotorEx.class, "liftMotor"),
                hardwareMap.get(Servo.class, "alignServo")
        );

        linearOpMode.waitForStart();
        if(linearOpMode.opModeIsActive()) {
            while(linearOpMode.opModeIsActive()) {
                drivetrain.run();
                climber.run();
                armHook.run();
            }
        }
    }
}
