package test;

import fr.cdiFestival.dao.RequestArticle;
import fr.cdiFestival.model.Article;

public class Test {
public static void main(String[] args) {
	
//	Article a = new Article(2, "jo", "21/11/2016", "Le titre", "L'article");
	
	
	RequestArticle re = new RequestArticle();
//	re.add(a);
//	System.out.println(re.getArticle(1));
	System.out.println("-----------Coupure------------");
	System.out.println(re.getArticles());
}
}
