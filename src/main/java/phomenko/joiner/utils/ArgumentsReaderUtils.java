package phomenko.joiner.utils;

import phomenko.joiner.domain.InputItem;
import phomenko.joiner.domain.Item;
import phomenko.joiner.exceprion.NonUniqueUUIDException;
import phomenko.joiner.service.data.DataProvider;
import phomenko.joiner.service.data.FileDataProvider;
import phomenko.joiner.service.data.WebDataProvider;
import phomenko.joiner.service.holder.Holder;
import phomenko.joiner.service.parser.Parser;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ArgumentsReaderUtils {

    public static DataProvider getDataProvider(String first, Parser parser) {
        if (first.contains("https://"))
            return new WebDataProvider(parser);
        else
            return new FileDataProvider(parser);
    }

    public static Holder read(List<String> arguments, Parser parser) {
        String first = arguments.get(0);
        String second = arguments.get(1);
        DataProvider firstDataProvider = getDataProvider(first, parser);
        DataProvider secondDataProvider = getDataProvider(second, parser);
        Holder holder = new Holder();
        readAndAddAll(first, second, firstDataProvider, secondDataProvider, holder);
        return holder;
    }

    public static void readAndAddAll(String first, String second, DataProvider firstDataProvider, DataProvider secondDataProvider, Holder holder) {
        Collection<InputItem> firstItems = firstDataProvider.read(first);
        Collection<InputItem> secondItems = secondDataProvider.read(second);
        firstItems.stream()
                .map(item -> Map.entry(item.getProductUuid(), new Item(item.getProductName(), item.getAmount())))
                .forEach(elem -> {
                    try {
                        holder.add(elem.getKey(), elem.getValue());
                    } catch (NonUniqueUUIDException e) {
                        System.out.println("Non unique product uuid");
                    }
                });
        secondItems.stream()
                .map(item -> Map.entry(item.getProductUuid(), new Item(item.getProductName(), item.getAmount())))
                .forEach(elem -> {
                    try {
                        holder.add(elem.getKey(), elem.getValue());
                    } catch (NonUniqueUUIDException e) {
                        System.out.println("Non unique product uuid");
                    }
                });
    }

}
