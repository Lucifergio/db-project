package geekbrains;

//import geekbrains.config.DbConfig;
import geekbrains.config.HibernateConfig;
import geekbrains.dao.ManufacturerDao;
import geekbrains.dao.OldJdbcManufacturerDao;
import geekbrains.dao.SpringJdbcManufacturerDao;
import geekbrains.entity.Manufacturer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ShopApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

//        ManufacturerDao manufacturerDao = new OldJdbcManufacturerDao();
        ManufacturerDao manufacturerDao = context.getBean(ManufacturerDao.class);
//        System.out.println(manufacturerDao.findNameNyId(5L));
        for (Manufacturer manufacturer : manufacturerDao.findAll()) {
            System.out.println(manufacturer);
        }
//        System.out.println(manufacturerDao.findNameById(5L));

    }
}
