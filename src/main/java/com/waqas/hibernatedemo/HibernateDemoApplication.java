package com.waqas.hibernatedemo;

import com.waqas.hibernatedemo.dao.StudentDao;
import com.waqas.hibernatedemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class HibernateDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDao studentDao) {
		return runner -> {
//			createStudent(studentDao);
//			readStudent(studentDao);
//			findAll(studentDao);
//			findByEmail(studentDao);
//			findByFirstName(studentDao);
//			findByFirstAndLastName(studentDao);
//			findByLastName(studentDao);
//			updateStudentLastNameUsingId(studentDao);
//			updateAllLastName(studentDao);
//			deleteStudentUsingId(studentDao);
//			deleteStudentUsingLastName(studentDao);
		};
	}

	private void createStudent(StudentDao studentDao) {
		System.out.println("Creating student Object");

		Student student = new Student("Waqas", "Ahmed", "waqasmlik565@gmail.com");

		studentDao.save(student);

		System.out.println("Created student with id: " + student.getId());
	}

	private void readStudent(StudentDao studentDao) {
		Student student = new Student("Shahzain", "Ali", "shahzain@gmail.com");

		studentDao.save(student);

		System.out.println("Finding student with id: " + student.getId());

		Student foundStudent = studentDao.findById(student.getId());

		System.out.println("Student email: " + foundStudent.getEmail());
	}

	private void findAll(StudentDao studentDao) {
		System.out.println("Finding all students");

		List<Student> studentList = studentDao.findAll();

		for (Student student : studentList) {
			System.out.println(student);
		}
	}

	private void findByEmail(StudentDao studentDao) {
		System.out.println("Finding student by email");

		Student student = studentDao.findByEmail("waqasmlik565@gmail.com");

		System.out.println(student);
	}

	private void findByFirstName(StudentDao studentDao) {
		System.out.println("Finding student by first name");

		Student student = studentDao.findByFirstName("Shahzain");

		System.out.println(student);
	}

	private void findByFirstAndLastName(StudentDao studentDao) {
		System.out.println("Finding student by first and last name");

		Student student = studentDao.findByFirstAndLastName("Waqas", "Ahmed");

		System.out.println(student);
	}

	private void findByLastName(StudentDao studentDao) {
		System.out.println("Finding student last name");

		Student student = studentDao.findByLastName("Ali");

		System.out.println(student);
	}

	private void updateStudentLastNameUsingId(StudentDao studentDao) {
		System.out.println("Update Student Last Name Using Id");

		Student student = studentDao.updateLastNameById(5001,"Khan");

		System.out.println(student);
	}

	private void updateAllLastName(StudentDao studentDao) {
		System.out.println("Update All Last Name");

		int updateCount = studentDao.updateAllLastNames("Asghar");

		System.out.println("Total records updated: " + updateCount);
	}

	private void deleteStudentUsingId(StudentDao studentDao) {
		System.out.println("Delete Student Using Id");

		studentDao.deleteStudentUsingId(5001);
	}

	private void deleteStudentUsingLastName(StudentDao studentDao) {
		System.out.println("Delete Using Last Name");

		int deletedRows = studentDao.deleteStudentUsingLastName("Asghar");

		System.out.println("Total records deleted: " + deletedRows);
	}

}
