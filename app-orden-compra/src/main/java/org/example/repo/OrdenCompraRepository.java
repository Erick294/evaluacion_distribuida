package org.example.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.example.db.OrdenCompra;

@ApplicationScoped
@Transactional
public class OrdenCompraRepository implements PanacheRepositoryBase<OrdenCompra,Integer>{
}
