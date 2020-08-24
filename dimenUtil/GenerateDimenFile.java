package com.gjcw.minemap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GenerateDimenFile {

    public static void main(String[] args) {
        File file = new File(".app/values/dimens.xml");
        if (!file.getParentFile().exists()) {
             boolean parentSuccess = file.getParentFile().mkdirs();
        }
        FileOutputStream fos = null;
        try {
            if (!file.exists()) {
                boolean success = file.createNewFile();
            }
            fos = new FileOutputStream(file);
            fos.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n".getBytes());
            fos.write("<resources>\n".getBytes());
            for (int i = 0; i <= 300; i++) {
                fos.write(("<dimen name=\"dp" + i + "\">" + i + "dp" + "</dimen>\n").getBytes());
            }
			for (int i = 320; i <= 600; i += 20) {
                fos.write(("<dimen name=\"dp" + i + "\">" + i + "dp" + "</dimen>\n").getBytes());
            }
			for (int i = 650; i <= 1000; i += 50) {
                fos.write(("<dimen name=\"dp" + i + "\">" + i + "dp" + "</dimen>\n").getBytes());
            }
			for (int i = 1100; i <= 1800; i += 100) {
                fos.write(("<dimen name=\"dp" + i + "\">" + i + "dp" + "</dimen>\n").getBytes());
            }
            fos.write("\n".getBytes());
            for (int i = 0; i <= 70; i++) {
                fos.write(("<dimen name=\"sp" + i + "\">" + i + "sp" + "</dimen>\n").getBytes());
            }
            fos.write("</resources>\n".getBytes());
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
