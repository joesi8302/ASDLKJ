<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Airport Form</title>
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

<div align="center">
    <h2>Airport Form</h2>
    <f:form action="saveAirport" modelAttribute="airport" method="post">

        <table border="1">
            <tr>
                <td><f:label path="airportId" >ID:</f:label></td>
                <td><f:input path="airportId" readOnly = "true" value="${airport.airportId}" /></td>
                <td><f:errors path="airportId"   /></td>
            </tr>
            <tr>
                <td><f:label path="airportName"  >Name:</f:label></td>
                <td><f:input path="airportName" value="${airport.airportName}" /></td>
                <td><f:errors path="airportName"   /></td>
            </tr>
            <tr>
                <td><f:label path="airportCode"  >Code:</f:label></td>
                <td><f:input path="airportCode" value="${airport.airportCode}" /></td>
                <td><f:errors path="airportCode"   /></td>
            </tr>
            <tr>
                <td><f:label path="airportCity"  >City:</f:label></td>
                <td><f:input path="airportCity" value="${airport.airportCity}" /></td>
                <td><f:errors path="airportCity"   /></td>
            </tr>

        </table>
        <button type="submit">Save</button>



    </f:form>
</div>

<div align="center">
    <h2>Airport List</h2>
    <table border="1">
        <thead>
        <tr>

            <th>ID </th>

            <th>Name </th>

            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${airports}" var="airport">
            <tr>
                <td>${airport.airportId} </td>
                <td>${airport.airportName} </td>
                <td>${airport.airportCode} </td>
                <td>${airport.airportCity} </td>
                <td>
                    <a href="updateAirport?airportId=${airport.airportId}">Update | </a>
                    <a href="deleteAirport?airportId=${airport.airportId}">Delete</a>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>