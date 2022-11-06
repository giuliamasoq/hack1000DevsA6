package hack1000DevsA6;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.net.URI;

public class Hackathon {      

        public static void main (String [] args) {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request;

                HttpResponse <String> response = null;

                String link = "https://programathor.com.br/jobs";
                
                request = HttpRequest.newBuilder().uri(URI.create(link)).GET().build();

                try {
                        response = client.send(request, HttpResponse.BodyHandlers.ofString());
                } catch (IOException | InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }


                String html = response.body().toString();

                /* Para reduzir a string de HTML */
				
                String idTitulo = "<h3 class=\"text-24 line-height-30\">";
				int posicaoInicial = html.indexOf(idTitulo);
				int tamanho = html.length();
				html = html.substring(posicaoInicial,tamanho);

		       /* Para remover as etiquetas novas do código */
                String labelNew = "<span class=\\\"new-label\\\">NOVA</span>";
                html.replaceAll(labelNew,"");
                
				posicaoInicial = 1;
                /*while (posicaoInicial !=0) {*/
                	posicaoInicial = pegarInformacoes (html, posicaoInicial);
                	html.substring(posicaoInicial);
                /*}*/
        }

        public static int pegarInformacoes (String html, int posicaoInicial) {
        	String idTitulo = "<h3 class=\\\"text-24 line-height-30\\\">";
        	String idFimTitulo = "</h3>";
        	int posicaoFinal;
        	posicaoInicial = html.indexOf(idTitulo);
        	posicaoFinal = html.indexOf(idFimTitulo);
        	posicaoInicial += idTitulo.length()-1;
          	System.out.println("Titulo: " + (html.substring(posicaoInicial,posicaoFinal)));
          	
          	String idEmpresa = "<i class='fa fa-briefcase'></i>";
          	String idFimEmpresa = "</span><span><i class='fas fa-map-marker-alt'>";
          	posicaoInicial = html.indexOf(idEmpresa);
        	posicaoFinal = html.indexOf(idFimEmpresa);
        	posicaoInicial += idEmpresa.length();
          	System.out.println("Empresa: " + (html.substring(posicaoInicial,posicaoFinal)));
          	
          	
          	
			return posicaoFinal;
        }

}
