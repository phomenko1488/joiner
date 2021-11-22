package phomenko.joiner.service.data;

import org.json.JSONArray;
import org.springframework.stereotype.Service;
import phomenko.joiner.domain.InputItem;
import phomenko.joiner.service.parser.Parser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class WebDataProvider implements DataProvider {
    private final Parser parser;

    public WebDataProvider(Parser parser) {
        this.parser = parser;
    }

    @Override
    public Collection<InputItem> read(String input) {
        URL url;
        try {
            url = new URL(input);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            String content = (String) connection.getContent();
            System.out.println(content);
            List<InputItem> res = new ArrayList<>();
            new JSONArray(content).forEach(o -> res.add((InputItem) o));
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
