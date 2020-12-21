import javax.swing.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Productor implements Runnable {

    private CustomBlockingQueue customBlockingQueue;
    Random random = new Random();
    JTable estanteria;
    JLabel entrada;
    JLabel salida;

    public Productor(CustomBlockingQueue customBlockingQueue, JTable estanteria, JLabel entrada, JLabel salida) {
        this.customBlockingQueue = customBlockingQueue;
        this.estanteria = estanteria;
        this.entrada = entrada;
        this.salida = salida;
    }
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            try {
                customBlockingQueue.putBox(i, estanteria, entrada, salida);
                TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}