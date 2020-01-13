package com.springapp.mvc.emailWorker;

import javax.mail.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by oem on 21.06.18.
 */
public class ReadEmail {

    static String   IMAP_AUTH_EMAIL = "kotofeya" ;
    static String   IMAP_AUTH_PWD   = "ash5800491"           ;
    static String   IMAP_Server     = "imap.yandex.ru";
    static String   IMAP_Port       = "993"           ;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public ReadEmail()
    {
        Properties properties = new Properties();
        properties.put("mail.debug"          , "false"  );
        properties.put("mail.store.protocol" , "imaps"  );
        properties.put("mail.imap.ssl.enable", "true"   );
        properties.put("mail.imap.port"      , IMAP_Port);

        Authenticator auth = new EmailAuthenticator(IMAP_AUTH_EMAIL, IMAP_AUTH_PWD);
        Session session = Session.getDefaultInstance(properties, auth);
        session.setDebug(false);
        try {
            Store store = session.getStore();

            // Подключение к почтовому серверу
            store.connect(IMAP_Server, IMAP_AUTH_EMAIL, IMAP_AUTH_PWD);

            // Папка входящих сообщений
            Folder inbox = store.getFolder("INBOX");
            Folder distanceReportsFolder = store.getFolder("Distance_reports");
            Folder movingReportsFolder = store.getFolder("Moving_reports");

            // Открываем папку в режиме для чтения и записи
            inbox.open(Folder.READ_WRITE);





            if (inbox.getMessageCount() == 0)
                return;

            Message[] messages = inbox.getMessages();
            for(Message message: messages){

                if(!message.getFlags().contains(Flags.Flag.SEEN)){


                    if (message.getFrom()[0].equals("report.starpoint@ya.ru")){

                        if(message.getSubject().contains("Отчет по пробегу")){
                            System.out.println(message.getFrom()[0]);
                            System.out.println(message.getSubject());
                            message.setFlag(Flags.Flag.SEEN, true);





                            Message[] m = new Message[1];
                            m[0]=message;
                            ;
                            inbox.copyMessages(m, distanceReportsFolder);

//                        inbox.delete(message)
                            message.setFlag(Flags.Flag.DELETED, true);
                            inbox.expunge();
                        }

                        else if(message.getSubject().contains("Отчет по движению-стоянкам")){
                            message.setFlag(Flags.Flag.SEEN, true);





                            Message[] m = new Message[1];
                            m[0]=message;
                            ;
                            inbox.copyMessages(m, movingReportsFolder);

//                        inbox.delete(message)
                            message.setFlag(Flags.Flag.DELETED, true);
                            inbox.expunge();
                        }


                    }



                }

                else {

//                    if(message.getSubject().contains("Отчет по пробегу")){
//                        Multipart mp = (Multipart) message.getContent();
//
//                        System.out.println("parsing");
//
//
////                        // Вывод содержимого в консоль
////                        for (int i = 0; i < mp.getCount(); i++){
////                            BodyPart  bp = mp.getBodyPart(i);
////                            if (bp.getFileName() == null)
////                                System.out.println("    " + i + ". сообщение : '" +
////                                        bp.getContent() + "'");
////                            else
////                                System.out.println("    " + i + ". файл : '" +
////                                        bp.getFileName() + "'");
////                        }
//                    }
                }
            }









        } catch (NoSuchProviderException e) {
            System.err.println(e.getMessage());
        } catch (MessagingException e) {
            System.err.println(e.getMessage());
        }

//        catch (IOException e) {
//            System.err.println(e.getMessage());
//        }
    }



    public class EmailAuthenticator extends javax.mail.Authenticator
    {
        private String login   ;
        private String password;
        public EmailAuthenticator (final String login, final String password)
        {
            this.login    = login;
            this.password = password;
        }
        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(login, password);
        }
    }
}
