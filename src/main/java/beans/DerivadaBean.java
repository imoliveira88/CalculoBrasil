/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import derivabr.Arvore;
import derivabr.StringImproved;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean(name = "derivadaBean")
@SessionScoped
public class DerivadaBean extends BeanGeral{
    
    private String expressao;
    private String derivada;
    private static List<String> exemplos;
    private static int indice;

    public DerivadaBean() {
        exemplos = new ArrayList<>();
        exemplos.add("sen(tan(x^2+5x^4)+cos(2x))-cot(x)");
        exemplos.add("x^3+x^4-tan(cos(sec(x)))");
        exemplos.add("5ln(x^3-tan(x^3))-e^(5x)");
        indice = 0;
    }

    public String getExpressao() {
        return expressao;
    }

    public void setExpressao(String expressao) {
        this.expressao = expressao;
    }

    public String getDerivada() {
        return derivada;
    }

    public void setDerivada(String derivada) {
        this.derivada = derivada;
    }
    
    public void derivar() throws Exception{
        try {
            /*StringImproved simproved = new StringImproved(this.expressao);
            this.derivada = simproved.derivadorInteligente();*/
            
            Arvore arvore = new Arvore();
            arvore = Arvore.stringToArvore(this.expressao);
            this.derivada = arvore.derivadaCorreta().imprimeArvore();

            addMensagem("Sua derivada está pronta!");
        } catch (Exception e) {
            addMensagem("Ocorreu algum erro... Reveja sua expressão!");
        }
    }
    
    public void exemplificar(){
        expressao = exemplos.get(indice);
        indice = (indice + 1)%3;
        derivada = "";
    }
    
}
