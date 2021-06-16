package pos.dreic.mbrunmaier18.salary_android_studio_project_mkast_mbrunmaier;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class ShoppingItem implements Serializable {
    private String name;
    private String numbers;
    private double price;

    public ShoppingItem(String name, String numbers, Double price) {
        this.name = name;
        this.numbers = numbers;
        this.price = price;
    }

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
}
