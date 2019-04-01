<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <%@ include file="/WEB-INF/include/header.jsp" %>
<body>
    <div class="container"></div>

    <jsp:include page="/WEB-INF/include/menu.jsp" />

    <div><br/><br/></div>

    <jsp:include page="/WEB-INF/include/greetings.jsp" />

    <div class="row">
        <div class="col s2 offset-s5">
            <form>
                <div class="input-field">
                    <input type="number" id="number">
                    <label class="active" for="number">Guess the number</label>
                </div>

                <br/>
                <div class="right-align">
                    <button class="btn waves-effect waves-light" type="submit" name="action">Guesssssss</button>
                </div>
            </form>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>