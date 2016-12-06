package derivabr;

public class Derivada {
	public double coeficiente;
	public String argumento;
	
	public Derivada(String arg, double coef){
		this.argumento = arg;
		this.coeficiente = coef;
	}
	
	public String derivadaToString(){
		if(this.coeficiente == 1 && !this.argumento.equals("")) return this.argumento;
		else{
			if(this.coeficiente == -1) return "-" + this.argumento;
			else return this.coeficiente + this.argumento;
		}
	}
	
}