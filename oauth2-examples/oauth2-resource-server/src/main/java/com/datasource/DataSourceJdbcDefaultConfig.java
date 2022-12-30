package com.datasource;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:datasource-jdbc-${spring.profiles.active}.properties", ignoreResourceNotFound = true)
public class DataSourceJdbcDefaultConfig 
{
    @Value("${datasource.jdbc.default.url}")
    private String url;
    @Value("${datasource.jdbc.default.username}")
    private String user;
    @Value("${datasource.jdbc.default.password}")
    private String password;
    @Value("${datasource.jdbc.default.driver-class-name}")
    private String driverClass;
    @Value("${datasource.jdbc.default.filters}")
    private String filters;
    @Value("${datasource.jdbc.default.initialSize}")
    private int initialSize;
    @Value("${datasource.jdbc.default.maxActive}")
    private int maxActive;
    @Value("${datasource.jdbc.default.minIdle}")
    private int minIdle;
    @Value("${datasource.jdbc.default.maxWait}")
    private int maxWait;

//    @ConfigurationProperties(prefix = "datasource.jdbc") 
    @Bean(name = "dataSourceJdbc")
    public DataSource dataSourceJdbc() throws SQLException 
    {
    	HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setMinimumIdle(minIdle);
        dataSource.setMaximumPoolSize(maxActive);
        dataSource.setMaxLifetime(maxWait);
        return dataSource;
    }
 
    @Bean//(name = "mysqlJdbcTemplate")
	public JdbcTemplate jdbcOperations(@Qualifier("dataSourceJdbc") DataSource dataSource)
	{
		return new JdbcTemplate(dataSource);
	}
    
	@Bean(name = "transactionManagerJdbc")
	@DependsOn({ "dataSourceJdbc" })
	public DataSourceTransactionManager transactionManagerJdbc() throws SQLException 
	{
		return new DataSourceTransactionManager(dataSourceJdbc());
	}
	
	@Bean(name = "transactionInterceptorJdbc")
	@DependsOn({ "transactionManagerJdbc" })
	public TransactionInterceptor transactionInterceptorJdbc() throws Throwable
	{
		Properties prop = new Properties();
		prop.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
		prop.setProperty("imp*", "PROPAGATION_REQUIRED,-Exception");
		prop.setProperty("upload*", "PROPAGATION_REQUIRED,-Exception");
		prop.setProperty("del*", "PROPAGATION_REQUIRED,-Exception");
		prop.setProperty("upd*", "PROPAGATION_REQUIRED,-Exception");
		prop.setProperty("get*", "PROPAGATION_NEVER,readOnly");
		prop.setProperty("query*", "PROPAGATION_NEVER,readOnly");
		TransactionInterceptor ti = new TransactionInterceptor();
		ti.setTransactionManager(transactionManagerJdbc());
		ti.setTransactionAttributes(prop);
		return ti;
	}

	@Bean(name = "beanNameAutoProxyCreatorJdbc")
	public BeanNameAutoProxyCreator beanNameAutoProxyCreatorJdbc() throws Throwable
	{
		BeanNameAutoProxyCreator bpc = new BeanNameAutoProxyCreator();
		bpc.setProxyTargetClass(true);
		bpc.setBeanNames("*Service");
		bpc.setInterceptorNames("transactionInterceptorJdbc");
		return bpc;
	}
}