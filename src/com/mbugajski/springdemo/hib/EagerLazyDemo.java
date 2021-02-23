package com.mbugajski.springdemo.hib;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mbugajski.springdemo.hib.entity.Course;
import com.mbugajski.springdemo.hib.entity.Instructor;
import com.mbugajski.springdemo.hib.entity.InstructorDetail;
import com.mbugajski.springdemo.hib.entity.Student;

public class EagerLazyDemo {

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
			Instructor tempInstructor = session.get(Instructor.class, theId);

			tempInstructor.getCourses();
			
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
