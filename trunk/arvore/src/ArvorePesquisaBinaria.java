
public class ArvorePesquisaBinaria implements IArvore {

	INo raiz;
	
	public INo buscar(Object chave) {
	
		INo noAtual = this.raiz; 

		while (noAtual.getChave() != null && !noAtual.getChave().equals(chave)) {
			if (new Comparador().comparar(chave, noAtual.getChave()) > 0) {
				noAtual = noAtual.getFilhoDireito();
			} else {
				noAtual = noAtual.getFilhoEsquerdo();
			}
		}
		
		return noAtual;
	}
	
	public void inserir(Object chave) {
		
		INo no = this.raiz;
		
		if (no == null) {
			no = new No(chave);
			this.raiz = no;
		} else {
			no = buscar(chave);
			no.setChave(chave);
		}
		
		INo nD = new No();
		no.setFilhoDireito(nD);
		nD.setPai(no);
		
		INo nE = new No();
		no.setFilhoEsquerdo(nE);
		nE.setPai(no);
	}
	
	public INo remover(Object chave) {
		
		INo no = buscar(chave);
		System.out.println("Rem "+ (Integer) no.getChave() +"\t fil esq: "+ no.getFilhoEsquerdo().getChave() +"\t fil dir: "+ no.getFilhoDireito().getChave());
		INo noPai = no.getPai();
		
		if (!no.isFolha()) {
			
			if (no.getFilhoEsquerdo().isFolha()) {
				if (noPai.getFilhoDireito() == no) {
					noPai.setFilhoDireito(no.getFilhoDireito());
					no.getFilhoDireito().setPai(noPai);
				} else {
					noPai.setFilhoEsquerdo(no.getFilhoDireito());
					no.getFilhoDireito().setPai(noPai);
				}
			//o nó a ser removido possui um sucessor
			} else {
				
				INo sucessor = sucessor(no);
				System.out.println("Suc "+ (Integer) sucessor.getChave());
				
				no.setChave(sucessor.getChave());
				
				INo paiS = sucessor.getPai();
				if (paiS.getFilhoEsquerdo() == sucessor) {
					paiS.setFilhoEsquerdo(sucessor.getFilhoDireito());
				} else {
					paiS.setFilhoDireito(sucessor.getFilhoDireito());
				}
				//sucessor.getPai().setFilhoEsquerdo(sucessor.getFilhoEsquerdo());
				sucessor.setPai(null);
			}
		}
		
		return no;
	}
	
	
	public INo sucessor(Object chave) {
		return sucessor(buscar(chave));
	}
	
	private INo sucessor(INo no) {
		System.out.println("sucessor de: "+ no.getChave());
		INo noAtual = no.getFilhoDireito();
		
		while (!noAtual.getFilhoEsquerdo().isFolha()) {
			noAtual = noAtual.getFilhoEsquerdo();
		}

		return noAtual;
	}
	
	/*testar*/
	public int altura(Object chave) {
		return altura(buscar(chave));
	}
	
	private int altura(INo no) {
		
		if (no.isExterno()) {
			return 0;
		} else {
			int alturaFd = altura(no.getFilhoDireito());
			int alturaFe = altura(no.getFilhoEsquerdo());
			int altura = Math.max(alturaFe, alturaFd);
			return 1 + altura;
		}
		
	}
	
	/*testar*/
	public int profundidade(Object chave) {
		return profundidade(buscar(chave));
	}
	
	private int profundidade(INo no) {
		if (no.isRaiz()) {
			return 0;
		} else {
			return 1 + profundidade(no.getPai());
		}
	}
	
	public void preOrdem(INo n) {
		System.out.print((Integer) n.getChave() +"\t");
		
		if (!n.getFilhoEsquerdo().isFolha()) {
			preOrdem(n.getFilhoEsquerdo());
		}
		
		if (!n.getFilhoDireito().isFolha()) {
			preOrdem(n.getFilhoDireito());
		}
	}
	
	/*testar*/
	public void posOrdem(INo n) {
		if (!n.getFilhoEsquerdo().isFolha()) {
			posOrdem(n.getFilhoEsquerdo());
		}
		
		if (!n.getFilhoDireito().isFolha()) {
			preOrdem(n.getFilhoDireito());
		}
		
		System.out.print((Integer) n.getChave() +"\t");
	}
	
	@Override
	public Object getChaveRaiz() {
		if (this.raiz == null) {
			return null;
		} else {
			return this.raiz.getChave();
		}
	}
	
	protected INo getRaiz () {
		return this.getRaiz();
	}
	
}
