package com.waqas.hibernatedemo.dao.impl;

import com.waqas.hibernatedemo.dao.StudentDao;
import com.waqas.hibernatedemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    private final EntityManager entityManager;

    public StudentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //CREATE Method
    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    //READ Methods
    @Override
    public Student findById(Integer id) {
        Student student = entityManager.find(Student.class, id);

        return student;
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student", Student.class);

        List<Student> resultList = query.getResultList();

        return resultList;
    }

    @Override
    public Student findByEmail(String email) {
        Query query = entityManager.createNativeQuery("SELECT * FROM Student WHERE email = :email",
                Student.class);

        query.setParameter("email", email);

        Object student = query.getSingleResult();

        return (Student) student;
    }

    @Override
    public Student findByFirstName(String firstName) {
        TypedQuery<Student> query = entityManager.createNamedQuery("Student_findByFirstName",
                Student.class);
        query.setParameter("firstName", firstName);

        Student student = query.getSingleResult();
        return student;
    }


    @Override
    public Student findByFirstAndLastName(String firstName, String lastName) {
        TypedQuery<Student> query = entityManager.createNamedQuery("Student_findByFirstAndLastName",
                Student.class);
        query.setParameter(1, firstName);
        query.setParameter(2, lastName);

        Student student = query.getSingleResult();
        return student;
    }

    @Override
    public Student findByLastName(String lastName) { //native named query
        TypedQuery<Student> query = entityManager.createNamedQuery("Student_findByLastName",
                Student.class);
        query.setParameter(1, lastName);

        Student student = query.getSingleResult();
        return student;
    }

    //UPDATE Methods
    @Override
    @Transactional
    public Student updateLastNameById(Integer id, String lastName) {
        Student student = findById(id);

        //update the student
        student.setLastName(lastName);

        //update student in database
        Student updatedStudent = entityManager.merge(student);

        return updatedStudent;
    }

    @Override
    @Transactional
    public int updateAllLastNames(String lastName) {
        int updatedRecords = entityManager.createQuery("UPDATE Student SET lastName = :lastName")
                .setParameter("lastName", lastName)
                .executeUpdate();

        return updatedRecords;
    }

    @Override
    @Transactional
    public void deleteStudentUsingId(int id) {
        Student student = findById(id);
        entityManager.remove(student);
    }

    @Override
    @Transactional
    public int deleteStudentUsingLastName(String lastName) {
        int deletedRows = entityManager.createQuery("DELETE FROM Student WHERE lastName = :lastName")
                .setParameter("lastName", lastName)
                .executeUpdate();

        return deletedRows;
    }
}
