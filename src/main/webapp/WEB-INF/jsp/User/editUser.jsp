<%--
  Created by IntelliJ IDEA.
  User: hnmnj
  Date: 1/5/2025
  Time: 7:54 AM
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
  <title>EasyPark</title>
</head>


<body>

<jsp:include page="../include/header.jsp"/>


<div class="form-container">
  <h2>User Edit</h2>
  <form action="/User/editSubmit" method="post" >
    <table>

      <tr>
        <td>
          <input type="hidden" name="id" value="${userEditDTO.id}">
          <input type="hidden" name="username" value="${userEditDTO.username}">
          <input type="hidden" name="password" value="${userEditDTO.password}">
          <input type="hidden" name="confirmPassword" value="${userEditDTO.confirmPassword}">

      </tr>
      <tr>
        <td>
          <lable for="firstname">First Name</lable>
        </td>
        <td>
          <input type="text" id="firstname"name="firstname" placeholder="First Name" value="${userEditDTO.firstname}">

          <c:if test="${bindingResult.hasFieldErrors('firstname')}">
            <c:forEach var="error" items="${bindingResult.getFieldErrors('firstname')}">
              <div class="input_error">${error.getDefaultMessage()}</div>
            </c:forEach>
          </c:if>
        </td>
      </tr>
      <tr>
        <td>
          <lable for="lastname">Last Name</lable>
        </td>
        <td>
          <input type="text" id="lastname" name="lastname" placeholder="Last Name" value="${userEditDTO.lastname}" >

          <c:if test="${bindingResult.hasFieldErrors('lastname')}">
            <c:forEach var="error" items="${bindingResult.getFieldErrors('lastname')}">
              <div class="input_error">${error.getDefaultMessage()}</div>
            </c:forEach>
          </c:if>
        </td>
      </tr>
      <tr>
        <td>
          <label for="phone">Contact Number</label>
        </td>
        <td>
          <input type="tel" id="phone" name="phone" placeholder="Phone Number" value="${userEditDTO.phone}" >

          <c:if test="${bindingResult.hasFieldErrors('phone')}">
            <c:forEach var="error" items="${bindingResult.getFieldErrors('phone')}">
              <div class="input_error">${error.getDefaultMessage()}</div>
            </c:forEach>
          </c:if>
        </td>
      </tr>
      <tr>
        <td>
          <label for="email">Email</label>
        </td>
        <td>
          <input type="email" id="email" name="email" placeholder="Email" value="${userEditDTO.email}">

          <c:if test="${bindingResult.hasFieldErrors('email')}">
            <c:forEach var="error" items="${bindingResult.getFieldErrors('email')}">
              <div class="input_error">${error.getDefaultMessage()}</div>
            </c:forEach>
          </c:if>
        </td>
      </tr>

      <tr></tr> <tr></tr>  <tr></tr> <tr></tr>  <tr></tr> <tr></tr>
      <tr>
        <td colspan="2">
          <button type="submit">Edit</button>
        </td>
      </tr>
    </table>
  </form>
</div>

<jsp:include page="../include/footer.jsp"/>
</body>

</html>
