package com.qa.utils;

import io.appium.java_client.screenrecording.CanRecordScreen;
import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class VideoManager {

    private GlobalParams params = new GlobalParams();
    TestUtils testUtils = new TestUtils();

    public void startRecording() {
        ((CanRecordScreen) new DriverManager().getDriver()).startRecordingScreen();
    }

    public void stopRecording(String scenarioName) {
        try {
            String media = ((CanRecordScreen) new DriverManager().getDriver()).stopRecordingScreen();
            String videoDirPath = "Videos" + File.separator + params.getPlatformName() +
                    File.separator + params.getDeviceName() + "_" + params.getUdid();

            File videoDir = new File(videoDirPath);
            synchronized (videoDir) {
                if (!videoDir.exists()) {
                    videoDir.mkdirs();
                }
            }

            File videoFile = new File(videoDir, scenarioName + "_" + testUtils.currentDateTime() + ".mp4");

            try (FileOutputStream stream = new FileOutputStream(videoFile)) {
                stream.write(Base64.decodeBase64(media));
                testUtils.logger().info("Video Path: {}", videoFile.getAbsolutePath());
            } catch (IOException e) {
                testUtils.logger().error("Error during video recording: {}", e.getMessage());
            }
        } catch (Exception e) {
            testUtils.logger().error("Video recording failed: {}", e.getMessage());
        }
    }

}
