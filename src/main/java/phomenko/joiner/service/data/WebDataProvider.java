package phomenko.joiner.service.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import phomenko.joiner.domain.InputItem;
import phomenko.joiner.service.parser.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            List<InputItem> res = new ArrayList<>();
            new JSONArray(content.toString()).forEach(json -> {
                JSONObject j = (JSONObject) json;
                try {
                    res.add(parser.parse(j.toString()));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

            });
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
