package application.service.mapper;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import application.service.model.Application;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationMapper implements ResultSetMapper<Application>
{
    public Application map(int index, ResultSet resultSet, StatementContext statementContext) throws SQLException
    {
        return new Application()
                .setApplicationId(resultSet.getLong("applicationid"))
                .setFullName(resultSet.getString("fullname"));
    }
}