package com.portfolioprashant.contactapi.entity;

// JPA/Hibernate imports commented out — DB disabled (cloud PostgreSQL expired).
// Re-enable along with @Entity and related annotations when DB is restored.
// import jakarta.persistence.*;
// import org.hibernate.annotations.CreationTimestamp;
// import java.time.LocalDateTime;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

// @Entity  ← disabled; DB not active. Class retained as a validated POJO for email forwarding.
public class Contact {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 50,
            message = "Name must be between 2 and 50 characters")
    private String name;

    @Getter @Setter
    //@NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^\\s*$|^[0-9]{10}$",
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

    // @CreationTimestamp  ← disabled with JPA
    @Getter @Setter
    private String timestamp;   // kept as String placeholder; re-type to LocalDateTime when DB is restored

}

