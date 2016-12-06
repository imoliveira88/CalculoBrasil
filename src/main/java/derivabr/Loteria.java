/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derivabr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 23/11/2016.
 */
public class Loteria {
    private int numInicial, numFinal, quantidade;
    private List<Integer> sorteados;

    public Loteria(){
        sorteados = new ArrayList<>();
    }

    public int getNumInicial() {
        return numInicial;
    }

    public void setNumInicial(int numInicial) {
        this.numInicial = numInicial;
    }

    public int getNumFinal() {
        return numFinal;
    }

    public void setNumFinal(int numFinal) {
        this.numFinal = numFinal;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public List<Integer> getSorteados() {
        return sorteados;
    }

    public void setSorteados(List<Integer> sorteados) {
        this.sorteados = sorteados;
    }

    public void sorteia(){
        int i = 0;
        int atual;
        do{
            atual = (int) Math.floor((numFinal - numInicial)*Math.random());
            if(!sorteados.contains(atual)){
                i++;
                sorteados.add(atual);
            }
        }while(i < quantidade);
    }
    
    public static void main(String[] args){
        Loteria loteria = new Loteria();
        loteria.setNumInicial(1);
        loteria.setNumFinal(60);
        loteria.setQuantidade(6);
        
        loteria.sorteia();
        
        System.out.println(loteria.getSorteados().toString());
    }
}

