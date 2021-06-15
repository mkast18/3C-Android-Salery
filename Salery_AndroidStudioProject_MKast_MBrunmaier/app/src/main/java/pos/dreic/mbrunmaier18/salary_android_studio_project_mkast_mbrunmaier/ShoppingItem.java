package pos.dreic.mbrunmaier18.salary_android_studio_project_mkast_mbrunmaier;

import android.os.Parcel;
import android.os.Parcelable;

public class ShoppingItem implements Parcelable {
    private String name;
    private String numbers;
    private double price;

    public ShoppingItem(String name, String numbers, Double price) {
        this.name = name;
        this.numbers = numbers;
        this.price = price;
    }

    protected ShoppingItem(Parcel in) {
        name = in.readString();
        numbers = in.readString();
        price = in.readDouble();
    }

    public static final Creator<ShoppingItem> CREATOR = new Creator<ShoppingItem>() {
        @Override
        public ShoppingItem createFromParcel(Parcel in) {
            return new ShoppingItem(in);
        }

        @Override
        public ShoppingItem[] newArray(int size) {
            return new ShoppingItem[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(numbers);
        dest.writeDouble(price);
    }
}
