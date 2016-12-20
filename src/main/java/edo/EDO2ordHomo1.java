/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edo;

public class EDO2ordHomo1 extends EDO2ordHomo{
    
    public EDO2ordHomo1(int a, int b, int c) {
        super(a, b, c);
    }

    @Override
    public String passoFinal() {
        String resposta = "";
        double p1, p2;
        resposta += "Como " + "$\\Delta = " + this.delta + " < 0$, "
                 + "a equação característica terá duas soluções complexas conjugadas: \n\n";
        
        resposta += "$$m = \\frac{-b \\pm \\sqrt{\\Delta}}{2\\times a} = \\frac{"
                 + Auxiliar.sinalCorreto('-',this.b) + " \\pm \\sqrt{" + this.delta + "}}{2\\times ("
                 + this.a + ")} = "
                 + "\\frac{" + Auxiliar.sinalCorreto('-',this.b) + " \\pm i \\times " + Auxiliar.duasCasas(Math.sqrt(-this.delta)) +"}{" + 2*this.a + "}$$\n\n";
        
        p1 = -this.b;
        p1 /= 2*this.a;
        
        p2 = Math.sqrt(-this.delta);
        p2 /= 2*this.a;
        
        resposta += "Portanto, \n\n";
        
        resposta += "$$"
                 + "\\begin\n" +
"                 + \"\\\\b{array}{l}\n"
                 + "m1 = " + Auxiliar.duasCasas(p1) + "- i \\times " + Auxiliar.duasCasas(p2) + "\\" + "\\" + "\n"
                 + "m2 = " + Auxiliar.duasCasas(p1) + "+ i \\times " + Auxiliar.duasCasas(p2) + "\n"
                 + "\\end{array}$$\n\n";
        
        resposta += "Desta forma, a solução geral da EDO é: \n\n";
        
        this.respostaY = "y = e^{" + Auxiliar.duasCasas(p1) + "x} \\times (Acos(" + Auxiliar.duasCasas(p2) + "x) + Bsen(" + Auxiliar.duasCasas(p2) + "x))";
        
        resposta += "$$" + this.respostaY + "$$";
        
        return resposta;
    }
    
}
