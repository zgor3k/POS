package input;

import other.PoS;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by zgoreckim on 2017-08-24.
 */
public class CashDrawer extends Observable implements Observer {

    private double priceFromBill = 0;
    private double payAmount = 0;
    private PoS pos;

    public void addPos(PoS pos) {
        addObserver(pos);
        this.pos = pos;
    }

    public boolean isPoS(Observable o) {
        return o.getClass().getName().indexOf("PoS") > 0;
    }

    public double restPrice() {
        return payAmount - priceFromBill;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (isPoS(o) && arg instanceof Double) {
            priceFromBill = pos.getPrice();
            payAmount = (double) arg;
            setChanged();
            notifyObservers(restPrice());
        }
    }
}
