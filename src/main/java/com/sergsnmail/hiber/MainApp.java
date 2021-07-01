package com.sergsnmail.hiber;

import com.sergsnmail.hiber.entity.Product;
import com.sergsnmail.hiber.service.ProductService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApp {

    private static SessionFactory factory;

    public static void main(String[] args) {
        try {
            init();
            ProductService service = new ProductService(factory);

            /**
             * Create new product
             */
            Product product = new Product(null, "Чеснок", 4.23F);
            service.create(product);
            System.out.println(product);

            /**
             * Get product from base
             */
            product = service.get(Product.class, 1L);
            System.out.println(product);

            /**
             * Update product
             */
            product.setCost(100500F);
            service.update(product);

            /**
             * Delete product
             */
            product = service.get(Product.class, 10L);
            service.delete(product);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }

    private static void shutdown() {
        factory.close();
    }

    private static void init() {
        PrepareDataApp.forcePrepareData();
        factory = new Configuration()
                .configure("config/mysql/hibernate.cfg.xml")
                .buildSessionFactory();
    }
}
