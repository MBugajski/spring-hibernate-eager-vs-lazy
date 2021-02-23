package com.mbugajski.springdemo.hib;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.mbugajski.springdemo.hib.entity.Course;
import com.mbugajski.springdemo.hib.entity.Instructor;
import com.mbugajski.springdemo.hib.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			int theId = 1;
			
			Query<Instructor> query = 
					session.createQuery("select i from Instructor i "
									+ "JOIN FETCH i.courses "
									+ "where i.id=:theInstructorId", Instructor.class);

			query.setParameter("theInstructorId", theId);
			
			Instructor tempInstructor = query.getSingleResult();
			
			session.getTransaction().commit();
			
			System.out.println("Session closed.");

			System.out.println("tempInstructor: " + tempInstructor);

			System.out.println("the associated courses: " + tempInstructor.getCourses());

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}


