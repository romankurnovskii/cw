package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import app.App.Article;

public class Avito {

	public static void start(String[] args) throws IOException {

		List<Article> articles = new ArrayList<>();

		Document doc = Jsoup.connect("https://avito.ru/samara/telefony/iphone").get(); // System.out.println(doc);

		// Отдельно товары
		Elements elemenFuElements = doc.select("div.item_table-wrapper");

		// Цены
		Elements pricesElements2 = elemenFuElements.select("span[data-marker$=price]");
		for (Element el : pricesElements2) {
			System.out.println(el.tagName() + " " + el.text());
		}

		// Названия
		Elements titlesElements = elemenFuElements.select("h3");
		for (Element el : titlesElements) {
			System.out.println(el.tagName() + " " + el.text() + " " + el.attr("a[href]"));
		}

		// Ссылки
		Elements linksElements = titlesElements.select("a[href]");
		for (Element el : linksElements) {
			System.out.println(el.tagName() + " " + el.attr("abs:href"));
		}

		int u = 5;

		articles.forEach(System.out::println);

	}
	
	
	

}
