package pos.dreic.mbrunmaier18.salary_android_studio_project_mkast_mbrunmaier;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ShopAdapter extends ArrayAdapter<Shop> {
    private List<Shop> shopList;
    private int layoutId;
    private LayoutInflater inflater;

    public ShopAdapter(@NonNull Context ctx, int layoutId, @NonNull List<Shop> shopList) {
        super(ctx, layoutId, shopList);
        this.shopList = shopList;
        this.layoutId = layoutId;
        this.inflater = (LayoutInflater) ctx.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return shopList.size();
    }

    @Override
    public Shop getItem(int i) {
        return shopList.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Shop shop = shopList.get(i);
        View listItem = (view == null) ? inflater.inflate(this.layoutId, null) : view;
        ((TextView) listItem.findViewById(R.id.id_show_name)).setText(shop.getName());
        return listItem;
    }
}
