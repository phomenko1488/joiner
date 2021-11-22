package phomenko.joiner.service.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import phomenko.joiner.domain.InputItem;

public interface Parser {
    InputItem parse(String input) throws JsonProcessingException;
}
