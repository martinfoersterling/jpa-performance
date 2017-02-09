package mafoe.jpaperformance.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
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
public class Marke extends Entitaet {

	private String name;

	@OneToMany(mappedBy = "marke", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@MapKey(name = "name")
	private Map<String, Modellgruppe> modellgruppen = new HashMap<>();

	public String getName() {
		return name;
	}

	public Map<String, Modellgruppe> getModellgruppen() {
		return modellgruppen;
	}
}
