package com.example.valg.service;

import com.example.valg.dto.CyclistRequest;
import com.example.valg.dto.CyclistResponse;
import com.example.valg.entities.Cyclist;
import com.example.valg.entities.Team;
import com.example.valg.repositories.CyclistRepository;
import com.example.valg.repositories.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CyclistServiceTest {

    @Autowired
    CyclistRepository cyclistRepository;
    CyclistService cyclistService;

    @Autowired
    TeamRepository teamRepository;


    static int cyclist1, cyclist2;
    static String team1, team2;

    @BeforeEach
    void setup(@Autowired CyclistRepository cyclistRepository, @Autowired TeamRepository teamRepository){
        cyclistRepository.deleteAll();
        teamRepository.deleteAll();

        Team tea1 = teamRepository.save(new Team("DK", "Shubidua"));
        Team tea2 = teamRepository.save(new Team("ESP", "Monster"));

        Cyclist cycl1 = cyclistRepository.save(new Cyclist("Maria Steenmand"));
        Cyclist cycl2 = cyclistRepository.save(new Cyclist("Maiken Ludvigsen"));

        cyclist1 = cycl1.getId();
        cyclist2 = cycl2.getId();
        team1 = tea1.getTeamName();
        team2 = tea2.getTeamName();

        tea1.addCyclists(Set.of(cycl1));
        tea2.addCyclists(Set.of(cycl2));

        teamRepository.saveAll(List.of(tea1, tea2));
    }

    @BeforeEach
    void setupService(){
        cyclistService = new CyclistService(cyclistRepository, teamRepository);
    }

    @Test
    void getAllCyclists() {

        List<CyclistResponse> cyclists = cyclistService.getAllCyclists("Monster");
        assertEquals(1, cyclists.size());
    }

    @Test
    void getCyclist() throws Exception{
        CyclistResponse cyclist = cyclistService.getCyclist(cyclist1);
        String cyclistName = "Maria Steenmand";
        assertEquals(cyclistName, cyclist.getName());
    }

}