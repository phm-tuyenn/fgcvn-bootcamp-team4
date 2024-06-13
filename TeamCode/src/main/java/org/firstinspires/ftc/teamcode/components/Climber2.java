package org.firstinspires.ftc.teamcode.components;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class Climber2 {
    private final DcMotorEx climbMotor;
    private final ToggleServo deployServo;
    public Climber2(DcMotorEx climbMotor, Servo deployServo) {
        this.climbMotor = climbMotor;
        this.deployServo = new ToggleServo(deployServo, 0);

        this.climbMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public void run() {
        //make motor stall if no button is pressed
        if(!gamepad1.a && !gamepad1.b) {
            this.climbMotor.setPower(0);
        }
        //toggle servo
        deployServo.run(gamepad1.start);
        //make both linear pull the robot up/down: button A for pull up, B for down
        if(gamepad1.a) {
            this.climbMotor.setPower(1);
        }
        if(gamepad1.b) {
            this.climbMotor.setPower(-1);
        }
    }
}