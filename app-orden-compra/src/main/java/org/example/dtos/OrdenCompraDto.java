package org.example.dtos;

import lombok.Data;
import lombok.ToString;
import org.example.db.OrdenCompra;
import java.math.BigDecimal;

@Data
@ToString
public class OrdenCompraDto {
    private Integer id;
    private BigDecimal precio;
    private String nombreCliente;
    private String productos;

    public static OrdenCompraDto from(OrdenCompra obj) {
        OrdenCompraDto ret = new OrdenCompraDto();

        ret.setId(obj.getId());
        ret.setPrecio(obj.getPrecio());
        return ret;
    }
}
