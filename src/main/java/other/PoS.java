package other;

import model.Product;
import view.ProductView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by zgoreckim on 2017-08-22.
 */

public class PoS extends Observable implements Observer {

    private ProductDatabase productDatabase = ProductDatabase.getINSTANCE();
    private List<ProductView> views = new ArrayList<>();
    private boolean startShopping = false;
    private boolean paid = false;

    public void addView(ProductView productView) {
        addObserver(productView);
        views.add(productView);
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        if (arg instanceof Integer && startShopping == true) {
            Product product = productDatabase.find((Integer) arg);
            if (product == null) {
                notifyObservers("PRODUCT_NOT_FOUND");
            } else {
                notifyObservers(product);
            }
        } else if (startShopping == false) {
            notifyObservers("NOT_STARTED");
        } else if (arg instanceof Double) {
            checkRest((Double) arg);
        } else {
            notifyObservers(arg);
        }
    }

    public void checkRest(double rest) {
        setChanged();
        views.forEach(x -> {
            x.setRest(rest);
        });
    }

    public void deleteProduct(int barCode) {
        setChanged();
        Product product = productDatabase.find(barCode);
        try {
            views.forEach(x -> x.delete(product));
            notifyObservers("DELETE_PRODUCT");
        } catch (NullPointerException e) {
            System.out.println("BARCODE OF PRODUCT TO DELETE IS INVALID.");
        }
    }

    public double getPrice() {
        return views.get(0).getPrice();
    }

    public void start() {
        setChanged();
        notifyObservers("START_SHOPPING");
        startShopping = true;
    }

    public void pay(double money) {
        if (money >= getPrice()) {
            setChanged();
            notifyObservers(money);
            this.paid = true;
            end();
        } else {
            System.out.println("ERROR. Payment must be same or higher than price.");
        }

    }

    public void goToPay() {
        setChanged();
        notifyObservers("GO_TO_PAY");
    }

    public void end() {
        if (paid == true) {
            setChanged();
            notifyObservers("END_SHOPPING");
            startShopping = false;
            views.forEach(ProductView::clear);
        } else {
            System.out.println("ERROR. You can not end shopping before pay.");
        }

    }

}
