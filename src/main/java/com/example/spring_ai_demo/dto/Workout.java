package com.example.spring_ai_demo.dto;

import java.util.List;

public record Workout(String duration,
                      String difficulty,
                      String equipment,
                      String muscleGroup,
                      String instructions,
                      List<Exercise> exerciseList){ }
