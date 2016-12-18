package derivabr;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class Arvore {
    
    private char tipo;
    /*'f' função, 'n' número, 's' sinal, 'v' variável
    Se for n, pode haver um sinal de * embaixo, talvez haja alguém do lado
    se for s, não haverá alguém embaixo, mas haverá alguém do lado
    se for f, haverá alguém embaixo, talvez alguém do lado
    se for v, não haverá alguém embaixo, mas pode haver do lado 
    
    */
    private String valor;
    private Arvore direita;
    private Arvore embaixo;
    private Arvore esquerda;
    
    public final String sinais = "-+";
    public final String algarismos = "0123456789.";
    
    public Arvore(){
    };
    
    public Arvore(char t, String v){
        this.tipo = t;
        this.valor = v;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Arvore getDireita() {
        return direita;
    }

    public void setDireita(Arvore direita) {
        this.direita = direita;
    }

    public Arvore getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Arvore esquerda) {
        this.esquerda = esquerda;
    }
    
    public Arvore getEmbaixo() {
        return embaixo;
    }

    public void setEmbaixo(Arvore embaixo) {
        this.embaixo = embaixo;
    }
    
    public static boolean ehInteiro(Double numero){
        int piso = (int) Math.floor(numero);
        return ((numero-piso) == 0);
    }
    
    public static String retornaIntDouble(Double numero){
        if(Arvore.ehInteiro(numero)) return "" + ((int) Math.floor(numero));
        else return "" + numero;
    }
            
    public String imprimeArvore(){
        String arvore = null;
        boolean polinomio = false;
        
        if(this == null) return "";
        
        if(this.valor.equals("pol")){
            polinomio = true;
            if(!this.embaixo.valor.equals("1")){
                if(this.embaixo.valor.equals("0")) arvore = "";
                else{
                     if(this.embaixo.valor.length() == 1){
                         if(this.embaixo.embaixo.valor.equals("x")) arvore = "x^" + Arvore.retornaIntDouble(Double.parseDouble(this.embaixo.valor));
                         else arvore = "(" + this.embaixo.getEmbaixo().imprimeArvore() + ")^" + Arvore.retornaIntDouble(Double.parseDouble(this.embaixo.valor));
                     }else{
                        if(this.embaixo.embaixo.valor.equals("x")) arvore = "x^(" + Arvore.retornaIntDouble(Double.parseDouble(this.embaixo.valor)) + ")";
                        else arvore = "(" + this.embaixo.getEmbaixo().imprimeArvore() + ")^(" + Arvore.retornaIntDouble(Double.parseDouble(this.embaixo.valor)) + ")";
                     }
                }
            }
            else{
                if(!this.embaixo.embaixo.valor.equals("x")) arvore = "*(" + this.embaixo.getEmbaixo().imprimeArvore() + ")";
                else arvore = this.embaixo.getEmbaixo().imprimeArvore();
            }
        }
        
        if(this.tipo == 'v') return "x";
        
        if(this.tipo == 's') arvore = valor;
        
        if(this.embaixo != null && !polinomio && this.tipo!='n'){
            arvore = valor;
            arvore += "(" + this.embaixo.imprimeArvore() + ")";
        }
        else if(this.tipo=='n'){
            if(!this.valor.equals("1")) arvore  = valor;
            else arvore = "";
            if(this.embaixo != null){
                if(this.embaixo.contaIrmaosDireita() > 0) arvore += "(" + this.embaixo.imprimeArvore() + ")";
                else arvore += this.embaixo.imprimeArvore();
            }
        }
        
        if(this.direita != null){
            if(multiplicaIrmaos()) arvore += "*(" + this.direita.imprimeArvore() + ")";
            else arvore += this.direita.imprimeArvore();
        }
        
        return arvore;
    }
    
    public boolean multiplicaIrmaos(){
         if(this.tipo == 'f' && this.direita.tipo == 'f') return true;
         if(this.tipo == 'f' && this.direita.tipo == 'n' && this.direita.embaixo != null) return true;
         return this.direita.tipo == 'f' && this.tipo == 'n' && this.embaixo != null;
    }
    
    //Retorna as partículas (elementos de combinação linear) de expressao
    public static List<String> particulasString(String expressao){
        List<String> lista = new ArrayList<>();
        String atual = "";
        int somaPar = 0;
                
        if(expressao.length() == 1){
            lista.add(expressao);
            return lista;
        }
                
        for(int i=0; i<expressao.length(); i++){
            if(expressao.charAt(i) == '(') somaPar++;
            if(expressao.charAt(i) == ')') somaPar--;
            
            if("+-*".contains(expressao.charAt(i)+"") && somaPar == 0){
                lista.add(atual);
                lista.add(expressao.charAt(i)+"");
                atual = "";
            }
            else atual += expressao.charAt(i);
        }
        
        lista.add(atual);
                       
        return lista;
    }
    
    //Separa coeficiente da função
    public static List<String> separaNumeroFuncao(String expressao){
        String numero = "";
        String funcao = "";
        String arvembaixo = "";
        List<String> processado = new ArrayList<>();
        
        
        for(int i=0; i<expressao.length(); i++){
            if("1234567890.".contains(expressao.charAt(i)+"")) numero += expressao.charAt(i);
            else{
                if(i == expressao.length() - 2) funcao = "";
                else funcao = expressao.substring(i,expressao.length());
                break;
            }
        }
                
        if(!funcao.equals("")){
            for(int i=0; i<funcao.length(); i++){
                if(funcao.charAt(i) == '('){
                    arvembaixo = funcao.substring(i+1,funcao.length()-1);
                    funcao = funcao.substring(0,i);
                }
            }
        }
                
        processado.add(numero);
        processado.add(funcao);
        processado.add(arvembaixo);
        
        return processado;
    }
    
    //Coloca a árvore arv como i + 1-ésima árvore da direita
    public void colocaArvoreDireita(int i, Arvore arv){
        if(this.direita == null) this.direita = new Arvore();
        if(i == 0) this.setDireita(arv);
        else this.getDireita().colocaArvoreDireita(i-1, arv);
    }
    
    public static String retiraNumero(String expressao){
        String parcial = "";
        
        for(int i=0; i<expressao.length(); i++){
            if("1234567890.".contains(expressao.charAt(i)+"")) parcial += expressao.charAt(i);
            else if(!parcial.equals("")) break;
        }
        
        return parcial;
    }
    
    public static Arvore stringToArvore(String expressao){
        Arvore arvore = new Arvore();
        String coeficiente, funcao, arvembaixo;
        List<String> particulas = Arvore.particulasString(expressao);
        
        for(int j = 0; j < particulas.size(); j++){
            if (j == 0) {
                if ("+-*".contains(particulas.get(j)) && particulas.get(j).length() == 1) {
                    arvore.tipo = 's';
                    arvore.valor = particulas.get(j);
                } else {
                    coeficiente = Arvore.separaNumeroFuncao(particulas.get(j)).get(0);
                    funcao = Arvore.separaNumeroFuncao(particulas.get(j)).get(1);
                    arvembaixo = Arvore.separaNumeroFuncao(particulas.get(j)).get(2);
                    
                    if (funcao.equals("")) {

                        if (arvembaixo.equals("")) {
                            arvore.tipo = 'n';
                            arvore.valor = coeficiente;
                        } else {
                            arvore.tipo = 'n';
                            arvore.valor = coeficiente;
                            arvore.setEmbaixo(Arvore.stringToArvore(arvembaixo));
                        }
                    } else if (!funcao.contains("x")) {
                        if (!coeficiente.equals("")) {
                            arvore.tipo = 'n';
                            arvore.valor = coeficiente;
                            arvore.embaixo = new Arvore();
                            arvore.embaixo.tipo = 'f';
                            arvore.embaixo.valor = funcao;
                            arvore.embaixo.setEmbaixo(Arvore.stringToArvore(arvembaixo));
                        } else {
                            arvore.tipo = 'f';
                            arvore.valor = funcao;
                            if (arvembaixo.equals("x")) {
                                arvore.setEmbaixo(new Arvore('v', "x"));
                            } else {
                                arvore.embaixo = new Arvore();
                                arvore.setEmbaixo(Arvore.stringToArvore(arvembaixo));
                            }
                        }
                    } else if (particulas.get(j).length() == 1) {
                        arvore.tipo = 'v';
                        arvore.valor = "x";
                    } else {
                        arvore.tipo = 'f';
                        arvore.valor = "pol";
                        if (funcao.equals("x")) {
                            arvore.embaixo = new Arvore('n', "1");
                            arvore.embaixo.embaixo = new Arvore('v', "x");
                        } else {
                            arvore.embaixo = new Arvore('n', Arvore.retiraNumero(funcao));
                            arvore.embaixo.embaixo = new Arvore('v', "x");
                        }
                        if (!coeficiente.equals("")) {
                            Arvore auxiliar = arvore;
                            arvore = new Arvore();
                            arvore.setEmbaixo(auxiliar);
                            arvore.setTipo('n');
                            arvore.setValor(coeficiente);
                        }
                    }
                }
            } else {
                arvore.colocaArvoreDireita(j - 1, Arvore.stringToArvore(particulas.get(j)));
            }
        }
                
        arvore.defineIrmaosEsquerda();
        
        return arvore;
    }
    
    public void defineIrmaosEsquerda(){
        if(this.direita != null){
            this.direita.esquerda = this;
            this.direita.defineIrmaosEsquerda();
            if(this.embaixo != null) this.embaixo.defineIrmaosEsquerda();
        }
        
        if(this.embaixo != null) this.embaixo.defineIrmaosEsquerda();
    }
    
    public static BigDecimal funcaoElementar(String funcao, Double x){
        Double aux;
        switch(funcao){
            case "sen":
                aux = Math.sin(x);
                break;
            case "cos":
                aux =  Math.cos(x);
                break;
            case "sec":
                aux = 1/Math.cos(x);
                break;
            case "tan":
                aux = Math.tan(x);
                break;
            case "cot":
                aux = 1/Math.tan(x);
                break;
            case "csc":
                aux = 1/Math.sin(x);
                break;
            case "e^":
                aux = Math.exp(x);
                break;
            default://ln
                aux = Math.log(x);
        }
        
        return BigDecimal.valueOf(aux);
    }
    
    public static BigDecimal jogoSinais(String sinal, BigDecimal valor){
        if(sinal.equals("-")) return valor.multiply(BigDecimal.valueOf(Double.parseDouble("-1")));
        else return valor;
    }   
    
    public BigDecimal avaliaArvore(Double x){
        System.out.println("Caso atual: " + this.tipo + " Valor atual: " + this.valor);
        
        if(this == null) return BigDecimal.valueOf(0);
        
        //necessidade alteração da gramática
        switch(this.tipo){
            case 'f':
                if(this.valor.equals("pol")) return BigDecimal.valueOf(Math.pow(Double.parseDouble(this.embaixo.valor),this.embaixo.getEmbaixo().avaliaArvore(x).doubleValue()));
                if(this.direita != null){
                    if(this.direita.valor.equals("*")) return Arvore.funcaoElementar(this.valor,this.embaixo.avaliaArvore(x).doubleValue()).multiply(this.direita.direita.avaliaArvore(x));
                    if(this.direita.tipo == 'n') return Arvore.funcaoElementar(this.valor,this.embaixo.avaliaArvore(x).doubleValue()).multiply(this.direita.avaliaArvore(x));
                    else{
                        if(this.direita.tipo == 'f') return Arvore.funcaoElementar(this.valor,this.embaixo.avaliaArvore(x).doubleValue()).multiply(this.direita.avaliaArvore(x));
                        else return Arvore.funcaoElementar(this.valor,this.embaixo.avaliaArvore(x).doubleValue()).add(Arvore.jogoSinais(this.direita.valor,this.direita.direita.avaliaArvore(x)));
                    }
                }
                else return Arvore.funcaoElementar(this.valor,this.embaixo.avaliaArvore(x).doubleValue());                
            case 'n':
                if(this.direita == null){
                    if(this.embaixo == null) return BigDecimal.valueOf(Double.parseDouble(this.valor));
                    else return BigDecimal.valueOf(Double.parseDouble(this.valor)).multiply(this.embaixo.avaliaArvore(x));
                }
                else{
                    if(this.embaixo == null) return BigDecimal.valueOf(Double.parseDouble(this.valor)).add(Arvore.jogoSinais(this.direita.valor,this.direita.direita.avaliaArvore(x)));
                    return BigDecimal.valueOf(Double.parseDouble(this.valor)).multiply(this.embaixo.avaliaArvore(x)).add(Arvore.jogoSinais(this.direita.valor,this.direita.direita.avaliaArvore(x)));
                }
            default: //case 'v' -> variável
                if(this.direita == null) return BigDecimal.valueOf(x);
                return BigDecimal.valueOf(x).add(Arvore.jogoSinais(this.direita.valor,this.direita.direita.avaliaArvore(x)));
        }
    }
    
    //P�e arv na pr�xima direita vaga de this
    public Arvore colocaProxDireitaVazia(Arvore arv){
        Arvore arvore = this;
        if(this.direita == null) arvore.setDireita(arv);
        else return this.direita.colocaProxDireitaVazia(arv);
        
        return arvore;        
    }
    
    public Arvore derivadaCorreta(){
        Arvore arvore = new Arvore();
        Arvore aux;
                        
        if(this.tipo == 'n'){
            if(this.embaixo == null && "+-".contains(this.direita.valor)){
                arvore = this.direita.derivadaCorreta();
            }else{
                arvore.setValor(this.valor);
                arvore.setTipo(this.tipo);
                if(this.direita != null) arvore.setDireita(this.direita.derivadaCorreta());
                if(this.embaixo != null) arvore.setEmbaixo(this.embaixo.derivadaCorreta());
            }
            return arvore;
        }else{
            if(this.tipo == 's'){
                arvore.setValor(this.valor);
                arvore.setTipo(this.tipo);
                arvore.setDireita(this.direita.derivadaCorreta());
                return arvore;
            }
        }
        
        arvore = this.arvoreDerivada();
        
        if(this.valor.equals("pol")){
            if(this.embaixo.embaixo.tipo != 'v'){
                arvore.setDireita(this.embaixo.embaixo.derivadaCorreta());
                if(this.direita != null) arvore.getDireita().setDireita(this.direita.derivadaCorreta());
            }else{
                if(this.direita != null) arvore.setDireita(this.direita.derivadaCorreta());
            }
        }else{//outros casos de f
            if(this.embaixo != null){
                if(this.embaixo.tipo != 'v'){
                    if(this.embaixo.contaIrmaosDireita() > 0){
                        aux = new Arvore('n',"1");
                        aux.setEmbaixo(this.embaixo.derivadaCorreta());
                        arvore.colocaProxDireitaVazia(aux);
                    }
                    else{
                        arvore.colocaProxDireitaVazia(this.embaixo.derivadaCorreta());
                    }
                    //arvore.colocaProxDireitaVazia(this.embaixo.derivadaCorreta());
                    if(this.direita != null) arvore.colocaProxDireitaVazia(this.direita.derivadaCorreta());                    
                }
                else if(this.direita != null) arvore.colocaProxDireitaVazia(this.direita.derivadaCorreta());
            }
            else return new Arvore('v',"x");
        }
                
        return arvore.operaArvore();
    }
    
    public int contaIrmaosDireita(){
        if(this.direita != null) return (1 + this.direita.contaIrmaosDireita());
        else return 0;
    }
    
    public Arvore operaArvore(){
        Arvore arvore = new Arvore();
        
        //Este trecho multiplica dois coeficientes contíguos
        if(this.tipo == 'n'){
            if(this.embaixo != null){
                if(this.embaixo.tipo == 'n' && this.embaixo.contaIrmaosDireita() == 0){
                    arvore.tipo = 'n';
                    arvore.valor = Arvore.retornaIntDouble(Double.parseDouble(this.valor)*Double.parseDouble(this.embaixo.valor));
                    if(this.embaixo.embaixo != null) arvore.setEmbaixo(this.embaixo.embaixo.operaArvore());
                }else{
                    arvore.tipo = 'n';
                    arvore.valor = Arvore.retornaIntDouble(Double.parseDouble(this.valor));
                    arvore.embaixo = this.embaixo.operaArvore();
                }
            }else{
                arvore.tipo = 'n';
                arvore.valor = Arvore.retornaIntDouble(Double.parseDouble(this.valor));
                if(this.embaixo != null )arvore.embaixo = this.embaixo.operaArvore();
            }
            if(this.direita != null) arvore.setDireita(this.direita.operaArvore());
            return arvore;
        }
        
        //Jogo de sinais,para aparecer um sinal apenas
        if(this.tipo == 's'){
            if(this.direita != null){
                if(this.direita.tipo == 's'){
                    arvore.tipo = 's';
                    if(this.valor.equals(this.direita.valor)) arvore.valor = "+";
                    else arvore.valor = "-";
                    if(this.direita.direita != null) arvore.setDireita(this.direita.direita.operaArvore());
                }else{
                    arvore.tipo = 's';
                    arvore.valor = this.valor;
                    arvore.direita = this.direita.operaArvore();
                }
            }else{
                arvore.tipo = 's';
                arvore.valor = this.valor;
            }
            return arvore;
        }
        
        arvore.tipo = this.tipo;
        arvore.valor = this.valor;
        if(this.direita != null) arvore.setDireita(this.direita.operaArvore());
        if(this.embaixo != null) arvore.setEmbaixo(this.embaixo.operaArvore());
        
        System.out.println("�rvore " + this.imprimeArvore() + " Ap�s operada " + arvore.imprimeArvore());
        
        return arvore;
    }
    
    //retorna a �rvore-base correspondente � derivada da �rvore cuja cabe�a � "func�o" e cuja �rvore abaixo � "embaixo"
    public Arvore arvoreDerivada(){
        Arvore arvore;
        switch(this.valor){
                    case "sen":
                        arvore = new Arvore('f',"cos");
                        arvore.setEmbaixo(this.embaixo);
                       return arvore;
                    case "pol":
                        arvore = new Arvore('n',this.embaixo.valor);
                        if(this.embaixo.valor.equals("1")) return arvore; //caso em que o expoente de x é 1
                        arvore.setEmbaixo(new Arvore('f',"pol"));
                        arvore.getEmbaixo().setEmbaixo(new Arvore('n',""+(Double.parseDouble(this.embaixo.valor)-1)));
                        arvore.getEmbaixo().getEmbaixo().setEmbaixo(this.embaixo.embaixo);
                        return arvore;
                    case "cos":
                        arvore = new Arvore('s',"-");
                        arvore.setDireita(new Arvore('f',"sen"));
                        arvore.getDireita().setEmbaixo(this.embaixo);
                        return arvore;
                    case "tan":
                        arvore = new Arvore('f',"pol");
                        arvore.setEmbaixo(new Arvore('n',"2"));
                        arvore.getEmbaixo().setEmbaixo(new Arvore('f',"sec"));
                        arvore.getEmbaixo().getEmbaixo().setEmbaixo(this.embaixo);
                        return arvore;
                    case "cot":
                        arvore = new Arvore('s',"-");
                        arvore.setDireita(new Arvore('f',"pol"));
                        arvore.getDireita().setEmbaixo(new Arvore('n',"2"));
                        arvore.getDireita().getEmbaixo().setEmbaixo(new Arvore('f',"csc"));
                        arvore.getDireita().getEmbaixo().getEmbaixo().setEmbaixo(this.embaixo);
                        return arvore;
                    case "csc":
                        arvore = new Arvore('s',"-");
                        arvore.setDireita(new Arvore('f',"csc"));
                        arvore.getDireita().setDireita(new Arvore('f',"cot"));
                        arvore.getDireita().setEmbaixo(this.embaixo);
                        arvore.getDireita().getDireita().setEmbaixo(this.embaixo);
                        return arvore;
                    case "sec":
                        arvore = new Arvore('f',"sec");
                        arvore.setDireita(new Arvore('f',"tan"));
                        arvore.setEmbaixo(this.embaixo);
                        arvore.getDireita().setEmbaixo(this.embaixo);
                        return arvore;
                    case "e^":
                        arvore = new Arvore('f',"e^");
                        arvore.setEmbaixo(this.embaixo);
                        return arvore;
                    default: //case ln
                        arvore = new Arvore('f',"pol");
                        arvore.setEmbaixo(new Arvore('n',"-1"));
                        arvore.getEmbaixo().setEmbaixo(this.embaixo);
                        return arvore;
                }
    }
    
    public static void main(String[] args){
        //2*sen(tan(x))-e^(x)
        Arvore arvore1 = new Arvore('n',2+"");
        Arvore arvore3 = new Arvore('f',"sen");
        Arvore arvore4 = new Arvore('f',"sec");
        Arvore arvore5 = new Arvore('v',"x");
        Arvore arvore6 = new Arvore('f',"e^");
        Arvore arvore7 = new Arvore('s',"-");
        //sen(tan(x+cos(x))) --> cos(tan(x))*(sec(x))^(2)
        //x^4+5x^5 --> 4((4(x))^(3.0))4+5(5((5(x))^(4.0))5)
        String arvore = "x^4+5x^5";
        
        String arvoreString = "sen(tan(x^2+5x^4)+cos(2x))-cot(x)";
        String teste = "sen(x)-e^(3x)";
        
        arvore1.setDireita(arvore7);
        arvore1.setEmbaixo(arvore3);
        arvore3.setEmbaixo(arvore4);
        arvore4.setEmbaixo(arvore5);
        arvore7.setDireita(arvore6);
        arvore6.setEmbaixo(arvore5);
        
        System.out.println("Fun��o: " + arvoreString + " Derivada: " + Arvore.stringToArvore(arvoreString).imprimeArvore());
        //System.out.println("�rvore: " + Arvore.stringToArvore(teste).imprimeArvore() + " Derivada: " + Arvore.stringToArvore(teste).derivadaCorreta().imprimeArvore());
        //System.out.println("Fun��o(2) = " + Arvore.stringToArvore(teste).avaliaArvore(2.0));
        //System.out.println("Derivada(2) = " + Arvore.stringToArvore(teste).derivadaCorreta().avaliaArvore(2.0));
        
        //numero direita.valor=* direita-direita=?  
        
        //System.out.println("Números de irmãos árvore principal: " + arvore1.contaIrmaosDireita());
        
        //System.out.println("StringToArvore: " + Arvore.stringToArvore(arvoreString).derivadaCorreta().imprimeArvore());
        
        //System.out.println("�?rvore atual: " + arvore1.imprimeArvore());
        //System.out.println("Derivada da árvore atual 2: " + arvore1.derivadaCorreta().imprimeArvore());
        //System.out.println("Valor da função no ponto 1: " + arvore1.avaliaArvore(1.0));
        //System.out.println("Valor da função no ponto 1: " + arvore1.derivadaCorreta().avaliaArvore(1.0));
        
        /*
        double m = 0.2;
        double m0 = 1;
        double tmeia = 50;
        
        double t = tmeia*Math.log(m0/m)/Math.log(2);
        int minutos = (int) t/60;
        int segundos = (int) t - 60*minutos;
        
        System.out.println("O tempo aproximado para que haja decaimento de " + m0 + "g para " + m + "g é aproximadamente " + minutos + "min " + segundos + "s");*/
    }
    
}