package org.firstinspires.ftc.teamcode.components;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Drivetrain {
    private final DcMotorEx leftDriveMotor, rightDriveMotor;

    public Drivetrain(DcMotorEx leftDriveMotor, DcMotorEx rightDriveMotor) {
        this.leftDriveMotor = leftDriveMotor;
        this.rightDriveMotor = rightDriveMotor;
    }

    public void run() {
        double leftSpeed = gamepad1.left_stick_y + gamepad1.left_stick_x;
        double rightSpeed = gamepad1.left_stick_y - gamepad1.left_stick_x;

        this.leftDriveMotor.setPower(leftSpeed);
        this.rightDriveMotor.setPower(rightSpeed);
    }

}