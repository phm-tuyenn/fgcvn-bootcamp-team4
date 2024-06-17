    package org.firstinspires.ftc.teamcode.components;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class Climber1 {
    private final DcMotorEx climbMotor, rotateArmMotor;
    public Climber1(DcMotorEx climbMotor, DcMotorEx rotateArmMotor) {
        this.climbMotor = climbMotor;
        this.rotateArmMotor = rotateArmMotor;

        this.rotateArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.climbMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public void run() {
        //make both linear pull the robot up/down: button A for pull up, B for down
        if(gamepad1.a) climbMotor.setPower(1);
        else if(gamepad1.b) climbMotor.setPower(-1);
        else climbMotor.setPower(0);
        //rotate arm: button X for rotate right, Y for left
        if(gamepad1.x) rotateArmMotor.setPower(0.3);
        else if(gamepad1.y) rotateArmMotor.setPower(-0.3);
        else rotateArmMotor.setPower(0);
    }
}