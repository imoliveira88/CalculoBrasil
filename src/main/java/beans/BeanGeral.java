package beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class BeanGeral {
    
    public void addMensagem(String mensagem){
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg;
        
        msg = new FacesMessage("",mensagem);
        context.addMessage("destinoAviso", msg);
    }
    
}