package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class Climber {
    private final DcMotorEx leftClimbMotor, rightClimbMotor;
    private final ToggleServo leftDeployServo, rightDeployServo;
    //full 2 linear
    public Climber(DcMotorEx leftClimbMotor, DcMotorEx rightClimbMotor, Servo leftDeployServo, Servo rightDeployServo) {
        this.leftClimbMotor = leftClimbMotor;
        this.rightClimbMotor = rightClimbMotor;
        this.leftDeployServo = new ToggleServo(leftDeployServo, 0);
        this.rightDeployServo = new ToggleServo(rightDeployServo, 1);

        this.leftClimbMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        this.rightClimbMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        this.rightClimbMotor.setDirection(DcMotorEx.Direction.REVERSE);
    }
    //one linear, left config default
    public Climber(DcMotorEx leftClimbMotor, Servo leftDeployServo) {
        this(leftClimbMotor, leftClimbMotor, leftDeployServo, leftDeployServo);
    }

    public void run() {
        //make motor stall if no button is pressed
        if(!gamepad1.a && !gamepad1.b) {
            this.leftClimbMotor.setPower(0);
            this.rightClimbMotor.setPower(0);
        }
        //toggle servo
        leftDeployServo.run(gamepad1.start);
        rightDeployServo.run(gamepad1.start);
        //make both linear pull the robot up/down: button A for pull up, B for down
        if(gamepad1.a) {
            this.leftClimbMotor.setPower(1);
            this.rightClimbMotor.setPower(1);
        }
        if(gamepad1.b) {
            this.leftClimbMotor.setPower(-1);
            this.rightClimbMotor.setPower(-1);
        }
    }
}