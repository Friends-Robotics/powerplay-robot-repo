/* Copyright (c) 2019 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.driveropmodes;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Consumer;
import org.firstinspires.ftc.robotcore.external.function.Continuation;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * This 2020-2021 OpMode illustrates the basics of using the TensorFlow Object Detection API to
 * determine the position of the Freight Frenzy game elements.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained below.
 */
@TeleOp(name = "Barcode Scanner", group = "Linear Opmode")
public class BarcodeScannerOpMode extends LinearOpMode {
    /*
     * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
     * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
     * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * Vuforia license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the Vuforia web site
     * and paste it in to your code on the next line, between the double quotes.
     */
    private static final String VUFORIA_KEY =
            "ASmf7uX/////AAABmTM1Hmvsakw2gXfUixO2NGQwY05LoFXWE43PK7h3QtlC5+8+AOy8qRhqszf5UY9UvlOn/vV76Po9E5iSATbsNcNVYHgxGnu+KGn3hya6FjYT/jGuay/PeTgMGnVJ5/Cy1cDQrJWk178dHgkT0MydhgI3nfU6qAvKui0Ad392QLghs0Pf6IdfcHzm/cNywFxCxb37QsC7mxbx6ULd/snwXaTa7PaYj26QJ32h3WJaGTTIRbYRmETJ4iyiSNpTlsyS5c250odtLw1MCkw4eHy64Bc/+J0XO4jCoLwjwUZMSn1e3lQeaa/KEgk635CfxesEocM7pejCgtLpSlATzr+3/hte7/fM5dtQQL4aQDKjr0O0";

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void runOpMode() throws InterruptedException {
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("sb4", 0);
            telemetry.update();

            vuforia.setFrameQueueCapacity(3);
            VuforiaLocalizer.CloseableFrame frame = vuforia.getFrameQueue().take();
            telemetry.addData("sb5", 0);
            telemetry.update();
            long numImages = frame.getNumImages();
            telemetry.addData("sb6", 0);
            telemetry.update();
            Image rgbFrame = null;

            telemetry.addData("sb3", 0);
            telemetry.update();

            for (int i = 0; i < numImages; i++) {
                rgbFrame = rgbFrame == null ? frame.getImage(i) : rgbFrame;
            }

            telemetry.addData("sb3", 1);
            telemetry.update();

            try (FileOutputStream stream = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + "/FIRST/data/image1")) {
                telemetry.addData("sb3", 0);
                telemetry.update();
                stream.write(rgbFrame.getPixels().array());
            } catch (FileNotFoundException e) {
                telemetry.addData("sb3", 0);
                telemetry.update();
                throw new RuntimeException(e);
            } catch (IOException e) {
                telemetry.addData("sb3", 0);
                telemetry.update();
                throw new RuntimeException(e);
            }

            telemetry.addData("sb2", 0);
            telemetry.update();

            //InputImage inputImage = InputImage.fromByteBuffer(rgbFrame.getPixels(), rgbFrame.getWidth(), rgbFrame.getHeight(), 0, rgbFrame.getFormat());

            telemetry.addData("sb7", 0);
            telemetry.update();

            //BarcodeScanner barcodeScanner = BarcodeScanning.getClient();

            telemetry.addData("sb1", 0);
            telemetry.update();

            /*
            barcodeScanner.process(inputImage).addOnSuccessListener(new OnSuccessListener<List<Barcode>>() {
                @Override
                public void onSuccess(List<Barcode> barcodes) {
                    telemetry.addData("Barcode value", barcodes.get(0).getRawValue());
                    telemetry.update();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    telemetry.addData("Failed to get barcode", e.getMessage());
                    telemetry.update();
                }
            });
            */
        }
    }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }
}
