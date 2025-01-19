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

    .btnact {
      background-color: #fff;
      color: #f39c12;
      text-decoration: none;
      padding: 10px 20px;
      border-radius: 5px;
      font-size: 1.2rem;
    }

    .btnact:hover {
      background-color: #e67e22;
    }

    a {
        color: #fff;
        font-size: 1.2rem;
    }

    a:hover {
        color: #e67e22;
    }


</style>

<div class="rate-container">
    <div class="preBookStyle">
        <form action="/ParkingSpot/deactivateParkingSpot" method="post">
            <h2>All Parking Spots</h2>
            <table>
                <tr>
                    <th>Parking Spot Name</th>
                    <th>Action</th>

                </tr>

                <c:forEach var="allParkingLevels" items="${parkingLevelList}">
                    <tr>
                        <td colspan="2" style="text-align: left;">${allParkingLevels.levelCode}</td>
                    </tr>
                    <c:forEach var="allParkingSpots" items="${parkingSpotList}">

                        <c:if test="${allParkingLevels.levelId eq allParkingSpots.parkingLevelId}">
                            <tr>
                                <td>${allParkingSpots.parkingSpotName}</td>
                                <td>
                                    <c:if test="${allParkingSpots.disable}">
                                      <button type="submit" class="btnact" onclick="submitForm('${allParkingSpots.parkingSpotId}')">Activate</button>
                                    </c:if>
                                    <c:if test="${!allParkingSpots.disable}">
                                      <button type="submit" class="btn" onclick="submitForm('${allParkingSpots.parkingSpotId}')">Deactivate</button>
                                    </c:if>
                                    <h3 style="text-align: center; color: #fff;">
                                        <c:if test="${not empty message}">
                                            <div>${message}</div>
                                        </c:if>
                                    </h3>
                                </td>
                            </tr>
                        </c:if>

                    </c:forEach>
                </c:forEach>
            </table>
            <input type="hidden" id="parkingSpotId" name="parkingSpotId" />
        </form>
    </div>

    <script>
        function submitForm(parkingSpotId){
            document.getElementById("parkingSpotId").value = parkingSpotId;
        }
    </script>


<jsp:include page="../include/footer.jsp"/>