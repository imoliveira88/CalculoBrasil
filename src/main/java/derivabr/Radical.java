package derivabr;

public class Radical {
	public int dentro;
	public int fora;
	
	public Radical(int d, int f){
		if(d < 0){
			System.out.println("O radicando deve ser maior que zero!");
			return;
		}
		this.dentro = d;
		this.fora = f;
	}
	
	public int maiorQuadradoDivisor(int num){ //Retorna o maior quadrado divisor de num
		int maior = 1;
		for(int i=1; i <= Math.sqrt(num); i++){
			if(num%(i*i) == 0){
				maior = i*i;
			}
		}
		return maior;
	}
	
	public void simplificaRadical(){
		int aux = maiorQuadradoDivisor(this.dentro);
		this.dentro /= aux;
		this.fora *= Math.sqrt(aux);
	}
	
}
