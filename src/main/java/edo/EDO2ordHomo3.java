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
public class EDO2ordHomo3 extends EDO2ordHomo{

    public EDO2ordHomo3(int a, int b, int c) {
        super(a, b, c);
    }

    @Override
    public String passoFinal() {
        String resposta = "";
        double m1, m2;
        resposta += "Como " + "$\\Delta = " + this.delta + " > 0$, "
                 + "a equação característica terá duas soluções reais distintas, que são: \n\n";
        
        resposta += "$$m = \\frac{-b \\pm \\sqrt{\\Delta}}{2\\times a} = \\frac{"
                 + EDO2ordHomo.sinalCorreto('-',this.b) + " \\pm \\sqrt{" + this.delta + "}}{2\\times ("
                 + this.a + ")} = "
                 + "\\frac{" + EDO2ordHomo.sinalCorreto('-',this.b) + " \\pm" + EDO2ordHomo.duasCasas(Math.sqrt(this.delta)) +"}{" + 2*this.a + "}$$\n\n";
        
        m1 = -this.b - Math.sqrt(this.delta);
        m1 /= 2*this.a;
        
        m2 = -this.b + Math.sqrt(this.delta);
        m2 /= 2*this.a;
        
        resposta += "Portanto, \n\n";
        
        resposta += "$$"
                 + "\\begin{array}{l}\n"
                 + "m1 = " + EDO2ordHomo.duasCasas(m1) + "\\" + "\\" + "\n"
                 + "m2 = " + EDO2ordHomo.duasCasas(m2) + "\n"
                 + "\\end{array}$$\n\n";
        
        resposta += "Desta forma, a solução geral da EDO é: \n\n";
        
        this.respostaY = "y = Ae^{" + EDO2ordHomo.duasCasas(m1) + "x} + Be^{" + EDO2ordHomo.duasCasas(m2) + "x}";
        
        resposta += "$$"+ this.respostaY + "$$";
        
        return resposta;
    }
    
}
