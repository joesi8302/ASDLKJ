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

        <div align="center">
            <h2>User Form</h2>
            <f:form action="saveUser" modelAttribute="user" method="post">
                <table border="1">
                    <tr>
                        <td><f:label path="userId"  >ID:</f:label></td>
                        <td><f:input path="userId" value="${user.userId}" /></td>
                        <td><f:errors path="userId" ></f:errors></td>
                    </tr>
                    <tr>
                        <td><f:label path="username"  >Username:</f:label></td>
                        <td><f:input path="username" value="${user.username}" /></td>
                        <td><f:errors path="username" cssClass="error"></f:errors></td>
                    </tr>
                    <tr>
                        <td><f:label path="userPassword">Password:</f:label></td>
                        <td><f:input path="userPassword" value="${user.userPassword}"/></td>
                        <td><f:errors path="userPassword" cssClass="error"></f:errors></td>
                    </tr>
                    <tr>
                        <td><f:label path="userEmail">User Email:</f:label></td>
                        <td><f:input path="userEmail" value="${user.userEmail}"/></td>
                        <td><f:errors path="userEmail" cssClass="error"></f:errors></td>
                    </tr>
                    <tr>
                        <td>Roles</td>
                        <td>
                            <c:forEach items="${roles}" var="role">
                                <c:choose>
                                    <c:when test="${retrievedRoles.contains(role)}">
                                        <f:checkbox  path="userRoles"  label="${role.roleName}" value="${role.roleId}" checked="true"/>
                                    </c:when>
                                    <c:otherwise>
                                        <f:checkbox  path="userRoles"  label="${role.roleName}" value="${role.roleId}" />
                                    </c:otherwise>
                                </c:choose>

                            </c:forEach>
                        </td>
                    </tr>
                </table>
                <button type="submit">Save</button>
            </f:form>
        </div>

        <sec:authorize access="hasAuthority('ADMIN')">
            <div align="center">
                <h2>User List</h2>
                <table border="1">
                    <thead>
                    <tr>

                        <th>ID </th>

                        <th>username </th>

                        <th>email </th>

                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.userId} </td>
                            <td>${user.username} </td>
                            <td>${user.userEmail} </td>
                            <td>
                                <a href="updateUser?userId=${user.userId}">Update | </a>
                                <a href="deleteUser?userId=${user.userId}">Delete</a>

                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </sec:authorize>

    </body>
</html>