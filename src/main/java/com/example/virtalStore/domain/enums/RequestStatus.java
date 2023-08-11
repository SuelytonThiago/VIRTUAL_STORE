package com.example.virtalStore.domain.enums;


import com.example.virtalStore.rest.services.exceptions.ObjectNotFoundException;

public enum RequestStatus {

    ORDER_PLACED("ORDER_PLACED"),
    PAYMENT_CONFIRMED("PAYMENT_CONFIRMED"),
    ORDER_DISPATCHED("ORDER_DISPATCHED"),
    ORDER_DELIVERED("ORDER_DELIVERED");

    private String codeName;

    private RequestStatus(String codeName){
        this.codeName = codeName;
    }

    public String getCodeName(){
        return codeName;
    }

    public static RequestStatus codeNameOf(String name){
        for(RequestStatus x : RequestStatus.values()){
            if(x.getCodeName().equals(name)){
                return x;
            }
        }
        throw new ObjectNotFoundException("Invalid Request status name");
    }
}
