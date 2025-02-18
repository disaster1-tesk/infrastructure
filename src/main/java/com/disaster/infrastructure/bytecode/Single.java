package com.disaster.infrastructure.bytecode;

public class Single {
    private static volatile Single SINGLE = null;

    public static Single getInstance(){
        if (SINGLE ==null){
            synchronized (SINGLE){
                if (SINGLE == null){
                    SINGLE = new Single();
                }
            }
        }
        return SINGLE;
    }


}
