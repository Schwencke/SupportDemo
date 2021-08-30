<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Waitinglist</title>
    </head>
    <body>
        <h1>Waiting for support</h1>
        
        <p>We will try to help in due order:</p>

        <ul>
            <c:forEach varStatus="status" var="ticket" items="${sessionScope.ticketList}">
                <li>TicketID: ${ticket.ticketId} Name: ${applicationScope.listofidsandnames.get(ticket.studentId)}</li>
            </c:forEach>
        </ul>

        <p>Add your name to the list</p>
        
        <form action="waitinglist" method="post">
            <input type="hidden" name="command" value="add"/>
            <select id="requestid" name="requestid" type="text">
                <c:forEach var="students" items="${sessionScope.studentList}" >
                <option value="${students.student_id}">${students.name}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Add"/>
        </form>
        
        <p>Or</p>

        <form action="waitinglist" method="post">
            <input type="hidden" name="command" value="remove"/>
            <input type="submit" value="Remove first on list"/>
        </form>   
        
        <p>
            <a href="index.jsp">To frontpage</a>
        </p>
        
    </body>
</html>
