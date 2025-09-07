package kr.study.spring6;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import kr.study.spring6.configure.DataConfig;
import kr.study.spring6.order.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

@Slf4j
public class DataClient {
    public static void main(String[] args) throws InterruptedException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        EntityManagerFactory emf = beanFactory.getBean(EntityManagerFactory.class);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Order order = new Order("100", BigDecimal.TEN);
        log.info("before save order, order : {}", order);
        em.persist(order);
        log.info("after save order, order : {}", order);

        em.getTransaction().commit();
        em.close();
    }
}
