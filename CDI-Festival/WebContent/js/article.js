/**
 * Check fields before validate
 * 
 * @returns boolean
 */
	function validateOnClick() {
		var authorOK  	= checkAuthor();
		var titleOK		= checkTitle();
		var contentOK	= checkContent();
		
		
		if ((authorOK == true) && (titleOK == true) && (contentOK == true));
		else {
			return false;
		}
		
		return true;
	}

/**
 * Check author value
 * 
 * @returns {Boolean}
 */
	function checkAuthor () {
		author = document.querySelector('#author').value;
		
		//If it's emppty
		if (author == '') {	
			document.querySelector('#author').style.border = "2px solid red";
			alert("Auteur vide");
		}
		
		//If it's too long
		else if (author.length > 20) 	{
			alert("Attention Auteur : Nombre de caractère limité à 20");
		}
		
		else {
			return true
		}
	
	return false
	}
	
/**
* Check title value
* 
* @returns {Boolean}
*/
	function checkTitle () {
		title = document.querySelector('#title').value;
		
		//If it's emppty
		if (title == '') {	
			document.querySelector('#title').style.border = "2px solid red";
			alert("Titre vide");
		}
		
		//If it's too long
		else if (title.length > 50) 	{
			alert("Attention Titre : Nombre de caractère limité à 50");
		}
		
		else {
			return true
		}
	
	return false
	}
		
/**
* Check content value
*
* @returns {Boolean}
*/
	function checkContent() {
		content = document.querySelector('#content').value;
			
		//If it's emppty
		if (content == '') {
			document.querySelector('#content').style.border = "2px solid red";
			alert("Contenu vide");
		}
			
		//If it's too long
		else if (content.lenth > 4000)		 	{
			alert("Attention Contenu : Nombre de caractère limité à 4000");
		}
			
		else {
			return true
		}
		
	return false
	}
	
	
/**
 * Warning message before deletion
 * 
 * @returns boolean
 */
	function deleteConfirm() {
		var ok=confirm("Êtes-vous sûr de vouloir supprimer l'article ?");
	
		if (ok) return true;	

	return false;
	}
