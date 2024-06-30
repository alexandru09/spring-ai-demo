package com.example.spring_ai_demo.service;

import com.example.spring_ai_demo.dto.Workout;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class WorkoutService {

    private final ChatClient chatClient;

    public WorkoutService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultSystem("You are an experienced fitness trainer specialized in creating workouts for " +
                        "your clients. Provide balanced workouts within the confinements described by the user. " +
                        "Prioritize warming up and injury prevention. " +
                        "Provide the necessary guidance without going in too much detail." +
                        "Always adhere to the output format provided in the prompt.")
                .build();
    }

    public Workout generateWorkout(String userRequest) {
        return chatClient.prompt()
                .user(userRequest)
                .call()
                .entity(Workout.class);
    }
}
