package pos.dreic.mbrunmaier18.salary_android_studio_project_mkast_mbrunmaier;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddFromSavedShoppingItemActivity extends AppCompatActivity {

    ShoppingItem item;
    public Button add_Button;
    public Button cancel_Button;
    public EditText editText_item_name;
    public EditText editText_item_number;
    public EditText editText_item_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_from_saved_shopping_item);

        item = (ShoppingItem) getIntent().getSerializableExtra("item");

        editText_item_name = findViewById(R.id.editText_add_savedshoppingItemName);
        editText_item_number = findViewById(R.id.editText_add_savedShoppingItemNumber);
        editText_item_price = findViewById(R.id.editText_add_savedShoppingItemPrice);


        editText_item_name.setText(item.getName());
        editText_item_price.setText(item.getPrice().toString());

        add_Button = findViewById(R.id.id_add_savedShopping_Button);
        cancel_Button = findViewById(R.id.id_cancel_edit_savedShopping_Button);
        add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText_item_name.getText().toString();
                String numbers = editText_item_number.getText().toString();
                String price = editText_item_price.getText().toString();

                boolean b = true;
                for (int i = 0; i < ShopActivity.shoppingList.size();i++){
                    ShoppingItem shoppingItem = ShopActivity.shoppingList.get(i);
                    if(name.equals(shoppingItem.getName()) && Double.parseDouble(price) == shoppingItem.getPrice()){
                        ShopActivity.shoppingList.get(i).setNumbers(""+(Integer.parseInt(shoppingItem.getNumbers()) + Integer.parseInt(numbers)));
                        b = false;
                        ShopActivity.showTotalPrice();
                        finish();
                    }
                }
                if(b) {
                    ShoppingItem item = new ShoppingItem(name, numbers, Double.parseDouble(price));
                    ShopActivity.shoppingList.add(item);
                    ShopActivity.showTotalPrice();
                    ShopActivity.shoppingAdapter.notifyDataSetChanged();
                }
                ShopActivity.showTotalPrice();
                finish();
            }
        });

        cancel_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopActivity.showTotalPrice();
                finish();
            }
        });

    }


}