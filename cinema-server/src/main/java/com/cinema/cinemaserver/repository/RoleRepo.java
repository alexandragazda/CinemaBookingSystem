//package com.cinema.cinemaserver.repository;
//
//import com.cinema.cinemaserver.domain.Role;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.query.Query;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//public class RoleRepo {
//
//    public void getRoles() {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Transaction tx = null;
//           // System.out.println("da");
//            try {
//                tx = session.beginTransaction();
//                List<Role> roles = session.createQuery("from com.cinema.cinemaserver.domain.Role").list();
//                System.out.println(roles.size() + " roles(s) found:");
//////                for (Role r : roles) {
//////                    System.out.println(r.getID());
//////                }
//                tx.commit();
//            } catch (RuntimeException ex) {
//                System.out.println(ex.getMessage());
////                if (tx != null)
////                    tx.rollback();
//           }
//        }
//    }
//}
