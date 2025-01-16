function submitMethod(event) {
    event.preventDefault();

    let email = $("#email").val();
    let password = $("#password").val();
    let errorMsg = "Email or Password are Empty!!";
    let emailError = "Please enter correct email!"
    //---------If Inputs are empty-------------------------

    if (email == "" || password == "") {
        console.log("----Here-----------");
        $("#emailLabel").css("color", "red");
        $("#passwordLabel").css("color", "red");
        $("#emptyError").text(errorMsg).css("color", "red");
    } else {
       // alert("Welcome " + email);
        $("#emptyError").hide;
    }

    //---------------Email Varification--------------------

    if (email.length > 0) {
        let emailRegEx = /^[a-zA-Z0-9._]+@[a-zA-Z]+.com+$/.test(email);
        if (!emailRegEx) {
            $("#emailLabel").css("color", "red");
            $("#emptyError").text(emailError).css("color", "red");
        }else{
            $("#emailLabel").css("color", "white");
            $("#emptyError").hide;
        }
    }


}