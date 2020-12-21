import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CentroDeAcopio {

    private JButton startButton;
    private JPanel panelCA;
    private JTable estanteria;
    private JLabel salida;
    private JLabel entrada;
    private String[] estanteriaColNames = {"x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x",};

    public CentroDeAcopio() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomBlockingQueue customBlockingQueue = new CustomBlockingQueue();
                Thread productor = new Thread(new Productor(customBlockingQueue, estanteria, entrada, salida));
                Thread consumidor = new Thread(new Consumidor(customBlockingQueue, estanteria, entrada, salida));

                consumidor.start();
                productor.start();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CentroDeAcopio");
        CentroDeAcopio centroDeAcopio = new CentroDeAcopio();
        frame.setContentPane(centroDeAcopio.panelCA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centroDeAcopio.init();
        frame.pack();
        frame.setVisible(true);

    }

    private void init() {

        DefaultTableModel modelEstanteria = (DefaultTableModel) estanteria.getModel();
        for (int i = 1; i <= 21; i++) {
            modelEstanteria.addColumn("Casilla-" + i);
        }
        estanteria.setModel(modelEstanteria);
        estanteria.setVisible(true);
        estanteria.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        estanteria.setGridColor(Color.black);
    }

}
