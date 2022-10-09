package by.tretyak.application.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth")
    private Timestamp birth;

    @Column(name = "email")
    private String email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "login", column = @Column(name = "user_login")),
            @AttributeOverride(name = "password", column = @Column(name = "user_password"))
    })
    private Credentials credentials;

    @Column(name = "creation_date")
    @JsonIgnore
    private Timestamp creationDate;

    @Column(name = "modification_date")
    @JsonIgnore
    private Timestamp modificationDate;

    @Column(name = "is_deleted")
    @JsonIgnore
    private Boolean isDeleted;

    @ManyToMany(
            mappedBy = "users",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JsonIgnoreProperties("users")
    private Set<Role> roles;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    @JsonManagedReference
    private Set<Purchase> purchases;
}
