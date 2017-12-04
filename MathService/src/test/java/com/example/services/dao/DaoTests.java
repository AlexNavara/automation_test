package com.example.services.dao;

import com.example.DbConfig;
import com.example.dao.EquationDAO;
import com.example.dao.EquationDAOImpl;
import org.dbunit.JdbcBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;

public class DaoTests extends JdbcBasedDBTestCase {

    private EquationDAO dao = new EquationDAOImpl();

    public void testShouldFindAll()
    {
        Assert.assertEquals(10, dao.findAll().size());
    }

    public void testShouldRemoveAll()
    {
        dao.deleteAll();
    }

    public void testShouldRemoveById()
    {
        for (int i = 1; i < 11; i++) {
            dao.delete(i);
        }
    }

    @Override
    protected String getConnectionUrl() {
        return DbConfig.INSTANCE.getDbUrl();
    }

    @Override
    protected String getDriverClass() {
        return DbConfig.INSTANCE.getDriverClass();
    }

    @Override
    protected String getUsername() {
        return DbConfig.INSTANCE.getUserName();
    }

    @Override
    protected String getPassword() {
        return DbConfig.INSTANCE.getPassword();
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getClass().getClassLoader().getResource("my_data.xml"));
    }
}
