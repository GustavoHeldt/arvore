
public class ArvoreAVL extends ArvoreBP implements IArvoreAVL {

	public void inserir(Object chave) {

		((IArvoreAVL) this).inserir(chave);
		this.buscar(chave);

	}

	@Override
	public boolean rotacaoDireita() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rotacaoEsquerda() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rotacaoDuplaDireita() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rotacaoDuplaEsquerda() {
		// TODO Auto-generated method stub
		return false;
	}



}
