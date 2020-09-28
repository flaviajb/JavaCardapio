package cybert.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cybert.objetos.Item;
import cybert.objetos.Pedido;

public class Printer {
	
	//Cria as strings para impressão no console.
	public static String imprimir(String op) throws FileNotFoundException {
		String strRetornoImpressao = "";

		switch (op) {
		//menu principal
		case "Principal":
			strRetornoImpressao += (System.lineSeparator()+"---------Gerenciador de Pedidos - Restaurante do mestre Yoda---------"+System.lineSeparator());
			strRetornoImpressao += (System.lineSeparator()+"Informe o código da operação desejada");
			strRetornoImpressao += (System.lineSeparator()+"1 - Incluir um Pedido");
			strRetornoImpressao += (System.lineSeparator()+"2 - Consultar Pedidos realizados");
			strRetornoImpressao += (System.lineSeparator()+"3 - Atualizar um Pedido");
			strRetornoImpressao += (System.lineSeparator()+"4 - Excluir um Pedido");
			strRetornoImpressao += (System.lineSeparator()+"5 - Consultar Cardápio");
			strRetornoImpressao += (System.lineSeparator()+"6 - Inserir itens no cardápio");
			strRetornoImpressao += (System.lineSeparator()+"7 - Atualizar itens no cardápio");
			strRetornoImpressao += (System.lineSeparator()+"8 - Excluir itens no cardápio");
			strRetornoImpressao += (System.lineSeparator()+"9 - Encerrar Sistema");
			break;
		//menu durante o processo de inclusão de um pedido
		case "MenuIncluirPedido":
			strRetornoImpressao += ("Escolha uma opção:");
			strRetornoImpressao += (System.lineSeparator()+"1 - Inserir um item");
			strRetornoImpressao += (System.lineSeparator()+"2 - Inserir observação");
			strRetornoImpressao += (System.lineSeparator()+"3 - Encerrar Pedido");
			strRetornoImpressao += (System.lineSeparator()+"4 - Cancelar operação");
			break;
		//retorna todos os itens
		case "Itens":
			for(Item item : BaseDeDados.getBaseItens()) {
				strRetornoImpressao += "Código: " +  item.getCodigo() + " - " + item.getDesc() + " - R$" + item.getPreco() + System.lineSeparator();
			}
			break;
		case "editorItens":
			strRetornoImpressao += System.lineSeparator()+"Informe o tipo de operação que deseja realizar";
			strRetornoImpressao += System.lineSeparator()+"1 - Alterar Nome";
			strRetornoImpressao += System.lineSeparator()+"2 - Alterar Valor";
			break;
		case "consultaPedidos":
			for(Pedido pedido : BaseDeDados.getBasePedidos()) {
				strRetornoImpressao += System.lineSeparator()+"Pedido Código: " + pedido.getIdPedido()+System.lineSeparator();
				strRetornoImpressao += "Itens: "+System.lineSeparator();
				int i = 0;
				double subtotal = 0;
				for(Item itemPedido : pedido.getItems()) {
					strRetornoImpressao += itemPedido.getCodigo() + " - " + itemPedido.getDesc() + " - " + "Qtd: " + pedido.getQtdItem(i) + " - " + "Unit: R$ " + itemPedido.getPreco() + " - Total: R$ " + (itemPedido.getPreco()*pedido.getQtdItem(i)) + System.lineSeparator();
					subtotal += (itemPedido.getPreco()*pedido.getQtdItem(i));
					i++;
				}
				strRetornoImpressao += System.lineSeparator()+"Obs: "+ pedido.getObs() + System.lineSeparator();
				strRetornoImpressao += System.lineSeparator()+"Subtotal: R$"+subtotal+System.lineSeparator();
				strRetornoImpressao += "----------------------------------------------------------------------------------------"+System.lineSeparator();
			}
			break;
		}
		return strRetornoImpressao;
	}
	
}
