package com.mbugajski.springdemo.hib;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mbugajski.springdemo.hib.entity.Course;
import com.mbugajski.springdemo.hib.entity.Instructor;
import com.mbugajski.springdemo.hib.entity.InstructorDetail;
import com.mbugajski.springdemo.hib.entity.Student;

public class CreateInstructorDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			Instructor tempInstructor = new Instructor("Mike", "Smith", "Mikesmith@gmail.com");

			InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.youtube.com/c/SmithClasses",
					"smithing");

			tempInstructor.setInstructorDetail(tempInstructorDetail);

			session.beginTransaction();
			session.save(tempInstructor);
//			No need to save tempInstructorDetail due to CascadeType.ALL setting in Instructor class
//			session.save(tempInstructorDetail);
			session.getTransaction().commit();
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
