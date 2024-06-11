package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.linearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "MainOpIdea1")
public class MainOpIdea1 {
    //define hardware
    private DcMotorEx leftMotor, rightMotor;
    private DcMotorEx climbMotor, armControlMotor;
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
        //make motor stall if no button is pressed
        if(!gamepad1.a && !gamepad1.b) climbMotor.setPower(0);
        if(!gamepad1.x && !gamepad1.y) armControlMotor.setPower(0);
        //deploy linear: button start for release, back for reset
        if(gamepad1.start) {
            deployArmServo.setPosition(1);
        }
        if(gamepad1.back) {
            deployArmServo.setPosition(-1);
        }
        //pull the robot up/down: button A for pull up, B for down
        if(gamepad1.a) {
            climbMotor.setPower(1);
        }
        if(gamepad1.b) {
            climbMotor.setPower(-1);
        }
        //change the angle of linear arm: button X for rotate right, Y for left
        if(gamepad1.x) {
            armControlMotor.setPower(0.3);
        }
        if(gamepad1.y) {
            armControlMotor.setPower(-0.3);
        }
    }

    @Override
    public void runOpMode() {
        //drivetrain motor
        leftMotor = hardwareMap.get(DcMotorEx.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotorEx.class, "rightMotor");
        //climbing motor & servo
        climbMotor = hardwareMap.get(DcMotorEx.class, "climbMotor");
        armControlMotor = hardwareMap.get(DcMotorEx.class, "armControlMotor");
        deployArmServo = hardwareMap.get(Servo.class, "deployArmServo");
        //set reverse for rightMotor in drivetrain and brake for motor in climbing mechanism
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        climbMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armControlMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        linearOpMode.waitForStart();
        if(linearOpMode.opModeIsActive()) {
            //set initial position for servo
            deployArmServo.setPosition(-1);
            //main loop
            while(linearOpMode.opModeIsActive()) {
                drive();
                climb();
            }
        }
    }
}
