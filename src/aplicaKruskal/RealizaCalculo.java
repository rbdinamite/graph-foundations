/*
 *      DATA: 24/11/2015
 *   AUTORES: ROBERTO BENTO, CAMILA MARTINS
 * DESCRICAO: SISTEMA DE CÁLCULO DE ÁREA DE COBERTURA DE UM REPRESENTANTE DE VENDAS
 * 		
*/

package aplicaKruskal;

import java.util.ArrayList;

public class RealizaCalculo {

	
	ArrayList<String> arestasSelecionadas = new ArrayList<String>();
	Double distanciaTotal = 0.0;
	
	// RECEBE OS VALORES ADVINDOS DA TELA PRINCIPAL
		public RealizaCalculo(String Svertices,
				String Sarestas, String Svalores) {
		
			System.out.println("###### INICIANDO PROGRAMA DE CALCULO ######");
			// INICIALIZA AS VARIÁVEIS NECESSÁRIAS PARA A CONSULTA
			ArrayList<String> vertices = new ArrayList<String>();
			ArrayList<String> arestas = new ArrayList<String>();
			ArrayList<Integer> valores = new ArrayList<Integer>();
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
			aux= Svalores.split(",");
			System.out.println("Valores");
			for (int i = 0; i < aux.length; i++) {
				valores.add(Integer.parseInt(aux[i]));
				System.out.println(Integer.parseInt(aux[i]));
			}
			// APLICA O ALGORITMO DE KRUSKAL PARA O CALCULO DA AREA DE COBERTURA
			aplicandoKruskal(vertices, arestas, valores);
			// MOSTRA O RESULTADO NA TELA
			Tela.txtResultado.setText(mostraResultado());
			Tela.lblDistancia.setText("Distância total: "+distanciaTotal);

		}
		
		// FUNCAO QUE APLICA O ALGORITMO DE KRUSKAL PARA AREA DE COBERTURA
		public void aplicandoKruskal(ArrayList<String> vertices,ArrayList<String> arestas,ArrayList<Integer> valores){
			System.out.println("Iniciando algoritmo de Kruskal ...");
			// CRIA UM ARRAY COM UMA LISTA PARA CADA VERTICE
			ArrayList<ArrayList<String>> listaArestas = new ArrayList<ArrayList<String>>();
			// ADICIONA A PRORIA VERTICE EM CADA LISTA
			for (int i = 0; i < vertices.size(); i++) {
				System.out.println("Adicionando ["+vertices.get(i)+"] na sua lista de conjuntos");
				listaArestas.add(new ArrayList<String>());
				listaArestas.get(i).add(vertices.get(i));
			}
			// CHAMA A FUNCAO QUE ORDENA A LISTA DE ARESTAS PELO PESO
			System.out.println("Vou ordenar as arestas ...");
			arestas = ordenaArestas(arestas, valores);
			// REALIZA UM LOOP PARA CADA ARESTA
			System.out.println("Vou ordenar as arestas ... OK");
			System.out.println("Vai iniciar o loop pelas arestas ...");
			for (int i = 0; i < arestas.size(); i++) {
				// VERIFICA DE AS DUAS PARTES DA ARESTA PERTENCEM AO MESMO CONJUNTO
				if(!verificaConjutos(listaArestas, arestas.get(i).substring(0,1),arestas.get(i).substring(1,2))) {
					System.out.println("Vertices ["+arestas.get(i).substring(0,1)+"] e ["+arestas.get(i).substring(1,2)+
							"] nao pertencem ao mesmo conjunto");
					System.out.println("Vai realizar a união");
					// ADICIONA A VERTICE A MATRIZ DE RETORNO
					arestasSelecionadas.add(arestas.get(i));
					// ACRESCENTA A VARIÁVEL A DISTÂNCIA DA ARESTA SELECIONADA
					distanciaTotal += valores.get(i);
					// REALIZA A UNIAO DAS DUAS PARTES NA MESMA LISTA
					atualizaConjuntos(listaArestas,arestas.get(i).substring(0,1),arestas.get(i).substring(1,2));
				}
				
				if (arestasSelecionadas.size() == (vertices.size()-1))
					break;
			}
			System.out.println("Acabou. Arestas selecionadas: ");
			for (int i = 0; i < arestasSelecionadas.size(); i++) {
				System.out.println(arestasSelecionadas.get(i));
			}
		}
		
