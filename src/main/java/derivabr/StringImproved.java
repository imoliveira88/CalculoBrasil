package derivabr;

import java.math.BigDecimal;

public class StringImproved{
	private String string;
        private final String algarismos = "0123456789.";
        private final String sinais = "-+*";
        private final String inicioFuncao1 = "0123456789"; //Número ao lado da função, que deve multiplicá-la
        private final String inicioFuncao2 = "(-+*"; //Início padrão
        private final String fimExpo = ")-+*"; //Início padrão
	
	public StringImproved(){
		this("");
	}
        
        public StringImproved(String exp){
            this.string = exp;
        }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
    
    //sen, cos, tan, cot, csc, sec, e^, ln, x^  
    //Retorna uma string com argumentos mais prioritários resolvidos
    public String resolve(String s,Double x){
        char aux;
        int leftpar = 0;
        int esquerda = 0; //posição do parêntesis à esquerda mais prioritário
        int direita = s.length()-1; //posição do parêntesis à direita mais prioritário
        int rightpar = 0;
        int i = 0;
        BigDecimal resul = BigDecimal.valueOf(0);
        int inicioFuncao = 0;
        String fun;

        do {
            aux = s.charAt(i);
            if (aux == '(') {
                leftpar++;
                esquerda = i;
            }//2-(((2+1)+(2-1)*3)*2)
            if (aux == ')') {
                rightpar++;
                direita = i;  // temos uma expressão sem parêntesis a ser avaliada
                resul = this.avaliaArgumento(s.substring(esquerda + 1, direita), x);
                break;
            }
            
            i++;
        } while (i < s.length()); 
        
        if(leftpar == 0){
           resul = this.avaliaArgumento(s.substring(esquerda, direita+1), x);
           return ""+resul;
        } //caso em que não tem mais nenhum parêntesis
        
        for(int j = esquerda-1; j>=0; j--){
            if(inicioFuncao1.contains(s.charAt(j)+"") || inicioFuncao2.contains(s.charAt(j)+"")){
                inicioFuncao = j+1; // j é a posição do sinal, j+1 a posição do início da função
                break;
            }
        }
        
        fun = s.substring(inicioFuncao,esquerda);
        
        System.out.println("Função: " + fun + " calculada em " + resul);
        
        if(esquerda == 0) return this.resolve(this.valorFuncaoIsolada(fun, resul)+s.substring(direita+1,s.length()),x);
        if(direita == s.length()-1) return this.resolve(s.substring(0,inicioFuncao)+this.valorFuncaoIsolada(fun, resul),x);
        return this.resolve(s.substring(0,inicioFuncao)+this.valorFuncaoIsolada(fun, resul)+s.substring(direita+1,s.length()),x);
    }
    
    //Avalia o argumento de certa função. A string arg não possui parêntesis, previamente eliminados em operações pertinentes
    public BigDecimal avaliaArgumento(String arg,Double num){
        String aux = "";
        int i = 0;
        do{
            if(arg.charAt(i) == 'x'){
                if(i >0 && algarismos.contains(arg.charAt(i-1)+"")){
                    aux += "*";
                }
                aux += num;
            }
            else aux += arg.charAt(i);
            i++;
        }while(i < arg.length());
        
        System.out.println("No avaliaArgumento, voltou " + aux);
        
        return this.resolveExpressoesSemParentesis(aux);
    }
    
    public BigDecimal valorFuncaoIsolada(String funcao, BigDecimal num){
        double aux;
        
        if(funcao.equals("")) return num;
        
        System.out.println("valorFuncaoIsolada  funcao: " + funcao + " num" + num);
        
        switch(funcao){
            case "sen":
                aux = Math.sin(num.doubleValue());
                break;
            case "cos":
                aux =  Math.cos(num.doubleValue());
                break;
            case "sec":
                aux = 1/Math.cos(num.doubleValue());
                break;
            case "tan":
                aux = Math.tan(num.doubleValue());
                break;
            case "cot":
                aux = 1/Math.tan(num.doubleValue());
                break;
            case "csc":
                aux = 1/Math.sin(num.doubleValue());
                break;
            case "e^":
                aux = Math.exp(num.doubleValue());
                break;
            default://ln
                aux = Math.log(num.doubleValue());
        }

        System.out.println("Resultado no valorFunçãoIsolada: " + aux);
        return BigDecimal.valueOf(aux);
    }
    
