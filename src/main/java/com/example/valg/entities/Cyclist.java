package com.example.valg.entities;

import com.example.valg.dto.CyclistRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cyclist{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private String name;

        @ManyToOne()
        private Team team;

        public Cyclist(String name) {
            this.name = name;
        }

    public Cyclist(CyclistRequest body){
        this.name = body.getName();}
    }