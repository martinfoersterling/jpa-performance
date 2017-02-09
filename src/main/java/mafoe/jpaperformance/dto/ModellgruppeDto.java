package mafoe.jpaperformance.dto;

import java.io.Serializable;

/**
 * TODO Doku
 *
 * @author EMAFOER
 * @version $Id$
 * @since 01.02.2017
 */
public class ModellgruppeDto implements Serializable {

	private String name;
	private String marke;

	public ModellgruppeDto(String name, String marke) {
		this.name = name;
		this.marke = marke;
	}

	public String getName() {
		return name;
	}

	public String getMarke() {
		return marke;
	}
}
