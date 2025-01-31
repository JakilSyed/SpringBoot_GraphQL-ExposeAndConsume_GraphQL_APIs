package com.jakil.graphql_demo.controller;

import com.jakil.graphql_demo.model.GraphQLResponse;
import com.jakil.graphql_demo.model.Player;
import com.jakil.graphql_demo.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest-controller")
public class CallToPlayerController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String QUERY_FIND_ONE = """
            query findOne($id: ID!) {
                findOne(id: $id) {
                    id
                    name
                    team
                }
            }
            """;

    private static final String GRAPHQL_URI = "http://localhost:8080/graphql";

    @RequestMapping("/findOne/{id}")
    public Player getPlayerByCallingGQLAPI(@PathVariable Integer id) {


        Map<String, Object> variables = new HashMap<>();
        variables.put("id", id);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("operationName", "findOne");
        requestBody.put("query", QUERY_FIND_ONE);
        requestBody.put("variables", variables);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody);

        ResponseEntity<GraphQLResponse> response = restTemplate.exchange(GRAPHQL_URI, HttpMethod.POST, entity, GraphQLResponse.class);
        System.out.println("Response of getPlayerByCallingGQLAPI is: " + response.getBody().getData().toString());
        Player player = response.getBody().getData().getFindOne();

        return player;
    }
}
