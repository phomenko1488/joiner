package phomenko.joiner.service.controller;

import org.springframework.stereotype.Service;
import phomenko.joiner.domain.Item;
import phomenko.joiner.service.parser.Parser;
import phomenko.joiner.utils.ArgumentsReaderUtils;

import java.util.List;
import java.util.Map;

@Service
public class ControllerService {
    public Map<String, Item> getItems(List<String> arguments, Parser parser) {
        return ArgumentsReaderUtils.read(arguments, parser).getItems();
    }
}
