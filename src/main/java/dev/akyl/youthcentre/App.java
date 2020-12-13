package dev.akyl.youthcentre;

import dev.akyl.youthcentre.model.Teenager;
import dev.akyl.youthcentre.repository.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Teenager student = new Teenager("Ramesh", "Fadatare", "test1@email.com");
        Teenager student1 = new Teenager("John", "Cena", "test2@email.com");
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(student);
            session.save(student1);
            session.flush();
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Teenager> students = session.createQuery("from Teenager", Teenager.class).list();
            students.forEach(s -> System.out.println(s));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
