import model.Setting;
import util.IntReaderUtil;
import util.Password;
import util.Winlose;

import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author SwordFlame
 */
public class ServerThread extends Thread {

    private Socket socket = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        OutputStream os = null;
        PrintWriter pw = null;
        try {
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            String operate = br.readLine();

            switch (operate){
                case "check" : {
                    String name = br.readLine();
                    String password = br.readLine();
                    socket.shutdownInput();
                    os = socket.getOutputStream();
                    pw = new PrintWriter(os);
                    pw.write(Password.check(name, password)+'\n');
                    pw.flush();
                    break;
                }
                case "insert" : {
                    String name = br.readLine();
                    String password = br.readLine();
                    socket.shutdownInput();
                    os = socket.getOutputStream();
                    pw = new PrintWriter(os);
                    pw.write(Password.insert(name, password)+'\n');
                    pw.flush();
                    break;
                }
                case "ip" : {
                    String ip = br.readLine();
                    socket.shutdownInput();
                    Password.insertip(ip);
                    os = socket.getOutputStream();
                    pw = new PrintWriter(os);
                    pw.write("success\n");
                    pw.flush();
                    break;
                }
                case "addscorename" :
                case "addscoreip" : {
                    String name = br.readLine();
                    double score = Double.parseDouble(br.readLine());
                    socket.shutdownInput();
                    Winlose.score.replace(name, Winlose.score.get(name)+score);
                    Winlose.wins.replace(name, Winlose.wins.get(name)+1);
                    break;
                }
                case "getsettings" : {
                    socket.shutdownInput();
                    os = socket.getOutputStream();
                    pw = new PrintWriter(os);
                    Scanner reader = Setting.getScanner();
                    pw.write(reader.nextLine()+'\n');
                    String floorHeight = reader.nextLine();
                    pw.write(floorHeight+'\n');
                    IntReaderUtil readfloorHeight = new IntReaderUtil(floorHeight);
                    int height = readfloorHeight.read();
                    for(int i = 0; i < height; ++i){
                        pw.write(reader.nextLine()+'\n');
                    }
                    pw.flush();
                    break;
                }
                case "getwins" : {
                    String ni = br.readLine();
                    socket.shutdownInput();
                    os = socket.getOutputStream();
                    pw = new PrintWriter(os);
                    pw.write(Integer.toString(Winlose.wins.get(ni))+'\n');
                    pw.flush();
                    break;
                }
                case "getlose" : {
                    String ni = br.readLine();
                    socket.shutdownInput();
                    os = socket.getOutputStream();
                    pw = new PrintWriter(os);
                    pw.write(Integer.toString(Winlose.lose.get(ni))+'\n');
                    pw.flush();
                    break;
                }
                case "getscore" : {
                    String ni = br.readLine();
                    socket.shutdownInput();
                    os = socket.getOutputStream();
                    pw = new PrintWriter(os);
                    pw.write(String.format("%.4f", Winlose.score.get(ni))+'\n');
                    pw.flush();
                    break;
                }
                default:
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            //关闭资源
            try {
                if (pw != null) {
                    pw.close();
                }
                if (os != null) {
                    os.close();
                }
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (is != null) {
                    is.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}