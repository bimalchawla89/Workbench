package arch.wb.retail.data.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@Entity(tableName = "categoryData")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryData implements Serializable {

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

    public CategoryData() {
        // Empty constructor
    }

    public CategoryData(@NonNull String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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

}
