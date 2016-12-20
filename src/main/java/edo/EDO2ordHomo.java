/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edo;

public abstract class EDO2ordHomo {
    protected final int a;
    protected final int b;
    protected final int c;
    protected int delta;
    protected String respostaY;
    
    public EDO2ordHomo(int a, int b, int c){
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public String resolve(){
        String resposta = "";
        this.delta = b*b - 4*a*c;
        
        resposta += "\\textbf{Equação a ser resolvida:} " + this.toString() + "\\" + "\\[0.5cm]\n\n";
        
        resposta += "Equação característica: " + "$" + this.eqCaracteristica() + "$" + "\n\n";
        
        resposta += "$$\\Delta = " + "(" + this.b + ")^2 - 4 \\times (" + this.a + ") \\times (" + this.c + ") = " + delta + "$$\n\n";
        resposta += this.passoFinal();
        
        return resposta;
    }
    
    public abstract String passoFinal();
    
    @Override
    public String toString(){
        return this.a + "y'' + " + this.b + "y' + " + this.c + "y = 0";
    }
    
    public String eqCaracteristica(){
        return this.a + "m^2 + " + this.b + "m + " + this.c + " = 0";
    }
    
}
