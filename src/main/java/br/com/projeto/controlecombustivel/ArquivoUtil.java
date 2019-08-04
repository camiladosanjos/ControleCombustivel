/***
 * @author Camila Dos Anjos Vargas
 */

package br.com.projeto.controlecombustivel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



public class ArquivoUtil {

	private List<Veiculo> listaVeiculos;
	private List<String[]> listaLinhasArquivo;
	private List<Trecho> listaTrechos;
	private int quantidadeDias;
	private int litrosDisponiveis;
	
/*
 * � respons�vel por capturar as linhas do arquivo (entrada_func_a.txt ou entrada_func_b.txt),
 * identificar as informa��es contidas no mesmo: Ve�culo, Dias, Trecho; 
 */
	public void lerArquivo(String nomeArquivo) throws IOException {

		listaLinhasArquivo = new ArrayList<>();
		listaVeiculos = new ArrayList<Veiculo>();
		listaTrechos = new ArrayList<Trecho>();
		
		int quantidadeDeVeiculos;
		int posicaoDias;

		
		try {

			FileReader frTexto = new FileReader(nomeArquivo);
			
			BufferedReader brArquivo = new BufferedReader(frTexto);

			String strLeitura;

			while ((strLeitura = brArquivo.readLine()) != null) {

				listaLinhasArquivo.add(strLeitura.split("\\s"));
			}

			brArquivo.close();

		} catch (IOException | ArrayIndexOutOfBoundsException e) {

			System.out.println("Arquivo n�o encontrado!");
		}
		
		
		// ser� tratado como arquivo entrada_func_b.txt, caso a condi��o seja True
		litrosDisponiveis = 0;
	
		
		if (listaLinhasArquivo.get(listaLinhasArquivo.size()-2)[0].isEmpty()) {
			
			litrosDisponiveis = Integer.parseInt(listaLinhasArquivo.get(listaLinhasArquivo.size()-1)[0]);

		}
		
		
		quantidadeDeVeiculos = Integer.parseInt(listaLinhasArquivo.get(0)[0]);
		
		for (int i = 1; i <= quantidadeDeVeiculos; i++) {

			listaVeiculos.add(new Veiculo(listaLinhasArquivo.get(i)));
		}

		
		posicaoDias = quantidadeDeVeiculos + 2;
		
		quantidadeDias = Integer.parseInt(listaLinhasArquivo.get(posicaoDias)[0]);

		int codigoPrimeiroVeiculo = Integer.parseInt(listaLinhasArquivo.get(posicaoDias + 1)[0]);

		int dia = 0;
		
		for (int posicaoVeiculo = posicaoDias + 1; posicaoVeiculo < listaLinhasArquivo.size(); posicaoVeiculo = posicaoVeiculo
				+ getQuantidadeDeTrechos(posicaoVeiculo) + 3) {

			int codigoVeiculo = Integer.parseInt(listaLinhasArquivo.get(posicaoVeiculo)[0]);

			if (codigoVeiculo == codigoPrimeiroVeiculo) {
			
				dia++;
			
			}

			for (int j = posicaoVeiculo + 2; j < posicaoVeiculo + 2 + getQuantidadeDeTrechos(posicaoVeiculo); j++) {

				listaTrechos.add(new Trecho(dia, codigoVeiculo, listaLinhasArquivo.get(j)));

			}

		}

	}
	
	
	public int getLitrosDisponiveis() {
		
		return litrosDisponiveis;
		
	}

	
	public List<Veiculo> getListaVeiculos() {
	
		return listaVeiculos;
		
	}


	public List<Trecho> getListaTrechos() {
		
		return listaTrechos;
	
	}

	
	public int getQuantidadeDias() {
		
		return quantidadeDias;
	
	}


	private int getQuantidadeDeTrechos(int posicaoVeiculo) {

		try {
	
			return Integer.parseInt(listaLinhasArquivo.get(posicaoVeiculo + 1)[0]);
		
		} catch(IndexOutOfBoundsException e) {
		
		}
			return 0;
	}

	
	public void escreverArquivo(String nomeArquivoSaida, List<String> relatorio) throws IOException {
		
	    Files.write(Paths.get(nomeArquivoSaida), relatorio);
		
	}

}
