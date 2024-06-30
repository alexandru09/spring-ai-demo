package com.example.spring_ai_demo.views;

import com.example.spring_ai_demo.dto.Exercise;
import com.example.spring_ai_demo.dto.Workout;
import com.example.spring_ai_demo.service.WorkoutService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Workout Generator")
@Menu(icon = "line-awesome/svg/globe-solid.svg", order = 0)
@Route(value = "")
@RouteAlias(value = "")
public class GenerateWorkoutView extends VerticalLayout {

    private final WorkoutService workoutService;

    public GenerateWorkoutView(WorkoutService workoutService) {
        this.workoutService = workoutService;

        TextField workoutDescription = new TextField("Describe the workout you want");
        workoutDescription.setWidth("300px");
        Button getWorkoutBtn = new Button("Get Workout");
        VerticalLayout generatedWorkout = new VerticalLayout();

        getWorkoutBtn.addClickListener(e -> {
            Workout workout = workoutService.generateWorkout(workoutDescription.getValue());
            generatedWorkout.removeAll();
            generatedWorkout.add(formatWorkoutResponse(workout));
        });
        getWorkoutBtn.addClickShortcut(Key.ENTER);

        HorizontalLayout hl1  = new HorizontalLayout(Alignment.END, workoutDescription, getWorkoutBtn);
        hl1.setWidthFull();
        hl1.setJustifyContentMode ( FlexComponent.JustifyContentMode.CENTER );

        add(hl1, generatedWorkout);
    }

    private static VerticalLayout formatWorkoutResponse(Workout workout) {
        VerticalLayout layout = new VerticalLayout();

        // Add workout details
        layout.add(new H3("Workout Details"));
        layout.add(new Div(new Span("Duration: "), new Span(workout.duration())));
        layout.add(new Div(new Span("Difficulty: "), new Span(workout.difficulty())));
        layout.add(new Div(new Span("Equipment: "), new Span(workout.equipment())));
        layout.add(new Div(new Span("Muscle Group: "), new Span(workout.muscleGroup())));
        layout.add(new Div(new Span("Instructions: "), new Span(workout.instructions())));

        // Add a separator
        layout.add(new Hr());

        // Add exercise list
        layout.add(new H3("Exercise List"));
        for (Exercise exercise : workout.exerciseList()) {
            VerticalLayout exerciseLayout = new VerticalLayout();
            exerciseLayout.add(new Span("Name: " + exercise.name()));
            exerciseLayout.add(new Span("Sets: " + exercise.sets()));
            exerciseLayout.add(new Span("Reps: " + exercise.repetitions()));
            exerciseLayout.add(new Span("Muscle Group: " + exercise.muscleGroup()));
            exerciseLayout.add(new Hr());
            layout.add(exerciseLayout);
        }

        // Adding some margin and padding for better spacing
        layout.getStyle().set("padding", "10px");
        layout.getStyle().set("margin", "10px");
        layout.getStyle().set("border", "1px solid #ccc");
        layout.getStyle().set("border-radius", "5px");

        return layout;
    }
}
