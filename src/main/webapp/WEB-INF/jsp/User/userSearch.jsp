

<jsp:include page="../include/header.jsp"/>

<div class="form-container" >
    <h2>Search User</h2>
    <form action="/User/searchUserByLastname">
        <table>
            <td><label for="lastname">Lastname</label>
            </td>
                <td>
                    <input type="text" id="lastname" name="lastname"placeholder="lastname">
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <button type="submit" style="width: 150px;">Search</button>
                </td>
            </tr>
        </table>
    </form>

</div>

<jsp:include page="../include/footer.jsp"/>

