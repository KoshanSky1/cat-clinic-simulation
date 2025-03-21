package cat_clinic_simulation.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "master_id")
    private Master master;
    @Column(name = "detail_name")
    private String name;
    private Double amount;
}