/*
 *      DATA: 21/08/2015
 *   AUTORES: ROBERTO BENTO, CAMILA MARTINS
 * DESCRICAO: CLASSE QUE REALIZA A CONSULTA DE RECONHECIMENTO DOS GRAFOS UTILIZANDO AS SEGUINTE FORMAS:
 * 		- LISTA DE ADJACÊNCIA
 * 		- MATRIZ DE ADJACÊNCIA
 * 		- MATRIZ DE INCIDÊNCIA
 * 		- LISTA DE ARESTAS
*/

package reconheceGrafo;

import java.util.ArrayList;


public class RealizaCalculo {

	// RECEBE OS VALORES ADVINDOS DA TELA PRINCIPAL
	public RealizaCalculo(String Svertices,
			String Sarestas, String Svalores,
			boolean Svalorado, boolean Sdirigido) {
	
		// INICIALIZA AS VARIÁVEIS NECESSÁRIAS PARA A CONSULTA
		ArrayList<String> vertices = new ArrayList<String>();
		ArrayList<String> arestas = new ArrayList<String>();
		ArrayList<Integer> valores = new ArrayList<Integer>();
		boolean valorado=false,dirigido=false;
		Sarestas = ","+Sarestas;
		
		// AJUSTA AS VERTICES PARA ADICIONAR NA LISTA
		String[] aux= Svertices.split(",");
		System.out.println("Vértices:");
		for (int i = 0; i < aux.length; i++) {
			vertices.add(aux[i]);
			System.out.println(aux[i]);
		}
		// AJUSTA AS ARESTAS PARA ADICIONAR NA LISTA
		aux = Sarestas.split("\\)");
		System.out.println("Arestas");
		for (int i = 0; i < aux.length; i++) {
			arestas.add(aux[i].substring(2,3)+""+aux[i].substring(4,5));
			System.out.println(aux[i].substring(2,3)+""+aux[i].substring(4,5));
		}
		// AJUSTA OS VALORES PARA ADICIONAR NA LISTA
		if(Svalorado){
		aux= Svalores.split(",");
		System.out.println("Valores");
		for (int i = 0; i < aux.length; i++) {
			valores.add(Integer.parseInt(aux[i]));
			System.out.println(Integer.parseInt(aux[i]));
		}
		}else{
			valores = null;
		}
		// ATRIBUI AS VARIAVEIS DE VALORAÇÃO E DIREÇÃO
		valorado = Svalorado;
		dirigido = Sdirigido;
		
		Tela.lbResultado1.setText(calculaListaAresta(vertices, arestas, dirigido));
		Tela.lbResultado2.setText(ajustaResultado(vertices, arestas, calculaMatrizAdjacencia(vertices, arestas, valores, valorado, dirigido), 2));
		Tela.lbResultado3.setText(ajustaResultado(vertices, arestas, calculaMatrizIncidencia(vertices, arestas, valores, valorado, dirigido), 3));
		Tela.lbResultado4.setText(ajustaResultado(vertices, arestas, null, 4));
	}

	// FUNCAO QUE RETORNA A MATRIZ DE ADJACENCIA
	public Integer[][] calculaMatrizAdjacencia(ArrayList<String> vertices,
			ArrayList<String> arestas, ArrayList<Integer> valores, boolean valorado, boolean dirigido){
		Integer[][] resultado = new Integer[vertices.size()][vertices.size()]; // CRIA A MATRIZ QUE SERA RETORNADA
		for (int i = 0; i < resultado.length; i++) { // LOOP NA MATRIZ DE VERTICES
			for (int j = 0; j < resultado.length; j++) {
				if(dirigido){ //MODO DE CONSULTA PARA O GRAFO DIRIGIDO
					String var = vertices.get(i)+""+vertices.get(j);
					for (int k = 0; k < arestas.size(); k++) {// LOOP NA LISTA DE ARESTAS
						if(var.equalsIgnoreCase(arestas.get(k))){
							if(valorado) //TRATA SE I GRAFO É VALORADO
							resultado[i][j]=valores.get(j);
							else
							resultado[i][j]=1;
						k+=9999;
						}else{
							resultado[i][j]=0;
						}	
					}					
				}else{//MODO DE CONSULTA PARA O GRAFO NÃO-DIRIGIDO
					String var = vertices.get(i)+""+vertices.get(j);
					for (int k = 0; k < arestas.size(); k++) {// LOOP NA LISTA DE ARESTAS						
						if((var.equalsIgnoreCase(arestas.get(k)))||(var.equalsIgnoreCase(arestas.get(k).substring(1,2)+""+arestas.get(k).substring(0,1)))){
							System.out.println("TEM VALOR!");
							if(valorado)
							resultado[i][j]=valores.get(j);
							else
							resultado[i][j]=1;
						k+=9999;
						}else{
							resultado[i][j]=0;
						}
					}
				}
			}
			
		}
		//RETORNA A MATRIZ RESULTADO DA CONSULTA
		return resultado;
		
	}
	
