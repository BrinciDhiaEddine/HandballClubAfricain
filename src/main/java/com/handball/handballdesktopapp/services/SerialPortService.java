package com.handball.handballdesktopapp.services;
import com.fazecast.jSerialComm.*;
import com.handball.handballdesktopapp.DAO.resultats;
import com.handball.handballdesktopapp.DatabaseConnector;
import com.handball.handballdesktopapp.H2DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;




public  class SerialPortService{
    private Stage stage;
    private Scene scene;
    private Parent root;

    private List finalArray;
    int timesNbr = 16;


    Scanner console = new Scanner(System.in);

   public boolean status = false;
    static  OutputStream outputStream ;
    public SerialPort[] comPorts;

    UserData userData =UserData.getUserData();

    public SerialPort activePort ;

    List<String> times = new ArrayList<String>();
    String serialMessage = "";
    int portIndex = 0;

public void saveResults(){
    System.out.println("Final array Size :      "+finalArray.size());
    if(finalArray.size()>24){
        ObservableList<resultats> resTotal =  FXCollections.observableArrayList();;
        for(int j=0;j<96;j+=24){
        resultats res = new resultats();
            int i=0;
        for(i=j;i<(22+j);i+=3){
            if(finalArray.get(i).toString().contains("A3 + A5")){
                res.setA35(finalArray.get(i+1)+" "+finalArray.get(i+2));
            }
            else if(finalArray.get(i).toString().contains("A5 * A3")){
                res.setA3_5(finalArray.get(i+1)+" "+finalArray.get(i+2));
            }
            else if(finalArray.get(i).toString().contains("A3 + A4")){
                res.setA34(finalArray.get(i+1)+" "+finalArray.get(i+2));
            }
            else if(finalArray.get(i).toString().contains("A4 * A3")){
                res.setA3_4(finalArray.get(i+1)+" "+finalArray.get(i+2));
            }
            else if(finalArray.get(i).toString().contains("A2 + A5")){
                res.setA25(finalArray.get(i+1)+" "+finalArray.get(i+2));
            }
            else if(finalArray.get(i).toString().contains("A2 * A5")){
                res.setA2_5(finalArray.get(i+1)+" "+finalArray.get(i+2));
            }
            else if(finalArray.get(i).toString().contains("A2 + A4")){
                res.setA24(finalArray.get(i+1)+" "+finalArray.get(i+2));
            }
            else if(finalArray.get(i).toString().contains("A2 * A4")){
                res.setA2_4(finalArray.get(i+1)+" "+finalArray.get(i+2));
            }

        }
        resTotal.add(res);
        }
        System.out.println("restot:   "+resTotal.toString());
        ObservableList<resultats> myResArray = FXCollections.observableArrayList();
        userData.setResultatsJoueurList(resTotal);
        userData.setLevel5(true);
    }


    else {
    resultats res = new resultats();
    for(int i=0;i<finalArray.size()-2;i+=3) {
        if (finalArray.get(i).toString().contains("A3 + A5")) {
            res.setA35(finalArray.get(i + 1) + " " + finalArray.get(i + 2));
        } else if (finalArray.get(i).toString().contains("A5 * A3")) {
            res.setA3_5(finalArray.get(i + 1) + " " + finalArray.get(i + 2));
        } else if (finalArray.get(i).toString().contains("A3 + A4")) {
            res.setA34(finalArray.get(i + 1) + " " + finalArray.get(i + 2));
        } else if (finalArray.get(i).toString().contains("A4 * A3")) {
            res.setA3_4(finalArray.get(i + 1) + " " + finalArray.get(i + 2));
        } else if (finalArray.get(i).toString().contains("A2 + A5")) {
            res.setA25(finalArray.get(i + 1) + " " + finalArray.get(i + 2));
        } else if (finalArray.get(i).toString().contains("A2 * A5")) {
            res.setA2_5(finalArray.get(i + 1) + " " + finalArray.get(i + 2));
        } else if (finalArray.get(i).toString().contains("A2 + A4")) {
            res.setA24(finalArray.get(i + 1) + " " + finalArray.get(i + 2));
        } else if (finalArray.get(i).toString().contains("A2 * A4")) {
            res.setA2_4(finalArray.get(i + 1) + " " + finalArray.get(i + 2));
        }

    }

    System.out.println(res.toString());
    ObservableList<resultats> myResArray = FXCollections.observableArrayList();
    myResArray.add(res);
    userData.setResultatsJoueur(myResArray);
        userData.setLevel5(false);
}
}

