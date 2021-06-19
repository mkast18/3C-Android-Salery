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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {
    public Shop shop;
    public ListView listView_shoppinglist;
    public static List<ShoppingItem> shoppingList = new ArrayList();
    public CurrentShoppingAdapter shoppingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_shoppinglist);
        listView_shoppinglist = findViewById(R.id.id_list_current_shopping);
        shop = (Shop) getIntent().getSerializableExtra("shop");
        shoppingList = shop.getCurrentShoppingList();
        shoppingAdapter = new CurrentShoppingAdapter(this,R.layout.layout_current_shoppinglist,shoppingList);
        listView_shoppinglist.setAdapter(shoppingAdapter);
        registerForContextMenu(listView_shoppinglist);
        showTotalPrice();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_current_shoppinglist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        int viewId = v.getId();
        if (viewId == R.id.id_list_current_shopping) {
            getMenuInflater().inflate(R.menu.menu_context_item, menu);
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
                shoppingList.remove(listView_shoppinglist.getAdapter().getItem(info.position));
                shoppingAdapter.notifyDataSetChanged();
                showTotalPrice();
                break;
            case R.id.context_edit:
                ShoppingItem shoppingItem = (ShoppingItem) listView_shoppinglist.getAdapter().getItem(info.position);
                LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View listItem =  inflater.inflate(R.layout.layout_add_shoppingitem, null);

                EditText editText_itemName = listItem.findViewById(R.id.editText_add_shoppingItemName);
                EditText editText_itemNumber = listItem.findViewById(R.id.editText_add_shoppingItemNumber);
                EditText editText_itemPrice = listItem.findViewById(R.id.editText_add_shoppingItemPrice);

                editText_itemName.setText(shoppingItem.getName());
                editText_itemNumber.setText(shoppingItem.getNumbers());
                editText_itemPrice.setText("1");

                buildDialog(shoppingItem.getName(),null)
                        .setView(R.layout.layout_add_shoppingitem)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int previousSize = shoppingList.size();
                                shoppingList.add(new ShoppingItem(shoppingItem.getName(),shoppingItem.getNumbers(),shoppingItem.getPrice()));
                                if (previousSize + 1 == shoppingList.size()) {
                                    shoppingList.remove(shoppingItem);
                                }
                            }
                        })
                        .setNegativeButton("CHANCEL",null)
                        .show();
                shoppingAdapter.notifyDataSetChanged();
                showTotalPrice();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.d("Menu select", "onOptionsItemSelected: " + id);

        switch (id){
            case R.id.menu_add_item:
                    ShoppingItem ite = new ShoppingItem("","",0.00);
                    startActivity(ite,AddShoppingItemActivity.class);
                    shoppingAdapter.notifyDataSetChanged();

                break;
            case R.id.menu_save:
                //api Überlegung
                break;

            case R.id.menu_home:
                shop.setCurrentShoppingList(shoppingList);
                int index = 0;
                for (int i = 0;i<MainActivity.shopList.size();i++){
                    if(shop.getName().equals(MainActivity.shopList.get(i).getName()))index = i;
                }
                MainActivity.shopList.get(index).setCurrentShoppingList(shoppingList);
                finish();
                break;
        }
        showTotalPrice();
        return super.onOptionsItemSelected(item);
    }

    public AlertDialog.Builder buildDialog(String title, String message){
        return new AlertDialog.Builder(this).setTitle(title).setMessage(message);
    }

    public void showTotalPrice(){
        TextView showPrice =  findViewById(R.id.textView_show_total_price);
        double price = 0;
        if(!shoppingList.isEmpty()) {
            for (ShoppingItem item : shoppingList) {

                price += (item.getPrice()*Integer.parseInt(item.getNumbers()));
            }
            showPrice.setText(price+"€");
        }else{
            showPrice.setText("Keine Einträge");
        }


    }

    public void startActivity(ShoppingItem item, Class cls) {
        Intent intent = new Intent(this, cls);
        intent.putExtra("item", item);
        startActivity(intent);
    }
}