package cybert.objetos;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
	private int idPedido = 0;
	private List<Item> itens = new ArrayList<>();
	private ArrayList<Object> qtdItens = new ArrayList<>();
	private String obs;

	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public void addItem(Item item) {
		this.itens.add(item);
	}
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	public List<Item> getItems() {
		return itens;
	}
	public ArrayList<Object> getQtdItens() {//retorna a lista com todas as quantidades
		return qtdItens;
	}
	public void setQtdItens(ArrayList<Object> qtdItens) {//recebe uma lista com todas as quantidades
		this.qtdItens = qtdItens;
	}
	public void setQtdItem(int qtdItem) {//seta quantidade do ultimo item especifico
		this.qtdItens.add(qtdItem);
	}
	public int getQtdItem(int index) {//busca a quantidade de itens especificos
		return (int) qtdItens.get(index);
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	
}
