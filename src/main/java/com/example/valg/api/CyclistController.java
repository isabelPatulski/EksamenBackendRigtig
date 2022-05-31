package com.example.valg.api;

import com.example.valg.dto.CyclistRequest;
import com.example.valg.dto.CyclistResponse;
import com.example.valg.service.CyclistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/cyclists")
public class CyclistController {
    CyclistService cyclistService;

    public CyclistController(CyclistService cyclistService) {
        this.cyclistService = cyclistService;
    }

    //Henter alle Cyclists - virker i frontend
    @GetMapping
    public List<CyclistResponse> getAllCyclists(@RequestParam(value="team", required = false) String team){
        return cyclistService.getAllCyclists(team);
    }

    //Henter en Cyclist ud fra et id - testet
    @GetMapping("/{id}")
    public CyclistResponse getCyclist(@PathVariable int id) throws Exception {
        return cyclistService.getCyclist(id);
    }

    //Adder en Cyclist - virker i frontend
    @PostMapping("/{id}")
    public CyclistResponse addCyclist(@RequestBody CyclistRequest body, @PathVariable int id) throws Exception{
        return cyclistService.addCyclist(body, id);
    }

    //Redigere en Cyklist ud fra et givent id
    @PutMapping("/{id}")
    public CyclistResponse editCyclist(@RequestBody CyclistRequest body, @PathVariable int id){
        return cyclistService.editCyclist(body,id);
    }

    //Sletter en Cyclist ud fra et givent id - virker i frontend
    @DeleteMapping("/{id}")
    public void deleteCyclist(@PathVariable int id){
        cyclistService.deleteCyclist(id);
    }

}



