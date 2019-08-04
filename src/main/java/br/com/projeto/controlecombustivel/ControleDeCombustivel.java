/***
 * @author Camila Dos Anjos Vargas
 */

package br.com.projeto.controlecombustivel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ControleDeCombustivel {
	
	ArquivoUtil leitura = new ArquivoUtil();
	
	public void controleEntradaSaida(String arquivo) throws IOException {
		
		leitura.lerArquivo(arquivo);
		
		List<Trecho> listaTrechos = leitura.getListaTrechos();
		List<Veiculo> listaVeiculos = leitura.getListaVeiculos();
		List<MediaDia> listaMediaDia = new ArrayList<MediaDia>();
		
		List<String> relatorioA = new ArrayList<String>();
		List<String> relatorioB = new ArrayList<String>();
		
		relatorioA.add(String.valueOf(leitura.getQuantidadeDias()));
		
		for (int i = 1; i <= leitura.getQuantidadeDias(); i++) {
			
			int dia = i;
			Trecho[] listaTrechosDias = listaTrechos.stream().filter(trecho -> trecho.getDia()==dia).toArray(Trecho[]::new);
			
			for (Veiculo veiculo : listaVeiculos) {
											
				Trecho[] listaTrechosVeiculos = Arrays.asList(listaTrechosDias).stream().filter(trecho -> trecho.getCodigoVeiculo()==veiculo.getCodigoVeiculo()).toArray(Trecho[]::new);
			
				double dSomaKilometragem = Arrays.asList(listaTrechosVeiculos).stream().mapToDouble(trecho -> trecho.getKilometragem()).sum();
				double dMediaDia = dSomaKilometragem / veiculo.getKilometroPorLitro();
				
				listaMediaDia.add(new MediaDia(dia, dMediaDia));
										 	
				relatorioA.add(veiculo.getCodigoVeiculo() + " " + String.format("%.2f", dMediaDia).replace(",", "."));
				
			}
			relatorioA.add(" ");
		}
		
		double dConsumoTotal = listaMediaDia.stream().mapToDouble(mediaDia -> mediaDia.getdMediaDia()).sum();
		double dEstoque = leitura.getLitrosDisponiveis() - dConsumoTotal;
		if(dEstoque < 0){
			dEstoque = 0;
		}
			
		relatorioB.add(String.format("%.2f", dEstoque).replace(",", "."));
		relatorioB.add(" ");
		
		dEstoque = leitura.getLitrosDisponiveis();
		
		for (int i = 1; i <= leitura.getQuantidadeDias(); i++){
			int dia = i;
			MediaDia[] listaMediaDiaTotal = listaMediaDia.stream().filter(mediaDia -> mediaDia.getDia()==dia).toArray(MediaDia[]::new);
			dConsumoTotal = Arrays.asList(listaMediaDiaTotal).stream().mapToDouble(mediaDia -> mediaDia.getdMediaDia()).sum();
			
			dEstoque -= dConsumoTotal;
			if(dEstoque < 0){
				dEstoque = 0;
			}
				
			relatorioB.add(String.format("%.2f", dEstoque).replace(",", "."));
		}
		
		leitura.escreverArquivo(arquivo.split("\\.")[0] + "_relatorioA.txt", relatorioA);
		leitura.escreverArquivo(arquivo.split("\\.")[0] + "_relatorioB.txt", relatorioB);
		
	}
	
}
