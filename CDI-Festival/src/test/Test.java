package test;

import fr.cdiFestival.dao.RequestArticle;
import fr.cdiFestival.dao.RequestId;
import fr.cdiFestival.model.Article;
import fr.cdiFestival.service.Articles;

public class Test {
public static void main(String[] args) {
	
	RequestId re = new RequestId();
	
	Article article1 = new Article("jo", "23", "titre1", "contenu 1");
	re.update(article1.getId());
	Article article2 = new Article("jo", "23", "titre2", "contenu 2");
	re.update(article2.getId());
	Article article3 = new Article("jo", "23", "titre3", "contenu 3");
	re.update(article3.getId());
	Article article4 = new Article("jo", "23", "titre4", "contenu 4");
	re.update(article4.getId());
	
	System.out.println(article1);
	System.out.println(article2);
	System.out.println(article3);
	System.out.println(article4);
	
	
	
	System.out.println(re.getRefId());
}
}
