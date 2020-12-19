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
public class Mesa {
    Tenedor arraytenedores[];  
    
    public Mesa(){
        arraytenedores = new Tenedor[5];
        for(int i=0; i<5; i++){
         arraytenedores[i] = new Tenedor(i);
        }
    }

    public Tenedor ObtenerTen(int notenedor){
        return arraytenedores[notenedor];
    }

    public int ObtenerTenedorDerecho(int numeroder){
        return (numeroder+1)%5;
    }
    public int ObtenerTenedorIzquierdo(int numeroizq){
        return numeroizq;
    }
}
