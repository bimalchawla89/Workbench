package architecturedemo.pkg.arun.retail.productslist;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import architecturedemo.pkg.arun.retail.R;

public class ProductDetailBindings {

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_drawer)
                .into(view);
    }
}
