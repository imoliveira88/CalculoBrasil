package derivabr;

import java.math.BigDecimal;

/*
 * Objetiva achar raízes de funções da forma
 * f(x) = ax^2 + bx + c + dln(ex)+ fe^(gx) + hsen(ix)
 * derivada(f(x)) = 2ax + b + de/x + fge^(gx) + hicos(ix)
 */

public class NewtonRaphson {
	
	public int itmax;
	public double m, n;
	public double erromax;
	public double raizatual;
	public int iteracoes = 0;
        public String expressaoFuncao;
	
	public NewtonRaphson(){
		this.itmax = 0;
		this.m = 0;
		this.n = 0;
		this.erromax = 0;
	}
	
	public BigDecimal funcao(double x){
		//StringImproved si = new StringImproved();
                Arvore expressao = Arvore.stringToArvore(expressaoFuncao);
                return expressao.avaliaArvore(x);
                
                //return BigDecimal.valueOf(Double.parseDouble(si.resolve(expressaoFuncao, x)));
	}
	
	public BigDecimal derivada(double x){
		/*StringImproved si = new StringImproved();
                Double aux;
                si.setString(expressaoFuncao);
                String der = si.derivadorInteligente();
                System.out.println("Prob der: f'(" + x + ") Derivada literal: " + der);
                //devemos multiplicar os coeficientes
                System.out.println("Problema na derivada! " + si.resolve(der, x));
                return BigDecimal.valueOf(Double.parseDouble(si.resolve(der, x)));*/
                Arvore derivada = Arvore.stringToArvore(expressaoFuncao).derivadaCorreta();
                return derivada.avaliaArvore(x);
	}
	
	public void resolve(){
		double erro = n-m;
		double raizanterior;
		raizatual = (m+n)/2;
		do{
			raizanterior = raizatual;
                        erro = (funcao(raizatual)).doubleValue()/(derivada(raizatual)).doubleValue();
                        raizatual -= erro;
                        
			iteracoes++;
			erro = Math.abs(erro);
		}while(iteracoes<itmax && erro>erromax);
	}
        
        public static void main(String[] args){
            NewtonRaphson nr = new NewtonRaphson();
            nr.expressaoFuncao = "2*sen(tan(x))-e^(x)";
            nr.m = -2;
            nr.n = 0;
            nr.itmax = 20;
            nr.erromax = 0.01;
            nr.resolve();
            
            System.out.println("Raiz atual: " + nr.raizatual + "Iterações: " + nr.iteracoes);
        }
        
}