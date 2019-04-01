<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <%@ include file="/WEB-INF/include/header.jsp" %>
<body>
<div class="container"></div>
<nav>
    <div class="nav-wrapper blue">
        <a href="" class="brand-logo">
            <img height="42" width="42" src="Servlet-JSP-Tutorial.png">
        </a>
    </div>
</nav>

<div>
    <br/><br/>
</div>

<div class="row">
    <div class="col s2 offset-s5">
        <form method="post" action="login">
            <div class="input-field">
                <input type="email" id="email" name="email" class="validate">
                <label class="active" for="email">Email</label>
            </div>
            <div class="input-field">
                <input type="password" id="password" name="password">
                <label class="active" for="password">Password</label>
            </div>
            <div class="right-align">
                <button class="btn waves-effect waves-light" type="submit" name="action">Login</button>
            </div>
        </form>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>