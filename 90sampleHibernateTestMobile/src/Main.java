import org.apache.log4j.BasicConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class Main {

	public static void main(String[] args) {
        BasicConfigurator.configure();
        
		Configuration cfg= new Configuration()
		        .addAnnotatedClass(Customer.class)
				.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
				.setProperty("hibernate.connection.url","jdbc:mysql://localhost/cmsmobile")
				.setProperty("hibernate.connection.username","root")
				.setProperty("hibernate.connection.password","root")
				.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect")
				.setProperty("hibernate.hbm2ddl.auto","create")
				.setProperty("hibernate.show_sql","true"); 
		
		Customer c= new Customer(1,"name2");
		SessionFactory sf = cfg.buildSessionFactory();
		Session ses=sf.openSession();
		Transaction tx= ses.beginTransaction();
		tx.begin();
		ses.save(c);
		tx.commit();
		ses.close();
		sf.close(); 
		
	}

}
