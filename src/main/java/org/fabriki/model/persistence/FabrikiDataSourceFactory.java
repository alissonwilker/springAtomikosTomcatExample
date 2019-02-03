package org.fabriki.model.persistence;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory;
import org.fabriki.utils.IHerokuConstantes;

public class FabrikiDataSourceFactory extends BasicDataSourceFactory {
    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment)
        throws Exception {
        String dbUrl = System.getenv(IHerokuConstantes.ENV_JDBC_DATABASE_URL);

        BasicDataSource dataSource = (BasicDataSource) super.getObjectInstance(obj, name, nameCtx, environment);
        dataSource.setUrl(dbUrl);

        return dataSource;
    }
}
