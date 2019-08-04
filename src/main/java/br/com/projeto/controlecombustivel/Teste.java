/***
 * @author Camila Dos Anjos Vargas
 */

package br.com.projeto.controlecombustivel;

import java.io.IOException;

public class Teste {

	public static void main(String[] args) throws IOException {
//teste		
		ControleDeCombustivel controle = new ControleDeCombustivel();
		
		try {
			controle.controleEntradaSaida("C:/teste/entrada_func_a.txt");
			controle.controleEntradaSaida("C:/teste/entrada_func_b.txt");
			
		} catch (Exception e) {
			System.out.println("Arquivo n�o encontrado! ");
		}
		
		
	}
	
}
