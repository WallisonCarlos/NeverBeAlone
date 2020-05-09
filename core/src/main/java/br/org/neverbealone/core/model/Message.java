package br.org.neverbealone.core.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.org.neverbealone.core.model.enumeration.ChannelMessageEnum;
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
	private String id;
	@ManyToOne
	private User from;
	@ManyToOne
	private User to;
	@Enumerated
	private ChannelMessageEnum channel;
	private String content;
	private ZonedDateTime created;
	private ZonedDateTime updated;
	private ZonedDateTime deleted;
	
}
