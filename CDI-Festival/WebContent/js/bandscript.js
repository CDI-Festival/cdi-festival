/**
 * Script name: Band script
 * Description: Script to call servlet on certain button click.
 * @author Claire
 * @version 20161120
 */

/**
 * This function is called on a link click.
 * 
 * Depending on the name in parameter,
 * it calls the BandController with the right URL
 * and reload the page.
 * 
 * @author Claire
 * @param name
 * @version 20161120
 */
function callBandController(name) {

	// Get the context path from where the function was called
	var contextPath = document.getElementById('contextPath').value;
	
	switch (name) {
	
	// Click on band menu from administration side (menu-admin.jsp)
	case 'Groupe': 
		
		console.log('JS: Click on admin menu Groupe'); // TEST CODE
		
		var url = contextPath + "/admin/groupes/gerer";
		location.href = url;
		break;

	// Click on 'Creer' button from band list page (liste-groupes.jsp)
	case 'Creer':
		
		console.log('JS: click on Creer'); // TEST CODE
		
		var url = contextPath + "/admin/groupes/creer";
		location.href = url;
		break;

	// Click on 'Modifier' button from band list page (liste-groupes.jsp)
	case 'Modifier':
		
		console.log('JS: click on Modifier'); // TEST CODE
		
		var selectedValue = getSelectedValue('listband');
		console.log('JS: selectedValue = ' + selectedValue); // TEST CODE
		
		// FIXME value en dur?
		var url = contextPath + "/admin/groupes/modifier?value=";
		location.href = url + selectedValue;
		break;

	// Click on 'Supprimer' button from band list page (liste-groupes.jsp)
	case 'Supprimer':
		
		console.log('JS: click on Supprimer'); // TEST CODE
		
		var selectedValue = getSelectedValue('listband');
		
		console.log('JS: selectedValue = ' + selectedValue); // TEST CODE
		
		if (confirm('Supprimer le groupe ' + selectedValue + ' ?')) {

			    console.log('Oui'); // TEST CODE
			    
			    var url = contextPath + "/admin/groupes/supprimer?value=";
			    location.href = url + selectedValue;
			    break;
		} else {
				  
		   console.log('Non'); // TEST CODE    
		}
		
	default:
		console.log('JS: no click source');
	}
}
	
/**
 * This funcion returns the selected option in a HTML select element.
 * 
 * @author Claire
 * @param selectId
 * @return value
 * @version 20161120
 */
function getSelectedValue(selectId) {
	
	// Get the HTML Select element
	var select = document.getElementById(selectId);
	
	/* Get the options tag and the index of the selected one
	 * Return the value of it
	 */
	return select.options[select.selectedIndex].value;
}
