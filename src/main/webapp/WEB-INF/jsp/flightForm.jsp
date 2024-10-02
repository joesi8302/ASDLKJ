<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Flight Form</title>
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
    <h2>Flight Form</h2>
    <f:form action="saveFlight" modelAttribute="flight" method="post">

        <table border="1">
            <tr>
                <td><f:label path="flightId" >ID:</f:label></td>
                <td><f:input path="flightId" readOnly = "true" value="${flight.flightId}" /></td>
                <td><f:errors path="flightId"   /></td>
            </tr>

            <tr>
                <td><f:label path="flightNumber" >Flight Number:</f:label></td>
                <td><f:input path="flightNumber" value="${flight.flightNumber}" /></td>
                <td><f:errors path="flightNumber"   /></td>
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
                <td><f:label path="departureDateTime" >Departure Time:</f:label></td>
                <td><f:input path="departureDateTime"  value="${flight.departureDateTime}" type="datetime-local" /></td>
                <td><f:errors path="departureDateTime"   /></td>
            </tr>
            <tr>
                <td><f:label path="arrivalDateTime" >Arrival Time:</f:label></td>
                <td><f:input path="arrivalDateTime" value="${flight.arrivalDateTime}" type="datetime-local" /></td>
                <td><f:errors path="arrivalDateTime"   /></td>
            </tr>
            <tr>
                <td><f:label path="ticketPrice" >Ticket Price:</f:label></td>
                <td><f:input path="ticketPrice" value="${flight.ticketPrice}" type="number" /></td>
                <td><f:errors path="ticketPrice"   /></td>
            </tr>

        </table>
        <button type="submit">Save</button>



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
                    <a href="updateFlight?flightId=${flight.flightId}">Update | </a>
                    <a href="deleteFlight?flightId=${flight.flightId}">Delete</a>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>