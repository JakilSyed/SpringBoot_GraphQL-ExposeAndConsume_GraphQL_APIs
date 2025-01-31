package com.jakil.graphql_demo.controller;

import com.jakil.graphql_demo.model.Player;
import com.jakil.graphql_demo.model.Team;
import com.jakil.graphql_demo.service.PlayerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @QueryMapping
    public List<Player> findAll() {
        return playerService.findAll();
    }

    @QueryMapping
    public Optional<Player> findOne(@Argument Integer id) {
        return playerService.findPlayerById(id);
    }

    @MutationMapping
    public Player create(@Argument String name, @Argument Team team) {
        return playerService.createPlayer(name, team);
    }

    @MutationMapping
    public Player update(@Argument Integer id, @Argument String name, @Argument Team team) {
        return playerService.updatePlayer(id, name, team);
    }

    @MutationMapping
    public List<Player> delete(@Argument Integer id) {
        return playerService.removePlayerById(id);
    }
}
