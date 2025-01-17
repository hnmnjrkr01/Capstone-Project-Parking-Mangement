<%--
  Created by IntelliJ IDEA.
  User: hnmnj
  Date: 1/1/2025
  Time: 11:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<jsp:include page="../include/header.jsp"/>

<style>
    .rate-container {
        display: flex;
        justify-content: space-around;
        padding-top: 10px;
    }

    .preBookStyle {
        background-color: rgba(0, 0, 0, 0.8);
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        padding: 15px 60px 40px 60px;
        margin: 10px;
        max-width: 750px;
        width: 100%;
        float: left;

    }

    .preBookStyle h2 {
        text-align: left;
        margin-bottom: 20px;
        font-size: 2rem;
    }

    .preBookStyle table {
        width: 100%;
        border-collapse: collapse;
        margin: 0 auto;
        color: #fff;
    }

    .preBookStyle table th, .preBookStyle table td {
        border: 1px solid #fff;
        padding: 10px;
        text-align: center;
    }

    .preBookStyle table th {
        background-color: #f39c12;
    }

    .preBookStyle label {
        font-weight: bold;
        color: #fff;
        padding: 15px;
    }

    input[type="datetime-local"], input[type="date"] {
        width: 70%;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 4px;
        font-size: 14px;
        color: #555;
    }
    .btn {
        background-color: #f39c12;
        color: #fff;
        text-decoration: none;
        padding: 10px 20px;
        border-radius: 5px;
        font-size: 1.2rem;
    }
    .btn:hover {
        background-color: #e67e22;
    }

    a{
        color: #fff;
        font-size: 1.2rem;
    }
    a:hover{
        color: #e67e22;
    }


</style>

<div class="rate-container">
    <div class="preBookStyle">
        <h2>Below are the ${availableSlots} available parking spots for the date and time of ${startParking} and ${endParking} on the level ${levelCode}.</h2>
         <table>
            <tr>
                <th>Level</th>
                <th>Spot Number</th>
                <th> </th>
            </tr>
            <c:forEach var="allParkingSpots" items="${allParkingSpots}">
                <tr>
                    <td>${levelCode}</td>
                    <td>${allParkingSpots.parkingSpotName}</td>
                    <td style="width: 100px"><button type="button" class="btn">Book</button></td>

                </tr>
            </c:forEach>
        </table>


    </div>


<jsp:include page="../include/footer.jsp"/>