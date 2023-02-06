package com.datasource;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:datasource-mybatis-${spring.profiles.active}.properties", ignoreResourceNotFound = true)
public class DataSourceMybatisDefaultConfig 
{
    @Value("${datasource.mybatis.default.url}")
    private String url;
    @Value("${datasource.mybatis.default.username}")
    private String user;
    @Value("${datasource.mybatis.default.password}")
    private String password;
    @Value("${datasource.mybatis.default.driver-class-name}")
    private String driverClass;
    @Value("${datasource.mybatis.default.minimumIdle}")
    private int minimumIdle;
    @Value("${datasource.mybatis.default.maximumPoolSize}")
    private int maximumPoolSize;
    @Value("${datasource.mybatis.default.maxLifetime}")
    private int maxLifetime;
    @Value("${datasource.mybatis.default.mapper-location}")
    private String mapperLocation;
    @Value("${mybatis.config-location}")
    private String mybatisConfigLocation;
 
    @Bean(name = "dataSourceMybatis")
    public DataSource dataSource() throws SQLException 
    {
    	HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setMinimumIdle(minimumIdle);
        dataSource.setMaximumPoolSize(maximumPoolSize);
        dataSource.setMaxLifetime(maxLifetime);
        return dataSource;
    }
 
    @Bean(name = "sqlSessionFactoryMybatis")
    @DependsOn({ "dataSourceMybatis" })
    public SqlSessionFactory sqlSessionFactory() throws Exception 
    {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocation));
        sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(mybatisConfigLocation));
        return sessionFactory.getObject();
    }

	@Bean(name = "sqlSessionTemplateMybatis")
	@DependsOn({ "sqlSessionFactoryMybatis" })
	public SqlSessionTemplate sqlSessionTemplate() throws Exception 
	{
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	@Bean(name = "transactionManagerMybatis")
	@DependsOn({ "dataSourceMybatis" })
	public DataSourceTransactionManager transactionManager() throws SQLException 
	{
		return new DataSourceTransactionManager(dataSource());
	}
	
	@Bean(name = "transactionInterceptorMybatis")
	@DependsOn({ "transactionManagerMybatis" })
	public TransactionInterceptor transactionInterceptor() throws Throwable
	{
		Properties prop = new Properties();
		
		prop.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
		prop.setProperty("del*", "PROPAGATION_REQUIRED,-Exception");
		prop.setProperty("upd*", "PROPAGATION_REQUIRED,-Exception");
		
		prop.setProperty("create*", "PROPAGATION_REQUIRED,-Exception");
		prop.setProperty("modify*", "PROPAGATION_REQUIRED,-Exception");
		prop.setProperty("remove*", "PROPAGATION_REQUIRED,-Exception");
		
		prop.setProperty("insert*", "PROPAGATION_REQUIRED,-Exception");
		prop.setProperty("update*", "PROPAGATION_REQUIRED,-Exception");
		prop.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");
		
		prop.setProperty("get*", "PROPAGATION_NEVER,readOnly");
		prop.setProperty("query*", "PROPAGATION_NEVER,readOnly");
		
		prop.setProperty("imp*", "PROPAGATION_REQUIRED,-Exception");
		prop.setProperty("exp*", "PROPAGATION_NEVER,readOnly");
		
		prop.setProperty("import*", "PROPAGATION_REQUIRED,-Exception");
		prop.setProperty("export*", "PROPAGATION_NEVER,readOnly");
		
		prop.setProperty("upload*", "PROPAGATION_REQUIRED,-Exception");
		prop.setProperty("download*", "PROPAGATION_NEVER,readOnly");
		
		TransactionInterceptor ti = new TransactionInterceptor();
		ti.setTransactionManager(transactionManager());
		ti.setTransactionAttributes(prop);
		return ti;
	}

	@Bean(name = "beanNameAutoProxyCreatorMybatis")
	public BeanNameAutoProxyCreator beanNameAutoProxyCreator() throws Throwable
	{
		BeanNameAutoProxyCreator bpc = new BeanNameAutoProxyCreator();
		bpc.setProxyTargetClass(true);
		bpc.setBeanNames("*Service");
		bpc.setInterceptorNames("transactionInterceptorMybatis");
		return bpc;
	}
}