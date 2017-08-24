package view;

import model.Product;

import java.util.Observer;

/**
 * Created by zgoreckim on 2017-08-23.
 */
public interface ProductView extends Observer {

    void show(Product p);
    void clear();
    void delete(Product p);
    double getPrice();
    void setRest(double rest);
    double getRest();
    void setPaid(double price);
    double getPaid();
}
