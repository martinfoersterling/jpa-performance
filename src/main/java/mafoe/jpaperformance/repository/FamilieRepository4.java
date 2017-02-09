package mafoe.jpaperformance.repository;

import mafoe.jpaperformance.dto.MarkeModellgruppeFamilieDto;
import mafoe.jpaperformance.model.Familie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.Set;

/**
 * @author EMAFOER
 * @version $Id$
 * @since 01.02.2017
 */
public interface FamilieRepository4 extends JpaRepository<Familie, Long> {

	@QueryHints(@QueryHint(name = "org.hibernate.fetchSize", value = "500"))
	@Query("SELECT NEW mafoe.jpaperformance.dto.MarkeModellgruppeFamilieDto("
			+ "f.modellgruppe.marke.name, f.modellgruppe.name, f.name)"
			+ "FROM Familie f "
			+ "WHERE f.modellgruppe.name IN :modellgruppen ")
	List<MarkeModellgruppeFamilieDto> findByModellgruppe(@Param("modellgruppen") Set modellgruppen);
}
