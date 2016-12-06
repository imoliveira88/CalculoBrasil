/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derivabr;

/**
 *
 * @author Administrador
 */
public class RaizIteracao {
    private String x, f, e, i;
    
    public RaizIteracao(String x, String f, String e, String i){
        this.i = i;
        this.x = x;
        this.e = e;
        this.f = f;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }
    
    
    
}
