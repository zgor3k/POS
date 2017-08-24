package input;

import other.PoS;
import java.util.Observable;

/**
 * Created by zgoreckim on 2017-08-23.
 */
public class BarCodeScanner extends Observable implements Scanner {

    public void addPoS(PoS pos) {
        addObserver(pos);
    }

    @Override
    public void scan(int barCode) {
        setChanged();
        if (String.valueOf(barCode).length() < 8) {
            notifyObservers("BARCODE_INVALID");
        } else {
            notifyObservers(barCode);
        }
    }
}
