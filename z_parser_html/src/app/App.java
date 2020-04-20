package app;

import java.io.IOException;


public class App {

	public static void main(String[] args) throws IOException {
		

		Investorss.start(args);
//		Avito.start(args);
		
		
		
		
		
//		Document doc = Jsoup.connect("https://avito.ru/samara/telefony/iphone").get();  //System.out.println(doc);


		
//		Elements links = doc.select("a[href]");
//        Elements media = doc.select("[src]");
//        Elements imports = doc.select("link[href]");
            
//		for (Element link : links) {
//			System.out.println(link.tagName() + " " + link.attr("abs:href")  + " " +  link.attr("rel"));
//        }
//
//		System.out.println(links.size());
		
		
		
		

		

	}

	
	
	
	
	
	
	static class Article {
		
		private String urlString;
		private String nameString;
		
		
		public Article(String urlString, String nameString) {
			super();
			this.urlString = urlString;
			this.nameString = nameString;
		}
		
		
		public String getUrlString() {
			return urlString;
		}
		public void setUrlString(String urlString) {
			this.urlString = urlString;
		}
		public String getNameString() {
			return nameString;
		}
		public void setNameString(String nameString) {
			this.nameString = nameString;
		}

		
		
		@Override
		public String toString() {
			return "Article [urlString=" + urlString + ", nameString=" + nameString + "]";
		}
		
		
	}
	
	
	
}
