package cybert.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import cybert.objetos.Item;
import cybert.objetos.Pedido;

public class FileProcessor {
	
	public static List<Item> readItens(String fileAddress) throws FileNotFoundException {
		File receivedFile = new File(fileAddress);
		Scanner reader = new Scanner(new FileInputStream(receivedFile), "UTF-8");
		if (reader.hasNextLine())
			reader.nextLine();
		List<Item> listItens = new ArrayList<>();
		while(reader.hasNextLine()) {
			String row = reader.nextLine();
			String[] parts = row.split(";");
			Item objItem = new Item();
			objItem.setCodigo(Integer.parseInt(parts[0]));
			objItem.setDesc(parts[1]);
			objItem.setPreco(Double.parseDouble(parts[2]));
			listItens.add(objItem);
		}
		reader.close();
		return listItens;
	}

	
	//retorna uma list estruturada com todos os pedidos que estão na base de dados do sistema
	public static List<Pedido> readPedidos(String fileAddress) throws FileNotFoundException{
		File receivedFile = new File(fileAddress);
		Scanner reader = new Scanner(new FileInputStream(receivedFile), "UTF-8");
		
		//pula o cabeçalho
		if (reader.hasNextLine())
			reader.nextLine();
		
		List<Pedido> listPedidos = new ArrayList<>();
		
		//instancia o primeiro pedido
		Pedido pedido = new Pedido();
		int aux = 1;
		
		while(reader.hasNextLine()) {
			String row = reader.nextLine();
			String[] parts = row.split(";");
			if(Integer.parseInt(parts[0]) != pedido.getIdPedido()) {
				if (aux != 1)
					listPedidos.add(pedido); 					// não adiciona o pedido na primeira volta do laço, pois ainda não está populado
				aux++;
				pedido = new Pedido(); 							// instancia um novo pedido caso seja um novo id da lista
			}

			pedido.setIdPedido(Integer.parseInt(parts[0])); 	// atualiza o id do pedido com a linha atual

			int idItem = Integer.parseInt(parts[1]); 			//pega o id do item da linha atual

			for(Item item : BaseDeDados.getBaseItens()) {
				if(item.getCodigo() == idItem) {
					pedido.addItem(item); 						//adiciona o item encontrado no pedido da iteração atual
					break;
				}
			}
			pedido.setQtdItem(Integer.parseInt(parts[2]));
			pedido.setObs(parts[3]);
			if(!reader.hasNextLine()) listPedidos.add(pedido);	//adiciona o ultimo pedido na lista
		}
		reader.close();
		return listPedidos;
	}
}