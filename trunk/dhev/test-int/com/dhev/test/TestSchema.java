package com.dhev.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.validator.event.ValidateEventListener;

public class TestSchema {
	public static AnnotationConfiguration config;
	static {
		config = new AnnotationConfiguration();

		config.setProperty("hibernate.dialect",
				"org.hibernate.dialect.HSQLDialect");
		config.setProperty("hibernate.connection.driver_class",
				"org.hsqldb.jdbcDriver");
		config.setProperty("hibernate.connection.url", "jdbc:hsqldb:mem:test");
		config.setProperty("hibernate.connection.username", "sa");
		config.setProperty("hibernate.connection.password", "");
		config.setProperty("hibernate.connection.pool_size", "1");
		config.setProperty("hibernate.connection.autocommit", "true");
		config.setProperty("hibernate.cache.provider_class",
				"org.hibernate.cache.HashtableCacheProvider");
		config.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		config.setProperty("hibernate.show_sql", "true");
		config.setListener("pre-insert", new ValidateEventListener());

	}

	public static void save(Object o) {

		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(o);
			transaction.commit();
		} finally {
			session.close();
		}
	}

	private static Session getSession() {
		SessionFactory factory = config.buildSessionFactory();

		return factory.openSession();
	}

}
