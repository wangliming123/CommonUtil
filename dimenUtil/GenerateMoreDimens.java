
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class GenerateMoreDimens {
	//base sw360dp(1920*1080 dpi480 destin)
    private static float[] times = {0.778f, 0.833f, 0.889f, 0.944f, 1f, 1.056f, 1.111f, 1.167f, 1.25f, 1.333f, 1.417f,
	1.5f, 1.583f, 1.667f, 1.833f, 2f, 2.222f, 2.5f, 2.778f};

    public static void main(String[] args) {
        File[] files = {
                new File("res/values-sw280dp/dimens.xml"),
                new File("res/values-sw300dp/dimens.xml"),
                new File("res/values-sw320dp/dimens.xml"),
                new File("res/values-sw340dp/dimens.xml"),
                new File("res/values-sw360dp/dimens.xml"),
                new File("res/values-sw380dp/dimens.xml"),
                new File("res/values-sw400dp/dimens.xml"),
                new File("res/values-sw420dp/dimens.xml"),
                new File("res/values-sw450dp/dimens.xml"),
                new File("res/values-sw480dp/dimens.xml"),
                new File("res/values-sw510dp/dimens.xml"),
                new File("res/values-sw540dp/dimens.xml"),
                new File("res/values-sw570dp/dimens.xml"),
                new File("res/values-sw600dp/dimens.xml"),
                new File("res/values-sw660dp/dimens.xml"),
                new File("res/values-sw720dp/dimens.xml"),
                new File("res/values-sw800dp/dimens.xml"),
                new File("res/values-sw900dp/dimens.xml"),
                new File("res/values-sw1000dp/dimens.xml")
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
        File baseFile = new File("values/dimens.xml");
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
                        float dimen = Float.parseFloat(dimenString.substring(0, dimenString.length() - 2)) * times[i];
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
