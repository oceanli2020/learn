package com.lihaiyang.learn.core.cache;


import java.util.HashMap;


public class ReplayPools {

    private static HashMap<Long, Integer> Map=
            new HashMap<Long,Integer>();

    public static void put(Long liveId,Integer key){
            Map.put(liveId,key);
    }
    public static Integer get(Long liveld)
    {
        return Map.get(liveld);
    }

}
