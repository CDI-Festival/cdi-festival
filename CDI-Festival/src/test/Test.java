package test;

import fr.cdiFestival.dao.RequestArticle;
import fr.cdiFestival.model.Article;
import fr.cdiFestival.service.Articles;

public class Test {
public static void main(String[] args) {
	
	
	
	RequestArticle re = new RequestArticle();

	
	
	Articles listArticle = re.getArticles();
	
	for (Article article : listArticle) {
		System.out.println(article.getId());
		System.out.println(article.getTitle());
		System.out.println(article.getDate());
		System.out.println( article.getAuthor());
		
			if (article.getContent().length() >= 149) {
				System.out.println(article.getContent().substring(0, 150) + "...");
			}
			else { 
				System.out.println(article.getContent());
			}
	}
	
}
}
