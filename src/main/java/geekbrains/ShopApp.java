package geekbrains;

//import geekbrains.config.DbConfig;
//import geekbrains.config.DbConfig;
//import geekbrains.config.HibernateConfig;

import geekbrains.config.HibernateConfig;
import geekbrains.dao.ProductDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ShopApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

//        ProductDao productDao = new OldJdbcProductDao();
        ProductDao productDao = context.getBean(ProductDao.class);
        System.out.println(productDao.findTitleById(5L));
//        for (Product product : productDao.findAll()) {
//            System.out.println(product);
//        }

    }
}
