package model;

/**
 * Created by zgoreckim on 2017-08-22.
 */
public class Product {

    private String name;
    private double price;
    private int barCode;

    public Product(String name, double price, int barCode) {
        this.name = name;
        this.price = price;
        this.barCode = barCode;
    }

    public int getBarCode() {
        return barCode;
    }

    public void setBarCode(int barCode) {
        this.barCode = barCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String LCDprint() {
        return name + " | " + price;
    }
    public String print() {
        return barCode + ": " + name + " | " + price;
    }
}
