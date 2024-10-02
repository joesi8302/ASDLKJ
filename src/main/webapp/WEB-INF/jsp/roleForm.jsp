<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Role Form</title>
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
    <h2>Role Form</h2>
    <f:form action="saveRole" modelAttribute="role" method="post">

        <table border="1">
            <tr>
                <td><f:label path="roleId" >ID:</f:label></td>
                <td><f:input path="roleId" readOnly = "true" value="${role.roleId}" /></td>
                <td><f:errors path="roleId"   /></td>
            </tr>
            <tr>
                <td><f:label path="roleName"  >Name:</f:label></td>
                <td><f:input path="roleName" value="${role.roleName}" /></td>
                <td><f:errors path="roleName"   /></td>
            </tr>

        </table>
        <button type="submit">Save</button>



    </f:form>
</div>

<div align="center">
    <h2>Role List</h2>
    <table border="1">
        <thead>
        <tr>

            <th>Role ID </th>

            <th>Name </th>

            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${roles}" var="role">
            <tr>
                <td>${role.roleId} </td>
                <td>${role.roleName} </td>
                <td>
                    <a href="updateRole?roleId=${role.roleId}">Update | </a>
                    <a href="deleteRole?roleId=${role.roleId}">Delete</a>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>