package mafoe.jpaperformance.repository;

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
public interface FamilieRepository3 extends JpaRepository<Familie, Long> {

	@QueryHints(@QueryHint(name = "org.hibernate.fetchSize", value = "5000"))
	@Query("FROM Familie f "
			+ "LEFT JOIN FETCH f.modellgruppe m "
			+ "LEFT JOIN FETCH m.marke marke "
			+ "WHERE m.name IN :modellgruppen ")
	List<Familie> findByModellgruppe(@Param("modellgruppen") Set modellgruppen);
}
