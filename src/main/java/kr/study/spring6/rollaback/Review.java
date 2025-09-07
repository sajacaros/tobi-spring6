package kr.study.spring6.rollaback;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name="review")
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    private String message;

    public Review(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
