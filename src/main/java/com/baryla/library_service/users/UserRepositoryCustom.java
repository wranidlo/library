package com.baryla.library_service.users;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> getUsersWithAccountType(AccountType accountType);
}
