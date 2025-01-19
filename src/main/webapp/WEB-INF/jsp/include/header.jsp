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

<style>
  .logo-container {
    background-image: url("/pub/images/EasyParkLogo.png");
    background-repeat: no-repeat; /* Prevent image repetition */
    background-size: contain; /* Adjust size as needed */
    width: 200px;  /* Set width of the container */
    height: 40px; /* Set height of the container */
  }

  nav {
    color: white;
    padding: 0 20px;
  }

  .menu {
    list-style: none;
    margin: 0;
    padding: 0;
    display: flex;
  }

  .menu > li {
    position: relative;
  }

  .menu > li > a {
    display: block;
    padding: 15px 20px;
    text-decoration: none;
    color: #f39c12;
    transition: background-color 0.3s;
  }

  .menu > li > a:hover {
    background-color: #444;
  }

  /* Dropdown Menu */
  .dropdown {
    display: none;
    position: absolute;
    background-color: #444;
    list-style: none;
    margin: 0;
    padding: 0;
    top: 100%;
    left: 0;
    min-width: 200px;
    z-index: 1000;
  }

  .dropdown li {
    border-bottom: 1px solid #555;
  }

  .dropdown li:last-child {
    border-bottom: none;
  }

  .dropdown a {
    display: block;
    padding: 10px 15px;
    text-decoration: none;
    color: #f39c12;
    transition: background-color 0.3s;
  }

  .dropdown a:hover {
    background-color: #555;
  }

  /* Show dropdown on hover */
  .menu > li:hover .dropdown {
    display: block;
  }

  /* Nested Dropdown */
  .dropdown .nested-dropdown {
    position: relative;
  }

  .dropdown .nested-dropdown .dropdown {
    left: 100%;
    top: 0;
  }

</style>
<body>
<header>

  <nav>
    <ul class="menu">
      <li><div class="logo-container"></div></li>
      <li><a href="/index">Home</a></li>



      <li>
        <a href="#">User</a>
        <ul class="dropdown">
          <sec:authorize access="isAuthenticated()">
          <li><a href="/User/userDashboard">Dashboard</a></li>
          </sec:authorize>

          <sec:authorize access="!isAuthenticated()">
            <li><a href="/User/signUp">Sign Up</a></li>
            <li><a href="/login/login">User Login</a></li>
          </sec:authorize>
          <li><a href="/login/logout">Logout</a></li>
        </ul>
      </li>
      <sec:authorize access="hasAuthority('ADMIN')">
      <li>
        <a href="#">Admin</a>
        <ul class="dropdown">
          <li><a href="/User/userSearch">User Search</a></li>
          <li><a href="/ParkingSpot/createParkingSpot">Create Parking Spot</a></li>
          <li><a href="/ParkingSpot/getAllParkingSpots">All Parking Slots</a></li>
        </ul>
      </li>
      </sec:authorize>

      <li>
        <a href="/OtherPages/about">About</a>
        <ul class="dropdown">
          <li><a href="/OtherPages/about">About</a></li>
          <li><a href="/OtherPages/contactUs">Contact</a></li>
        </ul>
      </li>
    </ul>

  </nav>


</header>
</body>
</html>