<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Reservation Form</title>
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
    <h2>Reservation Form</h2>
    <f:form action="saveReservation" modelAttribute="reservation" method="post">

        <table border="1">
            <tr>
                <td><f:label path="ticketNumber" >Ticket Number:</f:label></td>
                <td><f:input path="ticketNumber" readOnly = "true" value="${reservation.ticketNumber}" /></td>
                <td><f:errors path="ticketNumber"   /></td>
            </tr>
            <tr>
                <td><f:label path="passenger"  >Name:</f:label></td>
                <td><span>${reservation.passenger.firstName} ${reservation.passenger.lastName}</span></td>
                <f:input path="passenger" type="hidden" value="${reservation.passenger.passengerId}" />
                <td><f:errors path="passenger"   /></td>
            </tr>
            <tr>
                <td><f:label path="passenger"  >Flight:</f:label></td>
                <td>
                    <f:select path="flight">
                        <f:option value="">-- Select Flight --</f:option>
                        <c:forEach items="${flights}" var="flight">
                            <c:choose>
                                <c:when test="${flight == retrievedFlight}">
                                    <f:option value="${reservation.flight}" selected="selected">
                                        ${flight.operatingAirlines.airlinesName}: ${flight.departureCity} -> ${flight.arrivalCity}</f:option>
                                </c:when>
                                <c:otherwise>
                                    <f:option value="${reservation.flight}">
                                        ${flight.operatingAirlines.airlinesName}: ${flight.departureCity} -> ${flight.arrivalCity}</f:option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </f:select>
                </td>
                <td><f:errors path="flight"   /></td>
            </tr>
            <tr>
                <td><f:label path="checkedBags"  >Checked Bags:</f:label></td>
                <td><f:input path="checkedBags" value="${reservation.checkedBags}" /></td>
                <td><f:errors path="checkedBags"   /></td>
            </tr>
            <tr>
                <td><f:label path="checkedIn">Checked In:</f:label></td>
                <td><f:checkbox path="checkedIn" /></td>
                <td><f:errors path="checkedIn" /></td>
            </tr>

        </table>
        <button type="submit">Save</button>



    </f:form>
</div>


<sec:authorize access="hasAuthority('ADMIN')">

    <div align="center">
        <h2>Reservation List</h2>
        <table border="1">
            <thead>
            <tr>

                <th>Ticket Number </th>

                <th>Passenger Name </th>

                <th>Flight </th>

                <th>Checked Bags </th>

                <th>Checked In </th>

                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${reservations}" var="reservation">
                <tr>
                    <td>${reservation.ticketNumber} </td>
                    <td>${reservation.passenger.firstName} ${reservation.passenger.lastName}</td>
                    <td>${reservation.flight.operatingAirlines.airlinesName}: ${reservation.flight.departureCity} -> ${reservation.flight.arrivalCity} </td>
                    <td>${reservation.checkedBags} </td>
                    <td>${reservation.checkedIn} </td>
                    <td>
                        <a href="updateReservation?ticketNumber=${reservation.ticketNumber}">Update | </a>
                        <a href="deleteReservation?ticketNumber=${reservation.ticketNumber}">Delete | </a>
                        <a href="checkInReservation?ticketNumber=${reservation.ticketNumber}">Check In | </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</sec:authorize>>

</body>
</html>