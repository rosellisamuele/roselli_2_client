package client;

import java.io.*;
import java.net.*;


public class Client {
    String nomeServer = "localhost";
    int porta = 6789; 
    
    Socket mioSocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;

    

    public Socket connetti(){
        System.out.println("2 CLIENT partito in esecuzione ...");

        try{
            //Input tastiera utente
            tastiera = new BufferedReader(new InputStreamReader(System.in));

            //Creazione socket ['localhost':6789]
            mioSocket = new Socket(nomeServer, porta);
            //Creazione di entrambi gli stream (Output, Input)
            outVersoServer = new DataOutputStream(mioSocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));

        } catch (UnknownHostException e){
            //Qualora risultasse l'exception di host sconosciuto, riporto il messaggio specifico di errore
            System.err.println("Host sconosciuto.");
        }catch(Exception e){
            //Qualora risultasse un'exception generica, riporto un messaggio generico di errore
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione.");
            System.exit(1);
        }
        return mioSocket;
        
    }

    public void comunica(){
        /*
         Il ciclo for infinito permette interazioni con il server potenzialmente infinite,
         e viene arrestato da tastiera digitando la parola "FINE"
         */
        for(;;)
            try{
                
                System.out.println("4 ... Inserisci la stringa da trasmettere al server:  ");
                stringaUtente = tastiera.readLine();

                //Chiudo socket qualora uscisse la stringa FINE
                if(stringaUtente.equals("FINE")){
                    System.out.println("9 Chiusura socket "+mioSocket+"...");
                    mioSocket.close();
                    break;
                }
                
                //Invio stringa e ricezione risposta dal server
                System.out.println("5 ... invio la stringa al server e attendo ...");
                outVersoServer.writeBytes(stringaUtente+'\n');
                stringaRicevutaDalServer = inDalServer.readLine();
                System.out.println("8 ... risposta dal server "+'\n'+stringaRicevutaDalServer);

            }catch(Exception e){
                //Qualora risultasse un errore generico di comunicazione col server
                System.out.println(e.getMessage());
                System.out.println("Errore durante la comunicazione col server");
                System.exit(1);
            }
        
        
        
    }
}