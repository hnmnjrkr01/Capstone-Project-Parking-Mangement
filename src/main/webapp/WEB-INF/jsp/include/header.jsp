<%--
  Created by IntelliJ IDEA.
  User: hnmnj
  Date: 12/13/2024
  Time: 1:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel='stylesheet' type='text/css' href="/pub/css/globalStyleSheet.css">
  <title>CarParkingApplication - EasyPark </title>

</head>
<body>
<header>
  <h1>Welcome to EasyPark</h1>
  <nav>


    <a href="/index">Home</a>


    <a href="/OtherPages/about">About</a>


    <a href="/OtherPages/contactUs">Contact</a>

    <sec:authorize access="isAuthenticated()">
    <a href="/User/userDashboard">Dashboard</a>
    </sec:authorize>


    <sec:authorize access="!isAuthenticated()">
    <a href="/User/signUp">Sign Up</a>

    <a href="/login/login">User Login</a>
    </sec:authorize>

    <a href="/login/logout">Logout</a>

    <sec:authorize access="hasAuthority('ADMIN')">
    <a href="/User/userSearch">User Search</a>
    </sec:authorize>


  </nav>


</header>
</body>
</html>