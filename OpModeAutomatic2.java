package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IrSeekerSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;

@Autonomous(name = "Auto2", group = "Automatic")
public class OpModeAutomatic2 extends LinearOpMode {

    private PB11857 robot;
    private IrSeekerSensor irSeekerSensor;
    private ColorSensor colorSensor;
    private TouchSensor touchSensor;

    public OpModeAutomatic2() {
        this.robot = new PB11857();
    }

    private final String vuforiaLicenseKey = "AQjTnn3/////AAAAGUsGTIdhQUggtBzbiWb6v+6Ldxgyt7GICNkUM5imF" +
            "M9j4vJZ3PCaUtlYiyw3n5255HW4d8Rese9WgppYxiTUrRonJa6uBL1py5qUo7VBy2SANWSjEi" +
            "ToaGZOJdwQRKeapMOWlC48B+nFdKOVVVZp3P8Whm6lM8jyR4yc7lCuV1+3wjLiIq/d45F2Z44+gqfKP6" +
            "i7zL79ELgljBBWyeWsy1QOIk3fsp7HQp/ynwJOPQU3EaBfm483XQcBR+Ax5BwKHgE/mnY5I09sRAIFHH" +
            "eYzmKcQnU7icaszqqBzsn9a+38cSgzWlE0LaA9Mp2Q2scXdlJGkz7Qi98no18152OifsySxHoT28F4+kV3ggTx7wlQ";

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        irSeekerSensor = hardwareMap.get(IrSeekerSensor.class, "ir_sensor");
        colorSensor = hardwareMap.get(ColorSensor.class, "color_sensor");
        touchSensor = hardwareMap.get(TouchSensor.class, "touch_sensor");

        irSeekerSensor.setMode(IrSeekerSensor.Mode.MODE_600HZ);

        robot.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();

        robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        setClawsOpen();
    }

    private void setClawsOpen() {
        robot.left_Claw.setPosition(0);
        robot.right_Claw.setPosition(1);
    }
}
