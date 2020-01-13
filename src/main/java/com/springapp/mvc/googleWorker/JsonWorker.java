package com.springapp.mvc.googleWorker;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import org.apache.log4j.Logger;
import org.hibernate.annotations.SourceType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by kot on 15.06.17.
 */
public class JsonWorker{

    static Map<String,String> zsdCoordinates = new LinkedHashMap<>();


    //    Логирование
    private static final Logger logger = Logger.getLogger(JsonWorker.class.getName());
    private static final String logFilePath = "/home/kot/web";
    private static final String logFileName = "logWT.log";


    static {

//        zsdCoordinates.put("skandinavia1", "60.148501, 29.995839");
        zsdCoordinates.put("skandinavia2", "60.148405, 29.995759");

//        zsdCoordinates.put("kadSever1", "60.068579, 30.137525");
        zsdCoordinates.put("kadSever2", "60.065805, 30.139590");
        zsdCoordinates.put("kadSever3","60.055836, 30.145297");
//        zsdCoordinates.put("kadSever4", "60.054769, 30.146281");

//        zsdCoordinates.put("bogatyrskiy1", "60.007538, 30.233631");
        zsdCoordinates.put("bogatyrskiy2", "60.007656, 30.233996");
        zsdCoordinates.put("bogatyrskiy3", "59.996471, 30.231252");
//        zsdCoordinates.put("bogatyrskiy4", "59.996851, 30.231643");

//        zsdCoordinates.put("primorskii1", "59.986142, 30.229991");
        zsdCoordinates.put("primorskii2", "59.988117, 30.230914");

//        zsdCoordinates.put("makarova1", "59.962761, 30.216438");
        zsdCoordinates.put("makarova2", "59.962653, 30.216801");
        zsdCoordinates.put("makarova3", "59.957376, 30.209654");
//        zsdCoordinates.put("makarova4", "59.958498, 30.211258");

//        zsdCoordinates.put("ekateringofka1", "59.895566, 30.235273");
        zsdCoordinates.put("ekateringofka2", "59.895195, 30.237000");
        zsdCoordinates.put("ekateringofka3", "59.893453, 30.245439");
//        zsdCoordinates.put("ekateringofka4", "59.893523, 30.245621");

//        zsdCoordinates.put("blagodatnaya1", "59.875745, 30.294877");
        zsdCoordinates.put("blagodatnaya2", "59.880177, 30.290385");
        zsdCoordinates.put("blagodatnaya3", "59.866577, 30.293241");
//        zsdCoordinates.put("blagodatnaya4", "59.868937, 30.294859");

//        zsdCoordinates.put("krasnoputilovskaya1", "59.864112, 30.292522");

//        zsdCoordinates.put("avtomobilnaya1", "59.861110, 30.291822");
        zsdCoordinates.put("avtomobilnaya2", "59.857378, 30.291901");
        zsdCoordinates.put("avtomobilnaya3", "59.848789, 30.288707");
//        zsdCoordinates.put("avtomobilnaya4", "59.850632, 30.290123");

//        zsdCoordinates.put("kadYug1", "59.839095, 30.281414");
        zsdCoordinates.put("kadYug2", "59.842119, 30.283399");
//        zsdCoordinates.put("kadYug3", "59.836271, 30.280291");
        zsdCoordinates.put("kadYug4", "59.836185, 30.280591");


    }

//    узнаем координаты адреса
    public static String knowTheCoordinates(String adress) throws IOException {

        final String baseUrl = "http://maps.googleapis.com/maps/api/geocode/json";// путь к Geocoding API по HTTP
        final Map<String, String> params = Maps.newHashMap();

        params.put("sensor", "false");// исходит ли запрос на геокодирование от устройства с датчиком местоположения
        params.put("address", adress);// адрес, который нужно геокодировать
        final String url = baseUrl + '?' + encodeParams(params);// генерируем путь с параметрами

//        System.out.println(url);// Путь, что бы можно было посмотреть в браузере ответ службы
        final JSONObject response = JsonReader.read(url);// делаем запрос к вебсервису и получаем от него ответ
        // как правило наиболее подходящий ответ первый и данные о координатах можно получить по пути
        // //results[0]/geometry/location/lng и //results[0]/geometry/location/lat

        try {
            JSONObject location = response.getJSONArray("results").getJSONObject(0);

            location = location.getJSONObject("geometry");
            location = location.getJSONObject("location");
            final double lng = location.getDouble("lng");// долгота
            final double lat = location.getDouble("lat");// широта
            String ln = String.valueOf(lng).replace(",",".");
            String la = String.valueOf(lat).replace(",",".");

            return (String.format("%enums, %enums", la, ln));// итоговая широта и долгота
        }catch (JSONException e){
            return "";
        }

    }



