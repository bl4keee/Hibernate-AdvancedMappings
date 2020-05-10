package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

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
		
			// option 2: Hibernate query with HQL
			
			int theId = 1;
			
			Query<Instructor> query =
					session.createQuery("select i from Instructor i " 
								+ "JOIN FETCH i.courses " 
								+ "where i.id=:theInstructorId", 
							Instructor.class);
			
			query.setParameter("theInstructorId", theId);
			
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("Bartek: Instructor: " + tempInstructor);
			
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


