package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.Servo;

public class ToggleServo {
    private final Servo servo;
    private boolean changed;
    private final double startPos, endPos;

    public ToggleServo(Servo servo, double startPos) {
        this.servo = servo;
        this.changed = false;
        this.startPos = startPos;
        if (startPos == 0)
            this.endPos = 1;
        else
            this.endPos = 0;
        this.servo.setPosition(startPos);
    }
    public void run(boolean pressed) {
        if(pressed && !changed) {
            if(servo.getPosition() == startPos)
                servo.setPosition(endPos);
            else
                servo.setPosition(startPos);
            changed = true;
        } else if(!pressed)
            changed = false;
    }
}