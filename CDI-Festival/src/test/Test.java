package test;

import fr.cdiFestival.dao.article.RequestArticle;
import fr.cdiFestival.dao.article.RequestId;
import fr.cdiFestival.model.Article;
import fr.cdiFestival.service.Articles;

public class Test {
public static void main(String[] args) {
	
	RequestArticle re = new RequestArticle();
	
	Article article1 = new Article(48, "Jonathan", "23/11/2016", "Le titre updated", "Contenu update");

	System.out.println(re.upDate(article1));
	
	System.out.println(re.getArticles());
	

}
}
