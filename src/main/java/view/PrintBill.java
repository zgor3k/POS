package view;

import model.Product;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by zgoreckim on 2017-08-23.
 */
public class PrintBill implements ProductView, Observer {

    private List<Product> bill = new LinkedList<>();
    private List<Product> allProducts = new LinkedList<>();
    private List<String> deleteProducts = new LinkedList<>();
    private double restPrice = 0;
    private double paidPrice = 0;

    @Override
    public void show(Product p) {
        bill.add(p);
        allProducts.add(p);
    }

    @Override
    public void clear() {
        bill.clear();
    }

    @Override
    public double getPrice() {
        return bill.stream().mapToDouble(Product::getPrice).sum();
    }

    @Override
    public void setRest(double rest) {
        this.restPrice = rest;
    }

    @Override
    public double getRest() {
        return restPrice;
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
    public void delete(Product p) {
        deleteProducts.add("  - " + p.print());
        bill.remove(p);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Product) {
            show((Product) arg);
        }
        if (arg.equals("END_SHOPPING")) {
            NumberFormat shortFormat = new DecimalFormat("#0.00");
            System.out.println("--------------");
            System.out.println("-----BILL-----");
            allProducts.forEach(x -> System.out.println(x.print()));
            if (deleteProducts.size() > 0) {
                System.out.println("");
                deleteProducts.forEach(x -> System.out.println(x));
            }
            System.out.println("--------------");
            System.out.println("TOTAL PRICE: " + shortFormat.format(getPrice()));
            System.out.println("--------------");
            System.out.println("PAID: " + shortFormat.format(getPaid()));
            System.out.println("REST: " + shortFormat.format(getRest()));
            System.out.println("--------------");
        } else if (arg instanceof Double) {
            setPaid((Double) arg);
        }
    }

//        if ((arg instanceof Product || arg.equals("DELETE_PRODUCT"))) {
//            NumberFormat newformat = new DecimalFormat("#0.00");
//            double price = bill.stream().mapToDouble(Product::getPrice).sum();
//            System.out.println("--------");
//            System.out.println("--BILL--");
//            bill.forEach(x -> System.out.println(x.print()));
//            System.out.println("--------");
//            System.out.println("TOTAL PRICE: " + newformat.format(price));
//            System.out.println("--------");
//        }

}
