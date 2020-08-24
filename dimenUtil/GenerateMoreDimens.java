package com.gjcw.minemap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class GenerateMoreDimens {
    private static float[] times = {0.778f, 0.833f, 0.889f, 0.944f, 1f, 1.167f, 1.333f, 1.5f, 1.667f, 2f};

    public static void main(String[] args) {
        File[] files = {
                new File(".app/values-sw280dp/dimens.xml"),
                new File(".app/values-sw300dp/dimens.xml"),
                new File(".app/values-sw320dp/dimens.xml"),
                new File(".app/values-sw340dp/dimens.xml"),
                new File(".app/values-sw360dp/dimens.xml"),
                new File(".app/values-sw420dp/dimens.xml"),
                new File(".app/values-sw480dp/dimens.xml"),
                new File(".app/values-sw540dp/dimens.xml"),
                new File(".app/values-sw600dp/dimens.xml"),
                new File(".app/values-sw720dp/dimens.xml"),
        };
        StringBuilder[] stringBuilders = {
                new StringBuilder(),
                new StringBuilder(),
                new StringBuilder(),
                new StringBuilder(),
                new StringBuilder(),
                new StringBuilder(),
                new StringBuilder(),
                new StringBuilder(),
                new StringBuilder(),
                new StringBuilder()
        };
        File baseFile = new File(".app/values/dimens.xml");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(baseFile));
            String lineString;
            while ((lineString = reader.readLine()) != null) {
                if (!lineString.contains("dimen")){
                    for (StringBuilder sb : stringBuilders) {
                        sb.append(lineString).append("\n");
                    }
                }else {
                    for (int i = 0; i < times.length; i++) {
                        StringBuilder sb = stringBuilders[i];
                        sb.append(lineString.substring(0, lineString.indexOf(">") + 1));
                        String dimenString = lineString.substring(lineString.indexOf(">") + 1, lineString.indexOf("</"));
                        float dimen = Integer.parseInt(dimenString.substring(0, dimenString.length() - 2)) * times[i];
                        //四舍五入保留1位小数
                        dimen = new BigDecimal(dimen).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                        sb.append(dimen);
                        sb.append(dimenString.substring(dimenString.length() - 2));
                        sb.append(lineString.substring(lineString.indexOf("</"))).append("\n");
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < files.length; i++) {
            FileOutputStream fos = null;
            try {
                File file = files[i];
                if (!file.getParentFile().exists()) {
                    boolean parentSuccess = file.getParentFile().mkdirs();
                }
                if (!file.exists()) {
                    boolean success = file.createNewFile();
                }
                fos = new FileOutputStream(file);

                fos.write(stringBuilders[i].toString().getBytes());
                fos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
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
}
