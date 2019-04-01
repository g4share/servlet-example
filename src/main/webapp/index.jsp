<%@ page import="java.util.*" %>
<html>
<head>
    <title>Echo</title>
    <style>
        <jsp:include page="style.css" flush="true"/>
    </style>
</head>
<body>

<div class="container" style="width: 70%;">

    <div class="table">
        <div class="table-content">
            <div class="table-data"><h2><br/>HTTP Request Headers Received<br/><br/></h2></div>
        </div>
        <div class="table-header">
            <div class="header__item filter__link">Name</div>
            <div class="header__item filter__link">Value</div>
        </div>
        <div class="table-content">
            <%
                Enumeration eNames = request.getHeaderNames();
                while (eNames.hasMoreElements()) {
                    String name = (String) eNames.nextElement();
                    String value = normalize(request.getHeader(name));
            %>
                <div class="table-row">
                    <div class="table-data"><%= name %></div>
                    <div class="table-data"><%= value %></div>
                </div>
            <%
                }
            %>
        </div>
    </div>
</div>

</body>
</html>
<%!
    //
    // This function is just an example and should be treated as such.
    //
    private String normalize(String value) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            sb.append(c);
            if (c == ';')
                sb.append("<br>");
        }
        return sb.toString();
    }
%>