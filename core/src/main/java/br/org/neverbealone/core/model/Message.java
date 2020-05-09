package br.org.neverbealone.core.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = {"created", "updated", "deleted"})
@ToString
public class Message {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	@ManyToOne
	private User from;
	@ManyToOne
	private User to;
	private String content;
	private ZonedDateTime created;
	private ZonedDateTime updated;
	private ZonedDateTime deleted;
	
}
