package phomenko.joiner.service.holder;

import lombok.Data;
import phomenko.joiner.domain.InputItem;
import phomenko.joiner.domain.Item;
import phomenko.joiner.exceprion.NonUniqueUUIDException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Data
public class Holder {
    private final Map<String, Item> items;

    public Holder() {
        this.items = new HashMap<>();
    }

    public void add(String uuid, Item item) throws NonUniqueUUIDException {
        if (getItems().containsKey(uuid)) {
            throw new NonUniqueUUIDException();
        } else
            getItems().put(uuid, item);
    }

    public List<InputItem> printAll() {
        return items.entrySet().stream()
                .map((entry) -> new InputItem(entry.getKey(), entry.getValue().getAmount(), entry.getValue().getName()))
                .collect(toList());
    }
}
