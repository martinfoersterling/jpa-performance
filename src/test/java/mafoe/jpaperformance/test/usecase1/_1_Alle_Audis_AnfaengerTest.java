package mafoe.jpaperformance.test.usecase1;

import com.google.common.collect.ImmutableSet;
import mafoe.jpaperformance.config.PersistenzConfig;
import mafoe.jpaperformance.dto.ModellgruppeDto;
import mafoe.jpaperformance.model.Modellgruppe;
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
 * Erster Ansatz: Alle laden, dann filtern.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenzConfig.class)
public class _1_Alle_Audis_AnfaengerTest {

	@Autowired
	private ModellgruppeRepository repo;

	@Test
	public void findByMarke() {

		String markenname = "Audi";
		List<ModellgruppeDto> modellgruppen = findByMarkenname(markenname);

		assertEquals(5, modellgruppen.size());

		Set<String> erwarteteModellgruppen = ImmutableSet.of("A1", "A2", "A3", "A4", "A5");
		assertTrue(modellgruppen
				.stream()
				.allMatch(modellgruppe ->
						erwarteteModellgruppen.contains(modellgruppe.getName())));
	}

	private List<ModellgruppeDto> findByMarkenname(String markenname) {

		List<Modellgruppe> liste = repo.findAll();

		return liste
				.stream()
				.filter(modellgruppe -> modellgruppe.getMarke().getName().equals(markenname))
				.map(modellgruppe -> new ModellgruppeDto(modellgruppe.getName(), modellgruppe.getMarke().getName()))
				.collect(Collectors.toList());
	}
}
