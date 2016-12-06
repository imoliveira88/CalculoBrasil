/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import derivabr.NewtonRaphson;
import derivabr.RaizIteracao;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean(name = "calculoNBean")
@SessionScoped
public class CalculoNBean extends BeanGeral{
    private double m,n,raizatual,erromax;
    private int iteracoes, itmax;
    private String expressaoFuncao;
    private List<RaizIteracao> x;
    
    public CalculoNBean(){
        x = new ArrayList<>();
    }

    public double getM() {
        return m;
    }

    public void setM(double m) {
        this.m = m;
    }

    public List<RaizIteracao> getX() {
        return x;
    }

    public void setX(List<RaizIteracao> x) {
        this.x = x;
    }

    public double getN() {
        return n;
    }

    public void setN(double n) {
        this.n = n;
    }

    public double getRaizatual() {
        return raizatual;
    }

    public void setRaizatual(double raizatual) {
        this.raizatual = raizatual;
    }

    public int getIteracoes() {
        return iteracoes;
    }

    public void setIteracoes(int iteracoes) {
        this.iteracoes = iteracoes;
    }

    public double getErromax() {
        return erromax;
    }

    public void setErromax(double erromax) {
        this.erromax = erromax;
    }

    public int getItmax() {
        return itmax;
    }

    public void setItmax(int itmax) {
        this.itmax = itmax;
    }

    public String getExpressaoFuncao() {
        return expressaoFuncao;
    }

    public void setExpressaoFuncao(String expressaoFuncao) {
        this.expressaoFuncao = expressaoFuncao;
    }
    
    public void resolver(){
        NewtonRaphson nr = new NewtonRaphson();
        nr.m = m;
        nr.n = n;
        nr.itmax = itmax;
        nr.erromax = erromax;
        nr.expressaoFuncao = expressaoFuncao;
        
        try{
            nr.resolve();
            
            x = nr.x;

            iteracoes = nr.iteracoes;
            raizatual = nr.raizatual;
            
            this.addMensagem("A raiz aproximada foi calculada!");
            
        }catch(Exception e){
            this.addMensagem("Houve algum erro... revise suas entradas!");
        }
    }
}
