package org.firstinspires.ftc.teamcode.components;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class Climber3 {
    private final MotorGroup leftClimbMotorGroup, rightClimbMotorGroup;
    private final ToggleServo leftDeployServo, rightDeployServo;
    public Climber3(MotorGroup leftClimbMotorGroup, MotorGroup rightClimbMotorGroup, Servo leftDeployServo, Servo rightDeployServo) {
        this.leftClimbMotorGroup = leftClimbMotorGroup;
        this.rightClimbMotorGroup = rightClimbMotorGroup;
        this.leftDeployServo = new ToggleServo(leftDeployServo, 0);
        this.rightDeployServo = new ToggleServo(rightDeployServo, 1);

        this.leftClimbMotorGroup.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        this.rightClimbMotorGroup.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        this.rightClimbMotorGroup.setDirection(DcMotorEx.Direction.REVERSE);
    }

    public void run() {
        //toggle servo
        leftDeployServo.run(gamepad1.start);
        rightDeployServo.run(gamepad1.start);
        //make both linear pull the robot up/down: button A for pull up, B for down
        if(gamepad1.a) {
            this.leftClimbMotorGroup.setPower(1);
            this.rightClimbMotorGroup.setPower(1);
        }
        else if(gamepad1.b) {
            this.leftClimbMotorGroup.setPower(-1);
            this.rightClimbMotorGroup.setPower(-1);
        }
        else {
            this.leftClimbMotorGroup.setPower(0);
            this.rightClimbMotorGroup.setPower(0);
        }
    }
}