
function calculTotal() {
    if(document.getElementById("total") != undefined) {
        remove("total");
    }
    console.log("action");
    var priceElem = document.getElementById("pricing").innerHTML;
    var selectElem = document.getElementById('mySelect').value;
    var totalPrice = priceElem * selectElem;
    console.log("price "+ priceElem + " select "+selectElem);
    console.log("total "+ totalPrice);
    inserer(totalPrice, "total"); 
}




function inserer(textNode,  unId) {
    var parentDiv = document.getElementById("result");			
	var newChild = document.createElement("p");	
	console.log('newchield is :'+newChild);
	newChild.setAttribute("id", unId);
    newChild.setAttribute("class", "important");
    
	var newChildText = document.createTextNode(textNode +" Euros");	
	parentDiv.appendChild(newChild);    
    newChild.appendChild(newChildText);
}



function remove(ident) {  
var item = document.getElementById(ident);
item.parentNode.removeChild(item);
    
   
}