    public void test(){
        System.out.println(finalArray);
    }
    public void connect() throws IOException {

         comPorts = SerialPort.getCommPorts();
         for(SerialPort sp : comPorts)
             System.out.println(sp.getDescriptivePortName());
        comPorts[portIndex].openPort();
         activePort = comPorts[0];
        activePort.setBaudRate(115200);
        System.out.println("Port Connected");
        status = true;

    }

    public void setLevel(String level) throws IOException, InterruptedException, SQLException {
            UserData.getUserData().setLevel(level);
            String s = level.substring(6,7)+"*";
            if(s.equals("5*")) {
                s = "1234*";
                byte[] writeBuffer = s.getBytes();
                activePort.writeBytes(writeBuffer, writeBuffer.length);
                readDataMultiLevel(s);
            }
            else {
                byte[] writeBuffer = s.getBytes();
                activePort.writeBytes(writeBuffer, writeBuffer.length);
                readDataOneLevel(s);
            }


    }


    public void readDataMultiLevel(String level) throws InterruptedException, SQLException {

        int iteration =0;
        int myTimer = 0;
        activePort.flushIOBuffers();
        Thread thread = new Thread();
        times.clear();
        while(times.size()<96) {
            while (activePort.bytesAvailable() > 0) {
                Thread.sleep(50);
                byte[] readBuffer = new byte[activePort.bytesAvailable()];
                int numRead = activePort.readBytes(readBuffer, readBuffer.length);
                for (int i = 0; i < readBuffer.length; i++) {
                    serialMessage += (char) readBuffer[i];
                }
            }
            if(times.size()>1){
                thread.sleep(200);
                myTimer++;
            }
            if(myTimer>360){
                for(int k=times.size()-1;k<96;k++){
                    times.add(null);
                }
                finalArray=times;
                activePort.flushIOBuffers();
                activePort.closePort();

            }

            if (serialMessage.length() >= 10) {
                String temp[] = serialMessage.split("\\r?\\n");
                System.out.println("SSSSSSSSSS"+serialMessage);
                System.out.println(serialMessage);
                if (temp.length > 1) {
                    for (int z = 0; z < temp.length; z++) {
                        times.add(temp[z]);

                    }
                    serialMessage = "";
                }
            }

            if (times.size() == 96) {
                this.finalArray=times;
                activePort.flushIOBuffers();
                thread.sleep(1000);
                activePort.closePort();
                float average = 0;
                int responded = 1;

                String[] finalOut = new String[times.size()];
                times.toArray(finalOut);

               /* for (int z = 0; z < timesNbr; z++) {
                    String temp[] = finalOut[z].split(" : ");

                    if (isNumeric(temp[1])) {
                        average += Float.parseFloat(temp[1]);
                        responded += 1;
                    }

                }*/
               // if(responded>1) responded--;
                userData.setMoyenne(average/(responded));
                System.out.println("tab final  :"+Arrays.asList(finalOut));
                finalArray = Arrays.asList(finalOut);
                iteration++;
                System.out.println(average / responded);
                savePlayer();
                times.clear();

                if(iteration==4){
                    // activePort.closePort();
                    // switchToPlayerPage();
                    break;
                }

            }

        }

    }
    public void readDataOneLevel(String level) throws InterruptedException, SQLException {
    activePort.flushIOBuffers();
        int myTimer = 0;
        times.clear();
        int time;
        Thread thread = new Thread();
       while(times.size()<25) {
            while (activePort.bytesAvailable() > 0 && times.size()<24) {
                Thread.sleep(50);
                byte[] readBuffer = new byte[activePort.bytesAvailable()];
                int numRead = activePort.readBytes(readBuffer, readBuffer.length);
                for (int i = 0; i < readBuffer.length; i++) {
                    serialMessage += (char) readBuffer[i];
                }
            }
            if(times.size()>1){
                thread.sleep(200);
                myTimer++;
            }
            if(myTimer>90){
                for(int k=times.size()-1;k<23;k++){
                    times.add("A3");
                }
                finalArray=times;
                activePort.closePort();
            }

            System.out.println("SSSSSSSSSS"+serialMessage);
            if (serialMessage.length() >= 10) {
                String temp[] = serialMessage.split("\\r?\\n");
                System.out.println(serialMessage);
                if (temp.length > 1) {
                    for (int z = 0; z < temp.length; z++) {
                        times.add(temp[z]);
                    }
                    serialMessage = "";
                }

            }


            if (times.size() == 24) {
                finalArray=times;
                activePort.flushIOBuffers();
                thread.sleep(1000);
                activePort.closePort();
                float average = 0;
                int responded = 1;

                String[] finalOut = new String[times.size()];
                times.toArray(finalOut);

                /*for (int z = 0; z < timesNbr; z++) {
                    String temp[] = finalOut[z].split(" : ");
                    if (isNumeric(temp[1])) {
                        average += Float.parseFloat(temp[1]);
                        responded += 1;
                    }

                }*/
               // if(responded>1) responded--;
               // userData.setMoyenne(average/(responded));
                System.out.println("tab final  :"+Arrays.asList(finalOut));
                finalArray = Arrays.asList(finalOut);
                //System.out.println(average / responded);

               // savePlayer();


                System.out.println("done");
                times.clear();
                break;
            }


        }

    }
    public void switchToPlayerPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("newPlayer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void savePlayer() throws SQLException {
        H2DatabaseConnector connectNow = new H2DatabaseConnector();
        Connection connectDB = connectNow.getConnection();
        Statement stmt = stmt = connectDB.createStatement();;
        UserData userData = UserData.getUserData() ;
        int sessionId =  userData.getSession_id();
        String nomJoueur = "'"+ userData.getNom_joueur()+"'";
        float moyenne = userData.getMoyenne();
        ResultSet resultSet = null;
        int s = stmt.executeUpdate("insert into joueur(nom,session_id,moyenne) values("+nomJoueur+","+sessionId+","+moyenne+");");

    }

