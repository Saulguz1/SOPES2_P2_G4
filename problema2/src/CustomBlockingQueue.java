import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CustomBlockingQueue {

    final ReentrantLock lock = new ReentrantLock(true);
    final Condition putCondition = lock.newCondition();
    final Condition pullCondition = lock.newCondition();

    final LinkedList estanteria = new LinkedList<Integer>();
    final int limit = 20;
    int waitingPullersCounter = 0;
    int waitingPushersCounter = 0;

    public void colocarCaja(int caja, JTable estanteria, JLabel entrada, JLabel salida) throws InterruptedException {

        lock.lock();
        try {
            while (this.limit == this.estanteria.size()) {
                /**
                 * Estantería está llena
                 * persona debe esperar para colocar nueva caja
                 */
                System.out.println("Estantería llena, espere un espacio vacío.");
                waitingPushersCounter++;
                entrada.setText("Personas en cola: " +  waitingPushersCounter);
                putCondition.await();
            }
            if (waitingPushersCounter >0) {
                waitingPushersCounter--;
            }

            System.out.println("Colocando - " + caja + " en posición " +  this.estanteria.size());
            this.estanteria.add(caja);
            fillTable(estanteria);
            System.out.println(this.estanteria.toString());

            entrada.setText("Personas en cola: " +  waitingPushersCounter);

            pullCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public int quitarCaja(JTable estanteria, JLabel entrada, JLabel salida) throws InterruptedException {
        lock.lock();
        try {
            while (this.estanteria.size() == 0){
                /**
                 * La estanteria está vacia
                 * persona debe esperar nueva caja
                 */
                System.out.println("Estantería vacía, espere una nueva caja.");
                waitingPullersCounter++;
                salida.setText("Personas en cola: " +  waitingPullersCounter);
                pullCondition.await();
            }
            if (waitingPullersCounter > 0) {
                waitingPullersCounter--;
            }

            int caja = (int) this.estanteria.remove();
            fillTable(estanteria);
            System.out.println("Recogiendo - " + caja + " de posición 0");
            System.out.println(this.estanteria.toString());
            salida.setText("Personas en cola: " +  waitingPullersCounter);

            putCondition.signal();
            return caja;
        } finally {
            lock.unlock();
        }
    }

    public synchronized void putBox(int item, JTable estanteria, JLabel entrada, JLabel salida)
            throws InterruptedException  {
        while(this.estanteria.size() == this.limit) {
            System.out.println("Estantería llena, espere un espacio vacío.");
            waitingPushersCounter++;
            entrada.setText("Personas en cola: " +  waitingPushersCounter);
            this.wait();
        }

        System.out.println("Colocando - " + item + " en posición " +  this.estanteria.size());
        this.estanteria.add(item);
        fillTable(estanteria);
        System.out.println(this.estanteria.toString());
        if(this.estanteria.size() == 1) {
            waitingPushersCounter--;
            entrada.setText("Personas en cola: " +  waitingPushersCounter);
            this.notify();
        }
    }


    public synchronized int pullBox(JTable estanteria, JLabel entrada, JLabel salida)
            throws InterruptedException{
        while(this.estanteria.isEmpty()){
            System.out.println("Estantería vacía, espere una nueva caja.");
            waitingPullersCounter++;
            salida.setText("Personas en cola: " +  waitingPullersCounter);
            this.wait();
        }
        if(this.estanteria.size() == this.limit){
            waitingPullersCounter--;
            salida.setText("Personas en cola: " +  waitingPullersCounter);
            this.notify();
        }

        int caja = (int) this.estanteria.remove();
        fillTable(estanteria);
        System.out.println("Recogiendo - " + caja + " de posición 0");
        System.out.println(this.estanteria.toString());
        return caja;
    }

    private void fillTable(JTable estanteria) {
        DefaultTableModel model = (DefaultTableModel) estanteria.getModel();
        if (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        model.addRow(this.estanteria.toArray());

        //model.addRow(abc);
        estanteria.setModel(model);
        estanteria.setVisible(true);
    }
}