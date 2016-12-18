/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edo;

/**
 *
 * @author Magalhães Oliveira
 */
public class MediadorEDO2ordHomo {
    private final int a, b, c;
    
    public MediadorEDO2ordHomo(int a, int b, int c){
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public String resolve(){
        int delta = b*b - 4*a*c;
        
        if(delta < 0) return new EDO2ordHomo1(a,b,c).resolve();
        else{
            if(delta == 0) return new EDO2ordHomo2(a,b,c).resolve();
            else return new EDO2ordHomo3(a,b,c).resolve();
        }
    }
    
}
