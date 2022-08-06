package Connection;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mysql.cj.Session;

import model.userModel;

public class DBconnection {

	public static SessionFactory createSession()
	{
		Properties prop=null;
		SessionFactory sf=null;
		Configuration conf=null;
		prop=new Properties();
		prop.setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver");
		prop.setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/hibernate_project");
		prop.setProperty("hibernate.connection.username","root");
		prop.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
		prop.setProperty("hibernate.hbm2ddl.auto","update");
		prop.setProperty("hibernate.show_sql","true");
		conf=new Configuration();
		conf.setProperties(prop);
		conf.addAnnotatedClass(userModel.class);
		sf=conf.buildSessionFactory();
		return sf;
	}
}
