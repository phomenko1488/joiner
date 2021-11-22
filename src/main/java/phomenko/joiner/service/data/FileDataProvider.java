package phomenko.joiner.service.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import phomenko.joiner.domain.InputItem;
import phomenko.joiner.service.parser.Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Service
public class FileDataProvider implements DataProvider {
    private final Parser parser;

    public FileDataProvider(Parser parser) {
        this.parser = parser;
    }

    @Override
    public Collection<InputItem> read(String input) {
        Path path = Path.of(input);
        File inputFile = new File(path.toString());
        List<InputItem> items = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray array = new JSONArray(builder.toString());
        array.forEach(elem -> {
            System.out.println(elem);
            try {
                JSONObject elem1 = (JSONObject) elem;
                items.add(parser.parse(elem1.toString()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        return items;
    }
}
