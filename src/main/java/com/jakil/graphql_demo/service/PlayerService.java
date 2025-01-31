package com.jakil.graphql_demo.service;

import com.jakil.graphql_demo.model.Player;
import com.jakil.graphql_demo.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static com.jakil.graphql_demo.model.Team.*;

@Service
public class PlayerService {

    private List<Player> players = new ArrayList<>();
    AtomicInteger id = new AtomicInteger(0);

    public List<Player> findAll() {
        return players;
    }

    public Optional<Player> findPlayerById(Integer id) {
        return players.stream()
                .filter(player -> player.id() == id).findFirst();
    }

    public Player createPlayer(String name, Team team) {
        Player player = new Player(id.incrementAndGet(), name, team);
        players.add(player);
        return player;
    }

    public Player updatePlayer(Integer playerId, String name, Team team) {
        Player updatePlayer = new Player(playerId, name, team);

        Optional<Player> optionalPlayer = players.stream()
                .filter(player -> player.id() == playerId).findFirst();
        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            int index = players.indexOf(player);
            players.set(index, updatePlayer);

        } else {
            throw new IllegalArgumentException("Invalid Player");
        }

        return updatePlayer;
    }

    public List<Player> removePlayerById(Integer id) {
        players.removeIf(player -> player.id() == id);
        return players;
    }

    @PostConstruct
    private void init() {
        players.add(new Player(id.incrementAndGet(), "Sharukh Khan", Team.KKR));
        players.add(new Player(id.incrementAndGet(), "Rohit Sharma", Team.MI));
        players.add(new Player(id.incrementAndGet(), "Pat Cummins", Team.SRH));
        players.add(new Player(id.incrementAndGet(), "MS Dhoni", Team.CSK));
    }

}
