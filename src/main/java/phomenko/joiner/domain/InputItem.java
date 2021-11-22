package phomenko.joiner.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputItem {
    private String productUuid;
    private String productName;
    private long amount;

    public InputItem(String productUuid, long amount, String productName) {
        this.productUuid = productUuid;
        this.productName = productName;
        this.amount = amount;
    }

    public String toString() {
        return "{product uuid : " + productUuid +
                " , product name : " + productName +
                " , amount : " + amount;
    }
}
