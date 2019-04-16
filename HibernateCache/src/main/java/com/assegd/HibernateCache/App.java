package com.assegd.HibernateCache;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import org.hibernate.*;;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		Alien alien = null;

		Configuration con = new Configuration().configure().addAnnotatedClass(Alien.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory = con.buildSessionFactory(reg);
		Session session1 = sessionFactory.openSession();
		session1.beginTransaction();
		Query q1 = session1.createQuery("from Alien where aid=101");
		q1.setCacheable(true);
		alien = (Alien) q1.uniqueResult();
		System.out.println(alien);
		session1.getTransaction().commit();
		session1.close();

		Session session2 = sessionFactory.openSession();
		session2.beginTransaction();
		Query q2 = session2.createQuery("from Alien where aid=101");
		q2.setCacheable(true);
		alien = (Alien) q2.uniqueResult();
		System.out.println(alien);
		session2.getTransaction().commit();
		session2.close();

	}
}
