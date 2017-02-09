package mafoe.jpaperformance.repository;

import mafoe.jpaperformance.model.Familie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * @author EMAFOER
 * @version $Id$
 * @since 01.02.2017
 */
public interface FamilieRepository2 extends JpaRepository<Familie, Long> {

	@Query("FROM Familie f "
			+ "LEFT JOIN FETCH f.modellgruppe m "
			+ "LEFT JOIN FETCH m.marke marke "
			+ "WHERE m.name IN :modellgruppen ")
	List<Familie> findByModellgruppe(@Param("modellgruppen") Set modellgruppen);
}
