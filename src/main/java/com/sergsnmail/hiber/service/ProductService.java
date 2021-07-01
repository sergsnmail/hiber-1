package com.sergsnmail.hiber.service;

import com.sergsnmail.hiber.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ProductService implements EntityCRUD<Product> {

    private SessionFactory factory;

    public ProductService(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(Product entity) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public Product get(Class<Product> cls, Long id) {
        Product product;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            product = session.get(cls, id);
            session.getTransaction().commit();
        }
        return product;
    }

    @Override
    public void update(Product entity) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Product entity) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        }
    }
}
