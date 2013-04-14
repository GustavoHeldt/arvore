import static org.junit.Assert.*;

import org.junit.Test;


public class TesteArvoreBP {

	@Test
	public void testInserir() {
		
		IArvore a = new ArvoreBP();
		StringBuilder caminho = new StringBuilder();
		INo raiz;
		
		a.inserir(63);
		raiz = ((ArvoreBP) a).getRaiz();
		a.preOrdem(raiz, caminho);
		assertEquals("63\t", caminho.toString());
		
		a.inserir(33);
		caminho = new StringBuilder();
		a.preOrdem(raiz, caminho);
		assertEquals("63\t33\t", caminho.toString());
		
		a.inserir(19);
		caminho = new StringBuilder();
		a.preOrdem(raiz, caminho);
		assertEquals("63\t33\t19\t", caminho.toString());
		
		a.inserir(53);
		caminho = new StringBuilder();
		a.preOrdem(raiz, caminho);
		assertEquals("63\t33\t19\t53\t", caminho.toString());
		
		a.inserir(58);
		caminho = new StringBuilder();
		a.preOrdem(raiz, caminho);
		assertEquals("63\t33\t19\t53\t58\t", caminho.toString());
		
		a.inserir(91);
		caminho = new StringBuilder();
		a.preOrdem(raiz, caminho);
		assertEquals("63\t33\t19\t53\t58\t91\t", caminho.toString());
		
		a.inserir(109);
		caminho = new StringBuilder();
		a.preOrdem(raiz, caminho);
		assertEquals("63\t33\t19\t53\t58\t91\t109\t", caminho.toString());
		
		a.inserir(13);
		caminho = new StringBuilder();
		a.preOrdem(raiz, caminho);
		assertEquals("63\t33\t19\t13\t53\t58\t91\t109\t", caminho.toString());
		
		a.inserir(43);
		caminho = new StringBuilder();
		a.preOrdem(raiz, caminho);
		assertEquals("63\t33\t19\t13\t53\t43\t58\t91\t109\t", caminho.toString());
		
		a.inserir(78);
		caminho = new StringBuilder();
		a.preOrdem(raiz, caminho);
		assertEquals("63\t33\t19\t13\t53\t43\t58\t91\t78\t109\t", caminho.toString());
		
	}
	
	@Test
	public void testeAltura() {
		
		IArvore a = new ArvoreBP();
		
		int chave = 63;
		a.inserir(chave);
		assertEquals(0, a.altura(chave));
		
		chave = (33);
		a.inserir(chave);
		assertEquals(0, a.altura(chave));
		assertEquals(1, a.altura(63));
		
		chave = (19);
		a.inserir(chave);
		assertEquals(0, a.altura(chave));
		assertEquals(2, a.altura(63));
		assertEquals(1, a.altura(33));
		
		chave = (53);
		a.inserir(chave);
		assertEquals(0, a.altura(chave));
		assertEquals(2, a.altura(63));
		assertEquals(1, a.altura(33));
		
		chave = (58);
		a.inserir(chave);
		assertEquals(0, a.altura(chave));
		assertEquals(3, a.altura(63));
		assertEquals(2, a.altura(33));
		assertEquals(1, a.altura(53));
		
		chave = (91);
		a.inserir(chave);
		assertEquals(0, a.altura(chave));
		assertEquals(3, a.altura(63));
		assertEquals(2, a.altura(33));
		assertEquals(1, a.altura(53));
		assertEquals(0, a.altura(58));
		assertEquals(0, a.altura(19));
		
		chave = (109);
		a.inserir(chave);
		assertEquals(0, a.altura(chave));
		assertEquals(3, a.altura(63));
		assertEquals(2, a.altura(33));
		assertEquals(1, a.altura(53));
		assertEquals(0, a.altura(58));
		assertEquals(0, a.altura(19));
		assertEquals(1, a.altura(91));
		
		chave = (13);
		a.inserir(chave);
		assertEquals(0, a.altura(chave));
		assertEquals(3, a.altura(63));
		assertEquals(2, a.altura(33));
		assertEquals(1, a.altura(53));
		assertEquals(0, a.altura(58));
		assertEquals(1, a.altura(19));
		assertEquals(1, a.altura(91));
		assertEquals(0, a.altura(109));
		
		
		chave = (43);
		a.inserir(chave);
		assertEquals(0, a.altura(chave));
		assertEquals(0, a.altura(chave));
		assertEquals(3, a.altura(63));
		assertEquals(2, a.altura(33));
		assertEquals(1, a.altura(53));
		assertEquals(0, a.altura(58));
		assertEquals(1, a.altura(19));
		assertEquals(1, a.altura(91));
		assertEquals(0, a.altura(109));
		assertEquals(0, a.altura(13));
		
		chave = (78);
		a.inserir(chave);
		assertEquals(0, a.altura(chave));
		assertEquals(0, a.altura(chave));
		assertEquals(0, a.altura(chave));
		assertEquals(3, a.altura(63));
		assertEquals(2, a.altura(33));
		assertEquals(1, a.altura(53));
		assertEquals(0, a.altura(58));
		assertEquals(1, a.altura(19));
		assertEquals(1, a.altura(91));
		assertEquals(0, a.altura(109));
		assertEquals(0, a.altura(13));
		assertEquals(0, a.altura(43));
	}
	
