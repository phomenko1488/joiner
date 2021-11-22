package phomenko.joiner.service.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import phomenko.joiner.domain.InputItem;

@Service
public class ParserImpl implements Parser {
    @Override
    public InputItem parse(String input) throws JsonProcessingException {
        return new ObjectMapper().readValue(input, InputItem.class);
    }
}
