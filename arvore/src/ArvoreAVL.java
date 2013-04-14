
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
		remover(no);
		
		int dif = 0;
		if (no.getPai().getFilhoEsquerdo() == no) {
			dif = -1;
		} else {
			dif = 1;
		}
		
		atualizarFBRemocao((INoAVL) no, dif);
		
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
					noPai = (INoAVL) noAnterior.getPai();
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
		
		INoAVL noFilho = null;
		no.setFB(no.getFB() + dif);
		
		if (dif == -1) {
			noFilho = (INoAVL) no.getFilhoEsquerdo();
		} else if (dif == 1) {
			noFilho = (INoAVL) no.getFilhoDireito();
		}
		
		if (no.getFB() == -2) {
			//codigo repetido 2
			if (noFilho.getFB() <= 0) {
				rotacaoEsquerda(no);
			} else {
				rotacaoDuplaEsquerda(no);
			}
			
		} else if (no.getFB() == 2) {
			//codigo repetido 2
			if (noFilho.getFB() >= 0) {
				rotacaoDireita(no);
			} else {
				rotacaoDuplaDireita(no);
			}
		}
		
		if (no.getFB() == 0) {
			atualizarFBRemocao((INoAVL) no.getPai(), dif);
		}
		
	}

	@Override
	public void rotacaoDireita(INo no) {
		
		System.out.println(no.getChave());
		
		INo noPai = no.getPai();
		INo noNeto = no.getFilhoEsquerdo().getFilhoDireito();
		INo noFilho = no.getFilhoEsquerdo();
		
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
		
		StringBuilder caminho = new StringBuilder();
		preOrdem(getRaiz(), caminho);
		//System.out.println("Apos rot s dir: "+ caminho.toString());
	}
	
	private void rotacaoDireita(INoAVL no) {
		System.out.println("Rotacao Direita");
		INoAVL noPai = (INoAVL) no.getPai();
		INoAVL noFilho = (INoAVL) no.getFilhoEsquerdo();
		//System.out.println(no.getChave());
		rotacaoDireita((INo) no);
		
		//atualiza o FB dos nós envolvidos
		noFilho.setFB(calculaFB(noFilho));
		no.setFB(calculaFB(no));
		noPai.setFB(calculaFB(noPai));
	}

	@Override
	public void rotacaoEsquerda(INo no) {
		System.out.println(no.getChave());

		INo noPai = no.getPai();
		INo noSucessor = this.sucessor(no);
		INo noFilho = no.getFilhoDireito();
		
		//codigo repetido 1
		if (no == noPai.getFilhoDireito()) {
			noPai.setFilhoDireito(noFilho);
		} else {
			noPai.setFilhoEsquerdo(noFilho);
		}
		noFilho.setPai(noPai);
		
		no.setPai(noFilho);
		noFilho.setFilhoEsquerdo(no);
		
		if (noSucessor == noFilho) {
			noSucessor = (INoAVL) noFilho.getFilhoEsquerdo();
		}
		no.setFilhoDireito(noSucessor);
		noSucessor.setPai(no);
	}
	
	private void rotacaoEsquerda(INoAVL no) {
		System.out.println("Rotacao esquerda");
		INoAVL noPai = (INoAVL) no.getPai();
		INoAVL noFilho = (INoAVL) no.getFilhoDireito();
		
		rotacaoEsquerda((INo) no);
		
		//atualiza o FB dos nós envolvidos
		noFilho.setFB(calculaFB(noFilho));
		no.setFB(calculaFB(no));
		noPai.setFB(calculaFB(noPai));
	}

	@Override
	public void rotacaoDuplaDireita(INo no) {
		System.out.println("#####Iniciando rotacao Dupla direita");
		rotacaoEsquerda(no.getFilhoEsquerdo());
		rotacaoDireita(no);
		System.out.println("#####Encerrando rotacao Dupla direita");
	}

	@Override
	public void rotacaoDuplaEsquerda(INo no) {
		System.out.println("#####Iniciando rotacao Dupla Esquerda");
		rotacaoDireita(no.getFilhoDireito());
		rotacaoEsquerda(no);
		System.out.println("#####Encerrando rotacao Dupla Esquerda");
	}
	
	
	private void rotacaoDuplaDireita(INoAVL no) {
		System.out.println("#####Iniciando rotacao Dupla direita");
		rotacaoEsquerda((INoAVL) no.getFilhoEsquerdo());
		rotacaoDireita(no);
		System.out.println("#####Encerrando rotacao Dupla Esquerda");
	}

	private void rotacaoDuplaEsquerda(INoAVL no) {
		System.out.println("#####Iniciando rotacao Dupla Esquerda");
		rotacaoDireita((INoAVL) no.getFilhoDireito());
		rotacaoEsquerda(no);
		System.out.println("#####Encerrando rotacao Dupla Esquerda");
	}
	
	
	private int calculaFB(INoAVL no) {
		return this.altura((INo) no.getFilhoEsquerdo()) - this.altura((INo) no.getFilhoEsquerdo());
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
