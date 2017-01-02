/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import edo.MediadorEDO2ordHomo;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author MPR
 */

@ManagedBean(name = "edoBean")
@SessionScoped
public class EDOBean extends BeanGeral{
    private int a, b, c;
    private String resposta;

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

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
    
    public void resolver(){
        try{
            this.resposta = (new MediadorEDO2ordHomo(a,b,c)).resolve();
            this.addMensagem("Resolução em formato LaTeX pronta!");
        }catch(Exception e){
            this.addMensagem("Houve algum erro! Revise os formatos e tente novamente!");
        }
    }
    
}
