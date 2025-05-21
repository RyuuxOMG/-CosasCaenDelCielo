package autonoma.cosascaendelcielo.elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

public class PlainTextFileWriter extends Writer {
    private final Charset charset = Charset.forName("UTF-8");
    private String filePath;

    public PlainTextFileWriter(String filePath) {
        this.filePath = filePath;
    }

    public void write(String fileContent) throws IOException {
        File file = new File(this.filePath);
        FileWriter writer = new FileWriter(file, false);
        PrintWriter pw = new PrintWriter(writer);

        pw.println(fileContent);

        pw.close();
        writer.close();
    }
}