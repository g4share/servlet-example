//Copyright (c) 2023 g4share
package com.g4share.http.filter;

import com.g4share.http.data.User;
import com.g4share.http.helper.GroupReader;
import com.g4share.http.helper.SessionHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

public class AuthorizationGuard implements FilterGuard {

    private final SessionHelper sessionHelper;

    private final Map<String, String[]> groups;

    private final GroupReader groupReader = new GroupReader();

    public AuthorizationGuard(final SessionHelper sessionHelper) {
        this.sessionHelper = sessionHelper;

        groups = groupReader.readGroups();
    }

    @Override
    public boolean check(final HttpServletRequest request, final HttpServletResponse response) {
        String path = sessionHelper.path(request);

        final User currentUser = sessionHelper.getPrincipal(request).user();
        return checkGroup(currentUser, path);
    }

    private boolean checkGroup(final User user, final String servletPath) {
       final String[] servletGroups = groups.get(servletPath);
        if (servletGroups == null) {
            return false;
        }

        for (final String group : servletGroups) {
            if (user.isInGroup(group)) {
                return true;
            }
        }

        return false;
    }
}
