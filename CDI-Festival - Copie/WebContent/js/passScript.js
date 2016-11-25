/**
 * 
 */

function myFunction() {


	removeWarningLabel();
    /*
     * Day Values
     * 
    */
    var vendredi = document.getElementById('idVendredi').value;
    var samedi = document.getElementById('idSamedi').value;
    var dimanche = document.getElementById('idDimanche').value;
    
    var ven_sam = document.getElementById('idVen-sam').value;
    var sam_dim = document.getElementById('idSam-dim').value;
    
    var ven_sam_dim = document.getElementById('idVen-sam-dim').value;
    
    /*
     * Prices Values
     * 
    */     
    var price = document.getElementById('idPrice').value;
    var price_sam_dim = document.getElementById('idPrice-Sam-dim').value;
    var price_ven__sam_dim = document.getElementById('idPrice-ven-sam-dim').value;
    
    
    



    if(vendredi  == 0 || samedi == 0 || dimanche  == 0 || ven_sam  == 0 || sam_dim == 0 || ven_sam_dim  == 0) {
        console.log("jour is empty !")
        pseudoAlert = "jour Obligatoire";
        document.getElementById("idVendredi").style.borderColor="red";
        document.getElementById("idSamedi").style.borderColor="red";
        document.getElementById("idDimanche").style.borderColor="red";
        document.getElementById("idVen-sam").style.borderColor="red";
        document.getElementById("idSam-dim").style.borderColor="red";
        document.getElementById("idVen-sam-dim").style.borderColor="red";
        inserer('places Obligatoire', 'jour1ID');
        
        return false;
    
    }else {
        return true;
    }
    return false;
    
    
    
  
}



function inserer(textNode,  unId) {
    var parentDiv = document.getElementById('form');			
	var newChild = document.createElement('p');				
	newChild.setAttribute('id', unId);
    newChild.setAttribute("class", "important");
	var newChildText = document.createTextNode(textNode);	
	parentDiv.appendChild(newChild);    
    newChild.appendChild(newChildText);
}



function removeWarningLabel() {
	if(document.getElementById("jour1ID") != undefined) {
		remove("jour1ID");
	}
}

function remove(ident) {  
	var item = document.getElementById(ident);
	item.parentNode.removeChild(item); 
	}



function confirmSubmit()
{
var ok=confirm("vous etes sur ?");
if (ok){
	return true;	
}
	return false; 
}


