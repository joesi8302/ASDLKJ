<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>User Form</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div align="center">
    <h2>Logged User: ${pageContext.request.userPrincipal.name}</h2>

    <table>
        <tr>
            <td><a href="${pageContext.request.contextPath}/home">Home |</a></td>
            <td><a href="${pageContext.request.contextPath}/airlinesForm">Airlines Form |</a></td>
            <td><a href="${pageContext.request.contextPath}/airportForm">Airport Form |</a></td>
            <td><a href="${pageContext.request.contextPath}/flightForm">Flight Form |</a></td>
            <td><a href="${pageContext.request.contextPath}/passengerForm">Passenger Form |</a></td>
            <td><a href="${pageContext.request.contextPath}/reservationForm">Reservation Form |</a></td>
            <td><a href="${pageContext.request.contextPath}/roleForm">Role Form |</a></td>
            <td><a href="${pageContext.request.contextPath}/userForm">User Form |</a></td>
            <sec:authorize access="isAuthenticated()">
                <td><a href="${pageContext.request.contextPath}/logout">Logout</a></td>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
                <td><a href="${pageContext.request.contextPath}/login">Login</a></td>
            </sec:authorize>
        </tr>

    </table>
</div>
</body>
</html>