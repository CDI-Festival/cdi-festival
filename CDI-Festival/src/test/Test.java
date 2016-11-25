package test;

import fr.cdiFestival.dal.article.RequestArticle;
import fr.cdiFestival.model.Article;

public class Test {
public static void main(String[] args) {
	
	Article a = new Article("111", "23/05/78", "111", "11");
	
	RequestArticle re = new RequestArticle();
	System.out.println(re.add(a));
	System.out.println(re.getListArticle());
	
//	System.out.println(re.upDate(a));

	
//	System.out.println(re.getArticle(112));
}
}
