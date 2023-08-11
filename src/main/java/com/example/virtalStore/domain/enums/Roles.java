package com.example.virtalStore.domain.enums;

import com.example.virtalStore.rest.services.exceptions.CustomException;

public enum Roles {

    ROLE_ADMIN("ADMIN"),
    ROLE_CLIENT("CLIENT");

    private String roleName;

    private Roles(String roleName){
        this.roleName = roleName;
    }

    public String getRoleName(){
        return roleName;
    }

    public static Roles roleNameOf(String name){
        for(Roles x : Roles.values()){
            if(x.getRoleName().equals(name)){
                return x;
            }
        }
        throw new CustomException("Invalid Role name");
    }
}
