<%--
  Created by IntelliJ IDEA.
  User: hnmnj
  Date: 1/1/2025
  Time: 11:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../include/header.jsp"/>


<div class="form-container" >
    <h1>Parking Booking</h1>

    <form action="/Booking/bookNewParking" method="post">
        <div style="padding-right: 40px; ">
            <table>
                <tr>
                    <td>
                        <label for="startDateTime">From DateTime</label>
                    </td>
                    <td>
                        <input type="datetime-local" id="startDateTime" name="startDateTime" placeholder="From" value="${bookingDTO.startDateTime}">
                        <c:if test="${bindingResult.hasFieldErrors('startDateTime')}">
                            <c:forEach var="error" items="${bindingResult.getFieldErrors('startDateTime')}">
                                <div class="input_error">${error.getDefaultMessage()}</div>
                            </c:forEach>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="endDateTime">To Date-Time</label>
                    </td>
                    <td>
                        <input type="datetime-local" id="endDateTime" name="endDateTime" placeholder="To" value="${bookingDTO.endDateTime}">
                        <c:if test="${bindingResult.hasFieldErrors('endDateTime')}">
                            <c:forEach var="error" items="${bindingResult.getFieldErrors('endDateTime')}">
                                <div class="input_error">${error.getDefaultMessage()}</div>
                            </c:forEach>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="licensePlateNumber">License Plate No</label>
                    </td>
                    <td>
                        <input type="TEXT" id="licensePlateNumber" name="licensePlateNumber" placeholder="license Plate Number" value="${bookingDTO.licensePlateNumber}">
                        <c:if test="${bindingResult.hasFieldErrors('licensePlateNumber')}">
                            <c:forEach var="error" items="${bindingResult.getFieldErrors('licensePlateNumber')}">
                                <div class="input_error">${error.getDefaultMessage()}</div>
                            </c:forEach>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="levelId">Parking Level</label>
                    </td>
                    <td>
                        <select id="levelId" name="levelId" style="height: 40px; width: 325px;" value="${bookingDTO.levelId}">
                            <option value="">Select a Parking Floor</option>
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
                    <td>
                        <label for="paymentMethod">Payment Method</label>
                    </td>
                    <td>
                        <select id="paymentMethod" name="paymentMethod"  style="height: 40px; width: 325px;" value="${bookingDTO.paymentMethod}">
                            <option value="">Select a payment method</option>
                            <option value="card">Card</option>
                            <option value="cash">Cash</option>
                            <option value="cheq">Cheque</option>
                            <option value="venmo">Venmo</option>
                        </select>
                        <c:if test="${bindingResult.hasFieldErrors('paymentMethod')}">
                            <c:forEach var="error" items="${bindingResult.getFieldErrors('paymentMethod')}">
                                <div class="input_error">${error.getDefaultMessage()}</div>
                            </c:forEach>
                        </c:if>
                    </td>
                </tr>

               <tr></tr> <tr></tr><tr></tr> <tr></tr> <tr></tr><tr></tr>
                <tr>
                    <td> </td>
                    <td >
                        <button type="submit" class="btn" >Book </button>
                        <button type="reset" class="btn">Clear</button>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>

<script>
    var today = new Date().toISOString().slice(0, 16);

    document.getElementsByName("StarDateTime")[0].min = today;
    document.getElementsByName("EndDateTime")[0].min = today;
</script>
<jsp:include page="../include/footer.jsp"/>