package in.ineuron.persist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class PersistConfig {

	@Autowired
	private Environment env;

	@Bean
	public ComboPooledDataSource createDS() throws Exception {
		System.out.println("PersistConfig.createDS()");
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUser(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		return dataSource;
	}
}
