package arch.wb.retail.productslist;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import arch.wb.retail.R;

public class ProductBindings {

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        if(!imageUrl.startsWith("http")) {
            imageUrl = "http://" + imageUrl;
        }
        Picasso.with(view.getContext())
                .load(imageUrl)
                .error(R.drawable.ic_launcher_background)
                .into(view);

//        Picasso.Builder builder = new Picasso.Builder(view.getContext());
//        builder.listener(new Picasso.Listener() {
//            @Override
//            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
//                exception.printStackTrace();
//            }
//        });
//        builder.build().load(imageUrl).into(view);
    }
}
