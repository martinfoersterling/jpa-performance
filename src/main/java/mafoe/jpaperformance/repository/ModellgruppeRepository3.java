package mafoe.jpaperformance.repository;

import mafoe.jpaperformance.model.Modellgruppe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author EMAFOER
 * @version $Id$
 * @since 01.02.2017
 */
public interface ModellgruppeRepository3 extends JpaRepository<Modellgruppe, Long> {

	@Query("SELECT DISTINCT m "
			+ "FROM Modellgruppe m "
			+ "LEFT JOIN FETCH m.marke "
			+ "WHERE m.marke.name = :marke ")
	List<Modellgruppe> findByMarkeFetchMarke(@Param("marke") String markenname);
}
