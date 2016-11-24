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
		
		if (author == '') {	
			document.querySelector('#author').style.border = "2px solid red";
			alert("Auteur vide");
		}
		
		if (author.length > 20) 	{
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
		author = document.querySelector('#title').value;
		
		if (author == '') {	
			document.querySelector('#title').style.border = "2px solid red";
			alert("Titre vide");
		}
		
		if (author.length > 50) 	{
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
	function checkContent () {
		content = document.querySelector('#content').value;
			
		if (content == '') {
			document.querySelector('#content').style.border = "2px solid red";
			alert("Contenu vide");
		}
			
		if (content.lenth > 4000)		 	{
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
