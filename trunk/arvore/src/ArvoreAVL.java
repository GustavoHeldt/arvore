
public class ArvoreAVL extends ArvoreBP implements IArvoreAVL {

	public ArvoreAVL() {
		raiz = new NoAVL();
		raiz.setFilhoEsquerdo(new NoAVL());
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
	
	
	private void atualizarFBInsercao(INoAVL noInserido, int dif) {
		INoAVL noPai = (INoAVL) noInserido.getPai();
		noPai.setFB(noPai.getFB() + dif);
		
		if (noPai.getFB() == -2) {
			//codigo repetido 2
			if (noInserido.getFB() <= 0) {
				rotacaoEsquerda(noPai);
			} else {
				rotacaoDuplaEsquerda(noPai);
			}
			
		} else if (noPai.getFB() == 2) {
			//codigo repetido 2
			if (noInserido.getFB() >= 0) {
				rotacaoDireita(noPai);
			} else {
				rotacaoDuplaDireita(noPai);
			}
		}
		
		if (noPai.getFB() != 0) {
			atualizarFBInsercao(noPai, dif);
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
		
		INo noPai = no.getPai();
		INo noNeto = no.getFilhoDireito();
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


	}
	
	private void rotacaoDireita(INoAVL no) {
		INoAVL noPai = (INoAVL) no.getPai();
		INoAVL noNeto = (INoAVL) no.getFilhoDireito();
		INoAVL noFilho = (INoAVL) no.getFilhoEsquerdo();
		
		rotacaoDireita(no);
		
		//atualiza o FB dos nós envolvidos
		noFilho.setFB(calculaFB(noFilho));
		no.setFB(calculaFB(no));
		noPai.setFB(calculaFB(noPai));
	}

	@Override
	public void rotacaoEsquerda(INo no) {
		
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
		
		INoAVL noPai = (INoAVL) no.getPai();
		INoAVL noSucessor = (INoAVL) this.sucessor(no);
		INoAVL noFilho = (INoAVL) no.getFilhoDireito();
		
		rotacaoEsquerda(no);
		
		//atualiza o FB dos nós envolvidos
		noFilho.setFB(calculaFB(noFilho));
		no.setFB(calculaFB(no));
		noPai.setFB(calculaFB(noPai));
	}

	@Override
	public void rotacaoDuplaDireita(INo no) {
		rotacaoDireita(no.getFilhoEsquerdo());
		rotacaoEsquerda(no);
	}

	@Override
	public void rotacaoDuplaEsquerda(INo no) {
		rotacaoDireita(no.getFilhoDireito());
		rotacaoEsquerda(no);
	}
	
	
	private void rotacaoDuplaDireita(INoAVL no) {
		rotacaoDireita((INoAVL) no.getFilhoEsquerdo());
		rotacaoEsquerda(no);
	}

	private void rotacaoDuplaEsquerda(INoAVL no) {
		rotacaoDireita((INoAVL) no.getFilhoDireito());
		rotacaoEsquerda(no);
	}
	
	
	private int calculaFB(INoAVL no) {
		return this.altura(no.getFilhoEsquerdo()) - this.altura(no.getFilhoEsquerdo());
	}

}
