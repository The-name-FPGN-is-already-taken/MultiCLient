import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    ClientConnection cc;

    public static void main(String[] args) {
        new Client();
    }
    public Client(){
        try{
            Socket socket=new Socket("localhost",1234);
            cc=new ClientConnection(socket);
            cc.start();
            listenForInput();
        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void listenForInput(){
        Scanner scanner=new Scanner(System.in);
        while(true) {
            while(!scanner.hasNextLine()){
                try{
                    Thread.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            String input=scanner.nextLine();
            if(input.toLowerCase().equals("quit")){
                break;
            }
            cc.sendStringToServer(input);

        }
        cc.close();


    }










}
