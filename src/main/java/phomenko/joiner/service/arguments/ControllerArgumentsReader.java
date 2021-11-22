package phomenko.joiner.service.arguments;

import lombok.Getter;

import java.util.List;

@Getter
public class ControllerArgumentsReader implements ArgumentsReader {
    private final String first;
    private final String second;

    public ControllerArgumentsReader(String first, String second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public List<String> read() {
        return List.of(getFirst(), getSecond());
    }
}
