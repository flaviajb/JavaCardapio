package cybert.util;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import cybert.objetos.Item;
import cybert.objetos.Pedido;
import cybert.sistema.PedidoController;

public class FileRecorder {
	private static String pathPedidos = "C:\\Users\\36639\\Desktop\\WS Java\\java-menu-master\\java-menu-master\\output\\Pedidos_Realizados.csv";
	private static String pathItens = "C:\\Users\\36639\\Desktop\\WS Java\\java-menu-master\\java-menu-master\\data\\Itens.csv";
	
	//atualiza o arquivo com os pedidos gravados quando encerrada alteração, exclusão ou quaisquer informações 
	public static void gravarPedidos(List<Pedido> pedidos) throws IOException {
		
		FileWriter fileOutput = new FileWriter(pathPedidos);
		PrintWriter fileRecorder = new PrintWriter(fileOutput);
		
		fileRecorder.println("idPedido;Item;QtdItem;Obs");
		for(Pedido pedido : pedidos) {
			int i = 0;
			for(Item item : pedido.getItems()) {
				fileRecorder.println(pedido.getIdPedido() + ";" + item.getCodigo() + ";" + pedido.getQtdItem(i) + ";" + (pedido.getObs() == null ? "Nao Possui" : pedido.getObs()));
				i++;
			}
		}
	
		fileOutput.close();
		fileRecorder.close();
	}
	
	//atualiza o arquivo com os itens do cardapio gravados quando encerrada alteração, exclusão ou quaisquer informações 
		public static void gravarItens(List<Item> listItens) throws IOException {
			FileWriter fileOutput = new FileWriter(pathItens);
			PrintWriter fileRecorder = new PrintWriter(fileOutput);
			fileRecorder.println("COD;DESC;PRECO");
			for(Item item: listItens) {
				fileRecorder.println(item.getCodigo() + ";" + item.getDesc() + ";" + item.getPreco());
			}
			fileOutput.close();
			fileRecorder.close();
		}
		
}
