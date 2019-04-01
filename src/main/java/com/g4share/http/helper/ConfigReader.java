//Copyright (c) 2023 g4share
package com.g4share.http.helper;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@RequiredArgsConstructor
@AllArgsConstructor
public class ConfigReader {
    @NonNull
    private final String propertyFile;
    private boolean localFirst = true;

    public boolean isEmpty() {
        return load(propertyFile).isEmpty();
    }

    public Set<String> readKeys() {
        final Properties prop = load(propertyFile);
        return prop.stringPropertyNames();
    }

    public Map<String, String> readAll() {
        final Map<String, String> data = new HashMap<>();

        final Properties prop = load(propertyFile);
        for (final String key : prop.stringPropertyNames()) {
            final String value = prop.getProperty(key);
            data.put(key, value);
        }

        return data;
    }

    public String read(final String propertyName) {
        final String propertyValue = localFirst
                ? readFrom("local." + propertyFile, propertyName)
                : null;

        return propertyValue != null
                ? propertyValue
                : readFrom(propertyFile, propertyName);
    }

    public int readInt(final String propertyName) {
        return Integer.parseInt(read(propertyName));
    }

    public long readLong(final String propertyName) {
        return Long.parseLong(read(propertyName));
    }

    private String readFrom(final String propertyFile, final String propertyName) {
        final Properties prop = load(propertyFile);
        return prop.getProperty(propertyName);
    }

    private Properties load(final String propertyFile) {
        final Properties prop = new Properties();

        try(final InputStream stream = getClass().getClassLoader().getResourceAsStream(propertyFile)) {
            if (stream == null) return prop;

            prop.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return prop;
    }
}
