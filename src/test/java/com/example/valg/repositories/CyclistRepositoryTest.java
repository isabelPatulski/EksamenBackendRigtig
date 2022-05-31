package com.example.valg.repositories;

import com.example.valg.entities.Cyclist;
import com.example.valg.entities.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


    @DataJpaTest
    class CyclistRepositoryTest {

        @Autowired
        CyclistRepository cyclistRepository;

        @Autowired
        TeamRepository teamRepository;

        int cyclistID1, cyclistID2;
        String team1Name, team2Name;


        @BeforeEach
        void setUp() {
            cyclistRepository.deleteAll();
            teamRepository.deleteAll();
            Team team1 = new Team("DK", "Vinderne");
            Team team2 = new Team("UK", "IkkeHeltVinderne");

            Cyclist cyclist1 = cyclistRepository.save(new Cyclist("Mads Mikkelsen"));
            Cyclist cyclist2 = cyclistRepository.save(new Cyclist("Jørgen Leth"));

            cyclistID1 = cyclist1.getId();
            cyclistID2 = cyclist2.getId();
            team1Name = team1.getTeamName();
            team2Name = team2.getTeamName();

            team1.addCyclists(Set.of(cyclist1));
            team2.addCyclists(Set.of(cyclist2));

            teamRepository.saveAll(List.of(team1, team2));
        }

        @Test
        public void findCyclistByTeam_TeamName() {
            List<Cyclist> allCyclistInTeam = cyclistRepository.findCyclistByTeam_TeamName(team1Name);
            assertEquals(1,allCyclistInTeam.size());
        }

    }

