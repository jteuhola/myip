import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;

/* to install / update:
mkdir -p ~/.local/lib ~/.local/bin
cp /home/NAME/IdeaProjects/myip/out/artifacts/myip_jar/myip.jar ~/.local/lib/myip.jar

cat > ~/.local/bin/myip << 'EOF'
#!/bin/sh
exec java -jar "$HOME/.local/lib/myip.jar" "$@"
EOF
chmod +x ~/.local/bin/myip

*/

public class Main {

    public static String localIp() throws Exception {
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 80);
            return socket.getLocalAddress().getHostAddress();
        }
    }

    public static String publicIp() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://api.ipify.org")).build();
        return client.send(request, HttpResponse.BodyHandlers.ofString()).body().trim();
    }

    public static void main(String[] args) throws Exception{
        String version = "1.0";

        for (String arg : args) {
            switch (arg) {
                case "-v", "--version" -> {
                    System.out.println("myip " + version);
                    System.exit(0);
                }
                default -> {
                    System.err.println("Unknown option: " + arg);
                    System.exit(1);
                }
            }
        }

        try {
            System.out.println("Local ip:\t" + localIp());
        } catch (Exception e) {
            System.err.println("Could not determine local ip: " + e.getMessage());
        }
        try {
            System.out.println("Public ip:\t" + publicIp());
        } catch (Exception e) {
            System.err.println("Could not determine public ip: " + e.getMessage());
        }



    }
}