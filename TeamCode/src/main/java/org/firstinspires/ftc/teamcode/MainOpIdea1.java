package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.linearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "MainOpIdea1")
public class MainOpIdea1 {
    private DcMotorEx leftMotor, rightMotor;
    private DcMotorEx climbMotor, armControlMotor;

    private void drive() {
        double leftSpeed = gamepad1.left_stick_y + gamepad1.left_stick_x;
        double rightSpeed = gamepad1.left_stick_y - gamepad1.left_stick_x;

        leftMotor.setPower(leftSpeed);
        rightMotor.setPower(rightSpeed);
    }

    private void climb() {
        climbMotor.setPower(0);
        armControlMotor.setPower(0);
        if(gamepad1.a) {
            climbMotor.setPower(1);
        }
        if(gamepad1.b) {
            climbMotor.setPower(-1);
        }
        if(gamepad1.x) {
            armControlMotor.setPower(0.3);
        }
        if(gamepad1.y) {
            armControlMotor.setPower(-0.3);
        }
    }

    @Override
    public void runOpMode() {
        leftMotor = hardwareMap.get(DcMotorEx.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotorEx.class, "rightMotor");
        climbMotor = hardwareMap.get(DcMotorEx.class, "climbMotor");
        armControlMotor = hardwareMap.get(DcMotorEx.class, "armControlMotor");

        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        climbMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armControlMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        linearOpMode.waitForStart();
        if(linearOpMode.opModeIsActive()) {
            while(linearOpMode.opModeIsActive()) {
                drive();
                climb();
            }
        }
    }
}
