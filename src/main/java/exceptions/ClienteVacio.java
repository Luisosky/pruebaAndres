package exceptions;

import java.lang.reflect.Member;

public class ClienteVacio  extends Exception{
    public ClienteVacio(String mensaje)
    {
        super(mensaje);
    }
}