    public String jogoSinais(String s){
        String parcial = "";
        char aux;
        
        int i = 0;
        
        do{
            aux = s.charAt(i);
            if((aux=='+' || aux=='-') && i+1 < s.length()){
                if(aux == '+'){
                    if(s.charAt(i+1) == '-'){
                        parcial += '-';
                        i += 2;
                    }
                    else if(s.charAt(i+1) == '+'){
                        parcial += '+';
                        i += 2;
                    } else{
                        i++;
                        parcial += aux;
                    }
                }else{
                    if(s.charAt(i+1) == '-'){
                        parcial += '+';
                        i += 2;
                    }
                    else if(s.charAt(i+1) == '+'){
                        parcial += '-';
                        i += 2;
                    } else{
                        i++;
                        parcial += aux;
                    }
                }
            }
            else{
                parcial += aux;
                i++;
            }
        }while(i<s.length());
        
        return parcial;
    }
    
    public String retiraExponenciais(String exp){
        int esquerda = 0;
        int direita = exp.length()-1;
        boolean comeca = false;
        String resultado = "";
        char sinal;
        
        int i = 0;
        
        do{
            if(!comeca){
                if(algarismos.contains(exp.charAt(i)+"")){
                    comeca = true;
                    esquerda = i;
                }
                else{
                    if(sinais.contains(exp.charAt(i)+"")) resultado += exp.charAt(i);
                }
                i++;
            }
            else{
                if (exp.charAt(i) == 'E') {
                    i++;
                    if (sinais.contains(exp.charAt(i) + "")) {
                        resultado += BigDecimal.valueOf(Double.parseDouble(exp.substring(esquerda, i + 2)));
                        i += 2;
                        comeca = false;
                        continue;
                    } else {
                        resultado += BigDecimal.valueOf(Double.parseDouble(exp.substring(esquerda, i + 1)));
                        i++;
                        comeca = false;
                        continue;
                    }
                }
                else{
                    if(exp.charAt(i) == '/' || exp.charAt(i) == '^' || sinais.contains(exp.charAt(i)+"")|| i == exp.length()-1){
                        comeca = false;
                        resultado += exp.substring(esquerda,i+1);
                        i++;
                        continue;
                    }
                    i++;
                }
            }
        }while(i < exp.length());
        
        return resultado;
            
    }
    
    //Dá o valor do resultado da expressão sem parêntesis s
    public BigDecimal resolveExpressoesSemParentesis(String s){
        String parcial = this.jogoSinais(s);
        parcial = this.retiraExponenciais(parcial);
        boolean operacao = false;
        double temp;
        char sinal = '+';
        String esquerda = "";
        String direita = "";
        
        do {
            if (parcial.contains("^")) {
                parcial = resolveOperacao('^', parcial);
            } else if (parcial.contains("*")) {
                System.out.println("A ser resolvida: " + "* parcial: " + parcial);
                parcial = resolveOperacao('*', parcial);
            } else if (parcial.contains("/")) {
                parcial = resolveOperacao('/', parcial);
            }
        } while (temSinaisPrior(parcial));
        
        esquerda += parcial.charAt(0);
        if(parcial.length() == 1) return BigDecimal.valueOf(parcial.charAt(0));

        int i = 1;
        
        do{
            if(!operacao){
                if((parcial.charAt(i)=='+' || parcial.charAt(i)=='-') && parcial.charAt(i-1) != 'E'){
                    sinal = parcial.charAt(i);
                    operacao = true;
                }
                else esquerda += parcial.charAt(i);
                i++;
            }
            else{
                if(parcial.charAt(i)=='+' || parcial.charAt(i)=='-' || i == parcial.length()-1){
                    if(i != parcial.length()-1) i--;
                    esquerda = "" + this.resolveOperador(sinal, Double.parseDouble(BigDecimal.valueOf(Double.parseDouble(esquerda)).toPlainString()), Double.parseDouble(BigDecimal.valueOf(Double.parseDouble(direita)).toPlainString()));
                    operacao = false;
                    direita = "";
                }
                else{
                    if(parcial.charAt(i) == 'E'){
                        direita += parcial.charAt(i)+parcial.charAt(i+1);
                        i += 2;
                    }
                    else{
                        direita += parcial.charAt(i);
                        i++;
                    }
                }
            }
        }while (i < parcial.length());
        
        return BigDecimal.valueOf(Double.parseDouble(esquerda));
    }
    
