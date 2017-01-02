package derivabr;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NewtonRaphson implements Latex{
	
	public int itmax;
	public double m, n;
	public double erromax;
	public double raizatual;
	public int iteracoes = 0;
        public String expressaoFuncao;
        public List<RaizIteracao> x;
        public String latex;
	
	public NewtonRaphson(){
                this.x = new ArrayList<>();
		this.itmax = 0;
		this.m = 0;
		this.n = 0;
		this.erromax = 0;
                this.latex = "";
	}
	
	public BigDecimal funcao(double x){
                Arvore expressao = Auxiliar.stringToArvore(expressaoFuncao);
                return expressao.avaliaArvore(x);    
        }
	
	public BigDecimal derivada(double x){
                Arvore derivada = Auxiliar.stringToArvore(expressaoFuncao).derivadaCorreta();
                return derivada.avaliaArvore(x);
	}
	
	public void resolve(){
		double erro;
		double raizanterior;
		raizatual = (m+n)/2;
                
                latex += "Queremos calcular o valor aproximado da raiz de $^" + this.expressaoFuncao + "= 0$.\n\n";
                latex += "Utilizando como aproximação inicial $x_0 = " + raizatual + "$, que é o ponto médio do intervalo fornecido, temos:\n\n";
                
                x.add(new RaizIteracao(raizatual+"",funcao(raizatual)+"","--","0"));
                
                latex += "Iteração " + iteracoes + ", x_" + iteracoes + " = " + raizatual + ", f(x_" + iteracoes + ") = " + funcao(raizatual) + ", erro = --\n\n";
		do{
			raizanterior = raizatual;
                        erro = (funcao(raizatual)).doubleValue()/(derivada(raizatual)).doubleValue();
                        raizatual -= erro;
                        
                        x.add(new RaizIteracao(raizatual+"",funcao(raizatual)+"",erro+"",++iteracoes+""));
                        
                        latex += "Iteração " + iteracoes + ", x_" + iteracoes + " = " + raizatual + ", f(x_" + iteracoes + ") = " + funcao(raizatual) + ", erro = " + erro + "\n\n";
   
			erro = Math.abs(erro);
		}while(iteracoes<itmax && erro>erromax);
                
                latex += "Portanto, a raiz aproximada é $" + raizatual + "$ com erro absoluto de $" + erro + "$";
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
        
        @Override
        public String toLatex(){
            this.latex = "";
            this.x = new ArrayList<>();
            this.resolve();
            return latex;
        }
        
}
