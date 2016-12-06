/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import derivabr.Equacao2Grau;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean(name = "equacoesBean")
@SessionScoped
public class EquacoesBean extends BeanGeral{
    private int a,b,c;
    private String r1, r2;

    public EquacoesBean(){
        
    }
    
    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public String getR1() {
        return r1;
    }

    public void setR1(String r1) {
        this.r1 = r1;
    }

    public String getR2() {
        return r2;
    }

    public void setR2(String r2) {
        this.r2 = r2;
    }
    
    public void resolver(){
        Equacao2Grau e2g = new Equacao2Grau(a,b,c);
        
        try{
            e2g.resolve();

            r1 = e2g.r1;
            r2 = e2g.r2;
            
            this.addMensagem("A equação foi resolvida!");
            
            setA(0);
            setB(0);
            setC(0);
        }catch(Exception e){
            this.addMensagem("Houve algum problema... revise seus dados!");
        }
    }
    
}
