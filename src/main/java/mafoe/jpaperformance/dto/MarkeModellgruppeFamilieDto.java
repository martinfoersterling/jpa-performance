package mafoe.jpaperformance.dto;

import java.io.Serializable;

/**
 * Simples DTO für eine Familie plus deren Modellgruppe plus deren Marke.
 *
 * @author EMAFOER
 * @version $Id$
 * @since 01.02.2017
 */
public class MarkeModellgruppeFamilieDto implements Serializable {

	private String marke;
	private String modellgruppe;
	private String familie;

	public MarkeModellgruppeFamilieDto(String marke, String modellgruppe, String familie) {
		this.marke = marke;
		this.modellgruppe = modellgruppe;
		this.familie = familie;
	}
}
