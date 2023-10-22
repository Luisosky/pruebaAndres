package model;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente implements Serializable {
    private String nombre;
    private String cedula;
    private ArrayList<Libro> librosAlquilados;
}
