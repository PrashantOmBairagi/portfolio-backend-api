package com.portfolioprashant.contactapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
    @Getter @Setter
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 50,
            message = "Name must be between 2 and 50 characters")
    private String name;
    @Getter @Setter
    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone number must be exactly 10 digits"
    )
    private String phone;
    @Getter @Setter
    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    private String email;

    @Getter @Setter
    @NotBlank(message = "Message cannot be empty")
    @Size(min = 5, max = 500,
            message = "Message must be between 5 and 500 characters")
    private String message;


    @CreationTimestamp
    @Getter @Setter
    private LocalDateTime timestamp;

}
