package phomenko.joiner.service.data;

import phomenko.joiner.domain.InputItem;

import java.util.Collection;

public interface DataProvider {
    Collection<InputItem> read(String input);
}