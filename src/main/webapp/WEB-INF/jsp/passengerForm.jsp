<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Passenger Form</title>
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
    <h2>Passenger Form</h2>
    <f:form action="savePassenger" modelAttribute="passenger" method="post">

        <table border="1">
            <tr>
                <td><f:label path="passengerId" >ID:</f:label></td>
                <td><f:input path="passengerId" readOnly = "true" value="${passenger.passengerId}" /></td>
                <td><f:errors path="passengerId"   /></td>
            </tr>
            <tr>
                <td><f:label path="firstName"  >First Name:</f:label></td>
                <td><f:input path="firstName" value="${passenger.firstName}" /></td>
                <td><f:errors path="firstName"   /></td>
            </tr>
            <tr>
                <td><f:label path="lastName"  >Last Name:</f:label></td>
                <td><f:input path="lastName" value="${passenger.lastName}" /></td>
                <td><f:errors path="lastName"   /></td>
            </tr>
            <tr>
                <td><f:label path="email"  >Email:</f:label></td>
                <td><f:input path="email" value="${passenger.email}" /></td>
                <td><f:errors path="email"   /></td>
            </tr>
            <tr>
                <td><f:label path="phone"  >Phone Number:</f:label></td>
                <td><f:input path="phone" value="${passenger.phone}" /></td>
                <td><f:errors path="phone"   /></td>
            </tr>
            <tr>
                <td><f:label path="dateOfBirth"  >Date of Birth:</f:label></td>
                <td><f:input path="dateOfBirth" value="${passenger.dateOfBirth}" type="date"/></td>
                <td><f:errors path="dateOfBirth"   /></td>
            </tr>
            <tr>
                <td>Identification Type</td>
                <td>
                    <f:select path="idType">
                        <f:option value="">-- Select Identification Type --</f:option>
                        <c:forEach items="${idTypes}" var="idType">
                            <c:choose>
                                <c:when test="${idType == retrievedIdType}">
                                    <f:option value="${idType}" selected="selected">${idType}</f:option>
                                </c:when>
                                <c:otherwise>
                                    <f:option value="${idType}">${idType}</f:option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </f:select>
                </td>
            </tr>
            <tr>
                <td>Gender</td>
                <td>
                    <c:forEach items="${genders}" var="gender">
                        <c:choose>
                            <c:when test="${retrievedGender == gender}">
                                <f:checkbox  path="gender"  label="${gender}" value="${gender}" checked="true"/>
                            </c:when>
                            <c:otherwise>
                                <f:checkbox  path="gender"  label="${gender}" value="${gender}" />
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </td>
            </tr>

            <tr>
                <td>Flight</td>
                <td>
                    <f:select path="reservation.flight">
                        <f:option value="">-- Select Flight --</f:option>
                        <c:forEach items="${flights}" var="flight">
                            <c:choose>
                                <c:when test="${flight == retrievedFlight}">
                                    <f:option value="${flight}" selected="selected">
                                        ${flight.operatingAirlines.airlinesName}: ${flight.departureCity} -> ${flight.arrivalCity}</f:option>
                                </c:when>
                                <c:otherwise>
                                    <f:option value="${flight}">
                                        ${flight.operatingAirlines.airlinesName}: ${flight.departureCity} -> ${flight.arrivalCity}</f:option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </f:select>
                </td>
            </tr>

            <tr>
                <td><f:label path="reservation.checkedBags" >Checked Bags:</f:label></td>
                <td><f:input path="reservation.checkedBags" value="${passenger.reservation.checkedBags}" type="number" /></td>
                <td><f:errors path="reservation.checkedBags"   /></td>
            </tr>




        </table>
        <button type="submit">Save</button>



    </f:form>
</div>


<sec:authorize access="hasAuthority('ADMIN')">

    <div align="center">
        <h2>Passenger List</h2>
        <table border="1">
            <thead>
            <tr>

                <th>Passenger ID </th>

                <th>First Name </th>

                <th>Last Name </th>
                <th>Email </th>
                <th>Phone Number </th>
                <th>Date Of Birth </th>
                <th>Identification Type </th>
                <th>Gender </th>

                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${passengers}" var="passenger">
                <tr>
                    <td>${passenger.passengerId} </td>
                    <td>${passenger.firstName} </td>
                    <th>${passenger.lastName} </th>
                    <th>${passenger.email} </th>
                    <th>${passenger.phone} </th>
                    <th>${passenger.dateOfBirth} </th>
                    <th>${passenger.idType} </th>
                    <th>${passenger.gender} </th>
<!--                    <th>${passenger.reservation.ticketNumber} </th>-->
                    <td>
                        <a href="updatePassenger?passengerId=${passenger.passengerId}">Update | </a>
                        <a href="deletePassenger?passengerId=${passenger.passengerId}">Delete</a>

                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</sec:authorize>



</body>
</html>