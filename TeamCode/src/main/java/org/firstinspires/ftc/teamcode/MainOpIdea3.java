package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.linearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "MainOpIdea3")
public class MainOpIdea3 {
    //define hardware
    private DcMotorEx leftMotor, rightMotor;
    private DcMotorEx climbLeftMotor, climbRightMotor;
    private Servo deployArmLeftServo, deployArmRightServo;
    private Servo deployGrabLeftServo, deployGrabRightServo;
    private Servo gripLeftServo, gripRightServo;
    //drivetrain: 2 joystick for arcade
    private void drive() {
        double leftSpeed = gamepad1.left_stick_y + gamepad1.left_stick_x;
        double rightSpeed = gamepad1.left_stick_y - gamepad1.left_stick_x;

        leftMotor.setPower(leftSpeed);
        rightMotor.setPower(rightSpeed);
    }
    //climb
    private void climb() {
        //make motor stall if no button is pressed
        if(!gamepad1.a && !gamepad1.b) {
            climbLeftMotor.setPower(0);
            climbRightMotor.setPower(0);
        }
        //deploy both linear: button start for release, back for reset
        if(gamepad1.start) {
            deployArmLeftServo.setPosition(1);
            deployArmRightServo.setPosition(-1);
        }
        if(gamepad1.back) {
            deployArmLeftServo.setPosition(-1);
            deployArmRightServo.setPosition(1);
        }
        //make both linear pull the robot up/down: button A for pull up, B for down
        if(gamepad1.a) {
            climbLeftMotor.setPower(1);
            climbRightMotor.setPower(1);
        }
        if(gamepad1.b) {
            climbLeftMotor.setPower(-1);
            climbRightMotor.setPower(-1);
        }
    }
    //grab and grip
    private void grabGrip() {
        //deploy grab: left bumper for release, right for reset
        if(gamepad1.left_bumper) {
            deployGrabLeftServo.setPosition(1);
            deployGrabRightServo.setPosition(-1);
        }
        if(gamepad1.right_bumper) {
            deployGrabLeftServo.setPosition(-1);
            deployGrabRightServo.setPosition(1);
        }
        //gripper: left trigger for down, right for up
        if(gamepad1.left_trigger > 0) {
            deployGrabLeftServo.setPosition(1);
            deployGrabRightServo.setPosition(-1);
        }
        if(gamepad1.right_trigger > 0) {
            deployGrabLeftServo.setPosition(-1);
            deployGrabRightServo.setPosition(1);
        }
    }

    @Override
    public void runOpMode() {
        //drivetrain motor
        leftMotor = hardwareMap.get(DcMotorEx.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotorEx.class, "rightMotor");
        //climbing motor and servo
        climbLeftMotor = hardwareMap.get(DcMotorEx.class, "climbLeftMotor");
        climbRightMotor = hardwareMap.get(DcMotorEx.class, "climbRightMotor");
        deployArmLeftServo = hardwareMap.get(Servo.class, "deployArmLeftServo");
        deployArmRightServo = hardwareMap.get(Servo.class, "deployArmRightServo");
        //grab & grip servo
        deployGrabLeftServo = hardwareMap.get(Servo.class, "deployGrabLeftServo");
        deployGrabRightServo = hardwareMap.get(Servo.class, "deployGrabRightServo");
        gripLeftServo = hardwareMap.get(Servo.class, "gripLeftServo");
        gripRightServo = hardwareMap.get(Servo.class, "gripRightServo");
        //set reverse for right motor in drivetrain and climbing
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        climbRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        //set brake for motor in climbing mechanism
        climbLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        climbRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        linearOpMode.waitForStart();
        if(linearOpMode.opModeIsActive()) {
            //set initial position for servo
            deployArmLeftServo.setPosition(-1);
            deployArmRightServo.setPosition(1);

            deployGrabLeftServo.setPosition(-1);
            deployGrabRightServo.setPosition(1);

            gripLeftServo.setPosition(-1);
            gripRightServo.setPosition(1);
            //main loop
            while(linearOpMode.opModeIsActive()) {
                drive();
                climb();
            }
        }
    }
}
