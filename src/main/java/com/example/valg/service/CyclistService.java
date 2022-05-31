package com.example.valg.service;

import com.example.valg.Error.Client4xxException;
import com.example.valg.dto.CyclistRequest;
import com.example.valg.dto.CyclistResponse;
import com.example.valg.entities.Cyclist;
import com.example.valg.entities.Team;
import com.example.valg.repositories.CyclistRepository;
import com.example.valg.repositories.TeamRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CyclistService {
    CyclistRepository cyclistRepository;
    TeamRepository teamRepository;

    public CyclistService(CyclistRepository cyclistRepository, TeamRepository teamRepository) {
        this.cyclistRepository = cyclistRepository;
        this.teamRepository = teamRepository;
    }

    //Test OK
    public List<CyclistResponse> getAllCyclists(String teamName) {
        List<Cyclist> cyclists;
        if(teamName != null){
            cyclists = cyclistRepository.findCyclistByTeam_TeamName(teamName);
        } else {
            cyclists = cyclistRepository.findAll();
        }
        return cyclists.stream().map((cyclist)->new CyclistResponse(cyclist)).collect(Collectors.toList());
    }

    //Test OK
    public CyclistResponse getCyclist(int id) throws Exception {
        Cyclist cyclist = cyclistRepository.findById(id).orElseThrow(()->new Client4xxException("No cyclist with given id was found"));
        return new CyclistResponse(cyclist);
    }

    public CyclistResponse addCyclist(CyclistRequest body, int teamId) throws Exception{
        Team team = teamRepository.findById(teamId).orElseThrow(()->new Client4xxException("No cyclist with given id was found"));
        Cyclist newCyclist = new Cyclist(body);
        team.addCyclist(newCyclist);
        teamRepository.save(team);
        return new CyclistResponse(newCyclist);
    }

    public CyclistResponse editCyclist(CyclistRequest editedCyclist, int id){
        Cyclist cyclist = cyclistRepository.findById(id).orElseThrow(()-> new Client4xxException("No cyclist with given id was found"));
        cyclist.setName(editedCyclist.getName());
        return new CyclistResponse(cyclistRepository.save(cyclist));
    }

    public void deleteCyclist(int id){
        cyclistRepository.deleteById(id);
    }

}


