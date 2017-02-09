package mafoe.jpaperformance.repository;

import mafoe.jpaperformance.model.Familie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * TODO Doku
 *
 * @author EMAFOER
 * @version $Id$
 * @since 01.02.2017
 */
public interface FamilieRepository extends JpaRepository<Familie, Long> {

	@Query("FROM Familie f "
			+ "WHERE f.modellgruppe.name = :modellgruppe ")
	List<Familie> findByModellgruppe(@Param("modellgruppe") String modellgruppe);
}
