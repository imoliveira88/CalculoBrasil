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
public class EDO2ordHomo2 extends EDO2ordHomo{
    
    public EDO2ordHomo2(int a, int b, int c) {
        super(a, b, c);
    }

    @Override
    public String passoFinal() {
        String resposta = "";
        double m1;
        resposta += "Como " + "$\\Delta = " + this.delta + "$, "
                 + "a equação característica terá apenas uma solução: \n\n";
        
        resposta += "$$m = \\frac{-b}{2\\times a} = \\frac{"
                 + EDO2ordHomo.sinalCorreto('-',this.b) + "}{2\\times ("
                 + this.a + ")}$$\n\n";

        m1 = -this.b;
        m1 /= 2*this.a;
        
        resposta += "Portanto, " 
                 + "$m1 = m2 =" + EDO2ordHomo.duasCasas(m1) + "$\\" + "\\" + "\n\n";
        
        resposta += "Desta forma, a solução geral da EDO é: \n\n";
        
        this.respostaY = "y = e^{" + EDO2ordHomo.duasCasas(m1) + "x}(Ax + B)";
        
        resposta += "$$" + this.respostaY + "$$";
        
        return resposta;
    }
    
}
