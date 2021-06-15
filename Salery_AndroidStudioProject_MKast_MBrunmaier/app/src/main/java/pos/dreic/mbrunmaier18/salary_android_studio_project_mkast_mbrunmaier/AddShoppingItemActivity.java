package pos.dreic.mbrunmaier18.salary_android_studio_project_mkast_mbrunmaier;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddShoppingItemActivity extends AppCompatActivity {
    ShoppingItem item;
    public Button add_Button;
    public Button cancel_Button;
    public EditText editText_item_name;
    public EditText editText_item_number;
    public EditText editText_item_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shopping_item);
        item = getIntent().getParcelableExtra("item");

        editText_item_name = findViewById(R.id.editText_add_shoppingItemName);
        editText_item_number = findViewById(R.id.editText_add_shoppingItemNumber);
        editText_item_price = findViewById(R.id.editText_add_shoppingItemPrice);

        if(!item.getName().equals("") && !item.getNumbers().equals("")){
            editText_item_name.setText(item.getName());
            editText_item_number.setText(item.getNumbers());
            editText_item_price.setText(item.getPrice().toString());
        }

        add_Button = findViewById(R.id.id_add_shopping_Button);
        cancel_Button = findViewById(R.id.id_cancel_shopping_Button);
        add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = editText_item_name.getText().toString();
                String itemNumbers = editText_item_number.getText().toString();
                String itemPrice = editText_item_price.getText().toString();
                ShoppingItem item = new ShoppingItem(itemName,itemNumbers,Double.parseDouble(itemPrice));
                ShopActivity.shoppingList.add(item);
                finish();
            }
        });

        cancel_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}