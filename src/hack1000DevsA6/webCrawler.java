package hack1000DevsA6;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;

public class webCrawler {
	
/* TCOMENTARIO TESTE COMENTARIO TESTE 0202020No main ele está indicando qual a url e pedindo para acessar*/
/* Passa para a função acessar até 2 níveis (link do link) e um array list vazio*/
/* commit teste*/
	public static void main(String[] args) {
		String url = "https://www.vagas.com.br/vagas-de-desenvolvedor";
		acessar(1, url, new ArrayList<String>());
	}
	
/* A função acessar integra com a função puxarDados*/
	private static void acessar (int level, String url, ArrayList<String> visited) {
		if (level <=2) {
			Document doc = puxarDados(url, visited);
			
			if (doc != null) {
				System.out.print(doc);
				/*for (Element link : doc.select("a[href]")) {
					String next_link = link.absUrl("href");
					if (visited.contains(next_link) == false)  {
						acessar (level++, next_link, visited);
					}
				}*/
			}
		}
	}
	
	private static Document puxarDados(String url, ArrayList<String> v) {
		try {
			Connection con = Jsoup.connect(url);
			Document doc = con.get();
			if (con.response().statusCode() == 200) {
				System.out.println("Link: "+ url);
				System.out.println(doc.title());
				v.add(url);
				return doc;
			}
			return null;
		} catch (IOException e) {
			return null;
		}
	}
}
