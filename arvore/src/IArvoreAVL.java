public interface IArvoreAVL extends IArvore{

	public abstract void rotacaoDireita(INo no);
	public abstract void rotacaoEsquerda(INo no);

	public abstract void rotacaoDuplaDireita(INo no);
	public abstract void rotacaoDuplaEsquerda(INo no);

}