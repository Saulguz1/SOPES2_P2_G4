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
            if(cola.size()<1){
                vent.colaText.setText(0+"");
            }else{
                vent.colaText.setText((cola.size()-1)+"");
            }
            vent.colaTextArea.setText("");
            for(int i=0;i<cola.size();i++){
                if(i>0){
                    vent.colaTextArea.append("Cliente "+cola.get(i)+"\n");
                }
            }
            
            if (cola.size()>0){
                if(barberia.getBarbero1().isBussy()){
                    System.out.println("Barbero 1 esta ocupado "+barberia.getBarbero1().isBussy());
                    if(!barberia.getBarbero2().isBussy()){
                        System.out.println("Barbero 2 esta ocupado "+barberia.getBarbero2().isBussy());
                        try {
                            barberia.getBarbero2().cortarPelo(cola.remove(0));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    System.out.println("Barbero 1 esta ocupado "+barberia.getBarbero1().isBussy());
                    try {
                        barberia.getBarbero1().cortarPelo(cola.remove(0));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                System.out.println(cola.size()+" tam cola");
            }
        }
    }
}
