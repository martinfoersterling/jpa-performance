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
public interface ModellgruppeRepository2 extends JpaRepository<Modellgruppe, Long> {

	@Query("FROM Modellgruppe m "
			+ "WHERE m.marke.name = :marke ")
	List<Modellgruppe> findByMarke(@Param("marke") String markenname);
}
