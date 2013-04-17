
public class ArvoreRN extends ArvoreAVL implements IArvoreRN {
	
	public ArvoreRN() {
		raiz = new NoRN();
		INo no = new NoRN();
		raiz.setFilhoEsquerdo(no);
		no.setPai(raiz);
	}
	
	public void inserir(Object chave) {

		INoRN no = (INoRN) this.buscar(chave);

		this.inserir(no, chave);
		no.setCor(INoRN.COR_RUBRO);
		

		INoRN nD = new NoRN();
		nD.setCor(INoRN.COR_NEGRA);
		no.setFilhoDireito(nD);
		nD.setPai(no);

		INoRN nE = new NoRN();
		nE.setCor(INoRN.COR_NEGRA);
		no.setFilhoEsquerdo(nE);
		nE.setPai(no);
		
		System.out.println("Inserindo "+ chave);
		
		
		atualizarCORInsercao((INoRN) no);
	}
	
	public void atualizarCORInsercao(INoRN no) {
		
		INoRN noPai = (INoRN) no.getPai();
		INoRN noBisa = (INoRN) noPai.getPai();
		INoRN noAvo = (INoRN) noPai.getPai();
		INoRN noTio = (INoRN) noPai.getFilhoEsquerdo();
		if (noTio == noPai) {
			noTio = (INoRN) noAvo.getFilhoDireito();
		}
		
		boolean atualizaNovamente = false;
		
		//caso 1 - não fazer nada
		//caso 2
		if (noAvo.getCor() == INoRN.COR_NEGRA 
				&& noTio.getCor() == INoRN.COR_RUBRO) {
			noAvo.setCor(INoRN.COR_RUBRO);
			noPai.setCor(INoRN.COR_NEGRA);
			noTio.setCor(INoRN.COR_NEGRA);
			
			if (noBisa.getCor() == INoRN.COR_RUBRO) {
				atualizaNovamente = true;
				no = noPai;
			}
		}
		//caso 3
		else if (noAvo.getCor() == INoRN.COR_NEGRA 
				&& noPai.getCor() == INoRN.COR_RUBRO
				&& noTio.getCor() == INoRN.COR_NEGRA) {
			//caso 3a
			if (no == noPai.getFilhoEsquerdo()
					&& noPai == noAvo.getFilhoEsquerdo()) {
				rotacaoDireita(noAvo);
				
				noAvo.setCor(INoRN.COR_RUBRO);
				noPai.setCor(INoRN.COR_NEGRA);
			}
			//caso 3b
			else if (no == noPai.getFilhoDireito()
					&& noPai == noAvo.getFilhoDireito()) {
				rotacaoEsquerda(noAvo);
				
				noAvo.setCor(INoRN.COR_RUBRO);
				noPai.setCor(INoRN.COR_NEGRA);
			}
			//caso 3c
			else if (no == noPai.getFilhoEsquerdo()
					&& noPai == noAvo.getFilhoDireito()) {
				rotacaoDuplaEsquerda(noAvo);
				
				noAvo.setCor(INoRN.COR_RUBRO);
				no.setCor(INoRN.COR_NEGRA);
			}
			//caso 4d
			else {
				rotacaoDuplaDireita(noAvo);
				
				noAvo.setCor(INoRN.COR_RUBRO);
				no.setCor(INoRN.COR_NEGRA);
			}
			
		}
		
		if (atualizaNovamente) {
			atualizarCORInsercao(no);
		}
	}
	
	
	public INo remover(Object chave) {

		INoRN no = (INoRN) buscar(chave);
		
		INoRN sucessor = (INoRN) remover(no);
		System.out.println("\nRemovendo "+ chave +"\tpai "+ no.getPai().getChave());

		atualizarCORRemocao(no, sucessor);
		
		//desconecta totalmente o nó da árvore
		no.setPai(null);
		return no;
	}
	
