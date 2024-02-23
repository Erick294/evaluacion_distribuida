package org.example.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.example.db.Producto;

@ApplicationScoped
@Transactional
public class ProductoRepository implements PanacheRepositoryBase<Producto, Integer>{
}
