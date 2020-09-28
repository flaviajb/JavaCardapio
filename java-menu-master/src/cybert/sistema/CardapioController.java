package cybert.sistema;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import cybert.objetos.Item;
import cybert.objetos.Pedido;
import cybert.util.BaseDeDados;
import cybert.util.FileRecorder;
import cybert.util.Printer;
import cybert.util.Validador;

public class CardapioController {
	
	static Scanner leitorItem = new Scanner(System.in);
	
	public static void criarItem() throws IOException {
		List <Item> listItens = BaseDeDados.getBaseItens();
		
		//recebe o objeto do item cardápio (novo item ou item ja existente)
		Item item = new Item();

		System.out.println(System.lineSeparator()+"Insira o nome do Item: ");
		item.setDesc(leitorItem.nextLine());

		System.out.println(System.lineSeparator()+"Insira o preço para este item em reais (apenas numeros): ");
		item.setPreco(Double.parseDouble(leitorItem.nextLine()));

		int lastId = listItens.get(listItens.size()-1).getCodigo();		//pega o ultimo id de itens
		item.setCodigo(lastId+1);										//cria um novo id para o item
		listItens.add(item);											//grava o item na lista
		FileRecorder.gravarItens(listItens);							//envia para gravação no cardápio
	}
	
	public static void alterarExcluirItem(char tipo) throws NumberFormatException, IOException {
		//pega a base de pedidos
		List <Item> listItens = BaseDeDados.getBaseItens();

		System.out.println(Printer.imprimir("Itens"));
		System.out.println("Insira o código do item que deseja " + (tipo == 'E' ? "excluir: " : "alterar: "));
		int codItem = Integer.parseInt(Validador.validaOpUsu(leitorItem.next(), "codItem"));

		switch(tipo) {
			//atualização
			case 'A':
				Item item = new Item();
				System.out.println(Printer.imprimir("editorItens"));
				int opUsu = Integer.parseInt(Validador.validaOpUsu(leitorItem.next(), "opCardapio"));
				for(Item itemCardapio : listItens) {
					if(codItem == itemCardapio.getCodigo()) {
						item = itemCardapio;
					}
				}
				if(opUsu == 1) {
					//alteração de nome
					System.out.println(System.lineSeparator()+"Informe o nome atualizado abaixo: ");
					item.setDesc(leitorItem.next());
				} else {
					System.out.println(System.lineSeparator()+"Informe o valor atualizado abaixo em reais (apenas numeros): ");
					item.setPreco(Double.parseDouble(leitorItem.next()));
				}
				BaseDeDados.atualizaBaseItens(item, listItens);
				break;
			//exclusão
			case 'E':
				for(var i=0;i<listItens.size();i++) {
					if(listItens.get(i).getCodigo() == codItem) {
						listItens.remove(i);
					}
				}
				break;
		}
		//envia para gravação no cardápio
		FileRecorder.gravarItens(listItens);
	}
}