	public void atualizarCORRemocao(INoRN no, INoRN sucessor) {
		
		INoRN noPai = (INoRN) sucessor.getPai();
		INoRN noIrmao = (INoRN) sucessor.getPai().getFilhoEsquerdo();
		if (sucessor == noIrmao) {
			noIrmao = (INoRN) sucessor.getPai().getFilhoDireito();
		}
		INoRN sobrinhoE = (INoRN) noIrmao.getFilhoEsquerdo();
		INoRN sobrinhoD = (INoRN) noIrmao.getFilhoDireito();
		
		
		//situacao 1 - não fazer nada
		//situacao 2
		if (no.getCor() == INoRN.COR_NEGRA
				&& sucessor.getCor() == INoRN.COR_RUBRO) {
			
			sucessor.setCor(INoRN.COR_NEGRA);
			
		}
		//situacao 3
		else if (no.getCor() == INoRN.COR_NEGRA
				&& sucessor.getCor() == INoRN.COR_NEGRA) {
			
			//caso 1
			if (noPai.getCor() == INoRN.COR_NEGRA
					&& noIrmao.getCor() == INoRN.COR_RUBRO) {
				
				sucessor.setDuploNegro(INoRN.COM_DUPLO_NEGRO);
				rotacaoEsquerda(noPai);
				noIrmao.setCor(INoRN.COR_NEGRA);
				noPai.setCor(INoRN.COR_RUBRO);
			
			}
			//caso 2a
			else if (noPai.getCor() == INoRN.COR_NEGRA 
					&& noIrmao.getCor() == INoRN.COR_NEGRA 
					&& sobrinhoE.getCor() == INoRN.COR_NEGRA 
					&& sobrinhoD.getCor() == INoRN.COR_NEGRA ) {
				
				noIrmao.setCor(INoRN.COR_RUBRO);
			}
			//caso 2b
			else if (noPai.getCor() == INoRN.COR_RUBRO 
					&& noIrmao.getCor() == INoRN.COR_NEGRA 
					&& sobrinhoE.getCor() == INoRN.COR_NEGRA 
					&& sobrinhoD.getCor() == INoRN.COR_NEGRA ) {
				
				noIrmao.setCor(INoRN.COR_RUBRO);
				noPai.setCor(INoRN.COR_NEGRA);
				
				no.setDuploNegro(INoRN.SEM_DUPLO_NEGRO);
			}
			//caso 3
			else if (noIrmao.getCor() == INoRN.COR_NEGRA 
					&& sobrinhoE.getCor() == INoRN.COR_RUBRO 
					&& sobrinhoD.getCor() == INoRN.COR_NEGRA) {
				
				rotacaoDireita(noIrmao);
				
				noIrmao.setCor(INoRN.COR_RUBRO);
				sobrinhoE.setCor(INoRN.COR_NEGRA);
			}
			//caso4
			else if (noIrmao.getCor() == INoRN.COR_NEGRA 
					&& sobrinhoD.getCor() == INoRN.COR_RUBRO) {
				
				rotacaoEsquerda(noPai);
				noIrmao.setCor(noPai.getCor());
				noPai.setCor(INoRN.COR_NEGRA);
				sobrinhoD.setCor(INoRN.COR_NEGRA);
				
			}
			
		}
		//situacao 4
		else if (no.getCor() == INoRN.COR_RUBRO
				&& sucessor.getCor() == INoRN.COR_NEGRA) {
			
			sucessor.setCor(INoRN.COR_RUBRO);
			((INoRN) sucessor.getFilhoDireito()).setDuploNegro(INoRN.COM_DUPLO_NEGRO);
		}
		
	}
	
	public void preOrdem(INoRN no, StringBuilder caminho) {
		char cor = 'R';
		if (no.getCor() == INoRN.COR_NEGRA) {
			cor = 'N';
		}

		char duplo = ' ';
		if (no.getDuploNegro() == INoRN.COM_DUPLO_NEGRO) {
			duplo = 'D';
		}
		
		caminho.append((Integer) no.getChave() +"("+ duplo+cor +")" +"\t");

		if (no.hasFilhoEsquerdo()) {
			preOrdem((INoAVL) no.getFilhoEsquerdo(), caminho);
		}

		if (no.hasFilhoDireito()) {
			preOrdem((INoAVL) no.getFilhoDireito(), caminho);
		}
	}


}
