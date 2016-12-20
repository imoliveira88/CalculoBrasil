package derivabr;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Simpson implements Latex{
    private Arvore funcao;
    private Double esquerda, direita;
    private int n;
    private List<RaizIteracao> iteracoes;
    private BigDecimal integral;
    private String latex;
    
    public Simpson(String expressaoFuncao, Double esquerda, Double direita, int n){
        this.iteracoes = new ArrayList<>();
        this.funcao = Auxiliar.stringToArvore(expressaoFuncao);
        this.esquerda = esquerda;
        this.direita = direita;
        this.n = n;
    }

    public Arvore getFuncao() {
        return funcao;
    }

    public List<RaizIteracao> getIteracoes() {
        return iteracoes;
    }

    public String getLatex() {
        return latex;
    }

    public void setLatex(String latex) {
        this.latex = latex;
    }

    public void setIteracoes(List<RaizIteracao> iteracoes) {
        this.iteracoes = iteracoes;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }
    

    public void setFuncao(Arvore funcao) {
        this.funcao = funcao;
    }

    public Double getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Double esquerda) {
        this.esquerda = esquerda;
    }

    public Double getDireita() {
        return direita;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setDireita(Double direita) {
        this.direita = direita;
    }
    
    public BigDecimal funcao(double x) {
        return this.funcao.avaliaArvore(x);
    }
    
    public void resolve(){
        double h = (direita-esquerda)/n;
        double x;
        double E = 0;
        double P = 0;
        double I = 0;
        
        latex += "Queremos calcular o valor aproximado da integral $\\int^" + direita + "_" + esquerda + funcao.imprimeArvore() + "$.\n\n";
        latex += "$$ h = \\frac{" + direita + "-" + esquerda + "}{" + n + "}$ = " + "\frac{" + (direita - esquerda) + "}{" + n + "} = " + (direita-esquerda)/n + "$$";
        latex += "Tendo posse do intervalo de integração, bem como do valor de h, estamos aptos a calcular a integral aproximada por Simpson:\n\n";
        
        for(int i = 0; i < n+1; i++){
            x = esquerda + i*h;
            latex += "Iteração: " + i + ", $x_" + i + "$ = " + x + ", $f(x_" + i + ")$ = " + this.funcao(x) + "\n";
            iteracoes.add(new RaizIteracao(x+"",this.funcao(x)+"","",i+""));
            if(i == 0 || i == n) E += this.funcao(x).doubleValue();
            else{
                if(i%2 == 1) I += this.funcao(x).doubleValue();
                else P += this.funcao(x).doubleValue();
            }
        }
        latex += "\n";
        latex += "$E = " + E + "$, $P = " + P + "$, $I = " + I + "$\n\n";
        
        latex += "Utilizando a fórmula $integral \\approx \\frac{h}{3} \\times (E + 4I + 2P)$, temos: ";        
        
        this.integral = BigDecimal.valueOf(h*(E + 4*I + 2*P)/3);
        
        latex += "$$" + h/3 + "(" + E + "4\\times " + I + "2\\times " + P + ")$$ \\approx " + this.integral;
    }
    
    @Override
    public String toLatex(){
        resolve();
        return this.latex;
    }
    
}
