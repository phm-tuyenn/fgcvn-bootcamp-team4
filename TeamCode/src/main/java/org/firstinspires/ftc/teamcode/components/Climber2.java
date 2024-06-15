package org.firstinspires.ftc.teamcode.components;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class Climber2 {
    private final DcMotorEx leftClimbMotor, rightClimbMotor;
    public Climber2(DcMotorEx leftClimbMotor, DcMotorEx rightClimbMotor) {
        this.leftClimbMotor = leftClimbMotor;
        this.rightClimbMotor = rightClimbMotor;
        this.leftClimbMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        this.rightClimbMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        this.rightClimbMotor.setDirection(DcMotorEx.Direction.REVERSE);
    }

    public void run() {
        //make motor stall if no button is pressed
        if(!gamepad1.a && !gamepad1.b) {
            this.leftClimbMotor.setPower(0);
            this.rightClimbMotor.setPower(0);
        }
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