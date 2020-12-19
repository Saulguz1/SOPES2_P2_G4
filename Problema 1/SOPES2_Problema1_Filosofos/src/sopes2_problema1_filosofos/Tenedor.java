/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopes2_problema1_filosofos;

/**
 *
 * @author Saul Guzman ◢◤
 */
public class Tenedor {

    int numerotenedor;
    boolean banderausado;

    public Tenedor(int n) {
        numerotenedor = n;
    }

    synchronized public int Usartenedor(Ventana vent, int nofilo) {
        if (banderausado == true) {
            vent.Imprimir("Filosofo No." + (nofilo + 1) + " no pudo agarrar tenedor " + (numerotenedor + 1) + "\n");
            return 0;
        } else {
            vent.Imprimir("Filosofo No." + (nofilo + 1) + " agarro el tenedor No." + (numerotenedor + 1) + "\n");
            banderausado = true;
            return 1;
        }
    }

    synchronized public int SoltarTenedor(Ventana vent, int nofilo) {
        vent.Imprimir("Filosofo No." + (nofilo + 1) + " solto el tenedor No." + (numerotenedor + 1) + "\n");
        banderausado = false;

        return 1;
    }
}
