package org.firstinspires.ftc.teamcode.components;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.Servo;

public class Grab {
    private final ToggleServo leftGripServo, rightGripServo;
    private final ToggleServo leftDeployServo, rightDeployServo;
    //full 2 linear
    public Grab(Servo leftGripServo, Servo rightGripServo, Servo leftDeployServo, Servo rightDeployServo) {
        this.leftGripServo = new ToggleServo(leftGripServo, 0);
        this.rightGripServo = new ToggleServo(rightGripServo, 1);
        this.leftDeployServo = new ToggleServo(leftDeployServo, 0);
        this.rightDeployServo = new ToggleServo(rightDeployServo, 1);
    }

    public void run() {
        //toggle servo
        leftDeployServo.run(gamepad1.back);
        rightDeployServo.run(gamepad1.back);

        leftGripServo.run(gamepad1.left_bumper);
        rightGripServo.run(gamepad1.right_bumper);
    }
}