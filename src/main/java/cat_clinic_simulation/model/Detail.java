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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "master_id")
    private Master master;
    @Column(name = "detail_name")
    private String name;
    @Column(name = "detail_amount")
    private Double amount;
}