    //Resolve a primeira operação determinada por c, na String s e devolve uma String já operada
    public String resolveOperacao(char c, String s){
        String parcial = "";
        double esquerda = 0;
        double direita = 0;
        int limE = 0;
        int i;
        int limD = 0;
        for(i=0; i<s.length(); i++){
            if(s.charAt(i) == c){
                for(int j=i-1; j>=0; j--){
                    if(j == 0){
                        limE = 0;
                        esquerda = Double.parseDouble(s.substring(0,i));
                        System.out.println("Expressão da esquerda: " + esquerda);
                        break;
                    }
                    if(!algarismos.contains(s.charAt(j)+"")){
                        if(s.charAt(j) == '+' || s.charAt(j) == '-') limE = j;
                        else limE = j+1;
                        
                        esquerda = Double.parseDouble(s.substring(limE, i));
                        System.out.println("Expressão da esquerda: " + esquerda);
                        break;
                    }
                }
                for(int j=i+1; j < s.length(); j++){
                    if(j == s.length()-1){
                        limD = s.length()-1;
                        direita = Double.parseDouble(s.substring(i+1, limD+1));
                        System.out.println("Expressão da direita: " + direita);
                        break;
                    }
                    if(!algarismos.contains(s.charAt(j)+"") && (j>i+1)){
                        limD = j - 1;
                        direita = Double.parseDouble(s.substring(i+1, limD+1));
                        System.out.println("Expressão da direita: " + direita);
                        break;
                    }
                }
                break;
            }   
        }

        System.out.println("No resolve operação: " + esquerda + c + direita);
        
        esquerda = resolveOperador(c,esquerda,direita);
        
        System.out.println("Resultado: " + esquerda);
        
        if(limE != 0) parcial += s.substring(0,limE);
        if(esquerda >= 0 && limE != 0) parcial += '+';
        parcial += esquerda;
        if(limD != s.length() - 1) parcial += s.substring(limD+1,s.length());
        return parcial;        
    }
    
    public double resolveOperador(char op, double esq, double dir){
        switch(op){
            case '-':
                return esq - dir;
            case '+':
                return esq + dir;
            case '*':
                return esq*dir;
            case '/':
                return esq/dir;
            default:
                return Math.pow(esq, dir);
        }
    }
    
    //Retorna verdadeiro se a expressão em s tem operadores aritméticos
    public boolean temSinaisPrior(String s){
        return s.contains("*")||s.contains("/")||s.contains("^");
    }

	public int extraiTipo(){
		String aux = "";
		int comeca = 0;
		char c;
		for(int i=0; i<this.string.length(); i++){
			c = this.string.charAt(i);
			if(c > 'b' && c < 'z' && comeca == 0){
				comeca = 1;
				aux += c;
				continue;
			}
			if(comeca == 1){
				aux += c;
				switch(aux){
					case "sen":
						return 0;
					case "cos":
						return 1;
					case "tan":
						return 2;
					case "sec":
						return 3;
					case "csc":
						return 4;
					case "cot":
						return 5;
					case "ln":
						return 6;
					case "e^":
						return 7;
					default:
						continue;
				}
			}
		}
		return 8;
	}
	
