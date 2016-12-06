package derivabr;

public class Equacao2Grau {
	public int a, b, c;
	public String r1, r2;
	
	public Equacao2Grau(int a, int b, int c){
		this.a = a;
		this.b = b;
		this.c = c;
		this.r1 = "";
		this.r2 = "";
	}
	
	public Equacao2Grau(){
		this(0,0,0);
	}
	
	public String exibeEquacao(){
		return this.a + "x^2 + " + this.b + "x + " + this.c + " = 0";
	}
	
	public void resolve(){
		Radical rad = new Radical(1,1);
		Fracao f1,f2;
		rad.dentro = b*b - 4*a*c;
		rad.simplificaRadical();
		f1 = new Fracao(-b,2*a);
		f2 = new Fracao(rad.fora,2*a);
		f2.simplificaFracao();
		
		if(rad.dentro == 1){
			f1.num = -b + rad.fora;
			f1.simplificaFracao();
			this.r1 += "x1 = " + f1.exibeFracao();
			f1.num = -b - rad.fora;
			f1.den = 2*a;
			f1.simplificaFracao();
			this.r2 += "x2 = " + f1.exibeFracao();
		}
		else{
			if(rad.dentro == 0){
				f1.simplificaFracao();
				this.r1 += "x1 = " + f1.exibeFracao();
				this.r2 = this.r1;
			}
			else{
				if(rad.dentro > 0){
					f1.simplificaFracao();
					this.r1 += "x1 = " + f1.exibeFracao() + "+" + f2.exibeFracao() + "Raiz(" + rad.dentro + ")";
					this.r2 += "x2 = " + f1.exibeFracao() + "-" + f2.exibeFracao() + "Raiz(" + rad.dentro + ")";
				}
				else{
					rad.dentro *= -1;
					rad.simplificaRadical();
					f2.num = rad.fora;
					f2.den = 2*a;
					f2.simplificaFracao();		
					f1.simplificaFracao();
					if(rad.dentro != 1){
						this.r1 += "x1 = " + f1.exibeFracao() + "+" + f2.exibeFracao() + "i*Raiz(" + rad.dentro + ")";
						this.r2 += "x2 = " + f1.exibeFracao() + "-" + f2.exibeFracao() + "i*Raiz(" + rad.dentro + ")";
					}
					else{
						this.r1 += "x1 = " + f1.exibeFracao() + "+" + f2.exibeFracao() + "i";
						this.r2 += "x2 = " + f1.exibeFracao() + "-" + f2.exibeFracao() + "i";
					}
				}
			}
		}
		
	}

}
