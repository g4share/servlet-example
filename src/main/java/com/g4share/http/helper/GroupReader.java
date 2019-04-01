//Copyright (c) 2023 g4share
package com.g4share.http.helper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GroupReader {

    public Map<String, String[]> readGroups() {
        final Map<String, String[]> data = new HashMap<>();

        final ConfigReader configReader = new ConfigReader("auth/pages", false);
        for (final String pages : configReader.readKeys()) {
            add(data, pages, configReader.read(pages));
        }
        return data;
    }

    private void add(final Map<String, String[]> data,
                     final String pagesRaw,
                     final String groupsRaw) {
        String[] groups = groupsRaw.split(",");
        String[] pages = pagesRaw.split(",");

        Arrays.stream(pages)
                .forEach(page -> data.put("/" + page, groups));

    }
}
