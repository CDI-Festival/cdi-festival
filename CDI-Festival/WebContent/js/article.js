/* Check empty area and change border in red if is it*/
function isEmpty() {
	author 	= document.querySelector('#author').value;
	title 	= document.querySelector('#title').value;
	content = document.querySelector('#content').value;

	if (author == '') 	document.querySelector('#author').style.border 	= "2px solid red";
	if (title == '') 	document.querySelector('#title').style.border 	= "2px solid red";
	if (content == '') 	document.querySelector('#content').style.border = "2px solid red";
	
	else {
		return true;
	}

	return false;
}
