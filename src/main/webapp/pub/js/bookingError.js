
    document.getElementById("bookingForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const parkingFromDateTime = document.getElementById("FromparkingTime").value;
    const parkingToDateTime = document.getElementById("ToparkingTime").value;

    if (parkingFromDateTime != "" && parkingToDateTime != "") {
    alert("Searching parking availability for: "+ parkingFromDateTime +" "+parkingToDateTime);
    //-------future code----------------------
} else {
    alert("Please select both date and time.");
}
});
