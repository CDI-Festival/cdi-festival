/**
 * Script name: Band form script
 * Description: Script with input control for the creation of a new band form
 * Author: Claire
 * Version: 20161121
 */

// TODO erase console.log()

/**
 * This function checks all the form fields.
 * 
 * @param form
 * @returns boolean
 */
function validate(form) {
	
	var bandName = form.bandname;
	var bandBio = form.bandbio;
	var bandDisco = form.banddisco;
	var bandWebsite = form.bandwebsite;
	
	var nameOK = checkName(bandName);
	var bioOK = checkBio(bandBio);
	var discoOK = checkDisco(bandDisco);
	// TODO check URL (regex?)
	// var websiteOK = checkWebsite(bandWebsite);
	
	if (nameOK && bioOK && discoOK) {
		return true;
	}
	else {
		console.log('Validate = false'); // TEST CODE
		return false;
	}
}

/**
 * This function checks the band name field.
 * 
 * It calls the emptyString() and fiftyChar() functions.
 * 
 * @param field
 * @returns boolean
 */
function checkName(field) {
	if (emptyString(field.value)) {
		alert('Le nom du groupe doit être renseigné.');
		return false;
	}
	else if (fiftyChar(field.value)) {
		alert('Le nom du groupe ne peut pas dépasser cinquante caractères.');
		return false;
	}
	else {
		return true;
	}
}

/**
 * This function checks the band biography field.
 * 
 * It calls the fiveHundredChar() function.
 * 
 * @param field
 * @returns boolean
 */
function checkBio(field) {
	if (fiveHundredChar(field.value)) {
		alert('Le champ biographie ne peut pas faire plus de 500 caractères.');
		return false;
	}
	else {
		return true;
	}
}

function checkDisco(field) {
	// TODO checkDisco();
	return true;
}

function checkWebsite(field) {
	if (checkUrl(field.value)) {
		return true;
	}
	else {
		alert("Merci de vérifier l'URL du site Internet.");
		return false;
	}
}

/**
 * This function checks if a value is null or empty.
 * 
 * It returns true if it is.
 * 
 * @param attribute
 * @returns boolean
 */
function emptyString(attribute) {
	if (attribute == null || attribute == "") {
		console.log('Valeur null ou vide.'); // TEST CODE
		return true;
	}
	else {
		console.log('Valeur non null.')
		return false;
	}
}

/**
 * This function checks if a String is more than fifty characters.
 * 
 * It returns true if it is.
 * 
 * @param attribute
 * @returns boolean
 */
function fiftyChar(attribute) {
	if (attribute.length >= 50) {
		console.log('Valeur supérieure à 50 caractères.'); // TEST CODE
		return true;
	}
	else {
		console.log('Longueur 50 valeur OK.'); // TEST CODE
		return false;
	}
}

/**
 * This function checks if a text is more than five hundred characters.
 * 
 * It returns true if it is.
 * 
 * @param attribute
 * @returns boolean
 */
function fiveHundredChar(attribute) {
	if (attribute.length >= 500) {
		console.log('Valeur supérieure à 500 caractères.'); // TEST CODE
		return true;
	}
	else {
		console.log('Longueur 500 valeur OK.'); // TEST CODE
		return false;
	}
}

/**
 * This function checks an URL field.
 * 
 * @param attribute
 * @returns boolean
 */
function checkUrl(attribute) {
	// TODO checkUrl();
	// var regex = new RegExp("^(http://|https://)[a-zA-Z0-9");
	
	// return true;
}