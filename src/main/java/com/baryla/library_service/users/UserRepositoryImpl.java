package com.baryla.library_service.users;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = false)
public class UserRepositoryImpl implements UserRepositoryCustom{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> getUsersWithAccountType(AccountType accountType) {
        Query query = entityManager.createNativeQuery("SELECT users.* FROM jpa_users.user as users " +
                "WHERE users.account_type LIKE ?", User.class);
        query.setParameter(1, accountType.getValue() + "%");
        System.out.println(accountType.getValue());
        return query.getResultList();
    }
}
