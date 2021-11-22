package phomenko.joiner.service.arguments;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigArgumentsReader implements ArgumentsReader {
    @Value("${datasource.first:}")
    @Getter
    private String first;
    @Value("${datasource.second:}")
    @Getter
    private String second;

    @Override
    public List<String> read() {
        return List.of(getFirst() == null ? "" : getFirst(), getSecond() == null ? "" : getSecond());
    }
}
