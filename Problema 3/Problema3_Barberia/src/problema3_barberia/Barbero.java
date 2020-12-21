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
    public boolean isAsSleep = false;
    public boolean isBussy = false;
    Ventana vent;
    
    public Barbero(Barberia barberia, String nombre){
        this.barberia = barberia;
        this.nombre = nombre;
    }
    
    public String getNombre(){
        return nombre;
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
        while(true){
            
            if (barberia.cola.size()>0){
                if(!isBussy()){
                    
                    System.out.println(nombre+" esta docupado "+isBussy);
                    try {
                            if(barberia.cola.size()>0){
                                cortarPelo(barberia.cola.remove(0),this);
                            }
                        } catch (InterruptedException e) {
                            System.out.println(nombre+" Error con este barpero "+isBussy);
                            System.out.println(e.toString());
                        }
                }
            }else{
                System.out.println(barberia.cola.size()+" tam cola");
            }
            if(!isAsSleep){
                try {
                    dormir();
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    public synchronized void cortarPelo(String name,Barbero barber) throws InterruptedException {
        if(barber.isAsSleep()){
            vent.logTextArea.append(barber.getNombre()+": Despertó \n");
            barber.isAsSleep=false;
            if(barber.getNombre().equals("Barbero 1")){
                vent.wakeB1Icon.setVisible(true);
                sleep(500);
                vent.wakeB1Icon.setVisible(false);
                vent.sleepB1Icon.setVisible(false);
            }else if(barber.getNombre().equals("Barbero 2")){
                vent.wakeB2Icon.setVisible(true);
                sleep(500);
                vent.wakeB2Icon.setVisible(false);
                vent.sleepB2Icon.setVisible(false);
            }
        }
        barber.isBussy = true;
        vent.logTextArea.append(barber.getNombre()+ ": Cortando el pelo a Cliente " + name+"\n");
        if(nombre.equals("Barbero 1")){
            vent.corteB1Icon.setVisible(true);
            vent.corteB1Text.setText("Cliente "+name);
        }else if(nombre.equals("Barbero 2")){
            vent.corteB2Icon.setVisible(true);
            vent.corteB2Text.setText("Cliente "+name);
        }
        sleep(vent.sleepcorte);
        vent.logTextArea.append(barber.getNombre()+ ": Terminó de cortar el pelo a Cliente "+ name+"\n");
        barber.isBussy = false;
        if(nombre.equals("Barbero 1")){
            vent.corteB1Icon.setVisible(false);    
            vent.corteB1Text.setText("");
        }else if(nombre.equals("Barbero 2")){
            vent.corteB2Icon.setVisible(false);
            vent.corteB2Text.setText("");
        }
        vent.salidaIcon.setVisible(true);
        vent.salidaText.setText("Cliente "+name+" satisfecho");
        sleep(500);
        vent.salidaIcon.setVisible(false);
        vent.salidaText.setText("Salida Exitosa");
        
    }
    
    public synchronized void dormir() throws InterruptedException {
        vent.logTextArea.append(this.nombre+": Vuelve al cuaje \n");
        isAsSleep = true;
        isBussy = false;
        if(nombre.equals("Barbero 1")){
            vent.sleepB1Icon.setVisible(true);
        }else if(nombre.equals("Barbero 2")){
            vent.sleepB2Icon.setVisible(true);
        }
    }
    
    

    
    
}
