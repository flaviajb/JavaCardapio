package cybert.sistema;

import java.io.IOException;
import java.util.Scanner;

import cybert.objetos.Item;
import cybert.objetos.Pedido;
import cybert.util.BaseDeDados;
import cybert.util.Printer;
import cybert.sistema.PedidoController;


//Raiz inicializadora do sistema
public class Index {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		boolean encerrar = false;
		Scanner leitorUsuario = new Scanner(System.in);

		do {
			String op = "";
			
			//imprime opções
			System.out.println(Printer.imprimir("Principal"));
			op = leitorUsuario.next();
			
			switch(op) {
			case "1":
				//inclusão
				String[] pedidosAnteriores = {""};
				PedidoController.editorDePedido(new Pedido());
				break;
			case "2":
				// Consulta todos os pedidos
				System.out.println(Printer.imprimir("consultaPedidos"));
				break;
			case "3":
				//Atualização pedido
				PedidoController.alterarExcluirPedido('A');
				break;
			case "4":
				//Exclusão pedido
				PedidoController.alterarExcluirPedido('E');
				break;
			case "5":
				//consultar cardápios
				System.out.println(Printer.imprimir("Itens"));
				break;
			case "6":
				CardapioController.criarItem();
				break;
			case "7":
				CardapioController.alterarExcluirItem('A');
				break;
			case "8":
				CardapioController.alterarExcluirItem('E');
				break;
			case "9":
				encerrar = true;
				break;
				default:
					encerrar = false;
			}
		} while(!encerrar);
		
		leitorUsuario.close(); // fecha o leitor
		System.exit(0);        //encerra o programa
	}
}
