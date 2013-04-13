
public interface IArvore {

	public INo buscar(Object chave);
	
	public void inserir(Object chave);
	
	public INo remover(Object chave);
	
	
	public INo sucessor(Object chave);

	public int altura(Object chave);
	
	public int profundidade(Object no);
	
	
	public void preOrdem(INo n);
	
	public Object getChaveRaiz();
	
	public boolean isEmpty();
	
}
