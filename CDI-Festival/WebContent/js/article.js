/**
 * Checking, and if is empty : Display message and change border
 *  
 *  @returns void
 *  
 */
	function isEmpty() {
		
		
		/*Getting values by ID*/
		author 	= document.querySelector('#author').value;
		title 	= document.querySelector('#title').value;
		content = document.querySelector('#content').value;

		/*Checking attributes value and make actions if is it*/
		if (author == '') {	
			document.querySelector('#author').style.border 	= "2px solid red";
			messErreur('author', "Champs vide");
		}
		if (title == '') {
			document.querySelector('#title').style.border 	= "2px solid red";
			messErreur('title', "Champs vide");
		}
		
		if (content == '') 	{
			document.querySelector('#content').style.border = "2px solid red";
			messErreur('content', "Champs vide");
		}
		
		
		else {
			return true;
		}

		return false;
	}

/*Warning message before deletion*/
	function deleteConfirm() {
		var ok=confirm("Êtes-vous sûr de vouloir supprimer l'article ?");
	
		if (ok) return true;	

	return false;
	}

/**
* Permet d'indiquer les champs manquants à l'utilisateur (coloration de la
* bordure de l'élément passé en paramètre et le texte à afficher)
* 
* @param idElement
* @param text
* @returns
*/
	function messErreur(idElement, text) {
		var afficheErreur = document.createElement('span');
		afficheErreur.setAttribute('class', 'spanErreur')
		afficheErreur.setAttribute("style", "color : red");
		var txt = document.createTextNode(text);
		afficheErreur.appendChild(txt);
		var nodeParent = document.getElementById(idElement);
		nodeParent.parentNode.insertBefore(afficheErreur, nodeParent.nextSibling);
	}