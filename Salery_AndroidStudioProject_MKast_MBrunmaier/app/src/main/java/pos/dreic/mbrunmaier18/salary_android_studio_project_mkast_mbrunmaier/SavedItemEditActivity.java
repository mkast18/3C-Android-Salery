package pos.dreic.mbrunmaier18.salary_android_studio_project_mkast_mbrunmaier;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SavedItemEditActivity extends AppCompatActivity {
    public ShoppingItem item;
    public EditText savedShoppingName;
    public EditText savedShoppingPrice;
    public Button savedSaveItem;
    public Button savedCancleItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_item_edit);
        item = (ShoppingItem) getIntent().getSerializableExtra("item");
        savedShoppingName = findViewById(R.id.editText_edit_savedShoppingItemName);
        savedShoppingPrice = findViewById(R.id.editText_edit_savedShoppingItemPrice);
        savedSaveItem = findViewById(R.id.id_save_edit_shopping_Button);
        savedCancleItem = findViewById(R.id.id_cancel_edit_shopping_Button);
        savedShoppingName.setText(item.getName());
        savedShoppingPrice.setText(""+item.getPrice());
        savedSaveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0;i < SavedShoppingActivity.savedItemList.size();i++){
                    if(item.getName().equals(SavedShoppingActivity.savedItemList.get(i))){
                        SavedShoppingActivity.savedItemList.get(i).setName(savedShoppingName.getText().toString());
                        SavedShoppingActivity.savedItemList.get(i).setPrice(Double.parseDouble(savedShoppingPrice.getText().toString()));
                        finish();
                    }
                }
            }
        });
        savedCancleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}