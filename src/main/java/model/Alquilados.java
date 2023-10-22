package model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alquilados implements Serializable {

    private Cliente ciente;
    private Libro libro;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private float valor;

}
