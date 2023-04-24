package in.ineuron.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcUtil {

	private static HikariConfig config;
	private static HikariDataSource dataSource;

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private JdbcUtil() {
	}

	public static HikariDataSource getDataSource() throws SQLException, IOException {

		FileInputStream fis = new FileInputStream(
				"D:\\Java_Project(LibrarayManagementSystem)\\workspace_3\\LibraryManagementApp\\src\\main\\java\\properties\\application.properties");
		Properties properties = new Properties();
		properties.load(fis);

		config = new HikariConfig();
		config.setJdbcUrl(properties.getProperty("jdbcUrl"));
		config.setUsername(properties.getProperty("dataSource.user"));
		config.setPassword(properties.getProperty("dataSource.password"));
		config.setMinimumIdle(Integer.parseInt(properties.getProperty("dataSource.setMinimumIdle")));
		config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("dataSource.maximumPoolSize")));
		config.setIdleTimeout(Long.parseLong(properties.getProperty("dataSource.idleTimeout")));
		config.setMaxLifetime(Long.parseLong(properties.getProperty("dataSource.setMaxLifetime")));
		dataSource = new HikariDataSource(config);

		return dataSource;

	}
}
