
public class ArvoreAVL extends ArvoreBP implements IArvoreAVL {

	public ArvoreAVL() {
		raiz = new NoAVL();
		INo no = new NoAVL();
		raiz.setFilhoEsquerdo(no);
		no.setPai(raiz);
	}

	
	public void inserir(Object chave) {

		INo no = this.buscar(chave);

		this.inserir(no, chave);

		INo nD = new NoAVL();
		no.setFilhoDireito(nD);
		nD.setPai(no);

		INo nE = new NoAVL();
		no.setFilhoEsquerdo(nE);
		nE.setPai(no);
		
		System.out.println("Inserindo "+ chave);
		//repeticao 3
		int dif = 0;
		if (no.getPai().getFilhoEsquerdo() == no) {
			dif = 1;
		} else {
			dif = -1;
		}
		
		atualizarFBInsercao((INoAVL) no, dif);
	}
	
	
	public INo remover(Object chave) {

		INo no = buscar(chave);
		
		int dif = 0;
		
		if (no.getPai().getFilhoEsquerdo() == no) {
			dif = -1;
		} else {
			dif = 1;
		}

		no = remover(no);
		System.out.println("\nRemovendo "+ chave +"\tpai "+ no.getPai().getChave());

		atualizarFBRemocao((INoAVL) no.getPai(), dif);
		
		//desconecta totalmente o n� da �rvore
		no.setPai(null);
		return no;
	}
	

	private void atualizarFBInsercao(INoAVL noAnterior, int dif) {
		if (noAnterior != this.getRaiz()) {
			INoAVL noPai = (INoAVL) noAnterior.getPai();
			noPai.setFB(noPai.getFB() + dif);

			StringBuilder caminho = new StringBuilder();
			preOrdem((INoAVL)getRaiz(), caminho);
			System.out.println(caminho.toString());
			
			if (noPai.getFB() == -2) {
		//		System.out.println("desbalanceado - direita");
				//codigo repetido 2
				if (noAnterior.getFB() <= 0) {
		//			System.out.println("rotacao simples a esquerda");
					rotacaoEsquerda(noPai);
				} else {
		//			System.out.println("rotacao dupla a esquerda");
					rotacaoDuplaEsquerda(noPai);
				}

			} else if (noPai.getFB() == 2) {
			//	System.out.println("desbalanceado - esquerda");
				//codigo repetido 2
				if (noAnterior.getFB() >= 0) {
			//		System.out.println("rotacao simples");
					rotacaoDireita(noPai);
					//noPai = (INoAVL) noAnterior.getPai();
				} else {
			//		System.out.println("rotacao dupla");
					rotacaoDuplaDireita(noPai);
				}
			}

			if (noPai.getFB() != 0) {
				
				//repeticao 3
				if (noPai.getPai().getFilhoEsquerdo() == noPai) {
					dif = 1;
				} else {
					dif = -1;
				}
				
				atualizarFBInsercao(noPai, dif);
			}
		}
	}
	
	
	private void atualizarFBRemocao(INoAVL no, int dif) {
		if (no != this.raiz) {
			INoAVL noFilho = null;
			no.setFB(no.getFB() + dif);
			
			StringBuilder caminho = new StringBuilder();
			preOrdem((INoAVL)getRaiz(), caminho);
			System.out.println(caminho.toString());
			
			//System.out.println("No("+ no.getChave() +")\tfE "+ no.getFilhoEsquerdo().getChave() +"\tfD "+ no.getFilhoDireito().getChave());
			
			if (no.getFB() == -2) {
				noFilho = (INoAVL) no.getFilhoDireito();
				//codigo repetido 2
				if (noFilho.getFB() <= 0) {
					rotacaoEsquerda(no);
					no = noFilho;
				} else {
					rotacaoDuplaEsquerda(no);
					no = (INoAVL) noFilho.getPai();
				}
				
			} else if (no.getFB() == 2) {
				noFilho = (INoAVL) no.getFilhoEsquerdo();
				//codigo repetido 2
				if (noFilho.getFB() >= 0) {
					rotacaoDireita(no);
					no = noFilho;
				} else {
					rotacaoDuplaDireita(no);
					no = (INoAVL) noFilho.getPai();
				}
			}
			
			
			if (no.getFB() == 0) {
				if (no.getPai().getFilhoEsquerdo() == no) {
					dif = -1;
				} else {
					dif = 1;
				}
				System.out.println("chamou de novo para atualizar FB(remocao). O no agora eh "+ no.getPai().getChave());
				atualizarFBRemocao((INoAVL) no.getPai(), dif);
			}
		}
	}

	@Override
	public void rotacaoDireita(INo no) {
		
		//System.out.println(no.getChave());
		
		INo noPai = no.getPai();
		INo noFilho = no.getFilhoEsquerdo();
		INo noNeto = noFilho.getFilhoDireito();

		
		//codigo repetido 1
		if (no == noPai.getFilhoDireito()) {
			noPai.setFilhoDireito(noFilho);
		} else {
			noPai.setFilhoEsquerdo(noFilho);
		}
		noFilho.setPai(noPai);
		
		no.setPai(noFilho);
		noFilho.setFilhoDireito(no);
		
		no.setFilhoEsquerdo(noNeto);
		noNeto.setPai(no);
		
	}
	
