
public class ArvoreBP implements IArvore {

	INo raiz;

	public ArvoreBP () {
		raiz = new No();
		INo no = new No();
		raiz.setFilhoEsquerdo(no);
		no.setPai(raiz);
	}

	public INo buscar(Object chave) {

		INo noAtual = this.getRaiz(); 

		while (!noAtual.isNull() && !noAtual.getChave().equals(chave)) {
			if (new Comparador().comparar(chave, noAtual.getChave()) > 0) {
				noAtual = noAtual.getFilhoDireito();
			} else {
				noAtual = noAtual.getFilhoEsquerdo();
			}
		}

		return noAtual;
	}

	public void inserir(Object chave) {

		INo no = this.buscar(chave);

		if (no.isNull()) {
			this.inserir(no, chave);
	
			INo nD = new No();
			no.setFilhoDireito(nD);
			nD.setPai(no);
	
			INo nE = new No();
			no.setFilhoEsquerdo(nE);
			nE.setPai(no);
		}
	}
	
	protected void inserir(INo no, Object chave) {
		
		if (no.isNull()) {
			no.setChave(chave);
		} else {
			no = buscar(chave);
			no.setChave(chave);
		}
		
	}


	public INo remover(Object chave) {

		INo no = buscar(chave);

		no = remover(no);
		//retira o n� de vez da �rvore
		no.setPai(null);
		
		return no;
	}
	
	protected INo remover(INo no) {
		//System.out.println("Rem "+ (Integer) no.getChave() +"\t fil esq: "+ no.getFilhoEsquerdo().getChave() +"\t fil dir: "+ no.getFilhoDireito().getChave());

		if (!no.isNull()) {

			INo noPai = no.getPai();
			INo noFilhoEsquerdo = no.getFilhoEsquerdo();
			INo noFilhoDireito = no.getFilhoDireito();

			if (no.isExterno()) {

				//codigo repetido 1
				if (no == noPai.getFilhoDireito()) {
					noPai.setFilhoDireito(noFilhoDireito);
				} else {
					noPai.setFilhoEsquerdo(noFilhoDireito);
				}
				noFilhoDireito.setPai(noPai);
				no.setFilhoDireito(null);
				
				//no.setPai(null);

			} else if (!no.hasFilhoDireito()) {

				//codigo repetido 1
				if (no == noPai.getFilhoDireito()) {
					noPai.setFilhoDireito(noFilhoEsquerdo);
				} else {
					noPai.setFilhoEsquerdo(noFilhoEsquerdo);
				}

				noFilhoEsquerdo.setPai(noPai);

				//no.setPai(null);
				no.setFilhoEsquerdo(null);

			} else {

				INo sucessor = sucessor(no);
				Object chave = no.getChave();
				//System.out.println("Suc "+ (Integer) sucessor.getChave());

				no.setChave(sucessor.getChave());

				INo paiSucessor = sucessor.getPai();

				if (paiSucessor == no) {
					no.setFilhoDireito(sucessor.getFilhoDireito());
				} else {
					paiSucessor.setFilhoEsquerdo(sucessor.getFilhoDireito());
					sucessor.getFilhoEsquerdo().setPai(paiSucessor);
					//sucessor.getFilhoDireito().setPai(paiSucessor);
				}

				//sucessor.setPai(null);
				//na realidade quem foi removido foi o n� na posi��o do sucessor
				no = sucessor;
				//atualiza o valor da chave do sucessor, para o valor da chave removida
				no.setChave(chave);
			} 

		}

		return no;
	}


	public INo sucessor(Object chave) {
		return sucessor(buscar(chave));
	}

	protected INo sucessor(INo no) {
		//System.out.println("sucessor de: "+ no.getChave());

		INo noAtual = null;

		if (no.hasFilhoDireito()) {
			noAtual = no.getFilhoDireito();

			while (noAtual.hasFilhoEsquerdo()) {
				noAtual = noAtual.getFilhoEsquerdo();
			}
		}

		return noAtual;
	}


	public int altura(Object chave) {
		//System.out.println("altura object");
		return altura(buscar(chave));
	}

	public int altura(INo no) {

		if (no.isNull() ) { 
			return -1;
		} else if (no.isExterno()) {
			return 0;
		} else {
			//System.out.println("no chave: "+ no.getChave() +"\tfe: "+no.getFilhoEsquerdo().getChave() +"\tfd: "+ no.getFilhoDireito().getChave());
			int alturaFd = altura((INo) no.getFilhoDireito());
			int alturaFe = altura((INo) no.getFilhoEsquerdo());
			int altura = Math.max(alturaFe, alturaFd);
			return 1 + altura;
		}
	}
	

	/*testar*/
	public int profundidade(Object chave) {
		return profundidade(buscar(chave));
	}

	private int profundidade(INo no) {
		if (this.isRaiz(no)) {
			return 0;
		} else {
			return 1 + profundidade(no.getPai());
		}
	}

	public void preOrdem(INo no, StringBuilder caminho) {
		caminho.append((Integer) no.getChave() +"\t");
		//System.out.println(caminho);
		//System.out.print((Integer) no.getChave() +"\t");

		if (no.hasFilhoEsquerdo()) {
			preOrdem(no.getFilhoEsquerdo(), caminho);
		}

		if (no.hasFilhoDireito()) {
			preOrdem(no.getFilhoDireito(), caminho);
		}
	}

	/*testar*/
	public void posOrdem(INo no, StringBuilder caminho) {
		if (no.hasFilhoEsquerdo()) {
			posOrdem(no.getFilhoEsquerdo(), caminho);
		}

		if (no.hasFilhoDireito()) {
			posOrdem(no.getFilhoDireito(), caminho);
		}

		//System.out.print((Integer) no.getChave() +"\t");
		caminho.append(((Integer) no.getChave()) +"\t");
	}

	@Override
	public Object getChaveRaiz() {
		return this.raiz.getFilhoEsquerdo().getChave();
	}

	protected INo getRaiz () {
		return this.raiz.getFilhoEsquerdo();
	}

	@Override
	public boolean isEmpty() {

		if (getRaiz().isNull()) {
			return true;
		}

		return false;
	}

	public boolean isRaiz(INo no) {
		return no == this.getRaiz();
	}

}
