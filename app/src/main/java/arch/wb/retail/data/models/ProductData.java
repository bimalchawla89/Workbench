package arch.wb.retail.data.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@Entity(tableName = "productData")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductData implements Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @JsonProperty("id")
    private String id;
    @Nullable
    @ColumnInfo(name = "title")
    @JsonProperty("title")
    private String title;
    @Nullable
    @ColumnInfo(name = "description")
    @JsonProperty("description")
    private String description;
    @Nullable
    @ColumnInfo(name = "price")
    @JsonProperty("price")
    private Float price;
    @Nullable
    @ColumnInfo(name = "image_domain")
    @JsonProperty("image_domain")
    private String imageDomain;
    @Nullable
    @ColumnInfo(name = "image_suffix")
    @JsonProperty("image_suffix")
    private String imageSuffix;
    @Nullable
    @ColumnInfo(name = "productAdded")
    @JsonProperty("productAdded")
    private boolean productAdded;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("price")
    public Float getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Float price) {
        this.price = price;
    }

    @JsonProperty("image_domain")
    public String getImageDomain() {
        return imageDomain;
    }

    @JsonProperty("image_domain")
    public void setImageDomain(String imageDomain) {
        this.imageDomain = imageDomain;
    }

    @JsonProperty("image_suffix")
    public String getImageSuffix() {
        return imageSuffix;
    }

    @JsonProperty("image_suffix")
    public void setImageSuffix(String imageSuffix) {
        this.imageSuffix = imageSuffix;
    }

    @JsonProperty("productAdded")
    public boolean getProductAdded() {
        return productAdded;
    }

    @JsonProperty("productAdded")
    public void setProductAdded(@Nullable boolean productAdded) {
        this.productAdded = productAdded;
    }
}
