package pos.dreic.mbrunmaier18.salary_android_studio_project_mkast_mbrunmaier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {
    public Shop shop;
    public ListView listView_shoppinglist;
    public List<ShoppingItem> shoppingList = new ArrayList();
    public CurrentShoppingAdapter shoppingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_shoppinglist);
        listView_shoppinglist = findViewById(R.id.id_list_current_shopping);
        shoppingAdapter = new CurrentShoppingAdapter(this,R.layout.layout_current_shoppinglist,shoppingList);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        shop = MainActivity.shopList.get(Integer.parseInt(bundle.getString("current Shop")));
        MainActivity.SuperMessage superMessage = (MainActivity.SuperMessage)bundle.getSerializable("current Shop");

    }
}