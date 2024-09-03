import java.math.BigInteger;
/**
 * Beschreiben Sie hier die Klasse myClient.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class myClient extends Client
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    String[] Openkey;
    /**
     * Konstruktor für Objekte der Klasse myClient
     */
    public myClient(String pServerIP, int pServerPort)
    {   super(pServerIP,pServerPort);
        // Instanzvariable initialisieren
      
    }

    public void processMessage(String pMessage){
        Openkey = pMessage.split("/");
    }
    
    public void schicken(String pMessage){
        char[] Text = pMessage.toCharArray();
        int[] TextZahl= new int[Text.length];
        for(int i = 0;i<Text.length;i++){
            TextZahl[i] = Text[i];
        }
        String Send = "";
        for(int i = 0;i<Text.length;i++){
            BigInteger o = BigInteger.valueOf(TextZahl[i]);
            Send = Send + o.modPow(BigInteger.valueOf(Integer.parseInt(Openkey[0])),BigInteger.valueOf(Integer.parseInt(Openkey[1])))+",";
        }
        this.send(Send);
    }
  
}
