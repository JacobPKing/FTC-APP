package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IrSeekerSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

public class HardwareData {

    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor extensionArm;
    private DcMotor liftArm;
    private Servo leftClaw;
    private Servo rightClaw;
    //private IrSeekerSensor irSensor;
    //private ColorSensor colorSensor;
    private OpticalDistanceSensor distanceSensor;

    public HardwareData(HardwareMap hardware) {
        this.leftDrive = hardware.get(DcMotor.class, "left_drive");
        this.rightDrive = hardware.get(DcMotor.class, "right_drive");
        this.extensionArm = hardware.get(DcMotor.class, "forward_backward_arm");
        this.liftArm = hardware.get(DcMotor.class, "up_down_arm");
        this.leftClaw = hardware.get(Servo.class, "left_Claw");
        this.rightClaw = hardware.get(Servo.class, "right_Claw");
        this.distanceSensor = hardware.get(OpticalDistanceSensor.class, "distance_sensor");
        //this.irSensor = hardware.get(IrSeekerSensor.class, "ir_sensor");
        //this.colorSensor = hardware.get(ColorSensor.class, "color_sensor");

        this.leftDrive.setDirection(DcMotor.Direction.FORWARD);
        this.rightDrive.setDirection(DcMotor.Direction.REVERSE);
        this.extensionArm.setDirection(DcMotor.Direction.FORWARD);
        this.liftArm.setDirection(DcMotor.Direction.FORWARD);

        this.leftDrive.setPower(0);
        this.rightDrive.setPower(0);
        this.extensionArm.setPower(0);
        this.liftArm.setPower(0);

        this.leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.extensionArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.liftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //this.irSensor.setMode(IrSeekerSensor.Mode.MODE_1200HZ);

        //this.leftClaw.setPosition(0);
        //this.rightClaw.setPosition(1);
    }

    public DcMotor getLeftDrive() {
        return leftDrive;
    }

    public DcMotor getRightDrive() {
        return rightDrive;
    }

    public DcMotor getExtensionArm() {
        return extensionArm;
    }

    public DcMotor getLiftArm() {
        return liftArm;
    }

    public Servo getLeftClaw() {
        return leftClaw;
    }

    public Servo getRightClaw() {
        return rightClaw;
    }

    public OpticalDistanceSensor getDistanceSensor() {
        return distanceSensor;
    }

    /*public IrSeekerSensor getIrSensor() {
        return irSensor;
    }*/

    /*public ColorSensor getColorSensor() {
        return colorSensor;
    }*/

}
