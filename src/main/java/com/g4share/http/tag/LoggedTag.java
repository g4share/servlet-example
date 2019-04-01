//Copyright (c) 2023 g4share
package com.g4share.http.tag;

import com.g4share.http.data.Principal;
import com.g4share.http.helper.SessionHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import lombok.Setter;

import java.io.IOException;

@Setter
public class LoggedTag extends SimpleTagSupport {

    private String messageLogged;
    private String messageNotLogged;

    private SessionHelper sessionHelper = new SessionHelper();

    @Override
    public void doTag() throws IOException {
        final PageContext pageContext = (PageContext)getJspContext();
        final HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();

        final Principal principal =  sessionHelper.getPrincipal(request);
        getJspContext().getOut().print(principal.authenticated() ? messageLogged : messageNotLogged);
    }
}
