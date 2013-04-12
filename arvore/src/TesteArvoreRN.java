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

public class TesteArvoreRN {
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
			String arq="arv33.rn";
			System.out.println("Abrindo o arquivo "+arq);
			FileInputStream fis = new FileInputStream(arq);
			System.setIn(fis);
		}catch(IOException e){
			System.out.println("Erro ao abrir o arquivo");
		}
		System.out.println("Iniciando ¡rvore Rubro Negra");
		Comparador c=new Comparador();
		String ent;
		StringTokenizer tokenizer;		
		ArvoreRN Arv = new ArvoreRN(c);
		System.out.println("Realizando OperaÁıes");
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
			Arv.mostra((NÛRN)Arv.getRaiz(),1);
			System.out.println("\n----------------------------------");
		}
		if(!Arv.isEmpty()){
		    //System.out.println("\n----------Listagem com Links---------");
		    //Arv.preOrdemRN((ItemRN)Arv.getRaiz(),1);
		    System.out.println("\n----------Listagem hier·rquica-------");
		    Arv.mostra((NÛRN)Arv.getRaiz(),1);
		    System.out.println("\n----------------------------------");
		}else
		    System.out.println("¡rvore Vazia");
	}
}


