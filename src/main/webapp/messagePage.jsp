<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="/WEB-INF/logged.tld"%>

<!DOCTYPE html>
<html>
    <%@ include file="/WEB-INF/include/header.jsp" %>
<body>
    <div class="container"></div>

    <jsp:include page="/WEB-INF/include/menu.jsp" />

    <div><br/><br/></div>

    <h5 class="center-align">Hi, <ct:userName/></h5>

    <div class="row">
        <div class="col s8 offset-s2">
            <div class="text-container">
                <pre>${message}</pre>
            </div>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>