package view;

import model.Product;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by zgoreckim on 2017-08-23.
 */
public class LCDDisplay implements ProductView, Observer {

    private List<Product> products = new ArrayList<>();
    private Product deleteProduct;
    private double restPrice = 0;
    private double paidPrice = 0;

    @Override
    public void show(Product p) {
        products.add(p);
        System.out.println(p.LCDprint());
    }

    @Override
    public void clear() {
        products.clear();
    }

    @Override
    public void delete(Product p) {
        if (products.indexOf(p) > 0) {
            this.deleteProduct = p;
            products.remove(p);
        }
    }

    @Override
    public double getPrice() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    @Override
    public double getRest() {
        return restPrice;
    }

    @Override
    public void setRest(double rest) {
        this.restPrice = rest;
    }

    @Override
    public void setPaid(double price) {
        this.paidPrice = price;
    }

    @Override
    public double getPaid() {
        return paidPrice;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("LCD DISPLAY:");
        if (arg instanceof Product) {
            show((Product) arg);
        }
        NumberFormat shortFormat = new DecimalFormat("#0.00");
        if (arg.equals("BARCODE_INVALID")) {
            System.out.println("Barcode of product is invalid.");
        } else if (arg.equals("PRODUCT_NOT_FOUND")) {
            System.out.println("Product is not found in database.");
        } else if (arg.equals("GO_TO_PAY")) {
            System.out.println("TOTAL PRICE: " + shortFormat.format(getPrice()));
        } else if (arg.equals("END_SHOPPING")) {
            System.out.println("THANKS FOR SHOPPING.");
        } else if (arg.equals("DELETE_PRODUCT")) {
            System.out.println("Delete product: " + deleteProduct.LCDprint());
        } else if (arg.equals("START_SHOPPING")) {
            System.out.println("WELCOME IN OUR SHOP.");
        } else if (arg.equals("NOT_STARTED")) {
            System.out.println("POS IS NOT STARTED. PLEASE START POS BEFORE SHOPPING.");
        } else if (arg instanceof Double) {
            setPaid((Double) arg);
            System.out.println("PAID: " + shortFormat.format(getPaid()));
            System.out.println("REST: " + shortFormat.format(getRest()));
        }
        System.out.println("");
    }
}
