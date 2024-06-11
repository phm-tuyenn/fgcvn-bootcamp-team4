package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.linearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class MainOpIdea1 {
    private DcMotorEx rotateArmMotor;
    private void rotateArm() {
        if(!gamepad1.x && !gamepad1.y) rotateArmMotor.setPower(0);
        if(gamepad1.x) rotateArmMotor.setPower(0.3);
        if(gamepad1.y) rotateArmMotor.setPower(-0.3);
    }
    @Override
    public void runOpMode() {
        Drivetrain drivetrain = new Drivetrain(
                hardwareMap.get(DcMotorEx.class, "leftDriveMotor"),
                hardwareMap.get(DcMotorEx.class, "rightDriveMotor")
        );
        Climber climber = new Climber(
                hardwareMap.get(DcMotorEx.class, "leftClimbMotor"),
                hardwareMap.get(Servo.class, "leftDeployServo")
        );
        rotateArmMotor = hardwareMap.get(DcMotorEx.class, "rotateArmMotor");

        linearOpMode.waitForStart();
        if(linearOpMode.opModeIsActive()) {
            while(linearOpMode.opModeIsActive()) {
                drivetrain.run();
                climber.run();
                rotateArm();
            }
        }
    }
}
