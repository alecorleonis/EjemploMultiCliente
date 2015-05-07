package com.mygdx.game;

/**
 * Created by alecorleonis on 05-07-15.
 * from https://javadeveloperszone.wordpress.com/2013/04/20/java-tcp-chat-multiple-client/
 */
import java.net.*;
import java.io.*;

public class chatClient{
    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;

        BufferedReader inFromUser =
                new BufferedReader(
                        new InputStreamReader(System.in));

        Socket clientSocket = new Socket("localhost", 6789);

        while (true) {
            DataOutputStream outToServer =
                    new DataOutputStream(
                            clientSocket.getOutputStream());

            BufferedReader inFromServer =
                    new BufferedReader(
                            new InputStreamReader(
                                    clientSocket.getInputStream()));

            sentence = inFromUser.readLine();

            outToServer.writeBytes(sentence + '\n');

            if (sentence.equals("EXIT")) {
                break;
            }

            modifiedSentence = inFromServer.readLine();

            System.out.println("FROM SERVER: " + modifiedSentence);

        }
        clientSocket.close();
    }
}

