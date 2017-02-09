package mafoe.jpaperformance.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Eine Familie ist eine Art von Fahrzeugbestandteil wie z.B. Radio oder Motor. Dabei ist die Familie nicht ein
 * bestimmtes Radio, sondern nur die Möglichkeit eines Radios.
 *
 * @author EMAFOER
 * @version $Id$
 * @since 01.02.2017
 */
@Entity
public class Familie extends Entitaet {

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_FAMILIE_MODELLGRUPPE"),
			name = "MODELLGRUPPE_ID", nullable = false)
	private Modellgruppe modellgruppe;

	private String name;

	/** Potentiell sehr große Beschreibung. Könnte vielleicht ein XML Dokument sein. */
	@Lob
	private String beschreibung;

	public String getName() {
		return name;
	}

	public Modellgruppe getModellgruppe() {
		return modellgruppe;
	}
}
