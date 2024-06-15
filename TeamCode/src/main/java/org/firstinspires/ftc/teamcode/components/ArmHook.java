package org.firstinspires.ftc.teamcode.components;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmHook {
    private final DcMotorEx armMotor, liftMotor;
    private final Servo alignServo;
    private double angle = 0.00000; //angle for servo
    private double angleChange = 0.00555; //constant for angle change
    public ArmHook(DcMotorEx armMotor, DcMotorEx liftMotor, Servo alignServo) {
        this.armMotor = armMotor;
        this.liftMotor = liftMotor;
        this.alignServo = alignServo;
    }
    public void run() {
        //set servo position to desired angle
        alignServo.setPosition(angle);
        //make motor stall if no button is pressed
        if(!gamepad1.x && !gamepad1.y) armMotor.setPower(0);
        if(!gamepad1.start) liftMotor.setPower(0);
        //rotate arm: X for right, Y for left
        if(gamepad1.x) armMotor.setPower(0.3);
        if(gamepad1.y) armMotor.setPower(-0.3);
        //align hook
        if(gamepad1.left_bumper) angle -= angleChange;
        if(gamepad1.right_bumper) angle += angleChange;
        if(angle < 0) angle = 0;
        if(angle > 1) angle = 1;
        //lift robot
        if(gamepad1.start) liftMotor.setPower(1);
        if(gamepad1.back) liftMotor.setPower(-1);
    }
}
