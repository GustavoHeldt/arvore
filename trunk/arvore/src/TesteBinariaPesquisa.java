
public class TesteBinariaPesquisa {

	public static void main(String[] args) {

		ArvoreBP abp = new ArvoreBP();

		abp.inserir(6);
		abp.inserir(2);
		abp.inserir(1);
		abp.inserir(4);
		abp.inserir(3);
		abp.inserir(5);
		abp.inserir(9);
		abp.inserir(8);


		//System.out.println(abp.altura(5));
		System.out.println("Altura: "+ abp.altura(2));

		abp.preOrdem(abp.getRaiz());
		System.out.println("\n");
		abp.remover(2);

		abp.preOrdem(abp.getRaiz());

	}

}
