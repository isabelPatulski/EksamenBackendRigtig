package com.example.valg.dto;

import com.example.valg.entities.Cyclist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CyclistResponse {

    private int id;
    private String name;
    private String teamName;

    public CyclistResponse(Cyclist body) {
        this.id = body.getId();
        this.name = body.getName();
        this.teamName = body.getTeam().getTeamName();
    }
}


