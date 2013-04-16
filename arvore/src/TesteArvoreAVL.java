import static org.junit.Assert.*;

import org.junit.Test;


public class TesteArvoreAVL {

	@Test
	public void testInserir() {
		
		IArvore a = new ArvoreAVL();
		StringBuilder caminho = new StringBuilder();
		INo raiz;
		
		a.inserir(50);
		raiz = ((ArvoreAVL) a).getRaiz();
		a.preOrdem(raiz, caminho);
		assertEquals("50\t", caminho.toString());
		
		a.inserir(30);
		raiz = ((ArvoreAVL) a).getRaiz();
		caminho = new StringBuilder();
		a.preOrdem(raiz, caminho);
		assertEquals("50\t30\t", caminho.toString());
		
		a.inserir(70);
		raiz = ((ArvoreAVL) a).getRaiz();
		caminho = new StringBuilder();
		a.preOrdem(raiz, caminho);
		assertEquals("50\t30\t70\t", caminho.toString());

		a.inserir(60);
		raiz = ((ArvoreAVL) a).getRaiz();
		caminho = new StringBuilder();
		a.preOrdem(raiz, caminho);
		assertEquals("50\t30\t70\t60\t", caminho.toString());
		
		a.inserir(90);
		raiz = ((ArvoreAVL) a).getRaiz();
		caminho = new StringBuilder();
		a.preOrdem(raiz, caminho);
		assertEquals("50\t30\t70\t60\t90\t", caminho.toString());

		a.inserir(55);
		raiz = ((ArvoreAVL) a).getRaiz();
		caminho = new StringBuilder();
		a.preOrdem(raiz, caminho);
		assertEquals("60\t50\t30\t55\t70\t90\t", caminho.toString());
		
		a.inserir(20);
		raiz = ((ArvoreAVL) a).getRaiz();
		caminho = new StringBuilder();
		a.preOrdem(raiz, caminho);
		assertEquals("60\t50\t30\t20\t55\t70\t90\t", caminho.toString());

		a.inserir(10);
		raiz = ((ArvoreAVL) a).getRaiz();
		caminho = new StringBuilder();
		a.preOrdem(raiz, caminho);
		assertEquals("60\t50\t20\t10\t30\t55\t70\t90\t", caminho.toString());

		a.inserir(40);
		raiz = ((ArvoreAVL) a).getRaiz();
		caminho = new StringBuilder();
		a.preOrdem((INoAVL)raiz, caminho);
		assertEquals("60\t30\t20\t10\t50\t40\t55\t70\t90\t", caminho.toString());
	}
	
	@Test
	public void testRemover() {
		IArvore a = new ArvoreAVL();
		
		a.inserir(63);
		a.inserir(33);
		a.inserir(91);
		a.inserir(19);
		a.inserir(53);
		a.inserir(78);
		a.inserir(109);
		a.inserir(13);
		a.inserir(43);
		a.inserir(58);
		
		StringBuilder caminho = new StringBuilder();
		INo raiz = ((ArvoreAVL) a).getRaiz();
		((ArvoreAVL) a).preOrdem(raiz, caminho);
		System.out.println("\n\n\n=====Teste remoção: \n"+ caminho.toString());
		
		a.remover(33);
		raiz = ((ArvoreAVL) a).getRaiz();
		caminho = new StringBuilder();
		a.preOrdem(raiz, caminho);
		assertEquals("63\t43\t19\t13\t53\t58\t91\t78\t109\t", caminho.toString());
		
		a.remover(58);
		raiz = ((ArvoreAVL) a).getRaiz();
		caminho = new StringBuilder();
		a.preOrdem(raiz, caminho);
		assertEquals("63\t43\t19\t13\t53\t91\t78\t109\t", caminho.toString());
		
		a.remover(63);
		raiz = ((ArvoreAVL) a).getRaiz();
		caminho = new StringBuilder();
		a.preOrdem(raiz, caminho);
		assertEquals("78\t43\t19\t13\t53\t91\t109\t", caminho.toString());
		
		a.remover(109);
		raiz = ((ArvoreAVL) a).getRaiz();
		caminho = new StringBuilder();
		a.preOrdem(raiz, caminho);
		assertEquals("43\t19\t13\t78\t53\t91\t", caminho.toString());
		
		a.remover(19);
		raiz = ((ArvoreAVL) a).getRaiz();
		caminho = new StringBuilder();
		a.preOrdem(raiz, caminho);
		assertEquals("43\t13\t78\t53\t91\t", caminho.toString());
		
		a.remover(13);
		raiz = ((ArvoreAVL) a).getRaiz();
		caminho = new StringBuilder();
		a.preOrdem(raiz, caminho);
		assertEquals("78\t43\t53\t91\t", caminho.toString());
		
		a.remover(91);
		raiz = ((ArvoreAVL) a).getRaiz();
		caminho = new StringBuilder();
		a.preOrdem(raiz, caminho);
		assertEquals("53\t43\t78\t", caminho.toString());
		
	}

}
