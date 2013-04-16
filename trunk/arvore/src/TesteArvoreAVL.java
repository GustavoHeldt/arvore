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

}
