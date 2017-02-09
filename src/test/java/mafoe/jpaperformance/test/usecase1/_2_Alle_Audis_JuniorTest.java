package mafoe.jpaperformance.test.usecase1;

import com.google.common.collect.ImmutableSet;
import mafoe.jpaperformance.config.PersistenzConfig;
import mafoe.jpaperformance.dto.ModellgruppeDto;
import mafoe.jpaperformance.model.Marke;
import mafoe.jpaperformance.repository.MarkeRepository;
import mafoe.jpaperformance.repository.ModellgruppeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Wir wollen alle Modellgruppen laden, die zu "Audi" gehören, und als DTO liefern.
 * <p>
 * Zweiter Ansatz: Nur genau die Modellgruppen laden, die am "Audi"-Objekt hängen.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenzConfig.class)
public class _2_Alle_Audis_JuniorTest {

	@Autowired
	private MarkeRepository markeRepository;
	@Autowired
	private ModellgruppeRepository modellgruppeRepository;

	@Test
	public void findByMarke() {

		String markenname = "Audi";
		List<ModellgruppeDto> modellgruppen = findByMarkenname(markenname);

		assertEquals(5, modellgruppen.size());

		Set<String> erwarteteModellgruppen = ImmutableSet.of("A1", "A2", "A3", "A4", "A5");
		assertTrue(modellgruppen
				.stream()
				.allMatch(modellgruppe -> erwarteteModellgruppen.contains(modellgruppe.getName())));
	}

	private List<ModellgruppeDto> findByMarkenname(String markenname) {

		Marke marke = markeRepository.findByName(markenname);

		return modellgruppeRepository
				.findByMarke(marke)
				.stream()
				.map(modellgruppe -> new ModellgruppeDto(modellgruppe.getName(), modellgruppe.getMarke().getName()))
				.collect(Collectors.toList());
	}
}
