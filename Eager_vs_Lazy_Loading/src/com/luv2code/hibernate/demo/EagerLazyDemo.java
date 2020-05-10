package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {			
						
			session.beginTransaction();
		
			int theId = 1;
			
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Bartek: Instructor: " + tempInstructor);
			
			System.out.println("Bartek: Courses: " + tempInstructor.getCourses());
			
			session.getTransaction().commit();
			
			session.close();
			
			System.out.println("\nBartek: The session is now closed!\n");
		
			System.out.println("Bartek: Courses: " + tempInstructor.getCourses()); 
			
			System.out.println("Bartek: Done!");
		}
		finally {
			session.close();
			factory.close();
		}
	}

}


