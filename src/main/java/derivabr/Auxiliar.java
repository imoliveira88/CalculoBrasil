/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derivabr;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MPR
 */
public class Auxiliar {
    
    public static boolean ehInteiro(Double numero){
        int piso = (int) Math.floor(numero);
        return ((numero-piso) == 0);
    }
    
    public static String retornaIntDouble(Double numero){
        if(Auxiliar.ehInteiro(numero)) return "" + ((int) Math.floor(numero));
        else return "" + numero;
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
        List<String> particulas = Auxiliar.particulasString(expressao);
        
        for(int j = 0; j < particulas.size(); j++){
            if (j == 0) {
                if ("+-*".contains(particulas.get(j)) && particulas.get(j).length() == 1) {
                    arvore.setTipo('s');
                    arvore.setValor(particulas.get(j));
                } else {
                    coeficiente = Auxiliar.separaNumeroFuncao(particulas.get(j)).get(0);
                    funcao = Auxiliar.separaNumeroFuncao(particulas.get(j)).get(1);
                    arvembaixo = Auxiliar.separaNumeroFuncao(particulas.get(j)).get(2);
                    
                    if (funcao.equals("")) {

                        if (arvembaixo.equals("")) {
                            arvore.setTipo('n');
                            arvore.setValor(coeficiente);
                        } else {
                            arvore.setTipo('n');
                            arvore.setValor(coeficiente);
                            arvore.setEmbaixo(Auxiliar.stringToArvore(arvembaixo));
                        }
                    } else if (!funcao.contains("x")) {
                        if (!coeficiente.equals("")) {
                            arvore.setTipo('n');
                            arvore.setValor(coeficiente);
                            arvore.setEmbaixo(new Arvore('f',funcao));
                            arvore.getEmbaixo().setEmbaixo(Auxiliar.stringToArvore(arvembaixo));
                        } else {
                            arvore.setTipo('f');
                            arvore.setValor(funcao);
                            if (arvembaixo.equals("x")) {
                                arvore.setEmbaixo(new Arvore('v', "x"));
                            } else {
                                arvore.setEmbaixo(Auxiliar.stringToArvore(arvembaixo));
                            }
                        }
                    } else if (particulas.get(j).length() == 1) {
                        arvore.setTipo('v');
                        arvore.setValor("x");
                    } else {
                        arvore.setTipo('f');
                        arvore.setValor("pol");
                        if (funcao.equals("x")) {
                            arvore.setEmbaixo(new Arvore('n', "1"));
                            arvore.getEmbaixo().setEmbaixo(new Arvore('v', "x"));
                        } else {
                            arvore.setEmbaixo(new Arvore('n', Auxiliar.retiraNumero(funcao)));
                            arvore.getEmbaixo().setEmbaixo(new Arvore('v', "x"));
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
                arvore.colocaArvoreDireita(j - 1, Auxiliar.stringToArvore(particulas.get(j)));
            }
        }
                
        arvore.defineIrmaosEsquerda();
        
        return arvore;
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
    
}
