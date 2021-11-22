package phomenko.joiner.service.arguments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import phomenko.joiner.domain.InputItem;
import phomenko.joiner.service.parser.Parser;
import phomenko.joiner.utils.ArgumentsReaderUtils;

import javax.annotation.PostConstruct;
import java.util.List;


@Service
public class ThisArgumentReader {

    private final Environment environment;
    private final Parser parser;

    public ThisArgumentReader(@Autowired Environment environment, Parser parser) {
        this.environment = environment;
        this.parser = parser;
    }

    @PostConstruct
    public void readCurrentArguments() {
        ArgumentsReader reader = new CommandLineArgumentsReader(environment);
        List<String> arguments = reader.read();
        if (arguments != null && !arguments.isEmpty() && arguments.get(0) != null && arguments.get(1) != null && !arguments.get(0).equals("") && !arguments.get(1).equals("")) {
            try {
                ArgumentsReaderUtils.read(arguments, parser).printAll().stream().map(InputItem::toString).forEach(System.out::println);
            } catch (Exception ignored) {
            }
        } else {
            reader = new ConfigArgumentsReader();
            arguments = reader.read();
            if (arguments != null && !arguments.isEmpty() && arguments.get(0) != null && arguments.get(1) != null && !arguments.get(0).equals("") && !arguments.get(1).equals("")) {
                try {
                    ArgumentsReaderUtils.read(arguments, parser).printAll().stream().map(InputItem::toString).forEach(System.out::println);
                } catch (Exception ignored) {
                }
            } else {
                reader = new InteractiveArgumentsReader();
                arguments = reader.read();
                if (arguments != null && !arguments.isEmpty() && arguments.get(0) != null && arguments.get(1) != null && !arguments.get(0).equals("") && !arguments.get(1).equals("")) {
                    try {
                        ArgumentsReaderUtils.read(arguments, parser).printAll().stream().map(InputItem::toString).forEach(System.out::println);
                    } catch (Exception ignored) {
                    }
                }
            }
        }
    }

}
