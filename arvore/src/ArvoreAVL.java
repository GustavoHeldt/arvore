
public class ArvoreAVL extends ArvorePesquisaBinaria implements IArvoreAVL {

	public void inserir(Object chave) {
		
		((IArvoreAVL) this).inserir(chave);
		this.buscar(chave);
		
	}
	
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
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
