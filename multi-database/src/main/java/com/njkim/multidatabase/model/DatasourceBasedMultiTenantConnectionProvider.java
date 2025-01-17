package com.njkim.multidatabase.model;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Please describe the role of the DatasourceBasedMultiTenantConnectionProvider
 * <B>History:</B>
 * Created by namjug.kim on 2018. 9. 3.
 *
 * @author namjug.kim
 * @version 0.1
 * @since 2018. 9. 3.
 */
public class DatasourceBasedMultiTenantConnectionProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    private Map<String, DataSource> dataSourceMap;

    public DatasourceBasedMultiTenantConnectionProvider(Map<String, DataSource> dataSourceMap) {
        this.dataSourceMap = dataSourceMap;
    }

    @Override
    protected DataSource selectAnyDataSource() {
        return dataSourceMap.get("default");
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        DataSource dataSource = dataSourceMap.get(tenantIdentifier);

        if (dataSource == null) {
            throw new RuntimeException("Not found Data Source for " + tenantIdentifier);
        }

        return dataSource;
    }
}
