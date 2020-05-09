package br.org.neverbealone.model;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

import java.time.ZonedDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import br.org.neverbealone.model.enumeration.TypeUserEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = {"password", "bio", "phoneNumber", "name", "created", "updated", "deleted"})
@ToString(exclude = {"password"})
public class User {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	private String name;
	private String bio;
	private String email;
	private String password;
	private String phoneNumber;
	@ManyToMany
	private Set<User> contacts;
	@ManyToMany
	private Set<User> emergency;
	@Enumerated(STRING)
	private TypeUserEnum type;
	private ZonedDateTime created;
	private ZonedDateTime updated;
	private ZonedDateTime deleted;
	
}
