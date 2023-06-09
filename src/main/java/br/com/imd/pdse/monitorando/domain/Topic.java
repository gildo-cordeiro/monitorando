package br.com.imd.pdse.monitorando.domain;

import br.com.imd.pdse.monitorando.domain.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "TOPIC")
public class Topic extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "TITLE", nullable = false)
    private String title;
    @Column(name = "MESSAGE", nullable = false)
    private String message;
    @Column(name = "OPEN", columnDefinition = "boolean default true")
    private boolean open;
    @Column(name = "ACTIVE", columnDefinition = "boolean default true")
    private boolean active;
    @Column(name = "FIXED", columnDefinition = "boolean default false")
    private boolean fixed;
    @Column(name = "CLOSED_DATE")
    private LocalDate closedDate;
    @Column(name = "LIKES")
    private  int likes;
    @Column(name = "USER_LIKED")
    private UUID userLiked;
    @ManyToOne
    private User user;
    @ManyToOne
    private Report report;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Contribution> contributions;

    private int count;
    private int contributionCount; // Adicione essa propriedade

    public Topic() {
        super(Instant.now());
    }
}
