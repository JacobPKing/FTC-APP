package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 */
public class PB11857
{
    /* Public OpMode members. */

    /** The motor for the left wheels of the robot. */
    public DcMotor leftDrive;

    /** The motor for the right wheels of the robot. */
    public DcMotor rightDrive;

    /** The motor for the up and down movement of the arm. */
    public DcMotor upDownArmMotor;

    /** The motor for the forward and backward movement of the arm. */
    public DcMotor forwardBackwardArmMotor;

    /** The servo for the left claw on the arm. */
    public Servo left_Claw;

    /** The servo for the right claw on the arm. */
    public Servo right_Claw;

    public static final double MID_SERVO       =  0.0 ;
    public static final double ARM_UP_POWER    =  1 ;
    public static final double ARM_DOWN_POWER  = -1 ;

    /* Local OpMode members. */
    private HardwareMap hwMap;
    private ElapsedTime period = new ElapsedTime();
    private double leftClawPos = ARM_DOWN_POWER;
    private double rightClawPos = ARM_UP_POWER;

    /* Initialize standard hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftDrive = hwMap.get(DcMotor.class, "left_drive");
        rightDrive = hwMap.get(DcMotor.class, "right_drive");
        upDownArmMotor = hwMap.get(DcMotor.class, "up_down_arm");
        forwardBackwardArmMotor = hwMap.get(DcMotor.class, "forward_backward_arm");
        //sensor = hwMap.get(TouchSensor.class, "touch_sensor");
        leftDrive.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightDrive.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Set all motors to zero power
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        upDownArmMotor.setPower(0);
        forwardBackwardArmMotor.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        upDownArmMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        forwardBackwardArmMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos.
        left_Claw = hwMap.get(Servo.class, "left_Claw");
        right_Claw = hwMap.get(Servo.class, "right_Claw");
        left_Claw.setPosition(0.5);
        right_Claw.setPosition(0.5);
    }

    public double clamp(double var, double min, double max) {
        return var > max ? max : var < min ? min : var;
    }
}

