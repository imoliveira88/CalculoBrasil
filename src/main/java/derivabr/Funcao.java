package derivabr;

public class Funcao {
	public int tipo;
	public String argumento;
	public double coeficiente;
	public double expoente; //Usado somente para o mon�mio
	
	public Funcao(){
		this(0,"",0,0);
	}
	
	public Funcao(int t, String arg, double coef, double exp){
		this.tipo = t;
		this.argumento = arg;
		this.coeficiente = coef;
		this.expoente = exp;
	}
	
	public String expressaoFuncao(){
		String parcial = "";
		switch(this.tipo){
			case 0:
				parcial = "sen";
				break;
			case 1:
				parcial = "cos";
				break;
			case 2:
				parcial = "tan";
				break;
			case 3:
				parcial = "sec";
				break;
			case 4:
				parcial = "csc";
				break;
			case 5:
				parcial = "cot";
				break;
			case 6:
				parcial = "ln";
				break;
			case 7:
				parcial = "e^";
				break;
			case 8:
				return this.coeficiente + "x^" + this.expoente;
		}
		
		return this.coeficiente + parcial + "(" + this.argumento + ")";
	}
	
	public String poeArg(String s, String arg){
		return s + "(" + arg + ")";
	}
	
	public Derivada derivadaBasica(){ //Retorna a expressão da derivada da função básica dada, sem levar em conta a regra da cadeia
		String parcial = "";
		Derivada der = new Derivada("",1);
		double aux = this.coeficiente;
		switch(this.tipo){
			case 0:
				parcial = poeArg("cos",this.argumento);
				break;
			case 1:
				parcial = poeArg("sen",this.argumento);
				break;
			case 2:
				parcial = poeArg("sec^2",this.argumento);
				break;
			case 3:
				parcial = poeArg("sec",this.argumento) + poeArg("tan",this.argumento);
				break;
			case 4:
				parcial = poeArg("csc",this.argumento) + poeArg("cot",this.argumento);
				break;
			case 5:
				parcial = poeArg("csc^2",this.argumento);
				break;
			case 6:
				der.coeficiente = aux;
				der.argumento = "(" + this.argumento + ")" + "^(-1)";
				break;
			case 7:
				parcial = poeArg("e^",this.argumento);
				break;
			case 8:
				aux *= this.expoente;
				der.coeficiente = aux;
				if(this.expoente != 1){
					if(this.expoente != 2) der.argumento = "x^" + (this.expoente - 1);
					else der.argumento = "x";
				}
				else der.argumento = "";
				break;
		}
		if(this.tipo == 0 || this.tipo == 2 || this.tipo == 3 || this.tipo == 7){
			der.coeficiente = aux;
			der.argumento = parcial;
		}
		if(this.tipo == 1 || this.tipo == 4 || this.tipo == 5){
			der.coeficiente = -1*aux;
			der.argumento = parcial;
		}
		return der;
	}
	
	public Derivada derivadaCorreta(){ //Retorna a expressão da derivada correta usando a regra da cadeia
		Funcao func = new Funcao(this.tipo, this.argumento, this.coeficiente, this.expoente);
		StringImproved string = new StringImproved();
		int i = 0;
		string.setString(func.argumento);
		Derivada aux;
		Derivada der = new Derivada("",1);
		while(!string.getString().equals("x")){
			aux = func.derivadaBasica();
			der.argumento += "*("+ aux.argumento + ")";
			der.coeficiente *= aux.coeficiente;
			func = string.caracterizaFuncao();
			string.setString(func.argumento);
			i++;
		}
		aux = func.derivadaBasica();
		if(!aux.argumento.equals("") && Math.abs(aux.coeficiente) != 1){
                    der.argumento += "*(" + aux.argumento + ")";
                    System.out.println("Coeficiente do negócios: " + aux.coeficiente);
                }
                else der.argumento += aux.argumento;
		der.coeficiente *= aux.coeficiente;
		return der;
	}
        
        public static void main(String[] args){
            StringImproved string = new StringImproved("sen(tan(x^2+5x^4)+cos(2x))-cot(x)");
            Funcao func = new Funcao(0,"3x",2,1);
            
            //Estrutura de dados: desceu na hierarquia, '(', subiu na hierarquia, ')'
            //Leitura esquerda para direita, baixo para cima
            //Só passa para o próximo nó à direita, quando escrita toda a hierarquia filha
            //String escreveArvore(Arvore arvore)
            //String avaliaArvore(Arvore arvore, Double num) -> Avalia de baixo, para cima
            //Arvore derivaArvore(Arvore arvore)
            
            System.out.println("Função: " + string.getString() + " Derivada: " + string.derivadorInteligente());
            
        }
	
}

/*
 * Tipos:
 * 
 * 0: sen
 * 1: cos
 * 2: tan
 * 3: sec
 * 4: cosec
 * 5: cotg
 * 6: ln
 * 7: e^
 * 8: x^n
 */