	// FUNCAO QUE RETORNA A MATRIZ DE INCIDÊNCIA
	public Integer[][] calculaMatrizIncidencia(ArrayList<String> vertices,
			ArrayList<String> arestas, ArrayList<Integer> valores, boolean valorado, boolean dirigido){
			Integer[][] resultado = new Integer[vertices.size()][arestas.size()]; // CRIA A MATRIZ QUE SERA RETORNADA			
			for (int i = 0; i < vertices.size(); i++) { // LOOP NA MATRIZ DE VERTICES
				for (int j = 0; j < arestas.size(); j++) {
					if(dirigido){ //MODO DE CONSULTA PARA O GRAFO DIRIGIDO
						if(vertices.get(i).equalsIgnoreCase(arestas.get(j).substring(0,1))){
							if(valorado)
							resultado[i][j] = valores.get(j);
							else
							resultado[i][j] = 1;	
						}else if(vertices.get(i).equalsIgnoreCase(arestas.get(j).substring(1,2))){
							if(valorado)
							resultado[i][j] = ((valores.get(j))*(-1));
							else
							resultado[i][j] = -1;
						}else{
							resultado[i][j] = 0;
						}						
					}else{//MODO DE CONSULTA PARA O GRAFO NÃO-DIRIGIDO													
							if((vertices.get(i).equalsIgnoreCase(arestas.get(j).substring(0,1)))||
									(vertices.get(i).equalsIgnoreCase(arestas.get(j).substring(1,2)))){
								if(valorado)
								resultado[i][j]=valores.get(j);
								else
								resultado[i][j]=1;							
							}else{
								resultado[i][j]=0;
							}						
					}
					System.out.println("Matriz pos "+i+","+j+" = "+resultado[i][j]);
				}
				
			}
			//RETORNA A MATRIZ RESULTADO DA CONSULTA
			return resultado;
											
		}
	// FUNCAO QUE CALCULA A LISTA DE ARESTAS E JÁ RETORNA O TEXTO IDENTADO		
	public String calculaListaAresta(ArrayList<String> vertices,ArrayList<String> arestas,boolean dirigido){
			String resultadoFinal="\n LISTA DE ADJACÊNCIA \n";
			ArrayList<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();
			for (int i = 0; i < vertices.size(); i++) {
				lista.add(new ArrayList<String>());
				for (int j = 0; j < arestas.size(); j++) {					
					if(dirigido){
						if(vertices.get(i).equalsIgnoreCase(arestas.get(j).substring(0,1))){							
							lista.get(i).add(arestas.get(j).substring(1,2));
						}
					}else{
						if(vertices.get(i).equalsIgnoreCase(arestas.get(j).substring(0,1))){
							lista.get(i).add(arestas.get(j).substring(1,2));
						}else if(vertices.get(i).equalsIgnoreCase(arestas.get(j).substring(1,2))){
								lista.get(i).add(arestas.get(j).substring(0,1));					
						}
					}
				}
				
				resultadoFinal+=vertices.get(i)+" -> ";
				for (int j = 0; j < lista.get(i).size(); j++) {
					if(j>0){
						resultadoFinal+=",";
					}
					resultadoFinal+=lista.get(i).get(j);
				}
				resultadoFinal+="\n";
			}
			
			
			
			return resultadoFinal;
		}
	
	
	/* FUNCAO QUE ADAPTA O RESULTADO DA CONSULTA PARA MOSTRAR NA TELA PRINCIPAL
		   RECEBE A VERIÁVEL tipoConsulta QUE IDENTIFICA O TIPO DA CONSULTA REALIZADA*/
	public String ajustaResultado(ArrayList<String> vertices,
			ArrayList<String> arestas, Integer[][] resultado, Integer tipoConsulta){
			String resultadoFinal="";
			switch (tipoConsulta) {
			case 1:
				
				break;
			case 2:
				resultadoFinal="\n MATRIZ DE ADJACÊNCIA \n";
				resultadoFinal+="| # |";
				for (int i = 0; i < vertices.size(); i++) {
					resultadoFinal+=" "+vertices.get(i)+" |";					
				}
				resultadoFinal+="\n";
				for (int i = 0; i < vertices.size(); i++) {
					resultadoFinal+="| "+vertices.get(i)+" |";
					for (int j = 0; j < resultado.length; j++) {
						resultadoFinal+=" "+resultado[i][j]+" |";
					}					
					resultadoFinal+="\n";
				}				
				break;
			case 3:
				resultadoFinal="\n MATRIZ DE INCIDÊNCIA \n";
				resultadoFinal+="| # |";
				for (int i = 0; i < arestas.size(); i++) {
					resultadoFinal+=" "+arestas.get(i)+" |";					
				}
				resultadoFinal+="\n";
				for (int i = 0; i < vertices.size(); i++) {
					resultadoFinal+="| "+vertices.get(i)+" |";
					for (int j = 0; j < arestas.size(); j++) {
						resultadoFinal+="   "+resultado[i][j]+"  |";
					}					
					resultadoFinal+="\n";
				}
				break;
			case 4:
				resultadoFinal="\n LISTA DE ARESTAS \n";
				String p1="g = (",p2="h = (";
				for (int i = 0; i < arestas.size(); i++) {
					if(i>0){
						p1+=",";p2+=",";
					}
					p1+=arestas.get(i).substring(0,1);
					p2+=arestas.get(i).substring(1,2);
				}
				p1+=")";p2+=")";
				resultadoFinal+=p1+"\n"+p2+"\n";
				break;
			default:
				break;
			}
			System.out.println(resultadoFinal);
			return resultadoFinal;
		}


}
