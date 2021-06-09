package pos.dreic.mbrunmaier18.salary_android_studio_project_mkast_mbrunmaier;

public class ShoppingItem {
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
