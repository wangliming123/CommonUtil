
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GenerateDimenFile {

    public static void main(String[] args) {
        File file = new File("values/dimens.xml");
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
			fos.write(("<dimen name=\"dp0_5\">0.5dp" + "</dimen>\n").getBytes());
            for (int i = 1; i <= 300; i++) {
                fos.write(("<dimen name=\"dp" + i + "\">" + i + "dp" + "</dimen>\n").getBytes());
            }
			for (int i = 320; i <= 600; i += 10) {
                fos.write(("<dimen name=\"dp" + i + "\">" + i + "dp" + "</dimen>\n").getBytes());
            }
			for (int i = 650; i <= 1000; i += 50) {
                fos.write(("<dimen name=\"dp" + i + "\">" + i + "dp" + "</dimen>\n").getBytes());
            }
			for (int i = 1100; i <= 1800; i += 100) {
                fos.write(("<dimen name=\"dp" + i + "\">" + i + "dp" + "</dimen>\n").getBytes());
            }
            fos.write("\n".getBytes());
			fos.write(("<dimen name=\"sp0_5\">0.5sp" + "</dimen>\n").getBytes());
            for (int i = 1; i <= 70; i++) {
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
