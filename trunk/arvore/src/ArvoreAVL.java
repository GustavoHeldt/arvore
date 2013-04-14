
public class ArvoreAVL extends ArvoreBP implements IArvoreAVL {

	public void inserir(Object chave) {

		((IArvoreAVL) this).inserir(chave);
		this.buscar(chave);
	}
	
	
	public void atualizarFBInsercao(INoAVL noInserido, int dif) {
		INoAVL noPai = (INoAVL) noInserido.getPai();
		noPai.setFB(noPai.getFB() + dif);
		
		if (noPai.getFB() == -2) {
			if (noInserido.getFB() <= 0) {
				rotacaoEsquerda(noPai);
			} else {
				rotacaoDuplaEsquerda(noPai);
			}
			
		} else if (noPai.getFB() == 2) {
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
	
	
	public void atualizarFBRemocao(INoAVL no, int dif) {
		
		INoAVL noFilho;
		no.setFB(no.getFB() + dif);
		
		if (dif == -1) {
			noFilho = (INoAVL) no.getFilhoEsquerdo();
		} else if (dif == 1) {
			noFilho = (INoAVL) no.getFilhoDireito();
		}
		
		if (no.getFB() == -2) {
			if (no.getFB() <= 0) {
				rotacaoEsquerda(no);
			} else {
				rotacaoDuplaEsquerda(no);
			}
			
		} else if (no.getFB() == 2) {
			if (no.getFB() >= 0) {
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
	public void rotacaoDireita(INoAVL no) {
		
		INoAVL noPai = (INoAVL) no.getPai();
		INoAVL noNeto = (INoAVL) no.getFilhoDireito();
		INoAVL noFilho = (INoAVL) no.getFilhoEsquerdo();
		
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

		//atualiza o FB dos nós envolvidos
		noFilho.setFB(calculaFB(noFilho));
		no.setFB(calculaFB(no));
		noPai.setFB(calculaFB(noPai));
	}

	@Override
	public void rotacaoEsquerda(INoAVL no) {
		
		INoAVL noPai = (INoAVL) no.getPai();
		INoAVL noSucessor = (INoAVL) this.sucessor(no);
		INoAVL noFilho = (INoAVL) no.getFilhoDireito();
		
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

		//atualiza o FB dos nós envolvidos
		noFilho.setFB(calculaFB(noFilho));
		no.setFB(calculaFB(no));
		noPai.setFB(calculaFB(noPai));
	}

	@Override
	public void rotacaoDuplaDireita(INoAVL no) {
		rotacaoDireita((INoAVL) no.getFilhoEsquerdo());
		rotacaoEsquerda(no);
	}

	@Override
	public void rotacaoDuplaEsquerda(INoAVL no) {
		rotacaoDireita((INoAVL) no.getFilhoDireito());
		rotacaoEsquerda(no);
	}
	
	
	private int calculaFB(INoAVL no) {
		return this.altura(no.getFilhoEsquerdo()) - this.altura(no.getFilhoEsquerdo());
	}

}
