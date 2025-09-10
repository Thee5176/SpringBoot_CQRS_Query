package com.thee5176.ledger_query.user_interface;

import java.util.List;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import com.thee5176.ledger_query.record.domain.model.accounting.Tables;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class CodeOfAccountRepository {

    private final DSLContext dslContext;

    public List<AvailableCodeOfAccountDto> getAvailableCoa() {
        return dslContext.select(
                Tables.CODE_OF_ACCOUNT.CODE.as("code"),
                Tables.CODE_OF_ACCOUNT.TITLE.as("title"),
                Tables.CODE_OF_ACCOUNT.TYPE.as("type")
            )
            .from(Tables.CODE_OF_ACCOUNT)
            .where(Tables.CODE_OF_ACCOUNT.LEVEL.eq(1))
            .fetchInto(AvailableCodeOfAccountDto.class);
    }
}
