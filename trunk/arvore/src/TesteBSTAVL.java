import java.io.FileInputStream;
import java.io.IOException;
import java.util.StringTokenizer;

/*
 * Created on 09/03/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author robinson
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class TesteBSTAVL {
    static String read(){
		StringBuffer buff=new StringBuffer();
		int car;
		while(true){
			try{
				car=System.in.read();
				if ((car<0) || (car=='\n')) break;
				buff.append((char)car);
			}catch(IOException ioe){
				return null;
			}
		}
		return buff.toString();
	}
    
	public static void main(String[] args){
	    
	    try{
			String arq="arv1.avl";
			System.out.println("Abrindo o arquivo "+arq);
			FileInputStream fis = new FileInputStream(arq);
			System.setIn(fis);
		}catch(IOException e){
			System.out.println("Erro ao abrir o arquivo");
		}
		System.out.println("Iniciando AVL");
		Comparador c=new Comparador();
		String ent;
		StringTokenizer tokenizer;		
		AVL Arv = new AVL(c);
		System.out.println("Realizando Operações");
		while ((ent=read())!=null){
			tokenizer = new StringTokenizer(ent);
			int n= Integer.parseInt(tokenizer.nextToken());
			if(n==0)
			    break;			
			if(n>0){
			    Arv.incluir(new Integer(n));			    
			}else{
			    Arv.remover(new Integer(Math.abs(n)));
			}			
		}
		if(!Arv.isEmpty()){
		    System.out.println("\n----------Listagem com Links---------");
		    Arv.preOrdemFB((noAVL)Arv.getRaiz(),1);
		    System.out.println("\n----------Listagem hierárquica-------");
		    Arv.mostra((noAVL)Arv.getRaiz(),1);
		    System.out.println("\n----------------------------------");
		}else
		    System.out.println("Árvore Vazia");
	}
}


