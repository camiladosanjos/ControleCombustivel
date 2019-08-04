/***
 * @author Camila Dos Anjos Vargas
 */

package br.com.projeto.controlecombustivel;


public class Veiculo {
	
	private int codigoVeiculo;
	private int kilometroPorLitro;
	
	
	public int getCodigoVeiculo() {
	
		return codigoVeiculo;
	
	}

	
	public int getKilometroPorLitro() {
	
		return kilometroPorLitro;
	
	}

	
	public Veiculo(String[] strings) {
		
		this.codigoVeiculo = Integer.parseInt(strings[0]);
		this.kilometroPorLitro = Integer.parseInt(strings[1]) ;

	}
	
}