	@Test
	public void testeGenerico() {
		IArvore a = new ArvoreBP();
		
		assertTrue(a.isEmpty());
		
		a.inserir(63);
		assertFalse(a.isEmpty());
		assertTrue(a.buscar(63).isExterno());
		
		INo raiz = ((ArvoreBP) a).getRaiz();
		assertEquals(a.buscar(63), raiz);
		
		a.inserir(91);
		raiz = ((ArvoreBP) a).getRaiz();
		assertFalse(a.isEmpty());
		assertEquals(a.buscar(63), raiz);
		assertFalse(a.buscar(63).isExterno());
		assertTrue(a.buscar(91).isExterno());
		
		a.inserir(78);
		raiz = ((ArvoreBP) a).getRaiz();
		assertFalse(a.isEmpty());
		assertEquals(a.buscar(63), raiz);
		assertFalse(a.buscar(63).isExterno());
		assertFalse(a.buscar(91).isExterno());
		assertTrue(a.buscar(78).isExterno());
		
		a.remover(63);
		raiz = ((ArvoreBP) a).getRaiz();
		assertFalse(a.isEmpty());
		assertEquals(a.buscar(78), raiz);
	}
	
	
	@Test
	public void testeRemocao() {
		IArvore a = new ArvoreBP();
		StringBuilder caminho = new StringBuilder();
		INo raiz;
		
		a.inserir(63);
		a.inserir(33);
		a.inserir(19);
		a.inserir(53);
		a.inserir(58);
		a.inserir(91);
		a.inserir(109);
		a.inserir(13);
		a.inserir(43);
		a.inserir(78);
		
		
		a.remover(33);
		raiz = ((ArvoreBP) a).getRaiz();
		a.preOrdem(raiz, caminho);
		assertEquals("63\t43\t19\t13\t53\t58\t91\t78\t109\t", caminho.toString());
		
		a.remover(58);
		caminho = new StringBuilder();
		raiz = ((ArvoreBP) a).getRaiz();
		a.preOrdem(raiz, caminho);
		assertEquals("63\t43\t19\t13\t53\t91\t78\t109\t", caminho.toString());
		
		a.remover(63);
		caminho = new StringBuilder();
		raiz = ((ArvoreBP) a).getRaiz();
		a.preOrdem(raiz, caminho);
		assertEquals("78\t43\t19\t13\t53\t91\t109\t", caminho.toString());
		
		a.remover(109);
		caminho = new StringBuilder();
		raiz = ((ArvoreBP) a).getRaiz();
		a.preOrdem(raiz, caminho);
		assertEquals("78\t43\t19\t13\t53\t91\t", caminho.toString());
		
		a.remover(19);
		caminho = new StringBuilder();
		raiz = ((ArvoreBP) a).getRaiz();
		a.preOrdem(raiz, caminho);
		assertEquals("78\t43\t13\t53\t91\t", caminho.toString());
		
		a.remover(13);
		caminho = new StringBuilder();
		raiz = ((ArvoreBP) a).getRaiz();
		a.preOrdem(raiz, caminho);
		assertEquals("78\t43\t53\t91\t", caminho.toString());
		
		a.remover(91);
		caminho = new StringBuilder();
		raiz = ((ArvoreBP) a).getRaiz();
		a.preOrdem(raiz, caminho);
		assertEquals("78\t43\t53\t", caminho.toString());
	}
	
	

}
