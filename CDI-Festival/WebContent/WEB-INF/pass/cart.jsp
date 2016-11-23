<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="fr">

<head>
<meta charset="UTF-8" />

<title>Festival HELLFEST 2017</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/style/passStyle.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/style/grid.css">

</head>

<body>

	<header>
		<div></div>

	</header>




	<div class="title">
		<H1>Billet Festival</H1>
	</div>

	<div>

		<a href="<%= request.getContextPath() %>/" title="home"><img src="images/ios7-home.png"
			alt="Home" style="width: 24px; height: 24px;" /></a>


	</div>

	<hr>
	<section class="section-pass">


		<div class="subTitle">
			<h2>les pass disponibles</h2>
		</div>

		
		
		<div class="section group">
	<div class="col span_1_of_2"> 
    <img src="https://wzeweb-p-visuelorga-evn-affiche-thumb.s3-eu-west-1.amazonaws.com/affiche_161344.thumb53700.1457545074.jpg"
	alt="pass">
	</div>
	<div class="col span_1_of_2">
        <H3>HELLFEST : BILLETS 3 JOURS</H3>
        <p class="festi-date">Du Vendredi 23/06/2017 au Dimanche 25/06/2017</p>
        <p>Hippodrome Bor&eacute;ly de Marseille</p>
        <p class="align-right">Selectionnez vos places</p>
        <hr>
        <span class="checkout"><%=request.getAttribute("description")%>
        <%=request.getAttribute("price")%><%=request.getAttribute("date")%>
         
            
            
            <form name="quantity" method="post" action="checkout/Checkout"
							id="tikcetform">

							<select name="ticketQuantity" form="tikcetform">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>

							</select> <input type="submit">
						</form>
					</span>
	</section>
	<hr>
</body>
</html>
