package autonoma.cosascaendelcielo.elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class PlainTextFileReader extends FileReader {

    private String content;
    private Charset encoding = Charset.forName("UTF-8");

    public PlainTextFileReader() {
    }

    public String read(String path) throws IOException {
        File file = new File(path);
        FileReader reader = new FileReader(file);
        BufferedReader buffer = new BufferedReader(reader);

        StringBuilder builder = new StringBuilder();
        String line;

        while ((line = buffer.readLine()) != null) {
            builder.append(line);
        }

        buffer.close();
        reader.close();

        this.content = builder.toString().trim();
        return this.content;
    }

    public String getFleaConfig() {
        return content;
    }
}