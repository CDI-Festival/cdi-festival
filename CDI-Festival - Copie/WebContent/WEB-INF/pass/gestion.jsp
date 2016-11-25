<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>CDI Festival 2017</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style/passStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css" />
    <script src="<%= request.getContextPath() %>/js/passScript.js"></script>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
</head>

<body>


    	<%-- Include banner --%>
	<jsp:include page="/WEB-INF/include/header.jsp" />

	<!-- Include main menu -->
	<jsp:include page="/WEB-INF/include/menu.jsp" />

    <div class="title">
        <H1>Gestionnaire de Pass</H1>
    </div>
    
    
    <form id="form" onSubmit="return myFunction();"  action="<%= request.getContextPath() %>/ControlManager/ManagePass" method="post">

    <table>
        <tr>
            <th>Type dePass
                <th>Nombre de pass par jours
                    <th>Tarif
        </tr>
        <tr>
            <td>Pass 1 jours</td>
            <td><span>Vendredi
                    <div>
                        <input 	id="idVendredi" name="form" type="text"
									placeholder="Quantit&eacute" value="<%=(int) request.getAttribute("day1")%>">
									
                    </div>
                </span>
                <span>Samedi
                    <div>
                        <input 	id="idSamedi" name="form" type="number"
									placeholder="Quantité" value="<%=(int) request.getAttribute("day2")%>">
                    </div>
                </span>
                <span>Dimanche
                    <div>
                    <input 	id="idDimanche" name="form" type="number"
									placeholder="Quantité" value="<%=(int) request.getAttribute("day3")%>">
                    </div>
                </span></td>
            <td><input id="idPrice" name="price" type="number" placeholder="Tarif"  value="<%=(int) request.getAttribute("price1")%>"> Euros</td>
        </tr>

        <tr>

            <td>Pass 2 jours</td>
            <td><span>Vendredi -Samedi
                    <div>
                        <input 	id="idVen-sam" name="form" type="number"
									placeholder="Quantité" value="<%=(int) request.getAttribute("day12")%>">
                    </div>
                </span>

                <span>Samedi - Dimanche
                    <div>
                        <input 	id="idSam-dim" name="form" type="number"
									placeholder="Quantité" value="<%=(int) request.getAttribute("day23")%>">
                    </div>
                </span>
            </td>
            <td><input id="idPrice-Sam-dim" name="price" type="number" placeholder="Tarif"  value="<%=(int) request.getAttribute("price23")%>"> Euros</td>
        </tr>
        <tr>

            <td>Pass 3 jours</td>
            <td><span>Vendredi - Samedi - Dimanche
                    <div>
                        <input 	id="idVen-sam-dim" name="form" type="number"
									placeholder="Quantité" value="<%=(int) request.getAttribute("day123")%>">
                    </div>
                </span>


            </td>
            <td><input  id="idPrice-ven-sam-dim" name="price" type="number" placeholder="Tarif"  value="<%=(int) request.getAttribute("price123")%>"> Euros</td>
        </tr>
    </table>
    
    <div class="container">
    
    <p>Modification : Seulement les prix peuvent etre modifi�.</p>
    <p>Ajout 		: Supprimer un pack de pass avant l'ajout d'un nouveau.</p>
    <p>Les pass par jour doivent �tre maximum de 1000.</p>
    
    </div>
       


        <div class="container">
            <div class="center">
        <input type="submit" name="button" value="Ajouter">
        <input type="submit" name="button" value="Modifier">
        <input type="submit" name="button" value="Supprimer" onClick="return confirmSubmit()">
            </div>
        </div>
    
    </form>
    
    
    
<br>
        <H3>Pass disponibles</H3>

    <table>

        <tr>
            <th>TYPE DE PASS</th>
            <th>QUANTITE</th>
            <th>TARIF</th>
        </tr>
        <c:forEach items="${requestScope.allRow}" var="name">
            <tr>
                <td>
                    <c:out value="${name.dayType}" /> </td>
                <td>
                    <c:out value="${name.nombre}" /> </td>
                <td>
                    <c:out value="${name.price}" />
                </td>
            </tr>
        </c:forEach>

    </table>
    
                <jsp:include page="/WEB-INF/include/footer.jsp" />  
  <!--   <a  href="<%= request.getContextPath() %>/ControlManager/gene"/><input type="button" name="generate" value="envoie"/></a>
    -->



</body>
</html>