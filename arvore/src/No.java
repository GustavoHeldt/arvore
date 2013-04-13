
public class No implements INo {
	
	private Object chave;
	private INo pai;
	private INo filhoEsquerdo;
	private INo filhoDireito;

	
	public No() {
	}
	
	public No(Object chave) {
		this.chave = chave;
	}
	
	public INo getFilhoDireito() {
		return filhoDireito;
	}

	public INo getFilhoEsquerdo() {
		return filhoEsquerdo;
	}

	public INo getPai() {
		return pai;
	}

	public Object getChave() {
		return chave;
	}

	public void setFilhoDireito(INo fD) {
		filhoDireito = fD;
	}

	public void setFilhoEsquerdo(INo fE) {
		filhoEsquerdo = fE;
	}

	public void setPai(INo pai) {
		this.pai = pai;
	}

	public void setChave(Object ch) {
		chave = ch;
	}
	
	
	public boolean hasFilhoEsquerdo() {
		return this.getFilhoEsquerdo().getChave() != null;
	}
	
	public boolean hasFilhoDireito() {
		return this.getFilhoDireito().getChave() != null;
	}
	

	public boolean isFolha() {
		return chave == null;
	}
	
	public boolean isExterno() {
		return !this.hasFilhoEsquerdo() && !this.hasFilhoDireito();
	}
	
	public boolean isNull() {
		return this.getChave() == null;
	}

}
