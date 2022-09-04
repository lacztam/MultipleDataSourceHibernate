/*
package hu.multipledatasource.model.Properties;

import com.sun.istack.NotNull;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.metrics.MetricsTrackerFactory;
import hu.multipledatasource.service.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ScheduledExecutorService;

@Entity
public class PropertiesModel extends HikariDataSource {
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String driverClassName;
    @NotNull
    private String jdbcUrl;
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String dataSourceProperties;
    private boolean currentlyInUse;

    public PropertiesModel() {
    }

    public boolean isCurrentlyInUse() {
        return currentlyInUse;
    }

    public void setCurrentlyInUse(boolean currentlyInUse) {
        if(currentlyInUse){
            PropertiesService propertiesService = new PropertiesService();

            propertiesService.disableOtherProperties(this.id);
        }

        this.currentlyInUse = currentlyInUse;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDataSourcePropertiesAsString() {
        return dataSourceProperties;
    }

    public void setDataSourceProperties(String dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    public List<String> getDataSourcePropertiesAsList() {
        List<String> propertyList = new ArrayList<>();
        String[] splittedProperties = this.dataSourceProperties.split(";");
        if (splittedProperties != null) {
            for (int i = 0; i < splittedProperties.length; i++)
                splittedProperties[i] = splittedProperties[i].strip();

            for (String property : splittedProperties)
                propertyList.add(property);
            Collections.sort(propertyList);

            return propertyList;
        }else{
            return null;
        }
    }

    public void addProperty(String property) {
        this.dataSourceProperties += ";" + property;
    }


    @Override
    public String getCatalog() {
        return super.getCatalog();
    }

    @Override
    public void setCatalog(String catalog) {
        super.setCatalog(catalog);
    }

    @Override
    public long getConnectionTimeout() {
        return super.getConnectionTimeout();
    }

    @Override
    public void setConnectionTimeout(long connectionTimeoutMs) {
        super.setConnectionTimeout(connectionTimeoutMs);
    }

    @Override
    public long getIdleTimeout() {
        return super.getIdleTimeout();
    }

    @Override
    public void setIdleTimeout(long idleTimeoutMs) {
        super.setIdleTimeout(idleTimeoutMs);
    }

    @Override
    public long getLeakDetectionThreshold() {
        return super.getLeakDetectionThreshold();
    }

    @Override
    public void setLeakDetectionThreshold(long leakDetectionThresholdMs) {
        super.setLeakDetectionThreshold(leakDetectionThresholdMs);
    }

    @Override
    public long getMaxLifetime() {
        return super.getMaxLifetime();
    }

    @Override
    public void setMaxLifetime(long maxLifetimeMs) {
        super.setMaxLifetime(maxLifetimeMs);
    }

    @Override
    public int getMaximumPoolSize() {
        return super.getMaximumPoolSize();
    }

    @Override
    public void setMaximumPoolSize(int maxPoolSize) {
        super.setMaximumPoolSize(maxPoolSize);
    }

    @Override
    public int getMinimumIdle() {
        return super.getMinimumIdle();
    }

    @Override
    public void setMinimumIdle(int minIdle) {
        super.setMinimumIdle(minIdle);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public long getValidationTimeout() {
        return super.getValidationTimeout();
    }

    @Override
    public void setValidationTimeout(long validationTimeoutMs) {
        super.setValidationTimeout(validationTimeoutMs);
    }

    @Override
    public String getConnectionTestQuery() {
        return super.getConnectionTestQuery();
    }

    @Override
    public void setConnectionTestQuery(String connectionTestQuery) {
        super.setConnectionTestQuery(connectionTestQuery);
    }

    @Override
    public String getConnectionInitSql() {
        return super.getConnectionInitSql();
    }

    @Override
    public void setConnectionInitSql(String connectionInitSql) {
        super.setConnectionInitSql(connectionInitSql);
    }

    @Override
    public DataSource getDataSource() {
        return super.getDataSource();
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public String getDataSourceClassName() {
        return super.getDataSourceClassName();
    }

    @Override
    public void setDataSourceClassName(String className) {
        super.setDataSourceClassName(className);
    }

    @Override
    public void addDataSourceProperty(String propertyName, Object value) {
        super.addDataSourceProperty(propertyName, value);
    }

    @Override
    public String getDataSourceJNDI() {
        return super.getDataSourceJNDI();
    }

    @Override
    public void setDataSourceJNDI(String jndiDataSource) {
        super.setDataSourceJNDI(jndiDataSource);
    }

    @Override
    public Properties getDataSourceProperties() {
        return super.getDataSourceProperties();
    }

    @Override
    public void setDataSourceProperties(Properties dsProperties) {
        super.setDataSourceProperties(dsProperties);
    }

    @Override
    public String getDriverClassName() {
        return this.driverClassName;
    }

    @Override
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    @Override
    public String getJdbcUrl() {
        return this.jdbcUrl;
    }

    @Override
    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    @Override
    public boolean isAutoCommit() {
        return super.isAutoCommit();
    }

    @Override
    public void setAutoCommit(boolean isAutoCommit) {
        super.setAutoCommit(isAutoCommit);
    }

    @Override
    public boolean isAllowPoolSuspension() {
        return super.isAllowPoolSuspension();
    }

    @Override
    public void setAllowPoolSuspension(boolean isAllowPoolSuspension) {
        super.setAllowPoolSuspension(isAllowPoolSuspension);
    }

    @Override
    public long getInitializationFailTimeout() {
        return super.getInitializationFailTimeout();
    }

    @Override
    public void setInitializationFailTimeout(long initializationFailTimeout) {
        super.setInitializationFailTimeout(initializationFailTimeout);
    }

    @Override
    public boolean isIsolateInternalQueries() {
        return super.isIsolateInternalQueries();
    }

    @Override
    public void setIsolateInternalQueries(boolean isolate) {
        super.setIsolateInternalQueries(isolate);
    }

    @Override
    public MetricsTrackerFactory getMetricsTrackerFactory() {
        return super.getMetricsTrackerFactory();
    }

    @Override
    public void setMetricsTrackerFactory(MetricsTrackerFactory metricsTrackerFactory) {
        super.setMetricsTrackerFactory(metricsTrackerFactory);
    }

    @Override
    public Object getMetricRegistry() {
        return super.getMetricRegistry();
    }

    @Override
    public void setMetricRegistry(Object metricRegistry) {
        super.setMetricRegistry(metricRegistry);
    }

    @Override
    public Object getHealthCheckRegistry() {
        return super.getHealthCheckRegistry();
    }

    @Override
    public void setHealthCheckRegistry(Object healthCheckRegistry) {
        super.setHealthCheckRegistry(healthCheckRegistry);
    }

    @Override
    public Properties getHealthCheckProperties() {
        return super.getHealthCheckProperties();
    }

    @Override
    public void setHealthCheckProperties(Properties healthCheckProperties) {
        super.setHealthCheckProperties(healthCheckProperties);
    }

    @Override
    public void addHealthCheckProperty(String key, String value) {
        super.addHealthCheckProperty(key, value);
    }

    @Override
    public long getKeepaliveTime() {
        return super.getKeepaliveTime();
    }

    @Override
    public void setKeepaliveTime(long keepaliveTimeMs) {
        super.setKeepaliveTime(keepaliveTimeMs);
    }

    @Override
    public boolean isReadOnly() {
        return super.isReadOnly();
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        super.setReadOnly(readOnly);
    }

    @Override
    public boolean isRegisterMbeans() {
        return super.isRegisterMbeans();
    }

    @Override
    public void setRegisterMbeans(boolean register) {
        super.setRegisterMbeans(register);
    }

    @Override
    public String getPoolName() {
        return super.getPoolName();
    }

    @Override
    public void setPoolName(String poolName) {
        super.setPoolName(poolName);
    }

    @Override
    public ScheduledExecutorService getScheduledExecutor() {
        return super.getScheduledExecutor();
    }

    @Override
    public void setScheduledExecutor(ScheduledExecutorService executor) {
        super.setScheduledExecutor(executor);
    }

    @Override
    public String getTransactionIsolation() {
        return super.getTransactionIsolation();
    }

    @Override
    public String getSchema() {
        return super.getSchema();
    }

    @Override
    public void setSchema(String schema) {
        super.setSchema(schema);
    }
}
*/
