package pos.dreic.mbrunmaier18.salary_android_studio_project_mkast_mbrunmaier;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public ListView listView_shops;
    public List<Shop> shopList = new ArrayList();
    public ShopAdapter shopAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView_shops = findViewById(R.id.id_list_shoplist);
        shopAdapter = new ShopAdapter(this, android.R.layout.simple_list_item_1,shopList);
        listView_shops.setAdapter(shopAdapter);
    }
}