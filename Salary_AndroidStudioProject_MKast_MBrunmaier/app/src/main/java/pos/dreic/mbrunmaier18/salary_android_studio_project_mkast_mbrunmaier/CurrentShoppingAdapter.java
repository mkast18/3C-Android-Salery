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

public class CurrentShoppingAdapter extends ArrayAdapter<shoppingItem> {
    private List<shoppingItem> shoppingList = new ArrayList<>();
    private int layoutId;
    private LayoutInflater inflater;

    public CurrentShoppingAdapter(@NonNull Context ctx, int layoutId, @NonNull List<shoppingItem> shoppingList) {
        super(ctx,layoutId,shoppingList);
        this.shoppingList = shoppingList;
        this.layoutId = layoutId;
        this.inflater = (LayoutInflater) ctx.getSystemService(LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return shoppingList.size();
    }
    @Override
    public shoppingItem getItem(int i) {
        return shoppingList.get(i);
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        shoppingItem item = shoppingList.get(i);
        View listItem = (view == null) ? inflater.inflate(this.layoutId, null) : view;
        ((TextView) listItem.findViewById(R.id.id_show_name)).setText(item.getName());
        ((TextView) listItem.findViewById(R.id.id_show_numbers)).setText(item.getNumbers());
        ((TextView) listItem.findViewById(R.id.id_show_price)).setText(item.getPrice().toString());
        return listItem;
    }
}

