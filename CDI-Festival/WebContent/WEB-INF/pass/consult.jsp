<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="fr.cdiFestival.service.Passes, java.time.LocalDate" %>
<%@ page import="fr.cdiFestival.model.*" %>


<!doctype html>
    <html>

    <head>

        <title>Festival HELLFEST 2017</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/style/passStyle.css">
         <link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css" />
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    </head>

    <body>
    
    	<%-- Include banner --%>
	<jsp:include page="/WEB-INF/include/header.jsp" />

	<!-- Include main menu -->
	<jsp:include page="/WEB-INF/include/menu.jsp" />



        <div class="container">
            <H1>Billet Festival</H1>
       
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
                        
                        <section class="section-pass">

						 <% Passes pass = (Passes) request.getAttribute("allPasses");  
						 for(int i = 0; i < pass.size(); i++) {
						 	if(i%2 == 0) { %>
						 	<div class="section group">
								<div class="col span_1_of_2">
								<a href="../consult/ControlShowCase/ShowCase?id=<%=pass.get(i).gettype()%>"/>
								<img class="pass" src="${pageContext.request.contextPath}/images/pass.png" alt="Pass Festival">
								</a>
								
								<h3><%=pass.get(i).getDayType()%></h3>
								<%for(LocalDate current : pass.get(i).getDate()) { %>
									<p class="checkout"> <%=current.toString()%> </p>
								<% } %>
                          		</div> 
                          	<% }else { %>
                          	<div class="col span_1_of_2">
                          	<a href="../consult/ControlShowCase/ShowCase?id=<%=pass.get(i).gettype()%>"/>
        					<img class="pass" src="${pageContext.request.contextPath}/images/pass.png" alt="Pass Festival">
        					</a>
        					<h3><%=pass.get(i).getDayType()%></h3>
								<%for(LocalDate current : pass.get(i).getDate()) { 
								%>
									<p class="checkout"> <%=current.toString()%> </p>
								<% } %>
								
							</div>
							</div>
							<% } %>
							
							<% } %>

                        </section>
                        
             <jsp:include page="/WEB-INF/include/footer.jsp" /> 

    </body>

    </html>
