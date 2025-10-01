package com.thee5176.ledger_query.security;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import com.thee5176.ledger_query.auth.RegisterRequest;
import com.thee5176.ledger_query.record.domain.model.credential.Tables;
import com.thee5176.ledger_query.record.domain.model.credential.tables.pojos.Users;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@AllArgsConstructor
@Slf4j
public class JOOQUsersRepository {
    private final DSLContext dsl;

    public void createUser(RegisterRequest user) {
        dsl.insertInto(Tables.USERS)
                .columns(
                    Tables.USERS.ID,
                    Tables.USERS.USERNAME,
                    Tables.USERS.PASSWORD,
                    Tables.USERS.IS_ENABLED,
                    Tables.USERS.IS_ACCOUNT_NON_EXPIRED,
                    Tables.USERS.IS_CREDENTIALS_NON_EXPIRED,
                    Tables.USERS.IS_ACCOUNT_NON_LOCKED,
                    Tables.USERS.FIRSTNAME,
                    Tables.USERS.LASTNAME,
                    Tables.USERS.EMAIL
                )
                .values(
                    user.getId(),
                    user.getUsername(),
                    user.getPassword(),
                    true,
                    true,
                    true,
                    true,
                    user.getFirstname(),
                    user.getLastname(),
                    user.getEmail()
                )
                .execute();
    }



    public Users fetchUserByUsername(String username) {
        log.info("Fetching user by username: {}", username);
        
        Users user = dsl.select()
                .from(Tables.USERS)
                .where(Tables.USERS.USERNAME.eq(username))
                .fetchOneInto(Users.class);
                
        if (user != null) {
            log.info("Found user: ID={}, Username={}", user.getId(), user.getUsername());
        } else {
            log.warn("No user found with username: {}", username);
        }
        
        return user;
    }
}
