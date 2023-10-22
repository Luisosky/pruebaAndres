package exceptions;

public class ClienteExistente extends Exception{
    public ClienteExistente(String mensaje)
    {
        super(mensaje);
    }
}
