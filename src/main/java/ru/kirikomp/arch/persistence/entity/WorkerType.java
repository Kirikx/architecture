package ru.kirikomp.arch.persistence.entity;

import com.sun.xml.bind.v2.model.core.ID;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "worker_type")
@Data
@NoArgsConstructor
public class WorkerType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "name")
    private String name;
}
