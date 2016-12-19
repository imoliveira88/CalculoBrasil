/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derivabr;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class Simpson {
    private Arvore funcao;
    private Double esquerda, direita;
    private int n;
    private List<RaizIteracao> iteracoes;
    private BigDecimal integral;
    
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
        BigDecimal E = BigDecimal.ZERO;
        BigDecimal I = BigDecimal.ZERO;
        BigDecimal P = BigDecimal.ZERO;
        for(int i = 0; i < n+1; i++){
            x = esquerda + i*h;
            iteracoes.add(new RaizIteracao(x+"",this.funcao(x)+"","",i+""));
            if(i == 0 || i == n) E.add(this.funcao(x));
            else{
                if(i%2 == 1) I.add(this.funcao(x));
                else P.add(this.funcao(x));
            }
        }
        this.integral = (E.add(I.multiply(BigDecimal.valueOf(4.0)))).add(P.multiply(BigDecimal.valueOf(2.0))).multiply(BigDecimal.valueOf(h/3));
    }
    
}
