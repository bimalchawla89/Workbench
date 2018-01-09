package arch.wb.retail.data.models;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class ProductList implements Serializable {

    @JsonProperty("@odata.context")
    private String odataContext;
    @JsonProperty("value")
    private List<ProductData> value = null;

    @JsonProperty("@odata.context")
    public String getOdataContext() {
        return odataContext;
    }

    @JsonProperty("@odata.context")
    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    @JsonProperty("value")
    public List<ProductData> getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(List<ProductData> value) {
        this.value = value;
    }

}
