package br.org.neverbealone.core.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.ZonedDateTime;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"messages", "created", "updated", "deleted"})
public class Chat {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	@ManyToOne
	private User to;
	@ManyToOne
	private User from;
	@ManyToMany
	private Set<Message> messages;
	private ZonedDateTime created;
	private ZonedDateTime updated;
	private ZonedDateTime deleted;
	
}
