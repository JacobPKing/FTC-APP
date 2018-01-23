package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "AutonomousMode", group = "Autonomous")
public class AutonomousMode extends LinearOpMode {

    private HardwareData hardware;
    private ElapsedTime runtime = new ElapsedTime();
    private final String vuforiaLicenseKey = "AQjTnn3/////AAAAGUsGTIdhQUggtBzbiWb6v+6Ldxgyt7GICNkUM5imF" +
            "M9j4vJZ3PCaUtlYiyw3n5255HW4d8Rese9WgppYxiTUrRonJa6uBL1py5qUo7VBy2SANWSjEi" +
            "ToaGZOJdwQRKeapMOWlC48B+nFdKOVVVZp3P8Whm6lM8jyR4yc7lCuV1+3wjLiIq/d45F2Z44+gqfKP6" +
            "i7zL79ELgljBBWyeWsy1QOIk3fsp7HQp/ynwJOPQU3EaBfm483XQcBR+Ax5BwKHgE/mnY5I09sRAIFHH" +
            "eYzmKcQnU7icaszqqBzsn9a+38cSgzWlE0LaA9Mp2Q2scXdlJGkz7Qi98no18152OifsySxHoT28F4+kV3ggTx7wlQ";

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * Math.PI);
    static final double     DRIVE_SPEED             = 0.5;
    static final double     TURN_SPEED              = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {
        this.hardware = new HardwareData(hardwareMap);

        hardware.getLeftDrive().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hardware.getRightDrive().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();

        hardware.getLeftDrive().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hardware.getRightDrive().setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        encoderDrive(DRIVE_SPEED, 12, 12, 5);
    }

    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) throws InterruptedException {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = hardware.getLeftDrive().getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = hardware.getRightDrive().getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            hardware.getLeftDrive().setTargetPosition(newLeftTarget);
            hardware.getRightDrive().setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            hardware.getLeftDrive().setMode(DcMotor.RunMode.RUN_TO_POSITION);
            hardware.getRightDrive().setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            hardware.getLeftDrive().setPower(Math.abs(speed));
            hardware.getRightDrive().setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (hardware.getLeftDrive().isBusy() && hardware.getRightDrive().isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        hardware.getLeftDrive().getCurrentPosition(),
                        hardware.getRightDrive().getCurrentPosition());
                telemetry.update();

                // Allow time for other processes to run.
                idle();
            }

            // Stop all motion;
            hardware.getLeftDrive().setPower(0);
            hardware.getRightDrive().setPower(0);

            // Turn off RUN_TO_POSITION
            hardware.getLeftDrive().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            hardware.getRightDrive().setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }

}
