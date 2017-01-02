/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import derivabr.RaizIteracao;
import derivabr.Simpson;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean(name = "simpsonBean")
@RequestScoped
public class SimpsonBean extends BeanGeral{
    
    private String expressaoFuncao;
    private Double esquerda, direita;
    private int n;
    private List<RaizIteracao> iteracoes;
    private BigDecimal integral;
    private String resposta;

    public String getExpressaoFuncao() {
        return expressaoFuncao;
    }

    public void setExpressaoFuncao(String expressaoFuncao) {
        this.expressaoFuncao = expressaoFuncao;
    }


    public Double getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Double esquerda) {
        this.esquerda = esquerda;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
    

    public Double getDireita() {
        return direita;
    }

    public void setDireita(Double direita) {
        this.direita = direita;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
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
    
    public void resolver(){
        Simpson simp =  new Simpson(expressaoFuncao,esquerda,direita,n);
        
        try{
            simp.resolve();
            this.integral = simp.getIntegral();
            this.iteracoes = simp.getIteracoes();
            this.resposta = simp.toLatex();
            this.addMensagem("A integral aproximada foi calculada!");
        }catch(Exception e){
            this.addMensagem("Houve algum erro... revise suas entradas!");
        }
    }
}
