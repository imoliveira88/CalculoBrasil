package derivabr;

public class Fracao {
	public int num, den;
	
	public Fracao(int n, int d){
		if(d == 0){
			System.out.println("Erro! O denominador não pode ser zero!");
			return;
		}
		this.num = n;
		this.den = d;
	}
	
	private int mdc(int a, int b){
		int r;
	    int aux=a;
	    int fim=0;
	    if(a==0) return b;
	    if(b==0) return a;
	    if(a<b){
	            a=b;
	            b=aux;
	            }
	    while(fim==0){
	                  r=a%b;
	                  if(r==0) return b;
	                  a=b;
	                  b=r;
	                  }
	    return 1;
	}
	
	public void simplificaFracao(){
	     int m = mdc(this.num,this.den);
	     this.num = this.num/m;
	     this.den = this.den/m;
	     if(this.den<0){
	    	 this.num = -this.num;
	    	 this.den = -this.den;
	     }   
	}
	
	public String exibeFracao(){
		if(this.den == 1) return ""+ this.num;
		else return this.num + "/" + this.den;
	}
	
	public static Fracao somaFracao(Fracao f1, Fracao f2){
	     int n1 = f1.num;
	     int n2 = f2.num;
	     int d1 = f1.den;
	     int d2 = f2.den;
	     Fracao novo = new Fracao(1,1);
	     novo.num = n1*d2+n2*d1;
	     novo.den = d1*d2;
	     novo.simplificaFracao();
	     return novo;
	     }
	     
	public static Fracao subtracaoFracao(Fracao f1, Fracao f2){
	     int n1=f1.num;
	     int n2=f2.num;
	     int d1=f1.den;
	     int d2=f2.den;
	     Fracao novo = new Fracao(1,1);
	     novo.num = n1*d2-n2*d1;
	     novo.den = d1*d2;
	     novo.simplificaFracao();
	     return novo;
	     }
	     
	public static Fracao divideFracao(Fracao f1, Fracao f2){
	     int n1=f1.num;
	     int n2=f2.num;
	     int d1=f1.den;
	     int d2=f2.den;
	     Fracao novo = new Fracao(1,1);
	     novo.num = n1*d2;
	     novo.den = d1*n2;
	     novo.simplificaFracao();
	     return novo;
	     }
	     
	public static Fracao multiplicaFracao(Fracao f1, Fracao f2){
	     int n1=f1.num;
	     int n2=f2.num;
	     int d1=f1.den;
	     int d2=f2.den;
	     Fracao novo = new Fracao(1,1);
	     novo.num = n1*n2;
	     novo.den = d1*d2;
	     novo.simplificaFracao();
	     return novo;
	     }
}
