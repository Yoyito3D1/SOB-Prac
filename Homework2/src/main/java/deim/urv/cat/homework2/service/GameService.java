/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;

import java.util.List;
import deim.urv.cat.homework2.model.Game;

public interface GameService {

    List<Game> getFilteredVideoGames(String typeFilter, String consoleFilter);
    Game getGameDetailsByName(String name);

    
}
