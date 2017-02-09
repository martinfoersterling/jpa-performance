package mafoe.jpaperformance.test.usecase2;

import com.google.common.collect.ImmutableSet;
import mafoe.jpaperformance.config.PersistenzConfig;
import mafoe.jpaperformance.dto.MarkeModellgruppeFamilieDto;
import mafoe.jpaperformance.model.Familie;
import mafoe.jpaperformance.repository.FamilieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * Wir wollen für einige Modellgruppen die Familien laden und als DTO liefern.
 * <p>
 * Erster Ansatz: Iterieren und laden.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenzConfig.class)
public class _1_Modellgruppen_mit_FamilienTest {

	@Autowired
	private FamilieRepository repo;

	@Test
	public void find() {

		Set<String> gewünschteModellgruppen = ImmutableSet.of("A1", "A4", "A5");
		List<MarkeModellgruppeFamilieDto> daten = findModellgruppeMitFamilien(gewünschteModellgruppen);

		assertEquals(24, daten.size());
	}

	private List<MarkeModellgruppeFamilieDto> findModellgruppeMitFamilien(Set<String> gewünschteModellgruppen) {

		List<Familie> rueckgabe = new ArrayList<>();

		for (String modellgruppe : gewünschteModellgruppen) {

			rueckgabe.addAll(repo.findByModellgruppe(modellgruppe));
		}

		return rueckgabe
				.stream()
				.map(familie -> new MarkeModellgruppeFamilieDto(
						familie.getModellgruppe().getMarke().getName(),
						familie.getModellgruppe().getName(),
						familie.getName()))
				.collect(Collectors.toList());
	}
}