		// VERIFICA SE AS DUAS PARTES DA ARESTAS PERTENCEM AO MESMO CONJUNTO
		public boolean verificaConjutos(ArrayList<ArrayList<String>> conjuntos, String conjA, String conjB) {
			System.out.println("# INICIANDO VERIFICAÇÃO");
			Integer arrayA=999,arrayB=998;
			for (int i = 0; i < conjuntos.size(); i++) {
				arrayA=999;arrayB=998;
				for (int j = 0; j < conjuntos.get(i).size(); j++) {
					System.out.println(conjuntos.get(i).get(j));
					if (conjuntos.get(i).get(j).equalsIgnoreCase(conjA)) {
						System.out.println("Entrou no if A");
						System.out.println("# ACHOU O CONJUNTO DO ["+conjA+"] -> "+i);
						arrayA = i;
					}
					if (conjuntos.get(i).get(j).equalsIgnoreCase(conjB)) {
						System.out.println("Entrou no if B");
						System.out.println("# ACHOU O CONJUNTO DO ["+conjB+"] -> "+i);
						arrayB = i;
					}
				}
				if (arrayA == arrayB) {
					System.out.println("Vai retornar TRUE");
					return true;
				}
			}
			System.out.println("Vai retornar FALSE");
			return false;
		}
		
		// REALIZA A UNIAO DOS OBJETOS NO MESMO CONJUNTOS
		public ArrayList<ArrayList<String>> atualizaConjuntos(ArrayList<ArrayList<String>> conjuntos,String conjA,String conjB) {
			Integer arrayA=999,arrayB=999;
			
			// ENCONTRA O CONJUNTOS DAS DUAS PARTES
			for (int i = 0; i < conjuntos.size(); i++) {
				for (int j = 0; j < conjuntos.get(i).size(); j++) {
					if (conjuntos.get(i).get(j).equalsIgnoreCase(conjA)) {
						arrayA = i;
					}
					if (conjuntos.get(i).get(j).equalsIgnoreCase(conjB)) {
						arrayB = i;
					}
				}
				if ((arrayA != 999)&&(arrayB != 999))
					break;
			}
			
			// REALIZA A UNIAO DAS PARTES
			System.out.println("# Vai realizar  união ...");
			System.out.println("# Tamanhos dos conjuntos: ["+conjuntos.get(arrayA).size()+"] e ["+conjuntos.get(arrayB).size()+"]");
			Integer tamanhoArray = conjuntos.get(arrayB).size();
			for (int i = 0; i < tamanhoArray; i++) {
				System.out.println("## Indice -> "+i);
				System.out.println(conjuntos.get(arrayB).size());
				System.out.println("## Vou adicionar -> "+conjuntos.get(arrayB).get(i));
				conjuntos.get(arrayA).add(conjuntos.get(arrayB).get(i));
			}
			// LIMPA A LISTA RESTANTE
			conjuntos.get(arrayB).clear();

			System.out.println("# Tamanhos dos conjutos finais: ["+conjuntos.get(arrayA).size()+"] e ["+conjuntos.get(arrayB).size()+"]");
			return conjuntos;
		}
		
		
		// A FUNCAO QUE ORDENA A LISTA DE ARESTAS PELO PESO
		public ArrayList<String> ordenaArestas(ArrayList<String> arestas,ArrayList<Integer> valores){
			String auxiliar = "";
			Integer auxiliar2;
			char indice = 0;
			
			System.out.println("Vai iniciar o loop ...");
			while (indice < arestas.size()) {
				for (char i = (char) (indice+1); i < arestas.size(); i++) {
					if (valores.get(i)<valores.get(indice)) {
						System.out.println("Vai trocar ["+arestas.get(indice)+"] por ["+arestas.get(i)+"]");
						System.out.println("Seus valores ["+valores.get(indice)+"] por ["+valores.get(i)+"]");
						auxiliar = arestas.get(indice);
						auxiliar2 = valores.get(indice);
						arestas.set(indice, arestas.get(i));
						valores.set(indice, valores.get(i));
						arestas.set(i, auxiliar);
						valores.set(i, auxiliar2);
						System.out.println("Troca realizada. Ficou assim: ["+arestas.get(indice)+"] e ["+arestas.get(i)+"]");
						System.out.println("Troca realizada. Valores: ["+valores.get(indice)+"] e ["+valores.get(i)+"]");
						System.out.println("-----------------------");
					}
				}
				indice++;
			}
			System.out.println("Arestas ordenadas : ");
			for (int i = 0; i < arestas.size(); i++) {
				System.out.println(arestas.get(i)+" Valor -> "+valores.get(i));
			}
			return arestas;
		}
		
		// FUNCAO QUE ADAPTA O RESULTADO PARA MOSTRAR NA TELA
		public String mostraResultado() {
			String resultadoFinal = "";
			
			for (int i = 0; i < arestasSelecionadas.size(); i++) {
				resultadoFinal += "("+arestasSelecionadas.get(i).substring(0,1)+","+arestasSelecionadas.get(i).substring(1,2)+") \n";
			}
			return resultadoFinal;
		}
	}



