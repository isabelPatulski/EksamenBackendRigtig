package com.example.valg.config;


import com.example.valg.entities.Cyclist;
import com.example.valg.entities.Team;
import com.example.valg.repositories.CyclistRepository;
import com.example.valg.repositories.TeamRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Profile("!test")
public class MakeTestData implements ApplicationRunner {


    CyclistRepository cyclistRepository;
    TeamRepository teamRepository;

    public MakeTestData(CyclistRepository cyclistRepository, TeamRepository teamRepository) {
        this.cyclistRepository = cyclistRepository;
        this.teamRepository = teamRepository;
    }

    public void makeTeamsWithCyclist(){
        Team team1 = new Team("DK","Team Easy On");
        Team team2 = new Team("UK","Culten");
        Team team3 = new Team("SWE","Vikingerne");
        Team team4 = new Team("US","AMERICA");


        Cyclist cyclist1 = new Cyclist("Pim de Keysergracht");
        Cyclist cyclist2 = new Cyclist("Bobby Olsen");
        Cyclist cyclist3 = new Cyclist("Ronaldo Petersen");
        Cyclist cyclist4 = new Cyclist("Sine Winter");
        Cyclist cyclist5 = new Cyclist("Harry Styles");
        Cyclist cyclist6 = new Cyclist("Homer Simpson");


        team1.addCyclists(Set.of(cyclist1,cyclist2));
        team2.addCyclists(Set.of(cyclist3,cyclist4, cyclist5));
        team3.addCyclists(Set.of(cyclist6));

        teamRepository.saveAll(List.of(
                team1,
                team2));
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

        makeTeamsWithCyclist();
    }
}