	public double extraiExp(){
		int comeca = 0;
		String aux = "";
		char c;
		for(int i = 0; i < this.string.length(); i++){
			c = this.string.charAt(i);
			
			if(c == '^'){
				comeca = 1;
				continue;
			}			
			if(comeca == 1){
                            if(!fimExpo.contains(c+"")){
                                if(c != '(') aux += c;
                            }
                            else break;
                        }
		}
		if(comeca == 1){
                    System.out.println("Problema... o aux é: " + aux);
                    return Double.parseDouble(aux);
                }
		else return 1; // Caso de x sozinho
	}
	
	public double extraiCoef(){
		String aux = "";
		double coef;
		char c;
		for(int i=0; i<this.string.length(); i++){
			c = this.string.charAt(i);
			if(!Character.isLetter(c) && this.string.charAt(i) != '*') aux += c;
			else break;
		}
		if (aux != ""){
			if(aux.equals("-") || aux.equals("+")){
				if(aux.equals("-")) return -1;
				else return 1;
			}
			else coef = Double.parseDouble(aux);
		}
		else coef = 1;
		return coef;
	}
	
	public String extraiArgumento(){
		String arg = "";
		int leftpar = 0;
		int rightpar = 0;
		char c;
		int comeca = 0;
		for(int i=0; i<this.string.length(); i++){
			c = this.string.charAt(i);
			if(c =='('){
				comeca = 1;
				leftpar++;
				if(leftpar == 1) continue;
			}
			if(comeca == 1){
				if(c == ')'){
					rightpar++;
					if(rightpar == leftpar) break;
					else arg += c;
				}
				else arg += c;
			}
		}
		return arg;
	}
	
	public Funcao caracterizaFuncao(){
		Funcao func = new Funcao();
		func.tipo = this.extraiTipo();
		if(func.tipo == 8) func.expoente = this.extraiExp();
		func.coeficiente = this.extraiCoef();
		if(func.tipo != 8) func.argumento = this.extraiArgumento();
		else func.argumento = "x";
		return func;
	}
	
	public boolean temCaractere(char c){
		for(int i=0;i<this.string.length();i++){
			if(this.string.charAt(i) == c) return true;
		}
		return false;
	}
	
	public boolean temCaractereModif(char c){
		for(int i=1;i<this.string.length();i++){
			if(this.string.charAt(i) == c) return true;
		}
		return false;
	}
        
        //Evita coisas feias como -> Função: 2sen(3x) Derivada: +2.0cos(3x)*(+3.0)
        
	
	public String derivadorInteligente(){
		Funcao func;
		String derivada = "";
		Derivada der;
		StringImproved expAtual = new StringImproved();
		int i = 0;
		int leftpar = 0;
		int rightpar = 0;
		char aux='c';
		
		if(this.string.equals("x")) this.string += "^1";
		
		this.string += '+'; //Resolve o problema do fim da expressão, sem adicionar o outro erro
		
		do{
			aux = this.string.charAt(i);
			if(aux == '(') leftpar++;
			if(aux == ')') rightpar ++;
			if(leftpar == rightpar){ // Se a expressão está completa, então...
				if(aux == '+' || aux == '-'){ // Se a expressão está completa e acharmos um sinal de combinação linear, então...
					
                                    if (!expAtual.temCaractere('x')) {
                                        derivada += "0";
                                    } else {
                                        func = expAtual.caracterizaFuncao();
                                        der = func.derivadaCorreta();
                                        if (der.coeficiente < 0) derivada += der.derivadaToString();
                                        else derivada += "+" + der.derivadaToString();
                                        expAtual.string = func.argumento;
                                    }				
				
				expAtual.string = "";
				expAtual.string += aux;
				leftpar = 0;
				rightpar = 0;
				}
				else expAtual.string += aux;
			}
			else expAtual.string += aux;
			i++;
		}while(i<this.string.length());		
		return derivada;
		
	}
        
        public static void main(String[] args){
            StringImproved si = new StringImproved();
            String aux = "3.87866E-3+1.674322E2*2.54677";
            
            System.out.println("Original: " + aux + " Transformada: " + si.retiraExponenciais(aux));
            
            System.out.println("Resposta final: " + si.resolve("sen(x^2-3x)+tan(x)",2.0));
        }

}