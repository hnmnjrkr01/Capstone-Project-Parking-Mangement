


<!DOCTYPE html>
<html lang="en">

<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="/pub/js/loginJQuery.js"></script>

</head>
<style>
    .checkBoxClass{
        display: inline-block;
    }
</style>


<body>
<jsp:include page="../include/header.jsp"/>


<div class="form-container">
    <h2>Login</h2>
    <form action="/login/loginSubmit" method="post">
        <table>
            <tr>
                <td>
                    <label for="username">Username: </label>
                </td>
                <td>
                    <input type="text" id="username"  name ="username" placeholder="username">
                </td>
            </tr>
            <tr>
                <td><label for="password">Password: </label></td>
                <td>
                    <input type="password" id="password" name="password" placeholder="Password">
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <button type="submit" style="width: 150px;">Login</button>
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <div class="forgot-password">
                        <a href="forgotpassword.html">Forgot Password?</a>
                    </div>
                </td>
            </tr>

            <div id="emptyError"></div>
        </table>

    </form>

</div>

<jsp:include page="../include/footer.jsp"/>

</body>

</html>