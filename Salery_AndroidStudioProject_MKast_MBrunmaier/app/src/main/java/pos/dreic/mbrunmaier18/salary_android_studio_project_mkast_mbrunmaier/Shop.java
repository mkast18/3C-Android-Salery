package pos.dreic.mbrunmaier18.salary_android_studio_project_mkast_mbrunmaier;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Shop implements Serializable {
    private String name;
    private List<ShoppingItem> currentShoppingList;
    private List<ShoppingItem> savedShoppingItems;

    public Shop(String name) {
        this.name = name;
        this.currentShoppingList = new ArrayList();
        this.savedShoppingItems = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ShoppingItem> getCurrentShoppingList() {
        return currentShoppingList;
    }

    public void setCurrentShoppingList(List<ShoppingItem> currentShoppingList) {
        this.currentShoppingList = currentShoppingList;
    }

    public List<ShoppingItem> getSavedShoppingItems() {
        return savedShoppingItems;
    }

    public void setSavedShoppingItems(List<ShoppingItem> savedShoppingItems) {
        this.savedShoppingItems = savedShoppingItems;
    }

}
