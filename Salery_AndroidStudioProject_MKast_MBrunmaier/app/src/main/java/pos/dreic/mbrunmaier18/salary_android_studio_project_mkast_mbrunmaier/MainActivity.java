package pos.dreic.mbrunmaier18.salary_android_studio_project_mkast_mbrunmaier;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Serializable{
    public ListView listView_shops;
    public static List<Shop> shopList = new ArrayList();
    public ShopAdapter shopAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView_shops = findViewById(R.id.id_list_shoplist);
        shopAdapter = new ShopAdapter(this, R.layout.layout_shoplist,shopList);
        listView_shops.setAdapter(shopAdapter);
        registerForContextMenu(listView_shops);
        listView_shops.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(shopList.get(position),ShopActivity.class);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_shoplist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        int viewId = v.getId();
        if (viewId == R.id.id_list_shoplist) {
            getMenuInflater().inflate(R.menu.menu_context_shop, menu);
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (id){
            case R.id.context_delete:
                shopList.remove(listView_shops.getAdapter().getItem(info.position));
                shopAdapter.notifyDataSetChanged();
                break;
            case R.id.context_edit:
                Shop shop = shopList.get(info.position);
                int previousSize = shopList.size();

                startActivity(shop,AddShopActivity.class);
                if (previousSize + 1 == shopList.size()) {
                    shopList.remove(shop);
                }
                shopAdapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.d("Menu select", "onOptionsItemSelected: " + id);

        switch (id){
            case R.id.menu_add:
                Shop shop = new Shop("");
                startActivity(shop,AddShopActivity.class);
                shopAdapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }

    public AlertDialog.Builder buildDialog(String title, String message){
        return new AlertDialog.Builder(this).setTitle(title).setMessage(message);
    }

    public void startActivity(Shop shop, Class cls) {
        Intent intent = new Intent(this, cls);
        intent.putExtra("shop", shop);
        startActivity(intent);
    }
}