public interface INoRN extends INo {
	
	public final int COR_INDEFINIDA = 0;
	public final int COR_RUBRO = 1;
	public final int COR_NEGRA = 2;
	
	public final int COM_DUPLO_NEGRO = 1;
	public final int SEM_DUPLO_NEGRO = 2;

	public abstract void setCor(int a);

	public abstract int getCor();

	public abstract int getDuploNegro();

	public abstract void setDuploNegro(int duploNegro);

}