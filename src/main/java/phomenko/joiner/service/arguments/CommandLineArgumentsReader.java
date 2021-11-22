package phomenko.joiner.service.arguments;

import lombok.Getter;
import org.springframework.core.env.Environment;

import java.util.List;

@Getter
public class CommandLineArgumentsReader implements ArgumentsReader {
    private final String first;
    private final String second;

    public CommandLineArgumentsReader(Environment env) {
        this.first = env.getProperty("datasource.first") != null ? env.getProperty("datasource.first") : "";
        this.second = env.getProperty("datasource.second") != null ? env.getProperty("datasource.second") : "";
    }

    @Override
    public List<String> read() {
        return List.of(getFirst(), getSecond());
    }
}
