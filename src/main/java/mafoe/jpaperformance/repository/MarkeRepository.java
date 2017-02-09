package mafoe.jpaperformance.repository;

import mafoe.jpaperformance.model.Marke;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO Doku
 *
 * @author EMAFOER
 * @version $Id$
 * @since 01.02.2017
 */
public interface MarkeRepository extends JpaRepository<Marke, Long> {

	Marke findByName(String name);
}
