package client;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //Creazione client
        Client cliente = new Client();
        cliente.connetti();
        cliente.comunica();
    }
}
