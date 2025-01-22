


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




<jsp:include page="../include/header.jsp"/>


<div class="preBookStyle" >
  <h2>Parking Spot Availability for Guests</h2>
  <hr><br><br>
  <form action="">

   <b>Start Parking Date & Time</b>  ${startParking}<br><br>
   <b>End Parking Date & Time</b>  ${endParking}<br><br>
    <b>Available Spot for Guest</b> ${availableSlots}<br><br>

    <br><br>
    <i style="color: #f39c12">
      To book a spot please login or sign up.
    </i>
  </form>

</div>

<jsp:include page="../include/footer.jsp"/>
