package mafoe.jpaperformance.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO Doku
 *
 * @author EMAFOER
 * @version $Id$
 * @since 01.02.2017
 */
@Entity
public class Modellgruppe extends Entitaet {

	private String name;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_MODELLGRUPPE_MARKE"),
			name = "MARKE_ID", nullable = false)
	private Marke marke;

	@OneToMany(mappedBy = "modellgruppe", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@MapKey(name = "name")
	private Map<String, Familie> familien = new HashMap<>();

	public String getName() {
		return name;
	}

	public Marke getMarke() {
		return marke;
	}

	public Map<String, Familie> getFamilien() {
		return familien;
	}
}
