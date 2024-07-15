package com.dsanchez.forohub.ForoHub.domain.topic;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título no debe estar vacío")
    private String title;

    @NotBlank(message = "El mensaje no debe estar vacío")
    private String message;

    @NotBlank(message = "El nombre del curso no debe estar vacío")
    private String courseName;

    private String userEmail;  // Para guardar el email del usuario que creó el tópico
}