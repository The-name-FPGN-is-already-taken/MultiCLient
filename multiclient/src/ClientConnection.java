import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConnection extends Thread{
    Socket socket;
    DataInputStream din;
    DataOutputStream dout;
    boolean shouldRun =true;
    public ClientConnection(Socket socket){
        this.socket=socket;

    }
    public void sendStringToServer(String text){
        try{
            dout.writeUTF(text);
            dout.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run(){
        try{
            din=new DataInputStream(this.socket.getInputStream());
            dout =new DataOutputStream(this.socket.getOutputStream());
            while (shouldRun) {
                try {
                    while (din.available()==0){
                        try{
                            Thread.sleep(1);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    String reply =din.readUTF();
                    System.out.println(reply);
                }catch (IOException e){
                    e.printStackTrace();
                    close();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            close();
        }

    }
    public void close(){
        try {
            din.close();
            dout.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }





}
