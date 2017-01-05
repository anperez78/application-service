package demo.dao;

import demo.mapper.ApplicationMapper;
import demo.model.Application;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;


import java.util.List;


@RegisterMapper(ApplicationMapper.class)
public interface ApplicationDAO {

    @SqlQuery("select * from applications")
    public List<Application> getAll();

    @SqlQuery("select * from applications where applicationid = :applicationId")
    public Application findById(@Bind("applicationId") long applicationId);

    @SqlUpdate("delete from applications where applicationid = :applicationId")
    public long deleteById(@Bind("applicationId") long applicationId);

    @SqlUpdate("insert into applications (fullname) values (:fullName)")
    @GetGeneratedKeys
    public long insert(@Bind("fullName") String fullName);
}