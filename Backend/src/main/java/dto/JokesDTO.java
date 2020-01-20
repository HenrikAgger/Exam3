/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Henrik
 */
public class JokesDTO {
    private List<JokeDTO> jokes;
    private String reference;

    public JokesDTO(List<JokeDTO> jokes, String reference) {
        this.jokes = jokes;
        this.reference = reference;
    }
    
    public JokesDTO() {
    }

    public List<JokeDTO> getJokes() {
        return jokes;
    }

    public void setJokes(List<JokeDTO> jokes) {
        this.jokes = jokes;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.jokes);
        hash = 43 * hash + Objects.hashCode(this.reference);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final JokesDTO other = (JokesDTO) obj;
        if (!Objects.equals(this.reference, other.reference)) {
            return false;
        }
        if (!Objects.equals(this.jokes, other.jokes)) {
            return false;
        }
        return true;
    }
    
    
    
}
