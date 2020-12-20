/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopes2_problema1_filosofos;

import java.awt.Color;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saul Guzman ◢◤
 */
public class Filosofo implements Runnable {

    int nofilosofo, tenedorder, tenedorizq;
    int nsleep = 100;
    Mesa mesa;
    Thread hilo;
    Ventana vent;

    public Filosofo(int ns, Ventana ven, int n, Mesa mesa) {
        this.nofilosofo = n;
        this.nsleep = ns;
        this.mesa = mesa;
        this.vent = ven;
        tenedorizq = mesa.ObtenerTenedorIzquierdo(nofilosofo);
        tenedorder = mesa.ObtenerTenedorDerecho(nofilosofo);
        hilo = new Thread(this);
        hilo.start();
    }

    @Override
    public void run() {
        while (vent.band) {
            //Sentarse a pensar
            try {
                vent.Imprimir("Filosofo No." + (nofilosofo + 1) + " Se sento a pensar \n");
                hilo.sleep((int) ( nsleep));
                vent.Imprimir("Filosofo No." + (nofilosofo + 1) + " Le dio hambre\n");
               // hilo.sleep(nsleep);
                //Agarrar tenedor y verificar que estan disponible
                //PARA RESOLVER EL DEADLOCK DONDE TODOS LOS FILOSOFOS AGARRAN EL IZQUIERDO AL MISMO TIEMPO
                Tenedor tenizq = mesa.ObtenerTen(tenedorizq);
                Tenedor tender = mesa.ObtenerTen(tenedorder);
                
                int dispder = 1;
                int dispizq = 1;
                //Resolucion de bloque en cola de tenedor
                if ((nofilosofo+1) == 5) {
                    dispder = tender.Usartenedor(vent, nofilosofo);
                    dispizq = tenizq.Usartenedor(vent, nofilosofo);
                    
                } else {
                    dispizq = tenizq.Usartenedor(vent, nofilosofo);
                    dispder = tender.Usartenedor(vent, nofilosofo);
                }

                // Solucion al error que se quedan con el tenedor
                if (dispder == 0 || dispizq == 0) {
                    vent.Imprimir("Filosofo No." + (nofilosofo + 1) + " no pudo comer\n");
                    Tenedor dejtenizq = mesa.ObtenerTen(tenedorizq);
                    Tenedor dejtender = mesa.ObtenerTen(tenedorder);
                    // Solucion si no agarro ningun tenedor
                    if (dispder == 0 && dispizq == 0) {

                    } else {
                        if (dispder == 0) {
                            dejtenizq.SoltarTenedor(vent, nofilosofo);                         
                        }
                        if (dispizq == 0) {
                            dejtender.SoltarTenedor(vent, nofilosofo);
                            
                        }
                    }
                   // hilo.sleep(nsleep);
                } else {
                    //Empieza a Comer 
                    
                    vent.Imprimir("Filosofo No." + (nofilosofo + 1) + " Se puso a comer\n");
                    switch (nofilosofo + 1) {
                        case 1:
                            vent.ten1.setVisible(false);
                            vent.ten2.setVisible(false);
                            vent.bt1.setBackground(new Color(255,204,153));
                            vent.bt1.setText("Comer");
                            break;
                        case 2:
                            vent.ten2.setVisible(false);
                            vent.ten3.setVisible(false);
                            vent.bt2.setBackground(new Color(255,204,153));
                            vent.bt2.setText("Comer");
                            break;
                        case 3:
                            vent.ten3.setVisible(false);
                            vent.ten4.setVisible(false);
                            vent.bt3.setBackground(new Color(255,204,153));
                            vent.bt3.setText("Comer");
                            break;
                        case 4:
                            vent.ten5.setVisible(false);
                            vent.ten4.setVisible(false);
                            vent.bt4.setBackground(new Color(255,204,153));
                            vent.bt4.setText("Comer");
                            break;
                        case 5:
                            vent.ten5.setVisible(false);
                            vent.ten1.setVisible(false);
                            vent.bt5.setBackground(new Color(255,204,153));
                            vent.bt5.setText("Comer");
                            break;
                        default:
                            break;
                    }

                    hilo.sleep((int) (Math.random()* nsleep));
                    // Termino de comer suelta los tenedores               
                    vent.Imprimir("Filosofo No." + (nofilosofo + 1) + " termino de comer\n");
                    Tenedor dejtenizq = mesa.ObtenerTen(tenedorizq);
                    Tenedor dejtender = mesa.ObtenerTen(tenedorder);
                    dejtenizq.SoltarTenedor(vent, nofilosofo);
                    dejtender.SoltarTenedor(vent, nofilosofo);
                   // hilo.sleep(nsleep);
                    switch (nofilosofo + 1) {
                        case 1:
                            vent.ten1.setVisible(true);
                            vent.ten2.setVisible(true);
                            vent.bt1.setBackground(new Color(153,255,153));
                            vent.bt1.setText("Pensar");
                            break;
                        case 2:
                            vent.ten2.setVisible(true);
                            vent.ten3.setVisible(true);
                            vent.bt2.setBackground(new Color(153,255,153));
                            vent.bt2.setText("Pensar");
                            break;
                        case 3:
                            vent.ten3.setVisible(true);
                            vent.ten4.setVisible(true);
                            vent.bt3.setBackground(new Color(153,255,153));
                            vent.bt3.setText("Pensar");
                            break;
                        case 4:
                            vent.ten4.setVisible(true);
                            vent.ten5.setVisible(true);
                            vent.bt4.setBackground(new Color(153,255,153));
                            vent.bt4.setText("Pensar");
                            break;
                        case 5:
                            vent.ten5.setVisible(true);
                            vent.ten1.setVisible(true);
                            vent.bt5.setBackground(new Color(153,255,153));
                            vent.bt5.setText("Pensar");
                            break;
                        default:
                            break;
                    }

                }
            } catch (InterruptedException e) {
                hilo.interrupt();
            }

        }
    }

}
