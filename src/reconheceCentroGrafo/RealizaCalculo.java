/*
 *      DATA: 21/08/2015
 *   AUTORES: ROBERTO BENTO, CAMILA MARTINS
 * DESCRICAO: CLASSE QUE REALIZA A CONSULTA DE RECONHECIMENTO DOS GRAFOS UTILIZANDO AS SEGUINTE FORMAS:
 * 		- LISTA DE ADJACÊNCIA
 * 		- MATRIZ DE ADJACÊNCIA
 * 		- MATRIZ DE INCIDÊNCIA
 * 		- LISTA DE ARESTAS
*/

package reconheceCentroGrafo;

import java.util.ArrayList;

public class RealizaCalculo {

	
	Integer verticeMaiorTotal=99;	
	
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
			
			Integer[][] matrizAdjacencia = calculaMatrizAdjacencia(vertices, arestas, valores, valorado, dirigido);
			
			//ATRIBUI AO CAMPO O RESULTADO DA CONSULTA
			Tela.lbResultado1.setText(MostraResultado(CalculaCentroGrafo(aplicandoFloydWarshall(matrizAdjacencia), vertices), vertices));
		}

	// FUNÇÃO QUE APLICA O ALGORITMO DE FLOYD-WARSHALL
	public Integer[][] aplicandoFloydWarshall(Integer[][] matrizAdj){
		for (int i = 0; i < matrizAdj.length; i++) {
			for (int j = 0; j < matrizAdj.length; j++) {
				if ((matrizAdj[i][j]==0)&&(i!=j)){
					matrizAdj[i][j]=999;
				}
			}
		}
		Integer t = matrizAdj.length;
		Integer[][] matrizDistancia = new Integer[matrizAdj.length][matrizAdj.length]; 
		matrizDistancia = transfereDadosMatriz(matrizDistancia,matrizAdj);
		
		for (int k = 0; k < t; k++) {
			for (int i = 0; i < t; i++) {
				for (int j = 0; j < t; j++) {
					matrizDistancia[i][j] = valorMinimo(matrizDistancia[i][j], 
							(matrizDistancia[i][k]+matrizDistancia[k][j]));
					System.out.println("Valor em "+i+","+j+" -> "+matrizDistancia[i][j]);
				}
			}
			
		}
		return matrizDistancia;
	}
	
	// FUNÇÃO QUE RETORNA O MENOR VALOR REPASSADO NOS PARÂMETROS
	public int valorMinimo(Integer a, Integer b){
		if (a<b)
			return a;
		else
			return b;
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
								if(valorado){ //TRATA SE I GRAFO É VALORADO									
									resultado[i][j]=valores.get(j);
								}else
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
	//RECEBE A MATRIZ DE DISTÂNCIA E CALCULA O CENTRO O GRAFO
	public ArrayList<Integer> CalculaCentroGrafo(Integer[][] matrizDistancia, ArrayList<String> vertices){
		Integer verticeMaiorLinha=0;
		ArrayList<Integer> verticesCentrais = new ArrayList<Integer>();
		
		for (int i = 0; i < matrizDistancia.length; i++) {
			verticeMaiorLinha=0;
			for (int j = 0; j < matrizDistancia.length; j++) {
				if(matrizDistancia[i][j] > verticeMaiorLinha){
					verticeMaiorLinha = matrizDistancia[i][j];
				}
			}			
			if(verticeMaiorLinha < verticeMaiorTotal){
				System.out.println("To limpando o array e add "+i);
				verticesCentrais.clear();
				verticesCentrais.add(i);
				verticeMaiorTotal=verticeMaiorLinha;
			}else if (verticeMaiorLinha == verticeMaiorTotal){
				System.out.println("To adicionando "+i);
				verticesCentrais.add(i);
			}			
		}
		return verticesCentrais;
	}
	
	//AJUSTA O RESULTADO DO CENTRO DO GRAFO PARA MOSTRAR NA TELA
	public String MostraResultado(ArrayList<Integer> verticesCentrais,ArrayList<String> vertices){
		String resultadoFinal=" NÚMERO DE VÉRTICES CENTRAIS: "+verticesCentrais.size()+"\n\n";
		resultadoFinal+="VALOR DO CENTRO DO GRAFO: "+verticeMaiorTotal+"\n\n";
		for (int i = 0; i < verticesCentrais.size(); i++) {
			resultadoFinal+=verticesCentrais.get(i)+" -> VÉRTICE "+retornaNomeVetor(verticesCentrais.get(i), vertices)+"\n";
		}				
		return resultadoFinal;
	}
	
	//CALCULA A MATRIZ DE DISTÂNCIA PARA CALCULAR O CENTRO DO GRAFO
//	public Integer[][] MontaMatrizDistancia(ArrayList<String>vertices,ArrayList<ArrayList<String>> listaAdjacencia){
//		
//		Integer[][] matrizDistancia = new Integer[vertices.size()][vertices.size()];
//		
//		for (int i = 0; i < matrizDistancia.length; i++) {
//			for (int j = 0; j < matrizDistancia.length; j++) {								
//				matrizDistancia[i][j] = BuscaCaminhoDistancia(vertices, i, j, listaAdjacencia);				
//			}			
//		}		
//		return matrizDistancia;
//	}

	//RETORNA A POSICAO DE UM VETOR DO GRAFO
//	public Integer retornaPosicaoVetor(String vertice, ArrayList<String> vertices){				
//		for (int i = 0; i < vertices.size(); i++) {
//			if(vertices.get(i).equalsIgnoreCase(vertice)){				
//				return i; 
//			}			
//		}				
//		return null;
//	}
	//RETORNO O NOME DE UMA POSICAO DO VETOR
	public String retornaNomeVetor(Integer vertice, ArrayList<String> vertices){				
		for (int i = 0; i < vertices.size(); i++) {
			if(vertices.get(i).equalsIgnoreCase(vertices.get(vertice))){				
				return vertices.get(i); 
			}			
		}				
		return null;
	}
	//CALCULA A DISTÂNCIA DE DOIS PONTOS DO GRAFO
//	public Integer BuscaCaminhoDistancia(ArrayList<String> vertices, Integer linha,Integer coluna,ArrayList<ArrayList<String>> listaAdjacencia){
//		ArrayList<Integer> proxPonteiros = new ArrayList<Integer>();
//		ArrayList<Integer> proxIniciais = new ArrayList<Integer>();
//		proxIniciais.clear();
//		proxPonteiros.clear();
//		int contDistancia=0;
//		
//		if(vertices.get(linha).equalsIgnoreCase(vertices.get(coluna))){
//			return 0;			
//		}else{
//			boolean achei=false;
//			proxIniciais.add(linha);			
//				while(!achei){
//					contDistancia++;
//					System.out.println("cont distancia -> "+contDistancia);
//					for (int l = 0; l < proxIniciais.size(); l++) {													
//						for (int k = 0; k < listaAdjacencia.get(proxIniciais.get(l)).size(); k++) {					
//							if(listaAdjacencia.get(proxIniciais.get(l)).get(k).equalsIgnoreCase(vertices.get(coluna))){
//								System.out.println("Achei é TRUE!");
//								achei=true;
//								return contDistancia;
//							}else{
//								proxPonteiros.add(retornaPosicaoVetor(listaAdjacencia.get(proxIniciais.get(l)).get(k), vertices));
//							}
//						}
//					}
//					proxIniciais.clear();
//					proxIniciais = transfereDados(proxIniciais, proxPonteiros);
//					proxPonteiros.clear();
//				}
//			}
//		return null;
//	}
//	
	//TRANSFERE OS DADOS DE UMA MATRIZ PARA OUTRA
	public Integer[][] transfereDadosMatriz(Integer[][] iniciais,Integer[][] ponteiros){
		for (int i = 0; i < ponteiros.length; i++) {
			for (int j = 0; j < ponteiros.length; j++) {
				iniciais[i][j] = ponteiros[i][j];
			}
		}
		return iniciais;
	}
	
	//TRANSFERE OS DADOS DE ANALISE DE UM VETOR PARA O OUTRO INDICANDO QUE O CAMINHO AUMENTOU EM 1
//		public ArrayList<Integer> transfereDados(ArrayList<Integer> iniciais,ArrayList<Integer> ponteiros){
//			for (int i = 0; i < ponteiros.size(); i++) {
//				iniciais.add(ponteiros.get(i));
//			}
//			return iniciais;
//		}
}
