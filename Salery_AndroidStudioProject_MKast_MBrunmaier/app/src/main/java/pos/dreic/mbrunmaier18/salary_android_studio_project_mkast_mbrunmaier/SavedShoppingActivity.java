package pos.dreic.mbrunmaier18.salary_android_studio_project_mkast_mbrunmaier;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SavedShoppingActivity extends AppCompatActivity {
    public ListView savedShoppingListView;
    public List<ShoppingItem> savedItemList;
    public SavedShoppingAdapter savedAdapter;
    public Shop shop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_shoppingitems);
        savedShoppingListView = findViewById(R.id.id_list_saved_shoppinglist);
        shop = (Shop) getIntent().getSerializableExtra("shop");
        savedItemList = shop.getSavedShoppingItems();
        savedAdapter = new SavedShoppingAdapter(this,R.layout.layout_saved_shoppinglist,savedItemList);
        savedShoppingListView.setAdapter(savedAdapter);
        registerForContextMenu(savedShoppingListView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_saved_shoppinglist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        int viewId = v.getId();
        if (viewId == R.id.id_list_saved_shoppinglist) {
            getMenuInflater().inflate(R.menu.menu_context_saved_shoppinglist, menu);
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (id){
            case R.id.context_saved_add:
                shop.getCurrentShoppingList().add(shop.getSavedShoppingItems().get(info.position));
                savedAdapter.notifyDataSetChanged();
                break;
            case R.id.context_saved_edit:

                break;
            case R.id.context_saved_delete:
                break;

        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.d("Menu select", "onOptionsItemSelected: " + id);

        switch (id){

            case R.id.menu_home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}