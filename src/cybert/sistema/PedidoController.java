package cybert.sistema;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import cybert.objetos.Item;
import cybert.objetos.Pedido;
import cybert.util.BaseDeDados;
import cybert.util.FileProcessor;
import cybert.util.Printer;
import cybert.util.Validador;
import cybert.util.FileRecorder;


public class PedidoController {
	//leitor universal de entradas de usuário na classe de pedidos
	static Scanner leitorPedido = new Scanner(System.in);
		
	//gerencia o editor de pedidos em tempo de execução
	public static void editorDePedido(Pedido ped) throws IOException, InterruptedException {
		
		//pega a base de dados de todos os pedidos 
		List <Pedido> listPedidos = BaseDeDados.getBasePedidos();
		
		//recebe o objeto do pedido (novo pedido ou pedido ja existente)
		Pedido pedido = ped;
		
		String opPedido = "";
		boolean encerrarPedido = false;

		do {
			System.out.println(System.lineSeparator()+Printer.imprimir("MenuIncluirPedido"));
			opPedido = leitorPedido.next();
			switch (opPedido) {
				
				// registra um novo item no pedido
				case "1":
					registrarItens(pedido);
					mostraPedidoAtual(pedido);
					break;
				
				//seta obs do pedido
				case "2":
					System.out.println("Insira suas Observações para o Pedido");
					pedido.setObs(leitorPedido.next());
					break;

				//encerra pedido e envia para gravação
				case "3":
					mostraPedidoAtual(pedido);
					//valida na base de dados se o pedido ja existe e atualiza a lista (alteração)
					if(pedido.getIdPedido() > 0) {
						BaseDeDados.atualizaBasePedidos(pedido, listPedidos);
					} else {
						int lastId = listPedidos.get(listPedidos.size()-1).getIdPedido();	//pega o ultimo id de pedidos
						pedido.setIdPedido(lastId+1);										//cria um novo id para o pedido
						listPedidos.add(pedido);											//adiciona na lista
					}
					FileRecorder.gravarPedidos(listPedidos);
					encerrarPedido = true;
					break;
				
				//cancela toda a operação do pedido
				case "4":
					encerrarPedido = true;
					String[] a = {""};
					Index.main(a);
					break;
			}
		} while (!encerrarPedido);
	}
	
	
	
	//gerencia a interação do usuário durante a inserção de itens no pedido
	private static void registrarItens(Pedido pedido) throws IOException {

		String itensImprimir = Printer.imprimir("Itens");	//imprime na tela os itens a escolher
		System.out.println(itensImprimir);
		
		System.out.println("Digite o Código de um item desejado para inserir no pedido: ");
		String codItem = Validador.validaOpUsu(leitorPedido.next(), "codItem");			//lê e valida o item

		//manda registrar o item desejado
		gravarItemPedido(pedido, Integer.parseInt(codItem));
		
		//registra a quantidade desejada
		System.out.println("Digite a quantidade desejada para este item: ");
		String qtdItem = leitorPedido.next();
		
		pedido.setQtdItem(Integer.parseInt(qtdItem));
	}

	
	
	//registra item dentro do pedido ao encontrar na lista de itens
	private static void gravarItemPedido(Pedido pedido, int codItem) throws FileNotFoundException {
		for(Item item : BaseDeDados.getBaseItens()) {
			if(item.getCodigo() == codItem) {
				pedido.addItem(item);
				break;
			}
		}
	}
	
	
	//imprime no console o pedido durante a sua criação (após inclusao de item ou após encerrar)
	public static void mostraPedidoAtual(Pedido pedido) {
		int i = 0;
		double subTotal = 0;
		System.out.println(System.lineSeparator()+"Itens do Pedido atual: "+System.lineSeparator());
		for(Item itemPedido : pedido.getItems()) {
			System.out.println(itemPedido.getCodigo() + " - " + itemPedido.getDesc() + " - " + "Qtd: " + pedido.getQtdItem(i) + " - " + "Unit: R$ " + itemPedido.getPreco() + " - Total: R$ " + (itemPedido.getPreco()*pedido.getQtdItem(i)) + System.lineSeparator());			
			subTotal += (pedido.getQtdItem(i) * itemPedido.getPreco());
			i++;
		}
		System.out.println("Subtotal do Pedido: R$ " + subTotal);
		System.out.println(System.lineSeparator()+"Observação do Pedido: "+(pedido.getObs() == null ? "Nao possui" : pedido.getObs()));
	}

	
	
	//Pega o pedido para atualizar ou excluir
	public static void alterarExcluirPedido(char tipo) throws IOException, InterruptedException {
		//pega a base de pedidos
		List <Pedido> pedidos = BaseDeDados.getBasePedidos();
		
		System.out.println(Printer.imprimir("consultaPedidos"));
		System.out.println("Insira o código do pedido que deseja " + (tipo == 'E' ? "excluir: " : "alterar: "));
		int codPed = Integer.parseInt(Validador.validaOpUsu(leitorPedido.next(), "codPedido"));
		
		switch (tipo) {
		//atualização do pedido
		case 'A':
			Pedido pedido = new Pedido();
			//busca o objeto do pedido para enviar ao editor
			for(Pedido pedidoList : pedidos) {
				if(pedidoList.getIdPedido() == codPed) {
					pedido = pedidoList;
					break;
				}
			}
			editorDePedido(pedido);
			break;
		//exclusão do Pedido
		case 'E':
			for(int i = 0; i <pedidos.size();i++) {
				if(pedidos.get(i).getIdPedido() == codPed) {
					pedidos.remove(i);
					break;
				}
			}
			FileRecorder.gravarPedidos(pedidos);
			break;
		}
	}
}
