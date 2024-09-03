import java.math.BigInteger;
/**
 * Beschreiben Sie hier die Klasse myServer.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class myServer extends Server
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    int e=0;
    int d=0;
    int n;
    /**
     * Konstruktor für Objekte der Klasse myServer
     */
    public myServer(int pServerPort,int PZ1,int PZ2)
    {
        super(pServerPort);
        // Instanzvariable initialisieren
        int Phi = (PZ1 - 1)*(PZ2 - 1);
        n = (int)(PZ1 * PZ2);
        e=0;
        d=0;
        for(int i = Phi-1;i>1;i--){
            int a = i;
            int b = Phi;
            int c;
            while (a != b) {
                if (a < b) {
                    c = a;
                    a = b;
                    b = c;
                }
                a = a - b;
            }
            if(a == 1){
                e = i;
            }

            for(int j = e-1;j>1;j--){
                if((e*j)% Phi == 1){
                    d = j;
                    j = 1;
                }
            }
            if(d != 0){
                i = 1;
            }
        }
    }

    public void processNewConnection(String pClientIP, int pClientPort){
        this.send(pClientIP,pClientPort, e+"/"+n);
    }

    public void processMessage(String pClientIP, int pClientPort, String pMessage){
        String[] TextZahl = pMessage.split(",");
        int[] Message = new int[TextZahl.length];
        for(int i = 0; i<TextZahl.length;i++){
            Message[i] = BigInteger.valueOf(Integer.parseInt(TextZahl[i])).modPow(BigInteger.valueOf(d),BigInteger.valueOf(n)).intValue();
        }
        String End = "";
        for(int i = 0; i<TextZahl.length;i++){
            End = End + Character.toString(Message[i]);
        }
        System.out.println(End);
    }

    public void processClosingConnection(String pClientIP, int pClientPort){
    }

}
