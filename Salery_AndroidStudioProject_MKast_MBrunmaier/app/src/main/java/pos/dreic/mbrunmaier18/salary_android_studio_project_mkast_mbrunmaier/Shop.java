package pos.dreic.mbrunmaier18.salary_android_studio_project_mkast_mbrunmaier;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Parcelable {
    private String name;
    private List<ShoppingItem> currentShoppingList;
    private List<ShoppingItem> savedShoppingItems;

    public Shop(String name) {
        this.name = name;
        this.currentShoppingList = new ArrayList();
        this.savedShoppingItems = new ArrayList<>();
    }

    protected Shop(Parcel in) {
        name = in.readString();
        currentShoppingList = in.readArrayList(ClassLoader.getSystemClassLoader());
        savedShoppingItems = in.readArrayList(ClassLoader.getSystemClassLoader());
    }

    public static final Creator<Shop> CREATOR = new Creator<Shop>() {
        @Override
        public Shop createFromParcel(Parcel in) {
             return new Shop(in);
        }

        @Override
        public Shop[] newArray(int size) {
            return new Shop[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeTypedList(currentShoppingList);
        dest.writeTypedList(savedShoppingItems);
    }
}
