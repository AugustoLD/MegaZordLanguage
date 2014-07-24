import java.util.ArrayList;
import java.util.List;


public class SymbolTable{
	List<String> lista = new ArrayList<String>(); 
	/*
	 *  int chave;
	 *  String valor;
	 */
	
	public int add(String str){
		lista.add(str);
		return lista.lastIndexOf(str);
	}
	
	public String getValor(int chave){
		return lista.get(chave);
	}
	
	public void mostrarTabela(){
		System.out.println("+-------+---------------+");
		System.out.println("| Chave | Valor         |");
		System.out.println("+-------+---------------+");

		for(int i = 0; i < lista.size(); i++){
			System.out.println("|  "+Integer.toString(i)+"  |"+lista.get(i));
		}
		System.out.println("+-------+---------------+");
	}
}



