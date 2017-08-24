import input.BarCodeScanner;
import input.CashDrawer;
import other.PoS;
import view.LCDDisplay;
import view.PrintBill;

/**
 * Created by zgoreckim on 2017-08-22.
 */
public class Main {

    public static void main(String[] args) {

        PoS pos = new PoS();
        CashDrawer cashDrawer = new CashDrawer();
        BarCodeScanner barCodeScanner = new BarCodeScanner();

        pos.addView(new PrintBill());
        pos.addView(new LCDDisplay());
        pos.addObserver(cashDrawer);
        cashDrawer.addPos(pos);
        barCodeScanner.addPoS(pos);

        pos.start();
        barCodeScanner.scan(12345678);
        barCodeScanner.scan(23456789);
        barCodeScanner.scan(34567891);
        barCodeScanner.scan(45678912);
        barCodeScanner.scan(1231);
        barCodeScanner.scan(56789333);
        barCodeScanner.scan(56789123);
        barCodeScanner.scan(67891234);
        barCodeScanner.scan(67891234);
        pos.deleteProduct(67891234);
        pos.deleteProduct(56789123);
        barCodeScanner.scan(78912345);
        pos.goToPay();
        pos.pay(13.0);
        pos.pay(50.0);

    }

}
