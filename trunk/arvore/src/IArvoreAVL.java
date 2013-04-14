public interface IArvoreAVL extends IArvore{

	public abstract void rotacaoDireita(INoAVL no);
	public abstract void rotacaoEsquerda(INoAVL no);

	public abstract void rotacaoDuplaDireita(INoAVL no);
	public abstract void rotacaoDuplaEsquerda(INoAVL no);

}