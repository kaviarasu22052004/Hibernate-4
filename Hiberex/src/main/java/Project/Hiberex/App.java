package Project.Hiberex;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {

    SessionFactory sessionFactory;
    Session session;

    public App() {
        sessionFactory = new Configuration()
                .configure("hibernet.xml")  
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        session = sessionFactory.openSession();
    }

    void insert() {
    	session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Student s = new Student();
        s.setRollno(110);
        s.setNameString("Ram");
        s.setMarks(10000.0);

        session.save(s);
        tx.commit();
    }

    void search(int rollno) {
    	session = sessionFactory.openSession();
        Student rs = session.get(Student.class, rollno);
        if (rs != null) {
            System.out.println(rs.toString());
        } else {
            System.out.println("Not Found");
        }
    }

    void removeByRollNo(int rno) {
    	session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Student r = session.find(Student.class, rno);
        if (r == null) {
            System.out.println("Not Found");
        } else {
            session.delete(r);
        }

        tx.commit();
    }

    void updateNameFee(int roll, double marks, String name) {
    	session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Student fr = session.find(Student.class, roll);
        if (fr == null) {
            System.out.println("Not Found");
        } else {
            System.out.println("Found and Updating...");
            fr.setNameString(name);
            fr.setMarks(marks);
            session.update(fr);
        }

        tx.commit();
    }

    public static void main(String[] args) {
        App obj = new App();

        obj.updateNameFee(108, 1000, "Rahul");

        obj.session.close();
        obj.sessionFactory.close();

        System.out.println("Hello World!");
    }
}
