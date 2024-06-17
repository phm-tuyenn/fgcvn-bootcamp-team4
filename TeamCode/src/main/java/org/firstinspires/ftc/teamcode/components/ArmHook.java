package org.firstinspires.ftc.teamcode.components;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmHook {
    private final DcMotorEx armMotor, liftMotor;
    private final Servo alignServo;
    private double angle = 0.00000; //angle for servo

    public ArmHook(DcMotorEx armMotor, DcMotorEx liftMotor, Servo alignServo) {
        this.armMotor = armMotor;
        this.liftMotor = liftMotor;
        this.alignServo = alignServo;
    }
    public void run() {
        //set servo position to desired angle
        alignServo.setPosition(angle);
        //rotate arm: X for right, Y for left
        if(gamepad1.x) armMotor.setPower(0.3);
        else if(gamepad1.y) armMotor.setPower(-0.3);
        else armMotor.setPower(0);
        //align hook
        //constant for angle change
        double angleChange = 0.00555;
        if(gamepad1.left_bumper) angle -= angleChange;
        if(gamepad1.right_bumper) angle += angleChange;
        angle = Math.min(1, Math.max(0, angle));
        //lift robot
        if(gamepad1.start) liftMotor.setPower(1);
        else if(gamepad1.back) liftMotor.setPower(-1);
        else liftMotor.setPower(0);
    }
}
