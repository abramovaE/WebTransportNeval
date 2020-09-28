package com.springapp.mvc;

import com.ibm.icu.text.Transliterator;

import java.util.HashMap;
import java.util.Map;

public class Translit {

    private static  final String RUS_TO_ENG = "Russian-Latin/BGN";

//        public String transliterate(String s){
//            Transliterator transliterator = Transliterator.getInstance(RUS_TO_ENG, Transliterator.FORWARD);
//            return transliterator.transliterate(s);
//        }



    public String transliterate(String s){
        Transliterator transliterator = Transliterator.getInstance(RUS_TO_ENG, Transliterator.FORWARD);


        s =  translate(s);
        return transliterator.transliterate(s);
    }



    public String translate(String s){
        Map<String, String> map = new HashMap<>();
        map.put("ь", "");
        map.put("й", "y");


        for(Map.Entry<String, String> pair: map.entrySet()){
            s = s.replace(pair.getKey(), pair.getValue());
        }
        return s;
    }
    }

