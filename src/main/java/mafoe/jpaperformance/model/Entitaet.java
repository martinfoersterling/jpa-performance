package mafoe.jpaperformance.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

/**
 * Superklasse aller JPA Entitäten.
 *
 * @author EMAFOER
 * @version $Id$
 * @since 01.02.2017
 */
@MappedSuperclass
public class Entitaet {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JPA_ID_GENERATOR")
	@SequenceGenerator(name = "JPA_ID_GENERATOR",
			allocationSize = 50,
			sequenceName = "JPA_ID_SEQUENCE")
	private Long id;

	/**
	 * Version der Entität. (Verwendet für optimistisches Locking.)
	 */
	@Version
	@Column(name = "OPT_LOCK", nullable = false)
	private long optlock;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Liefert die Version des Objekts (OPT_LOCK) zurück.
	 *
	 * @return Version des Objekts
	 */
	public long getOptlock() {
		return optlock;
	}

	public void setOptlock(long version) {
		this.optlock = version;
	}

	@Override
	public String toString() {
		return "Entität mit ID " + id;
	}
}
