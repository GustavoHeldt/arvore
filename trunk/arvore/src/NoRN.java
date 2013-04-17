
public class NoRN extends No implements INoRN {

	public int cor = COR_INDEFINIDA;
	public int duploNegro = 0;
	
	@Override
	public void setCor(int cor) {
		this.cor = cor;
	}

	@Override
	public int getCor() {
		return this.cor;
	}

	@Override
	public int getDuploNegro() {
		return this.duploNegro;
	}

	@Override
	public void setDuploNegro(int duploNegro) {
		this.duploNegro = duploNegro;
	}

}
