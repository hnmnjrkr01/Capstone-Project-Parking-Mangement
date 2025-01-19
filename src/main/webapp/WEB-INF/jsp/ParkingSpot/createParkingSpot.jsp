<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../include/header.jsp"/>

<div class="form-container" >
  <h2>Create Parking Spot</h2>
  <form action="/ParkingSpot/createParkingSpotSubmit" method="post">
    <table>
     <tr>
       <td>
         <label for="parkingSpotName">Parking Slot Name</label>
       </td>
       <td>
         <input type="text" id="parkingSpotName" name="parkingSpotName" value="${parkingSpotDTO.parkingSpotName}">
           <c:if test="${bindingResult.hasFieldErrors('parkingSpotName')}">
               <c:forEach var="error" items="${bindingResult.getFieldErrors('parkingSpotName')}">
                   <div class="input_error">${error.getDefaultMessage()}</div>
               </c:forEach>
           </c:if>
       </td>
     </tr>
      <tr>
        <td>
          <label for="parkingLevelId">Parking Level</label>
        </td>
        <td>
         <select id="parkingLevelId" name="parkingLevelId" value="${parkingSpotDTO.parkingLevelId}" style="height: 40px; width: 350px;">
             <option value="">Select Parking Level</option>
             <option value="1">Level-1</option>
             <option value="2">Level-2</option>
             <option value="3">Level-3</option>

         </select>
            <c:if test="${bindingResult.hasFieldErrors('parkingLevelId')}">
                <c:forEach var="error" items="${bindingResult.getFieldErrors('parkingLevelId')}">
                    <div class="input_error">${error.getDefaultMessage()}</div>
                </c:forEach>
            </c:if>
        </td>
      </tr>

      <tr>
        <td colspan="2">
          <button type="submit" style="width: 150px;">Create</button>
        </td>
      </tr>
    </table>
      <h4 style="text-align: center; color: #fff;">
          <c:if test="${not empty message}">
              <div>${message}</div>
          </c:if>
      </h4>
  </form>

</div>





<jsp:include page="../include/footer.jsp"/>
