package mafoe.jpaperformance.repository;

import mafoe.jpaperformance.model.Marke;
import mafoe.jpaperformance.model.Modellgruppe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author EMAFOER
 * @version $Id$
 * @since 01.02.2017
 */
public interface ModellgruppeRepository extends JpaRepository<Modellgruppe, Long> {

	List<Modellgruppe> findByMarke(Marke marke);
}
