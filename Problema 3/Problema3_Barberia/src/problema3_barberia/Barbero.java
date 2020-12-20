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
public class Barbero extends Thread{
    private Barberia barberia;
    private String nombre;
    private boolean isAsSleep = false;
    private boolean isBussy = false;
    Ventana vent;
    
    public Barbero(Barberia barberia, String nombre){
        this.barberia = barberia;
        this.nombre = nombre;
    }
    
    
    public boolean isAsSleep() {
        return isAsSleep;
    }
    
    public boolean isBussy() {
        return isBussy;
    }
    
    @Override
    public void run() {
        try {
             vent.logTextArea.append(this.nombre+": Inicia cuaje \n");
            dormir();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
    
    public synchronized void dormir() throws InterruptedException {
        vent.logTextArea.append(this.nombre+": Vuelve al cuaje \n");
        isAsSleep = true;
        isBussy = false;
    }
    
    

    public synchronized void cortarPelo(String name) throws InterruptedException {
        if(isAsSleep){
            vent.logTextArea.append(this.nombre+": Despertó \n");
            isAsSleep=false;
        }
        isBussy = true;
        vent.logTextArea.append(this.nombre+ ": Cortando el pelo a Cliente " + name+"\n");
        this.sleep(10000);
        vent.logTextArea.append(this.nombre+ ": Terminó de cortar el pelo a Cliente "+ name+"\n");
        isBussy = false;
    }
    
}
