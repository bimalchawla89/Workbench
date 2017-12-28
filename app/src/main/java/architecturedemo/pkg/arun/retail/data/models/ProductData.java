package architecturedemo.pkg.arun.retail.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class ProductData implements Serializable {

    @JsonProperty("@search.score")
    private Float searchScore;
    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("category")
    private String category;
    @JsonProperty("categoryId")
    private String categoryId;
    @JsonProperty("subcategory")
    private String subcategory;
    @JsonProperty("subcategoryId")
    private String subcategoryId;
    @JsonProperty("modifiers")
    private List<String> modifiers = null;
    @JsonProperty("color")
    private List<String> color = null;
    @JsonProperty("size")
    private List<String> size = null;
    @JsonProperty("price")
    private Float price;
    @JsonProperty("image_domain")
    private String imageDomain;
    @JsonProperty("image_suffix")
    private String imageSuffix;

    @JsonProperty("@search.score")
    public Float getSearchScore() {
        return searchScore;
    }

    @JsonProperty("@search.score")
    public void setSearchScore(Float searchScore) {
        this.searchScore = searchScore;
    }

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

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("categoryId")
    public String getCategoryId() {
        return categoryId;
    }

    @JsonProperty("categoryId")
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @JsonProperty("subcategory")
    public String getSubcategory() {
        return subcategory;
    }

    @JsonProperty("subcategory")
    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    @JsonProperty("subcategoryId")
    public String getSubcategoryId() {
        return subcategoryId;
    }

    @JsonProperty("subcategoryId")
    public void setSubcategoryId(String subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    @JsonProperty("modifiers")
    public List<String> getModifiers() {
        return modifiers;
    }

    @JsonProperty("modifiers")
    public void setModifiers(List<String> modifiers) {
        this.modifiers = modifiers;
    }

    @JsonProperty("color")
    public List<String> getColor() {
        return color;
    }

    @JsonProperty("color")
    public void setColor(List<String> color) {
        this.color = color;
    }

    @JsonProperty("size")
    public List<String> getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(List<String> size) {
        this.size = size;
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

}
