<%@ taglib prefix="ct" uri="/WEB-INF/logged.tld"%>
<%@ page import="com.g4share.http.helper.MenuHelper" %>
<nav>
    <div class="nav-wrapper blue">
        <a href="" class="brand-logo">
            <img height="64" src="${pageContext.request.contextPath}/Servlet-JSP-Tutorial.png">
        </a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="${pageContext.request.contextPath}/index.jsp" class="<%= MenuHelper.ifActive(request, "selected", "/", "/index.jsp") %>">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/hello" class="<%= MenuHelper.ifActive(request, "selected", "/hello") %>">Hello</a></li>
            <li><a href="${pageContext.request.contextPath}/auth/hello" class="<%= MenuHelper.ifActive(request, "selected", "/auth/hello") %>">Auth Hello</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/hello" class="<%= MenuHelper.ifActive(request, "selected", "/admin/hello") %>">Admin Hello</a></li>
            <li><a href="${pageContext.request.contextPath}/hello-api" class="<%= MenuHelper.ifActive(request, "selected", "/hello-api") %>">Hello Api</a></li>

            <li>
                <ct:logged
                        messageLogged="<a href='logout'>Logout</a>"
                        messageNotLogged="<a href='login'>Login</a>"/>
            </li>
        </ul>
    </div>
</nav>