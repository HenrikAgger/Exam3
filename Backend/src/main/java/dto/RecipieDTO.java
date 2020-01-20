/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Henrik
 */
public class RecipieDTO {

    private int stars;
    private String name;
    private String description;

    public RecipieDTO(int stars, String name, String description) {
        this.stars = stars;
        this.name = name;
        this.description = description;
    }

}
