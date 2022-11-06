package hack1000DevsA6;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.net.URI;

public class Hackathon {      

        public static void main (String [] args) {

                String link = "https://programathor.com.br/jobs";
                boolean naoTemLink = false;
                while (naoTemLink==false) {
                        link = acessarPagina(link);
                        naoTemLink = link.equals("");
                }
        
        }

        public static String acessarPagina (String link) {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request;

                HttpResponse <String> response = null;
                request = HttpRequest.newBuilder().uri(URI.create(link)).GET().build();

                try {
                        response = client.send(request, HttpResponse.BodyHandlers.ofString());
                } catch (IOException | InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }


                String html = response.body().toString();

                /* Para reduzir a string de HTML */
				
                String idTitulo = "<div class=\"col-md-9\">";
				int posicaoInicial = html.indexOf(idTitulo);
				html = html.substring(posicaoInicial);

		/* Para remover as etiquetas novas do código */
                String labelNew = "<span class=\"new-label\">NOVA</span>";
                html=html.replaceAll(labelNew,"");

		posicaoInicial = 0;
                while (posicaoInicial !=-1) {
                	posicaoInicial = pegarInformacoes (html, posicaoInicial);
                        if(posicaoInicial!=-1){
                	html = html.substring(posicaoInicial);
                        }
                }
                boolean temVencida = false;
                while (temVencida == false) {              
                        
                        return link;
                }
        }


        public static int pegarInformacoes (String html, int posicaoInicial) {
        	
                String idLinkVaga = "<a href=\"";
                String idFimLinkVaga = "<div class=\"row\">";
                posicaoInicial = html.indexOf(idLinkVaga);
                int posicaoFinal = html.indexOf(idFimLinkVaga);
                posicaoInicial += idLinkVaga.length();     
                String link = (html.substring(posicaoInicial,(posicaoFinal)));
                idFimLinkVaga = "\">";
                posicaoFinal = link.indexOf(idFimLinkVaga);
                link = link.substring(0, posicaoFinal);   

        	String idTitulo = "<h3 class=\"text-24 line-height-30\">";
        	String idFimTitulo = "</h3>";
        	posicaoInicial = html.indexOf(idTitulo);
        	posicaoFinal = html.indexOf(idFimTitulo);
        	posicaoInicial += idTitulo.length();
          	System.out.println("Titulo: " + (html.substring(posicaoInicial,posicaoFinal)));
          	
          	String idEmpresa = "<i class='fa fa-briefcase'></i>";
          	String idFimEmpresa = "</span><span><i class='fas fa-map-marker-alt'></i>";
          	posicaoInicial = html.indexOf(idEmpresa);
        	posicaoFinal = html.indexOf(idFimEmpresa);
        	posicaoInicial += idEmpresa.length();
          	System.out.println("Empresa: " + (html.substring(posicaoInicial,posicaoFinal)));

                String idCidade = "<i class='fas fa-map-marker-alt'></i>";
                String idFimCidade = "</span><span><i class='fa fa-building'>";
                posicaoInicial = html.indexOf(idCidade);
        	posicaoFinal = html.indexOf(idFimCidade);
        	posicaoInicial += idCidade.length();
                String cidade = html.substring(posicaoInicial,posicaoFinal);
                if (cidade.equals("Remoto")) {
                        System.out.println("Cidade: Não se aplica");
                        System.out.println("Tipo de Vaga: Remoto");
                } else {
                        System.out.println("Cidade: " + cidade);
                        System.out.println("Tipo de Vaga: Presencial");
                }
                
                String idSalario = "<i class='far fa-money-bill-alt'></i>";
                String idFimSalario = "</span><span><i class='far fa-chart-bar'>";
                posicaoInicial = html.indexOf(idSalario);
                posicaoFinal = html.indexOf(idFimSalario);
                posicaoInicial += idSalario.length();
                if (posicaoInicial>posicaoFinal) {
                        System.out.println("Salario: Não informado");
                } else {
                        System.out.println("Salario: " + (html.substring(posicaoInicial,posicaoFinal)));
                }

                System.out.println("Link: https://programathor.com.br" + link);

                posicaoFinal += idFimSalario.length();
          	System.out.println();
                String existeMaisVagaNaPagina = html.substring(posicaoFinal);
                int testeMaisVaga = existeMaisVagaNaPagina.indexOf(idTitulo);
                if (testeMaisVaga != -1) {
			return posicaoFinal;
                } else {
                        return -1;
                }
        }

}
