package com.portfolioprashant.contactapi.entity;

import jakarta.persistence.*;
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
    private String name;
    @Getter @Setter
    private String phone;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String message;


    @CreationTimestamp
    @Getter @Setter
    private LocalDateTime timestamp;

}
