package cybert.util;

import java.io.FileNotFoundException;
import java.util.List;

import cybert.objetos.Item;
import cybert.objetos.Pedido;

public class BaseDeDados {
	
	public static List<Pedido> getBasePedidos() throws FileNotFoundException{
		List<Pedido> listPedidos = FileProcessor.readPedidos("C:\\Users\\36639\\Desktop\\WS Java\\java-menu-master\\java-menu-master\\output\\Pedidos_Realizados.csv");
		return listPedidos;
	}
	
	public static List <Item> getBaseItens() throws FileNotFoundException{
		List<Item> listItens = FileProcessor.readItens("C:\\Users\\36639\\Desktop\\WS Java\\java-menu-master\\java-menu-master\\data\\Itens.csv");
		return listItens;
	}

	//atualiza todos os dados do pedido na posição em que se encontra da lista
	public static void atualizaBasePedidos(Pedido pedido, List <Pedido> listPedidos) throws FileNotFoundException {
		for(Pedido order : listPedidos) {
			if(order.getIdPedido() == pedido.getIdPedido()) { 	//Encontra o index onde o pedido está localizado
				List<Item> listItensOldOrder = order.getItems();
				List<Object> qtdsOldOrder = order.getQtdItens();
				qtdsOldOrder.removeAll(qtdsOldOrder);
				listItensOldOrder.removeAll(listItensOldOrder);
				
				order.setItens(pedido.getItems());
				order.setQtdItens(pedido.getQtdItens());
				
				order.setObs(pedido.getObs());
			}
		}
	}
	
	//atualiza todos os dados do cardápio nas devidas posições
	public static void atualizaBaseItens(Item item, List <Item> listItens) throws FileNotFoundException {
		for(Item itemCardapio : listItens) {
			if(itemCardapio.getCodigo() == item.getCodigo()) { 	//Encontra o index onde o pedido está localizado
				itemCardapio.setDesc(item.getDesc());
				itemCardapio.setPreco(item.getPreco());
			}
		}
	}
}
