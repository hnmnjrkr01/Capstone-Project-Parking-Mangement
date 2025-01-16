<%--
  Created by IntelliJ IDEA.
  User: hnmnj
  Date: 1/11/2025
  Time: 4:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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


<h2 style="text-align: end;">Welcome, ${loggedUser.firstname}! <br>
    <a href="/User/myself/${loggedUser.id}">Edit My-Profile</a> <br>
    <a href="/Booking/parkingBooking">Book New Parking</a>
</h2>
<hr>


<div class="rate-container">
    <div class="preBookStyle">
        <h2>Your Bookings</h2>
        <table>
            <tr>
                <th>Booing-Id</th>
                <th>Payment-Method</th>
                <th>Floor-Level</th>
                <th>Total-Price</th>
                <th>Start Date & Time</th>
                <th>End Date & Time</th>
                <th>Duration</th>
                <th>License PLate Number</th>
            </tr>
            <c:forEach var="booking" items="${booking}">
                <tr>
                    <td>${booking.bookingId}</td>
                    <td>${booking.paymentMethodId}</td>
                    <td>${booking.levelId}</td>
                    <td>${booking.totalPrice}</td>
                    <td>${booking.startDateTime}</td>
                    <td>${booking.endDateTime}</td>
                    <td>${booking.duration}</td>
                    <td>${booking.licensePlateNumber}</td>
                </tr>
            </c:forEach>
        </table>


    </div>


    <div class="preBookStyle">
        <h1>Check Availability</h1>
        <form id="bookingForm" action="/Booking/availableSlots">
            <table>
                <tr class="form-group">
                    <td>
                        <label for="startParkingTime">From Date and Time</label>
                    </td>
                    <td>
                        <input type="datetime-local" id="startParkingTime" name="startParkingTime" value="${searchDTO.startParkingTime}">
                        <c:if test="${bindingResult.hasFieldErrors('startParkingTime')}">
                            <c:forEach var="error" items="${bindingResult.getFieldErrors('startParkingTime')}">
                                <div class="input_error">${error.getDefaultMessage()}</div>
                            </c:forEach>
                        </c:if>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label for="endParkingTime">To Date and Time </label>
                    </td>
                    <td>
                        <input type="datetime-local" id="endParkingTime" name="endParkingTime" value="${searchDTO.endParkingTime}">
                        <c:if test="${bindingResult.hasFieldErrors('endParkingTime')}">
                            <c:forEach var="error" items="${bindingResult.getFieldErrors('endParkingTime')}">
                                <div class="input_error">${error.getDefaultMessage()}</div>
                            </c:forEach>
                        </c:if>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label for="levelId">Parking Level</label>
                    </td>
                    <td>
                        <select id="levelId" name="levelId" style="height: 40px; width: 300px;" >
                            <option value=" ">Select a Parking Floor</option>
                            <option value="1">Floor-1</option>
                            <option value="2">Floor-2</option>
                            <option value="3">Floor-3</option>
                        </select>
                        <c:if test="${bindingResult.hasFieldErrors('levelId')}">
                            <c:forEach var="error" items="${bindingResult.getFieldErrors('levelId')}">
                                <div class="input_error">${error.getDefaultMessage()}</div>
                            </c:forEach>
                        </c:if>
                    </td>
                </tr>
                <tr></tr> <tr></tr><tr></tr> <tr></tr> <tr></tr><tr></tr>
                <tr>
                    <td colspan="2">
                        <button type="submit" class="btn">Search</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>

</div>






<script>
    var today = new Date().toISOString().slice(0, 16);

    document.getElementsByName("startParkingTime")[0].min = today;
    document.getElementsByName("endParkingTime")[0].min = today;

</script>

<jsp:include page="../include/footer.jsp"/>