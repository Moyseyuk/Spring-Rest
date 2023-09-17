package com.tms.springrest.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "cars")
public class Car extends RepresentationModel<Car> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private MODEL model;
    @Temporal(TemporalType.DATE)
    private Date dateOfIssue;
    private String owner;

}
