<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="fr.cdiFestival.model.*" %>


<!doctype html>
    <html>

    <head>

        <title>Festival HELLFEST 2017</title>
        <link rel="stylesheet" href="../styles/main.css">
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    </head>

    <body>


        <header>
            <nav>
                <ul>
                    <li><a href="<%= request.getContextPath() %>/pass/show">Achat de billet</a></li>
                    <li><a href="<%= request.getContextPath() %>/pass/manage">Gestionnaire de billet</a></li>

                </ul>
            </nav>



        </header>





        <div class="container">
            <H1>Billet Festival</H1>
            <a href="<%= request.getContextPath() %>/main.jsp" title=""><img src="images/ios7-home.png" alt="Home" style="width: 24px; height: 24px;" /></a>

            <%
			boolean list = (boolean) request.getAttribute("listAvail");
			if (list == false) {
		%>
                <p>There is no Passes available today, please come back later...</p>
                <%
			} else {
		%>
                    <p>There is Passes available today !</p>
                    <%
			}
		%>
                        </div>

                        <div class="row">
                            <h2>les pass disponibles</h2>
                        </div>

<% ArrayList<Pass> pass = (ArrayList<Pass>) request.getAttribute("allPasses");  
						 for(int i = 0; i < pass.size(); i++) {
						 	if(i%2 == 0) { %>
						 	<div class="section group">
								<div class="col span_1_of_2">
								<a href="../consult/ControlShowCase/ShowCase?id=<%=pass.get(i).gettype()%>"/>
								<img src="http://static2.digitick.com/commun/images/upload/evenements/300x30012_300.jpg" alt="Pass Festival">
								</a>
                          		</div> 
                          	<% }else { %>
                          	<div class="col span_1_of_2">
                          	<a href="../consult/ControlShowCase/ShowCase?id=<%=pass.get(i).gettype()%>"/>
        					<img src="http://static2.digitick.com/commun/images/upload/evenements/300x30012_300.jpg" alt="Pass Festival">
        					</a>
							</div>
							</div>
							<% } %>
							
							<% } %>


						
							
						 	
						 
						 
						 
						 <c:forEach items="${requestScope.allPasses}" var="name">                    	
                            <div class="section group">
                                <div class="col span_1_of_2">
                                    <a href="../consult/ControlShowCase/ShowCase?id=<c:out value=" ${name.type} "/>">${name.type}</a>
                                  <div class="pass-feature">
                                        <c:out value="${name.dayType}" />
                                        <c:out value="${name.price}" /> Euros
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

    </body>

    </html>
