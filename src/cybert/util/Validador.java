package cybert.util;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import cybert.objetos.Item;
import cybert.objetos.Pedido;

public class Validador {
	static Scanner leitor = new Scanner(System.in);

	//valida a opções inseridas pelo usuario nos menus de item e escolha de pedido
	public static String validaOpUsu(String opItem, String tipoValidacao) throws NumberFormatException, FileNotFoundException {
		switch(tipoValidacao) {
		case "codItem":
			List <Item> listItens = BaseDeDados.getBaseItens();
			int i1 = (listItens.size()-1); //pega ultimo codigo da lista
			//valida a entrada de códigos de item
			while(Integer.parseInt(opItem) > listItens.get(i1).getCodigo() || Integer.parseInt(opItem) < 1){
				System.out.println(System.lineSeparator() + Printer.imprimir("Itens") + "Opção Inválida! Digite o Código do item desejado: ");
				opItem = leitor.next();
			}
			break;
		case "opCardapio":
			while(Integer.parseInt(opItem) != 1 && Integer.parseInt(opItem) != 2) {
				System.out.println("Opção Inválida! "+System.lineSeparator()+Printer.imprimir("editorItens")+System.lineSeparator());
				opItem = leitor.next();
			}
			break;
		case "codPedido":
			List <Pedido> listPedidos = BaseDeDados.getBasePedidos();
			int i2 = (listPedidos.size()-1); //pega ultimo codigo da lista
			//valida a entrada de códigos de item
			while(Integer.parseInt(opItem) > listPedidos.get(i2).getIdPedido() || Integer.parseInt(opItem) < 1){
				System.out.println(System.lineSeparator() + Printer.imprimir("consultaPedidos") + "Opção Inválida! Digite o Código do pedido desejado: ");
				opItem = leitor.next();
			}
			break;
		}
		return opItem;
	}	
}
