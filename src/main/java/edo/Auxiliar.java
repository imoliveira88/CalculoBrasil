/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edo;

/**
 *
 * @author MPR
 */
public class Auxiliar {
    
    public static double duasCasas(double valor){
        double parcial;
        parcial = Math.round(100*valor);
        return parcial/100;
    }
    
    public static String sinalCorreto(char sinal, int valor){
        String parcial = "";
        if(sinal == '+') return "" + valor;
        else{
            if(valor >= 0) return "-" + valor;
            else{
                parcial += '+' + Math.abs(valor);
                return parcial;
            }
        }
    }
    
}
