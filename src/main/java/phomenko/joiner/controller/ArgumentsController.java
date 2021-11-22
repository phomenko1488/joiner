package phomenko.joiner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import phomenko.joiner.dto.Request;
import phomenko.joiner.service.arguments.ArgumentsReader;
import phomenko.joiner.service.arguments.ControllerArgumentsReader;
import phomenko.joiner.service.controller.ControllerService;
import phomenko.joiner.service.parser.Parser;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/read")
public class ArgumentsController {
    private final Parser parser;
    private final ControllerService controllerService;

    @Autowired
    public ArgumentsController(Parser parser, ControllerService controllerService) {
        this.parser = parser;
        this.controllerService = controllerService;
    }

    @PostMapping
    public ResponseEntity<?> getResponse(@RequestBody Request request) {
        ArgumentsReader reader = new ControllerArgumentsReader(request.getFirst(), request.getSecond());
        List<String> read = reader.read();
        return ResponseEntity.ok(controllerService.getItems(read, parser));
    }
}
