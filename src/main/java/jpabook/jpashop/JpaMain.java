package jpabook.jpashop;

import jpabook.jpashop.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //EntityManagerFactory를 만드는 순간 DB랑 연결도 되고 왠만한게 다 된다.
        EntityManager em = emf.createEntityManager();
        //DB와 관련된 code 작성
        //위의 코드들은 생성하는 역활이기 때문에 최초 한 번만 작성되면 된다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // JPA는 트랜잭션이 반드시 있어야 한다.
        try{

            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("김영한");
            em.persist(book);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

