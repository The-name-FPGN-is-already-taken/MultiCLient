import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client2  {
    ClientConnection cc2;

    public static void main(String[] args) {
        new Client2();
    }
    public Client2(){
        try{
            Socket socket=new Socket("localhost",1234);
            cc2=new ClientConnection(socket);
            cc2.start();
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
            cc2.sendStringToServer(input);

        }
        cc2.close();


    }










}
