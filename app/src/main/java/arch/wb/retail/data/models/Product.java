//package arch.wb.retail.data.models;
//
//import android.arch.persistence.room.ColumnInfo;
//import android.arch.persistence.room.Entity;
//import android.arch.persistence.room.PrimaryKey;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//
///**
// * Created by Arun.Kumar04 on 12/20/2017.
// */
//@Entity(tableName = "product")
//public class Product {
//    @PrimaryKey
//    @NonNull
//    @ColumnInfo(name = "productId")
//    private final String productId;
//
//    @Nullable
//    @ColumnInfo(name = "name")
//    private final String name;
//
//    @Nullable
//    @ColumnInfo(name = "description")
//    private final String description;
//
//    @Nullable
//    @ColumnInfo(name = "imageUrl")
//    private final String imageUrl;
//
//    public Product(@NonNull String productId, String name, String description, String imageUrl) {
//        this.productId = productId;
//        this.name = name;
//        this.description = description;
//        this.imageUrl = imageUrl;
//    }
//
//    @NonNull
//    public String getProductId() {
//        return productId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    @Override
//    public String toString() {
//        return "id :" + productId + ", name : "+ name + ", description : "+ description + ", imageUrl : "+ imageUrl;
//    }
//}
