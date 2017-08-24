package other;

import model.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zgoreckim on 2017-08-22.
 */
public class ProductDatabase {

    private static ProductDatabase INSTANCE;
    private static Map<Integer, Product> productDB = new HashMap<>();

    public ProductDatabase() {
    }

    static {
        productDB.put(12345678,new Product("Orange",2.20,12345678));
        productDB.put(23456789,new Product("Apple",1.10,23456789));
        productDB.put(34567891,new Product("Juice",3.49,34567891));
        productDB.put(45678912,new Product("Cola-Cola",5.10,45678912));
        productDB.put(56789123,new Product("Milk",1.79,56789123));
        productDB.put(67891234,new Product("Fanta",4.16,67891234));
        productDB.put(78912345,new Product("Twix",1.99,78912345));
        productDB.put(89123456,new Product("Bread",2.59,89123456));
    }

    public static ProductDatabase getINSTANCE() {
        if (null == INSTANCE) {
            return new ProductDatabase();
        }
        return INSTANCE;
    }

    public Product find(int barCode) {
        return productDB.get(barCode);
    }

}
