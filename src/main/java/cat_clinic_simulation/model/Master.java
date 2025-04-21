package cat_clinic_simulation.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "masters")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Master {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "master_number")
    private Integer number;
    @Column(name = "master_date")
    private LocalDate date;
    @Column(name = "master_amount")
    private Double amount;
    @Column(name = "master_description")
    private String description;
}