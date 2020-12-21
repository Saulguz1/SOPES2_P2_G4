import javax.swing.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Consumidor implements Runnable {
    private CustomBlockingQueue customBlockingQueue;
    Random random = new Random();
    JTable estanteria;
    JLabel entrada;
    JLabel salida;

    public Consumidor(CustomBlockingQueue customBlockingQueue, JTable estanteria, JLabel entrada, JLabel salida) {
        this.customBlockingQueue = customBlockingQueue;
        this.estanteria = estanteria;
        this.entrada = entrada;
        this.salida = salida;
    }
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            try {
                customBlockingQueue.pullBox(estanteria, entrada, salida);
                TimeUnit.MILLISECONDS.sleep(random.nextInt(1500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}