    private static String encodeParams(final Map<String, String> params) {
        final String paramsUrl = Joiner.on('&').join(// получаем значение вида key1=value1&key2=value2...
                Iterables.transform(params.entrySet(), new Function<Map.Entry<String, String>, String>() {

                    @Override
                    public String apply(final Map.Entry<String, String> input) {
                        try {
                            final StringBuffer buffer = new StringBuffer();
                            buffer.append(input.getKey());// получаем значение вида key=value
                            buffer.append('=');
                            buffer.append(URLEncoder.encode(input.getValue(), "utf-8"));// кодируем строку в соответствии со стандартом HTML 4.01
                            return buffer.toString();
                        } catch (final UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }));
        return paramsUrl;
    }


//возвразает дистанцию мжду жвумя точками в метрах
    public static int knowTheDistance(String originCoords, String destinationCoords) {

        String distance = null;
        int d = 0;
        int count = 0;

        while (true) {
            try {
                final String baseUrl = "https://maps.googleapis.com/maps/api/directions/json";// путь к Geocoding API по HTTP

                final Map<String, String> params = Maps.newHashMap();

                params.put("key", "AIzaSyCLCY1wNb0Y1ksqlgFxL5tVhSYQZYxqvm4");
                params.put("sensor", "false");// указывает, исходит ли запрос на геокодирование от устройства с датчиком
                params.put("language", "ru");// язык данные на котором мы хотим получить
                params.put("mode", "driving");// способ перемещения, может быть driving, walking, bicycling
                params.put("origin", originCoords);// адрес или текстовое значение широты и долготы отправного пункта маршрута
                params.put("destination", destinationCoords);// адрес или текстовое значение широты и долготы конечного пункта маршрута

                final String url = baseUrl + '?' + encodeParams(params);// генерируем путь с параметрами

//                System.out.println("********** " + url); // Можем проверить что вернет этот путь в браузере

                final JSONObject response;// делаем запрос к вебсервису и получаем от него ответ

                response = JsonReader.read(url);

//                logger.info(response);

                if(response.getJSONArray("routes").length() == 0){
                    logger.info(response);
                }
//                System.out.println(response);


                // как правило наиболее подходящий ответ первый и данные о координатах можно получить по пути
                // //results[0]/geometry/location/lng и //results[0]/geometry/location/lat

                if (response.getJSONArray("routes").length() > 0) {
                    JSONObject location = response.getJSONArray("routes").getJSONObject(0);
                    location = location.getJSONArray("legs").getJSONObject(0);
                    distance = location.getJSONObject("distance").getString("text");


//            System.out.println("********* " + distance);
                    String[] mass = distance.split(" ");

                    if (mass[1].equals("м")) {
                        d = (int) Double.parseDouble(distance.split(" ")[0]);
                    } else if (mass[1].equals("км")) {

//                System.out.println("********** " + distance);
                        String s = distance.split(" ")[0].replace(",", ".");

//                        System.out.println(enums);
                        d = (int) (Double.parseDouble(s) * 1000);
                    }


                }
//                else {
//                    knowTheDistance(originCoords, destinationCoords);
//                }

//        final String duration = location.getJSONObject("duration").getString("text");

                count --;


                if(d > 0){
                    return d;
                }

                if(count < -5){
                    return 0;
                }
                Thread.sleep(1000);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        return d;
//        return distance;
    }



//    считает дистанцию с учетом проезда по ЗСД
    public static int knowTheDistanceWithZsd(String originCoords, String destinationCoords) {

        int depDist = Integer.MAX_VALUE;
        String depAdr = "";

        for(Map.Entry<String,String> pair: zsdCoordinates.entrySet()){

            int dep = knowTheDistance(originCoords, pair.getValue());
            if(dep > 0 && dep < depDist){
                depDist = dep;
                depAdr = pair.getKey();

            }
        }

//        System.out.println("******* " + depAdr + " " + depDist);

        int uchastok1 = knowTheDistance(originCoords, zsdCoordinates.get(depAdr));
        int uchastok2 = knowTheDistance(zsdCoordinates.get(depAdr), destinationCoords);

        int totalDist = uchastok1 + uchastok2;
        return totalDist;
    }



}
