package pos.dreic.mbrunmaier18.salary_android_studio_project_mkast_mbrunmaier;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddShopActivity extends AppCompatActivity {
    public Shop shop;
    public EditText editText_shopName;
    public TextView textView_shopName;
    public Button addButton;
    public Button cancelButton;
    public Context s = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);
        shop = (Shop) getIntent().getSerializableExtra("shop");

        editText_shopName = findViewById(R.id.editText_add_shop_Name);
        textView_shopName = findViewById(R.id.textView_add_shop_Name);

        if(!shop.getName().equals("")){
            editText_shopName.setText(shop.getName());
            textView_shopName.setText(shop.getName());
        }

        addButton = findViewById(R.id.id_add_shop_Button);
        cancelButton = findViewById(R.id.id_cancel_shop_Button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shopName = editText_shopName.getText().toString();
                boolean b = true;
                for(Shop s:MainActivity.shopList){
                    if(shopName.equals(s.getName())) b = false;
                }
                if(b){
                    Shop shop = new Shop(shopName);
                    MainActivity.shopList.add(shop);
                    finish();
                }else{
                    new AlertDialog.Builder(s).setTitle("Error").setMessage("Shop exists already")
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            .show();
                }
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