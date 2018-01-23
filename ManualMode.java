package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "ManualMode", group = "ManualMode")
public class ManualMode extends LinearOpMode {

    private static final double CLAW_INCREMENT = 0.01;
    private static final double CLAW_POS_MIN = 0;
    private static final double CLAW_POS_MAX = 1;
    private static final double ARM_MOTOR_SPEED = 0.1;
    private static final double MOTOR_OFF = 0;

    private HardwareData data;
    private double clawPos;

    @Override
    public void runOpMode() throws InterruptedException {
        this.data = new HardwareData(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            updateDriveMotors();
            updateExtensionArm();
            updateLiftArm();
            updateClaws();

            idle();
        }
    }

    private void updateDriveMotors() {
        data.getLeftDrive().setPower(gamepad1.right_stick_y);
        data.getRightDrive().setPower(gamepad1.left_stick_y);

        //data.getLeftDrive().setPower(gamepad1.left_stick_y);
        //data.getRightDrive().setPower(gamepad1.right_stick_y);
    }

    private void updateExtensionArm() {
        if (gamepad1.y || gamepad2.y)
            extendArm();
        else if (gamepad1.a || gamepad2.a)
            retractArm();
        else
            data.getExtensionArm().setPower(MOTOR_OFF);
    }

    private void extendArm() {
        data.getExtensionArm().setPower(-0.3);
    }

    private void retractArm() {
        data.getExtensionArm().setPower(0.3);
    }

    private void updateLiftArm() {
        if ((gamepad1.left_bumper && gamepad1.dpad_up) || (gamepad2.left_bumper && gamepad2.dpad_up))
            raiseArm(ARM_MOTOR_SPEED * 2);
        else if (gamepad1.dpad_up || gamepad2.dpad_up)
            raiseArm(ARM_MOTOR_SPEED);
        else if ((gamepad1.left_bumper && gamepad1.dpad_down) || (gamepad2.left_bumper && gamepad2.dpad_down))
            lowerArm(-(ARM_MOTOR_SPEED * 2));
        else if (gamepad1.dpad_down || gamepad2.dpad_down)
            lowerArm(-ARM_MOTOR_SPEED);
        else
            data.getLiftArm().setPower(MOTOR_OFF);
    }

    private void raiseArm(double speed) {
        data.getLiftArm().setPower(speed);
    }

    private void lowerArm(double speed) {
        data.getLiftArm().setPower(speed);
    }

    private void updateClaws() {
        boolean rightTrigger = gamepad1.right_trigger > 0 || gamepad2.right_trigger > 0;
        if (rightTrigger)
            closeClaws();
        else if (gamepad1.right_bumper || gamepad2.right_bumper)
            openClaws();
    }

    private void openClaws() {
        increaseClawPos();

        data.getLeftClaw().setPosition(clawPos);
        data.getRightClaw().setPosition(1 - clawPos);
    }

    private void increaseClawPos() {
        clawPos = clamp(clawPos + CLAW_INCREMENT, CLAW_POS_MIN, CLAW_POS_MAX);
    }

    private void closeClaws() {
        decreaseClawPos();

        data.getLeftClaw().setPosition(clawPos);
        data.getRightClaw().setPosition(1 - clawPos);
    }

    private void decreaseClawPos() {
        clawPos = clamp(clawPos - CLAW_INCREMENT, CLAW_POS_MIN, CLAW_POS_MAX);
    }

    private double clamp(double var, double min, double max) {
        return var > max ? max : var < min ? min : var;
    }

}
