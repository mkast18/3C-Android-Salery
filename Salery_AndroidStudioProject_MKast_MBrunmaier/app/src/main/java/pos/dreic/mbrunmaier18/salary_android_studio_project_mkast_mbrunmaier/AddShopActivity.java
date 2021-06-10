package pos.dreic.mbrunmaier18.salary_android_studio_project_mkast_mbrunmaier;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddShopActivity extends AppCompatActivity {
    public EditText editText_shopName;
    public Button addButton;
    public Button cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);
        editText_shopName = findViewById(R.id.editText_add_shop_Name);
        addButton = findViewById(R.id.id_add_shop_Button);
        cancelButton = findViewById(R.id.id_cancel_shop_Button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shopName = editText_shopName.getText().toString();
                Shop shop = new Shop(shopName);
                MainActivity.shopList.add(shop);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}