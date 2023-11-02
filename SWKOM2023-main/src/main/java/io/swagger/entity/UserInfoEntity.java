package io.swagger.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_info")
//@Data
public class UserInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Assuming there's an ID for each user in the database.

    @Column(nullable = true, unique = true)
    private String username;

    @Column(nullable = true)
    private String password;


}
