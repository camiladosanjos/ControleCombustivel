/***
 * @author Camila Dos Anjos Vargas
 */

package br.com.projeto.controlecombustivel;

public class Trecho {
	
	private int dia;
	private int codigoVeiculo;
	private String codigoCidade;
	private double kilometragem;

	public Trecho(int dia, int codigoVeiculo, String[] strings) {
		
		this.dia = dia;
		this.codigoVeiculo = codigoVeiculo;
		this.codigoCidade = strings[0];
		this.kilometragem = Double.parseDouble(strings[1]);
		
	}
	
	
	public int getDia() {
	
		return dia;
	
	}

	
	public int getCodigoVeiculo() {
	
		return codigoVeiculo;
	
	}


	public String getCodigoCidade() {
	
		return codigoCidade;
	
	}

	
	public double getKilometragem() {
	
		return kilometragem;
	
	}
	

	@Override
	public String toString() {
	
		return "Trecho [dia=" + dia + ", codigoVeiculo=" + codigoVeiculo + ", codigoCidade=" + codigoCidade
				+ ", kilometragem=" + kilometragem + "]";
	
	}

}
