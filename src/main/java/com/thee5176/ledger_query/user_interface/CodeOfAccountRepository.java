package com.thee5176.ledger_query.user_interface;

import java.util.List;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import com.thee5176.ledger_query.record.domain.model.Tables;
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

    public List<BalanceQueryDTO> getBalancePerAccount(List<Integer> listOfCoa) {
        return dslContext.select(
            Tables.CODE_OF_ACCOUNT.CODE.as("coa"),
            Tables.CODE_OF_ACCOUNT.TYPE.as("coaBalanceType"),
            Tables.LEDGER_ITEMS.TYPE.as("balanceType"),
            Tables.LEDGER_ITEMS.AMOUNT.cast(Double.class).as("balance")
        )
        .from(Tables.CODE_OF_ACCOUNT)
        .leftJoin(Tables.LEDGER_ITEMS)
        .on(Tables.CODE_OF_ACCOUNT.CODE.eq(Tables.LEDGER_ITEMS.COA))
        .where(
            Tables.CODE_OF_ACCOUNT.LEVEL.eq(1)
            .and(Tables.CODE_OF_ACCOUNT.CODE.in(listOfCoa))
        )
        .fetchInto(BalanceQueryDTO.class);
    }
}
