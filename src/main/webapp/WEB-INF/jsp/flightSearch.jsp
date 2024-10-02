<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Flight Search</title>
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
    <h2>Search Flights</h2>
    <f:form action="searchFlight" modelAttribute="flight" method="GET">

        <table border="1">

            <tr>
                <td>Departure City:</td>
                <td>
                    <f:select path="departureCity">
                        <f:option value="">-- Select City --</f:option>
                        <c:forEach items="${airportCities}" var="city">
                            <f:option value="${city}">${city}</f:option>
                        </c:forEach>
                    </f:select>
                </td>
            </tr>
            <tr>
                <td>Arrival City:</td>
                <td>
                    <f:select path="arrivalCity">
                        <f:option value="">-- Select City --</f:option>
                        <c:forEach items="${airportCities}" var="city">
                            <f:option value="${city}">${city}</f:option>
                        </c:forEach>
                    </f:select>
                </td>
            </tr>

            <tr>
                <td>Airline:</td>
                <td>
                    <f:select path="operatingAirlines">
                        <f:option value="">-- Select Airline --</f:option>
                        <c:forEach items="${airlines}" var="airline">
                            <f:option value="${airline}">${airline.airlinesName}</f:option>
                        </c:forEach>
                    </f:select>
                </td>
            </tr>


        </table>
        <button type="submit">Search</button>



    </f:form>
</div>

<div align="center">
    <h2>Flight List</h2>
    <table border="1">
        <thead>
        <tr>

            <th>ID </th>

            <th>Flight Number </th>

            <th>Airline</th>

            <th>Departure City</th>

            <th>Arrival City</th>

            <th>Departure Time</th>

            <th>Arrival Time</th>

            <th>Ticket Price</th>

            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${flights}" var="flight">
            <tr>
                <td>${flight.flightId} </td>
                <td>${flight.flightNumber} </td>
                <td>${flight.operatingAirlines.airlinesName} </td>
                <td>${flight.departureCity} </td>
                <td>${flight.arrivalCity} </td>
                <td>${flight.departureDateTime} </td>
                <td>${flight.arrivalDateTime} </td>
                <td>${flight.ticketPrice} </td>
                <td>

                    <a href="bookPassenger?reservation.flight.flightId=${flight.flightId}">Book</a>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>