	private void rotacaoDireita(INoAVL no) {
		System.out.println("Rotacao Direita - chave: "+ no.getChave());
		INoAVL noPai = (INoAVL) no.getPai();
		INoAVL noFilho = (INoAVL) no.getFilhoEsquerdo();
		//System.out.println(no.getChave());
		rotacaoDireita((INo) no);

		StringBuilder caminho = new StringBuilder();
		//preOrdem((INoAVL)getRaiz(), caminho);
		//System.out.println("Apos rot s dir: "+ caminho.toString());
		
		//atualiza o FB dos n�s envolvidos
		noFilho.setFB(calculaFB(noFilho));
		no.setFB(calculaFB(no));
		noPai.setFB(calculaFB(noPai));
		
		//System.out.println("no: "+ no.getChave() +"\tfilho: "+ noFilho.getChave() +"\tpai: "+ noPai.getChave());
		//System.out.println("Pai alt esq: "+ altura(noPai.getFilhoEsquerdo().getChave()) +" "+ noPai.getFilhoEsquerdo().getChave() +"\talt dir: "+ altura(noPai.getFilhoDireito().getChave()) +" "+ noPai.getFilhoDireito().getChave());		

		caminho = new StringBuilder();
		preOrdem((INoAVL)getRaiz(), caminho);
		//System.out.println("Apos calcular FB: "+ caminho.toString());
	}

	@Override
	public void rotacaoEsquerda(INo no) {
		//System.out.println(no.getChave());

		INo noPai = no.getPai();
		INo noFilho = no.getFilhoDireito();
		INo noNeto = noFilho.getFilhoEsquerdo();
		
		//codigo repetido 1
		if (no == noPai.getFilhoDireito()) {
			noPai.setFilhoDireito(noFilho);
		} else {
			noPai.setFilhoEsquerdo(noFilho);
		}
		noFilho.setPai(noPai);
		
		no.setPai(noFilho);
		noFilho.setFilhoEsquerdo(no);

		no.setFilhoDireito(noNeto);
		noNeto.setPai(no);
	}
	
	private void rotacaoEsquerda(INoAVL no) {
		System.out.println("Rotacao esquerda - chave:"+ no.getChave());
		INoAVL noPai = (INoAVL) no.getPai();
		INoAVL noFilho = (INoAVL) no.getFilhoDireito();
		
		rotacaoEsquerda((INo) no);
		
		//System.out.println("no: "+ no.getChave() +"\tfilho: "+ noFilho.getChave() +"\tpai: "+ noPai.getChave());
		//System.out.println("no: "+ no.getChave() +"\tno.fe: "+ no.getFilhoEsquerdo().getChave() +"\tno.fd: "+ no.getFilhoDireito().getChave() +"\tFB: "+ calculaFB(no));
		System.out.println("Altura no.fe: "+ altura((INo) no.getFilhoEsquerdo()) +"\tno.fd: "+ altura((INo) no.getFilhoDireito()) +"\tFB: "+ calculaFB(no));
		//System.out.println("Altura no.fe: "+ altura(20) +"\tno.fd: "+ altura(no.getFilhoDireito().getChave()));

		//atualiza o FB dos n�s envolvidos
		no.setFB(calculaFB(no));
		noFilho.setFB(calculaFB(noFilho));
		noPai.setFB(calculaFB(noPai));
		
		StringBuilder caminho = new StringBuilder();
		preOrdem((INoAVL)getRaiz(), caminho);
		//System.out.println("Apos calcular FB: "+ caminho.toString());
	}

	@Override
	public void rotacaoDuplaDireita(INo no) {
		//System.out.println("#####Iniciando rotacao Dupla direita");
		rotacaoEsquerda(no.getFilhoEsquerdo());
		rotacaoDireita(no);
		//System.out.println("#####Encerrando rotacao Dupla direita");
	}

	@Override
	public void rotacaoDuplaEsquerda(INo no) {
		//System.out.println("#####Iniciando rotacao Dupla Esquerda");
		rotacaoDireita(no.getFilhoDireito());
		rotacaoEsquerda(no);
		//System.out.println("#####Encerrando rotacao Dupla Esquerda");
	}
	
	
	private void rotacaoDuplaDireita(INoAVL no) {
		//System.out.println("#####Iniciando rotacao Dupla direita");
		rotacaoEsquerda((INoAVL) no.getFilhoEsquerdo());
		rotacaoDireita(no);
		//System.out.println("#####Encerrando rotacao Dupla Esquerda");
	}

	private void rotacaoDuplaEsquerda(INoAVL no) {
		//System.out.println("#####Iniciando rotacao Dupla Esquerda");
		rotacaoDireita((INoAVL) no.getFilhoDireito());
		rotacaoEsquerda(no);
		//System.out.println("#####Encerrando rotacao Dupla Esquerda");
	}
	
	
	private int calculaFB(INoAVL no) {
		if (no == this.raiz) {
			return 0;
		}
		return altura(no.getFilhoEsquerdo()) - altura(no.getFilhoDireito());
	}
	
	public void preOrdem(INoAVL no, StringBuilder caminho) {
		caminho.append((Integer) no.getChave() +"("+ no.getFB() +")" +"\t");
		//System.out.println(caminho);
		//System.out.print((Integer) no.getChave() +"\t");

		if (no.hasFilhoEsquerdo()) {
			preOrdem((INoAVL) no.getFilhoEsquerdo(), caminho);
		}

		if (no.hasFilhoDireito()) {
			preOrdem((INoAVL) no.getFilhoDireito(), caminho);
		}
	}

}