    public static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }
    public double start(String level) throws InterruptedException {

        System.out.println(level);
        SerialPort[] comPorts = SerialPort.getCommPorts();
        Double average =0.0;
        comPorts[portIndex].openPort();
        SerialPort activePort = comPorts[2];
        activePort.setBaudRate(115200);
        String result="";
        Thread.sleep(10000);
        try {


            while (true) {

                //if keyboard token entered read it
              if (System.in.available() > 0) {
                     String s = console.nextLine() + "*";
                    //String s = "1";
                    byte[] writeBuffer = s.getBytes();
                    comPorts[portIndex].writeBytes(writeBuffer, writeBuffer.length);
                    // System.out.println("printed");
                }

                // read serial port and display data

                while (comPorts[portIndex].bytesAvailable() > 0) {
                  //  ArrayList results = new ArrayList<Double>();
                    byte[] readBuffer = new byte[comPorts[portIndex].bytesAvailable()];
                    int numRead = comPorts[portIndex].readBytes(readBuffer, readBuffer.length);
                    //System.out.println(readBuffer);

                    for (int i = 0; i < readBuffer.length; i++) {

                        // result=result+(char)readBuffer[i];
                        // results.add(Double.intBitsToDouble(readBuffer[i]));
                        // System.out.println(result);
                        System.out.print((char) readBuffer[i]);

                    }
                    // System.out.println(result);


                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        comPorts[portIndex].closePort();
        return average;
    }

   /* SerialPort activePort;
    SerialPort[] ports = SerialPort.getCommPorts();


    public SerialPortService() {
    }

    public void showAllPort() {
        int i = 0;
        for(SerialPort port : ports) {
            System.out.print(i + ". " + port.getDescriptivePortName() + " ");
            System.out.println(port.getPortDescription());
            i++;
        }
    }


    public void setPort(int portIndex) throws InterruptedException {
        activePort = ports[portIndex];


        if (activePort.openPort()) {
            activePort.setBaudRate(115200);
            System.out.println(activePort.getPortDescription() + " port opened.");
            System.out.println(activePort.getDescriptivePortName());
        }
        activePort.addDataListener(new SerialPortDataListener() {

            @Override
            public void serialEvent(SerialPortEvent event) {
                int size = event.getSerialPort().bytesAvailable();
                byte[] buffer = new byte[size];
                event.getSerialPort().readBytes(buffer, size);
                for(byte b:buffer)
                    System.out.print((char)b);
            }

            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }
        });
    }
    public void start() throws InterruptedException {
        showAllPort();
        //Scanner reader = new Scanner(System.in);
        //System.out.print("Port? ");
        //int p = reader.nextInt();
        setPort(2);
        byte[] WriteByte = new byte[1];
        WriteByte[0] = 65;
      activePort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING,
              1000,
                0);

       // reader.close();
    }

    public void setLevel(String level){
        if(level.equals("level 1")){
            System.out.println(level);
            byte[] WriteByte = new byte[1];
            activePort.writeBytes(WriteByte,1);
        }
    }*/
}