
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
	
	@Override
	public INo getFilhoDireito() {
		// TODO Auto-generated method stub
		return filhoDireito;
	}

	@Override
	public INo getFilhoEsquerdo() {
		// TODO Auto-generated method stub
		return filhoEsquerdo;
	}

	@Override
	public INo getPai() {
		// TODO Auto-generated method stub
		return pai;
	}

	@Override
	public Object getChave() {
		// TODO Auto-generated method stub
		return chave;
	}

	@Override
	public void setFilhoDireito(INo fD) {
		// TODO Auto-generated method stub
		filhoDireito = fD;
	}

	@Override
	public void setFilhoEsquerdo(INo fE) {
		// TODO Auto-generated method stub
		filhoEsquerdo = fE;
	}

	@Override
	public void setPai(INo pai) {
		// TODO Auto-generated method stub
		this.pai = pai;
	}

	@Override
	public void setChave(Object ch) {
		// TODO Auto-generated method stub
		chave = ch;
	}

	@Override
	public boolean isFolha() {
		// TODO Auto-generated method stub
		return chave == null;
	}
	
	public boolean isExterno() {
		// TODO Auto-generated method stub
		if (this.getFilhoDireito() == null || this.getFilhoEsquerdo() == null) {
			return false;
		}
		return this.getFilhoDireito().getChave() == null && this.getFilhoEsquerdo().getChave() == null;
	}
	
	public boolean isRaiz() {
		// TODO Auto-generated method stub
		return this.getPai() == null;
	}

}
