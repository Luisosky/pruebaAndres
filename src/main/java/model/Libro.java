package model;

import enums.Categorias;
import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Libro implements Serializable {

    private int idLibro;
    private int cantidadPaginas;
    private String nombre;
    private Categorias categoria;

}
