package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="OpMode Manual", group="Manual")
public class OpModeManual extends LinearOpMode {

    private static final double INCREMENT = 0.01;
    private static final double UP_DOWN_MOTOR_SPEED = 0.3;
    private static final double FORWARD_BACKWARD_MOTOR_SPEED = 0.3;

    private Servo left_Claw;
    private Servo right_Claw;

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    private double posLeft = 0;
    private double posRight= 1;

    private PB11857 robot = new PB11857();

    @Override
    public void runOpMode() {
        sendDataToController("Status", "Initialized");
        //telemetry.addData("Status", "Initialized");
        //telemetry.update();
        robot.init(hardwareMap);
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).

        // Deprecated: Members already declared in PB11857 class.
        //leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        //rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        //leftArm = hardwareMap.get(DcMotor.class, "left_arm");
        left_Claw = hardwareMap.get(Servo.class, "left_Claw");
        right_Claw = hardwareMap.get(Servo.class, "right_Claw");
        //sensor = hardwareMap.get(TouchSensor.class, "touch_sensor");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        robot.leftDrive.setDirection(DcMotor.Direction.FORWARD);
        robot.rightDrive.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        /*
        * I want to know what this does
        */
        gamepad1.setJoystickDeadzone(0.05f);

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            //updateDriveMotors();

            // updateVerticalArmMotors();
            updateHorizontalArmMotors();
            // updateClaws();

            robot.leftDrive.setPower(gamepad1.right_stick_y);
            robot.rightDrive.setPower(gamepad1.left_stick_y);

            // If the 'a' button is pressed, close the claws
           /* if ((gamepad1.a || gamepad2.a))
                robot.upDownArmMotor.setPower(0.3);

            else if (gamepad1.b || gamepad2.b)
                robot.upDownArmMotor.setPower(-0.3);
            else if (robot.upDownArmMotor.getCurrentPosition() < 0)
                robot.upDownArmMotor.setPower(0.1);
            else if (robot.upDownArmMotor.getCurrentPosition() > 2500)
                robot.upDownArmMotor.setPower(-0.1);

            else {
                robot.upDownArmMotor.setPower(0);*/

                //updateClaws();

                if (gamepad1.x || gamepad2.x) {
                    posLeft += INCREMENT;
                    posRight -= INCREMENT;
                } else if (gamepad1.y || gamepad2.y) {
                    posLeft -= INCREMENT;
                    posRight += INCREMENT;
                }

                //if(posLeft>0.8) posLeft = 0.8;
                //if(posRight>1) posRight = 1;
                //if(posLeft<0) posLeft = 0;
                //if(posRight<0.2) posRight = 0.2;

                if (gamepad1.dpad_up) {
                    robot.upDownArmMotor.setPower(UP_DOWN_MOTOR_SPEED);
                } else if (gamepad1.dpad_down) {
                    robot.upDownArmMotor.setPower(-UP_DOWN_MOTOR_SPEED);
                } else if (robot.upDownArmMotor.getCurrentPosition() < 0) {
                    robot.upDownArmMotor.setPower(0.1);
                } else if (robot.upDownArmMotor.getCurrentPosition() > 2500) {
                    robot.upDownArmMotor.setPower(-0.1);
                } else {
                    robot.upDownArmMotor.setPower(0);
                }

                left_Claw.setPosition(posLeft);
                right_Claw.setPosition(posRight);

                //leftClaw.getPosition();
                sendDataToController("Left Servo: " + String.valueOf(left_Claw.getPosition()));
                sendDataToController("Right Servo: " + String.valueOf(right_Claw.getPosition()));

                //telemetry.addLine(String.valueOf(robot.rightClaw.getPosition()) + " right servo");
                //telemetry.addLine(String.valueOf(robot.leftClaw.getPosition()) + " left servo");
                //telemetry.update();

                // Send calculated power to wheels
                //leftDrive.setPower(leftPower);
                //rightDrive.setPower(rightPower);

            /*if (sensor.isPressed()) {
                telemetry.addLine("Touch sensor pressed");
                telemetry.update();
            }*/

                // Show the elapsed game time and wheel power.
                //sendDataToController("Status", "Runtime: " + runtime.toString());
                //telemetry.addData("Status", "Run Time: " + runtime.toString());
                //telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
                //telemetry.update();
            }
        }

    private void updateDriveMotors() {
        robot.leftDrive.setPower(gamepad1.left_stick_y);
        robot.rightDrive.setPower(gamepad1.right_stick_y);
    }

    private void updateVerticalArmMotors() {
        if (gamepad1.dpad_up) {
            robot.upDownArmMotor.setPower(UP_DOWN_MOTOR_SPEED);
        } else if (gamepad1.dpad_down) {
            robot.upDownArmMotor.setPower(-UP_DOWN_MOTOR_SPEED);
        } else if (robot.upDownArmMotor.getCurrentPosition() < 0) {
            robot.upDownArmMotor.setPower(0.1);
        } else if (robot.upDownArmMotor.getCurrentPosition() > 2500) {
            robot.upDownArmMotor.setPower(-0.1);
        } else {
            robot.upDownArmMotor.setPower(0);
        }

        //sendDataToController("UpDownMotorPos: " + robot.upDownArmMotor.getCurrentPosition());
    }

    private void updateHorizontalArmMotors() {
        if (gamepad1.a || gamepad2.a) {
            robot.forwardBackwardArmMotor.setPower(-FORWARD_BACKWARD_MOTOR_SPEED);
        } else if (gamepad1.b || gamepad2.b) {
            robot.forwardBackwardArmMotor.setPower(FORWARD_BACKWARD_MOTOR_SPEED);
        } else {
            robot.forwardBackwardArmMotor.setPower(0);
        }

        if (robot.forwardBackwardArmMotor.getCurrentPosition() > 0) {
            // Motor is extended too far back; extend forward
            robot.forwardBackwardArmMotor.setPower(-FORWARD_BACKWARD_MOTOR_SPEED);
        } else if (robot.forwardBackwardArmMotor.getCurrentPosition() < -6000) {
            // Motor is extended too far out; retract backward
            robot.forwardBackwardArmMotor.setPower(FORWARD_BACKWARD_MOTOR_SPEED);
        }

        //double idealPos = robot.clamp(robot.forwardBackwardArmMotor.getCurrentPosition(), -6000, 0);

        //sendDataToController("FBMotorPos: " + robot.forwardBackwardArmMotor.getCurrentPosition());
    }

    private void updateClaws() {
        if (gamepad1.x || gamepad2.x) {
            posLeft += INCREMENT;
            posRight -= INCREMENT;
        } else if (gamepad1.y || gamepad2.y) {
            posLeft -= INCREMENT;
            posRight += INCREMENT;
        }

       // posLeft = robot.clamp(posLeft, 0, 0.8);
       // posRight = robot.clamp(posRight, 0.2, 1);

        //if(posLeft > 0.8) posLeft = 0.8;
        //if(posRight>1) posRight = 1;
        //if(posLeft<0) posLeft = 0;
        //if(posRight<0.2) posRight = 0.2;

        left_Claw.setPosition(posLeft);
        right_Claw.setPosition(posRight);
    }

    private void sendDataToController(String caption, Object value) {
        telemetry.addData(caption, value);
        telemetry.update();
    }

    private void sendDataToController(String message) {
        telemetry.addLine(message);
        telemetry.update();
    }

}
