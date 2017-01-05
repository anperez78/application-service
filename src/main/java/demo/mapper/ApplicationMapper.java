package demo.mapper;

import demo.model.Application;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

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