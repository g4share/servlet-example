//Copyright (c) 2023 g4share
package com.g4share.http.helper;

import org.mindrot.jbcrypt.BCrypt;

public class Crypt {
    static public String hash(String email, String password) {
        return BCrypt.hashpw(email + "/" + password, BCrypt.gensalt());
    }

    static public boolean checkMatch(String email, String password, String hashed) {
        return email != null
                && password != null
                && hashed != null
                && BCrypt.checkpw(email + "/" + password, hashed);
    }
}
