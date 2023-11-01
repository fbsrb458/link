package com.myweb.www.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;



@Configuration
//<mybatis-spring:scan base-package="com.ezen.myProject.repository"/>
@MapperScan(basePackages = {"com.myweb.www.repository"})
//servlet-context.xml
//<context:component-scan base-package="com.ezen.myProject" />
@ComponentScan(basePackages = {"com.myweb.www.service", "com.myweb.www.handler"})

@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableScheduling
public class RootConfig {
	//DB 설정
	//어노테이션으로 처리
	///root-context.xml
	
	//DB 설정부분
	//전과 달라진 부분 log4jdbc-log4j2 사용
	//hikariCP 사용
	
	//@Autowired : 인젝트와 비슷하다.
	@Autowired
	ApplicationContext applicationContext;
	
	@Bean
	public DataSource dataSource() {
		HikariConfig hikaroConfig = new HikariConfig();
		//log4jdbc-log4j2의 드라이버 클래스 url 사용
		hikaroConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		hikaroConfig.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/springdb");
		hikaroConfig.setUsername("springuser");
		hikaroConfig.setPassword("mysql");
		
		hikaroConfig.setMaximumPoolSize(5); //최대 커넥션 개수(반드시 같은 값으로 설정)
		hikaroConfig.setMinimumIdle(5); //최소 유효 커넥션 개수(반드시 같은 값으로 설정)
		
		hikaroConfig.setConnectionTestQuery("SELECT now()"); //test query 날짜구하기
		hikaroConfig.setPoolName("springHikariCP");
		
		//config의 추가 설정
		//cache 사용여부 설정 true (보통은 true로 설정한다.)
		//cache : cache을 사용하면 속도가 빨라진다.
		hikaroConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
		//mysql 드라이버가 연결당 cache statement의 수에 관한 설정 보통 256 250 ~ 500 권장한다.
		hikaroConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
		//mysql connection당 캐싱할 preparedStatement의 개수 지정 옵션 default 256
		hikaroConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "true");//기본값
		//mysql 서버에서 최신 이슈가 있을경우 지원받을 설정 server-side 지원 설정(업데이트 같은경우)
		hikaroConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");
		
		HikariDataSource hikariDataSource = new HikariDataSource(hikaroConfig);
		return hikariDataSource;
		
	}
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlFactoryBean = new SqlSessionFactoryBean();
		sqlFactoryBean.setDataSource(dataSource());
		sqlFactoryBean.setMapperLocations(
				applicationContext.getResources("classpath:/mappers/*.xml"));
		sqlFactoryBean.setConfigLocation(
				applicationContext.getResource("classpath:/MybatisConfig.xml"));
		return (SqlSessionFactory)sqlFactoryBean.getObject();
		
	}
	//트렌젝션메니저 빈 설정
	@Bean
	public DataSourceTransactionManager transactionManager() {
	return new DataSourceTransactionManager(dataSource());
	
	}
		
	
}
