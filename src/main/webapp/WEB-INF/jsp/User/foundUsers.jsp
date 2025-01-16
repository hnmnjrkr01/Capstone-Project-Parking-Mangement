<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel='stylesheet' type='text/css' href="/pub/css/globalStyleSheet.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="javaScripts/loginJQuery.js"></script>
  <title>EasyPark</title>
</head>
<style>

  .preBookStyle {
    background-color: rgba(0, 0, 0, 0.8);
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    padding: 15px 40px 20px 40px;
    margin: 10px;
    margin-left: auto;
    margin-right: auto;
    margin-top: 180px;
    max-width: 600px;
    width: 100%;
    float: inherit;

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


</style>


<body>

<jsp:include page="../include/header.jsp"/>

<div class="preBookStyle" >
  <h2>Found Users</h2>
  <form action="/User/editUser">
    <table  >
    <tr>
      <th>Firstname</th>
      <th>Lastname</th>
      <th>Email</th>
      <th>Edit</th>
    </tr>

      <c:if test="${not empty lastname}">
      <c:forEach var="user" items="${userList}">
        <tr>
          <td>${user.firstname}</td>
          <td>${user.lastname}</td>
          <td>${user.email}</td>
          <td><a href="/User/edit/${user.id}">Edit</a></td>
        </tr>

      </c:forEach>
      </c:if>

    </table>

  </form>

</div>

<jsp:include page="../include/footer.jsp"/>

</body>

</html>