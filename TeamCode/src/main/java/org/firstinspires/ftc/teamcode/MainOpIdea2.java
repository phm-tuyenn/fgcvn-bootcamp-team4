package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.linearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "MainOpIdea2")
public class MainOpIdea2 {
    //define hardware
    private DcMotorEx leftMotor, rightMotor;
    private DcMotorEx climbMotor;
    private Servo deployArmServo;
    //drivetrain: 2 joystick for arcade
    private void drive() {
        double leftSpeed = gamepad1.left_stick_y + gamepad1.left_stick_x;
        double rightSpeed = gamepad1.left_stick_y - gamepad1.left_stick_x;

        leftMotor.setPower(leftSpeed);
        rightMotor.setPower(rightSpeed);
    }
    //climb
    private void climb() {
        //stall motor if no button is pressed
        if(!gamepad1.a && !gamepad1.b) climbMotor.setPower(0);
        //deploy the linear: start for release, back for reset
        if(gamepad1.start) {
            deployArmServo.setPosition(1);
        }
        if(gamepad1.back) {
            deployArmServo.setPosition(-1);
        }
        //pull the robot up/down: A for up, B for down
        if(gamepad1.a) {
            climbMotor.setPower(1);
        }
        if(gamepad1.b) {
            climbMotor.setPower(-1);
        }

    }

    @Override
    public void runOpMode() {
        //drivetrain motor
        leftMotor = hardwareMap.get(DcMotorEx.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotorEx.class, "rightMotor");
        //climbing motor & servo
        climbMotor = hardwareMap.get(DcMotorEx.class, "climbMotor");
        deployArmServo = hardwareMap.get(Servo.class, "deployArmServo");
        //set reverse for rightMotor in drivetrain and brake for climbMotor
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        climbMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        linearOpMode.waitForStart();
        if(linearOpMode.opModeIsActive()) {
            //init deploy servo
            deployArmServo.setPosition(-1);
            //main loop
            while(linearOpMode.opModeIsActive()) {
                drive();
                climb();
            }
        }
    }
}
