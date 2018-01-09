package arch.wb.retail.productdetails;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import arch.wb.retail.R;

public class ProductDetailBindings {

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
