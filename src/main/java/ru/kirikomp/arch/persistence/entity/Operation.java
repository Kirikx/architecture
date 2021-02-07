package ru.kirikomp.arch.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "operation")
@Data
@NoArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @Column(name = "parent_id")
    private Operation parent;

    @Column(name = "dimension_operation")
    private Duration durationOperation;

    @ManyToOne
    @Column(name = "worker_type_id")
    private WorkerType workerType;

    @ManyToMany
    @JoinTable(name = "specification_operation",
            joinColumns = @JoinColumn(name = "operation_id"),
            inverseJoinColumns = @JoinColumn(name = "specification_id"))
    private List<Specification> specifications;
}
