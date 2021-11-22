package phomenko.joiner.service.arguments;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class InteractiveArgumentsReader implements ArgumentsReader {
    @Override
    public List<String> read() {
        System.out.println("Give me first path/URL");
        Scanner scanner = new Scanner(System.in);
        String first = scanner.nextLine();
        System.out.println("Give me second path/URL");
        String second = scanner.nextLine();
        return List.of(first, second);
    }
}
