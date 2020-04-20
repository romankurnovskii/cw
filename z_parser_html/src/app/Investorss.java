package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import app.App.Article;

public class Investorss {
	
	
	
	
public static void start(String[] args) throws IOException {
		

		
		List<Article> articles = new ArrayList<>();
		
		Document doc = Jsoup.connect("http://investorss.ru/").get();		
		Elements aElements = doc.getElementsByAttributeValue("class", "go");
		
				
		for(Element a: aElements) {
			
			String str1 = a.attr("href");
			String str2 = a.text();
	
			articles.add(new Article(str1, str2));
			
			
		}
		
		articles.forEach(System.out::println);
		
		

	}



}
