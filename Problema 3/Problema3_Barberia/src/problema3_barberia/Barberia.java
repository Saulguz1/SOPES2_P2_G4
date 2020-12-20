/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problema3_barberia;

/**
 *
 * @author Oscar
 */
public class Barberia extends Thread{
    
    public Barbero getBarbero1() {
        return barbero1;
    }
    public Barbero getBarbero2() {
        return barbero2;
    }
    private Barbero barbero1;
    private Barbero barbero2;

    public Barberia(){
        this.barbero1 = new Barbero(this,"Barbero 1");
        this.barbero2 = new Barbero(this,"Barbero 2");
    }

    @Override
    public void run() {
        barbero2.start();
        barbero1.start();
        while (true);
    }
}
