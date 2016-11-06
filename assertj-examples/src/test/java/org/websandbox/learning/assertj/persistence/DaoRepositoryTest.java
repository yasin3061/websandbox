package org.websandbox.learning.assertj.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.websandbox.learning.assertj.model.HogwartsAllumni;
import org.websandbox.learning.assertj.model.House;

public class DaoRepositoryTest {

	private DaoRepository repository = new DaoRepository();
	private HogwartsAllumni harry = new HogwartsAllumni("Harry", House.GRYFFINDOR);
	private HogwartsAllumni ron = new HogwartsAllumni("Ron", House.GRYFFINDOR);
	private HogwartsAllumni snape = new HogwartsAllumni("Snape", House.SALAZAR_SLYTHERIN);
	private HogwartsAllumni hegla = new HogwartsAllumni("Hegla", House.HELGA_HUFFLEPUFF);

	@Test
	public void findHarry() {
		HogwartsAllumni harry = repository.findByName("Harry");
		assertThat(harry.getName()).isEqualTo("Harry");
	}

	@Test
	public void findAllOfThemAndCheckWhoIsPresent() {
		List<HogwartsAllumni> allOfThem = repository.findAll();
		assertThat(allOfThem).hasSize(3).extracting(HogwartsAllumni::getName).contains(harry.getName(), ron.getName(),
				snape.getName());
	}

	@Test
	public void findAllOfThemAndCheckWhoIsNotPresent() {
		List<HogwartsAllumni> allOfThem = repository.findAll();
		assertThat(allOfThem).extracting(HogwartsAllumni::getName).doesNotContain(hegla.getName());
	}

	@Test
	public void findAllOfThemAndCheckWhoIsPresentAndWhoIsNot() {
		List<HogwartsAllumni> allOfThem = repository.findAll();
		assertThat(allOfThem).extracting(HogwartsAllumni::getName)
				.contains(harry.getName(), ron.getName(), snape.getName()).doesNotContain(hegla.getName());
	}

	@Test
	public void failingAssertionWithCustomMessage() {
		// It is in the destiny of this test case to fail so that the muggles
		// can see the descriptive message
		assertThat(harry.getHouse()).as("Check %s's house", harry.getName()).isEqualTo(House.SALAZAR_SLYTHERIN);
	}

	@Test
	public void filterByHouse() {
		List<HogwartsAllumni> gryffindors = repository.findAllByHouse(House.GRYFFINDOR);
		assertThat(gryffindors).filteredOn(character -> character.getHouse().equals(House.GRYFFINDOR))
				.containsOnly(harry, ron);
	}

	@Test
	public void filterByHouseAndCompareUsingName() {
		List<HogwartsAllumni> gryffindors = repository.findAllByHouse(House.GRYFFINDOR);
		assertThat(gryffindors).filteredOn(character -> character.getHouse().equals(House.GRYFFINDOR))
				.extracting(HogwartsAllumni::getName).containsOnly(harry.getName(), ron.getName());
	}

	@Test
	public void pring() {
		List<HogwartsAllumni> gryffindors = repository.findAllByHouse(House.GRYFFINDOR);
		List<String> strs = gryffindors.stream().map(HogwartsAllumni::getName).collect(Collectors.toList());
		System.out.println(strs);
	}

}
