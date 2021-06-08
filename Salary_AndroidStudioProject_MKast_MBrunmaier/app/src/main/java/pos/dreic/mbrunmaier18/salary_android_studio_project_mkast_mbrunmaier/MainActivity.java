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
                startActivity(position);
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
                listView_shops.setAdapter(shopAdapter);
                break;
            case R.id.context_edit:
                Shop shop = (Shop) listView_shops.getAdapter().getItem(info.position);
                LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View listItem =  inflater.inflate(R.layout.layout_add_shop, null);

                EditText editText_shop = listItem.findViewById(R.id.editText_add_shopName);
                editText_shop.setText(shop.getName());

                buildDialog(shop.getName(),null)
                        .setView(R.layout.layout_add_shop)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int previousSize = shopList.size();
                                shopList.add(new Shop(editText_shop.getText().toString()));
                                if (previousSize + 1 == shopList.size()) {
                                    shopList.remove(shop);
                                }
                            }
                        })
                        .setNegativeButton("CHANCEL",null)
                        .show();
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
                LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View listItem =  inflater.inflate(R.layout.layout_add_shop, null);

                EditText editText_shop = listItem.findViewById(R.id.editText_add_shopName);

                buildDialog("Add new Shop-List",null)
                        .setView(R.layout.layout_add_shop)
                        .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String name = editText_shop.getText().toString();
                                Shop shop = new Shop(name);
                                shopList.add(shop);
                                listView_shops.setAdapter(shopAdapter);
                            }
                        })
                        .setNegativeButton("CHANCEL",null)
                        .show();
                System.out.println();
        }
        return super.onOptionsItemSelected(item);
    }

    public AlertDialog.Builder buildDialog(String title, String message){
        return new AlertDialog.Builder(this).setTitle(title).setMessage(message);
    }

    class SuperMessage implements Serializable {
        private Shop shop;

        public SuperMessage(Shop shop) {
            this.shop = shop;
        }
        public Shop getMsg() {
            return shop;
        }
        public void setMsg(Shop msg) {
            this.shop = msg;
        }
    }

    public void startActivity(int position) {
        Intent intent = new Intent(this, ShopActivity.class);
        intent.putExtra("current Shop", position);
        startActivity(intent);
    }
}