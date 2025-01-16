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


<div class="form-container" >
    <h2>Available-Slots</h2>
    <form action="">
        ${availableSlots} Slots are available between ${startParking} and ${endParking}.
    </form>

</div>


<jsp:include page="../include/footer.jsp"/>