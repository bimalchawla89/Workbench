package arch.wb.retail.productslist;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import arch.wb.retail.R;

public class ProductBindings {

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        if (null != imageUrl && !imageUrl.startsWith("http")) {
            imageUrl = "http://" + imageUrl;
        }

        Picasso.with(view.getContext())
                .load(imageUrl)
                .error(R.drawable.ic_launcher_background)
                .into(view);
    }
}
