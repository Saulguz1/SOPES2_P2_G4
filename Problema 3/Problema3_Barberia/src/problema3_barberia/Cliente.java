/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problema3_barberia;
import java.util.ArrayList;

/**
 *
 * @author Oscar
 */
public class Cliente extends Thread{
    Barberia barberia;
    Ventana vent;
    ArrayList<String> cola;
    
    
    
    public Cliente(Barberia barberia){
        this.barberia = barberia;
        this.cola = new ArrayList<String>();
    }
    
    public void newCliente(String name){
        cola.add(name);
    }
    
    
    
    @Override
    public void run() {
        while (true){
            if(barberia.cola.size()<1){
                vent.colaText.setText(0+"");
            }else{
                vent.colaText.setText((barberia.cola.size()-1)+"");
            }
            vent.colaTextArea.setText("");
            for(int i=0;i<barberia.cola.size();i++){
                if(i>0){
                    vent.colaTextArea.append("Cliente "+barberia.cola.get(i)+"\n");
                }
            }
        }
    }
    
    public synchronized void cortarPelo(String name,Barbero barber) throws InterruptedException {
        if(barber.isAsSleep()){
            vent.logTextArea.append(barber.getNombre()+": Despertó \n");
            barber.isAsSleep=false;
        }
        barber.isBussy = true;
        vent.logTextArea.append(barber.getNombre()+ ": Cortando el pelo a Cliente " + name+"\n");
        sleep(8000);
        vent.logTextArea.append(barber.getNombre()+ ": Terminó de cortar el pelo a Cliente "+ name+"\n");
        barber.isBussy = false;
    }
}
