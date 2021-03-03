package com.baryla.library_service.users;

import java.util.HashMap;
import java.util.Map;

public enum AccountType {
    FREE(0),
    PREMIUM(1);

    private int value;
    private static Map map = new HashMap<>();

    private AccountType(int value) {
        this.value = value;
    }

    static {
        for (AccountType pageType : AccountType.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static AccountType valueOf(int pageType) {
        return (AccountType) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
