package org.websandbox.learning.assertj.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.websandbox.learning.assertj.model.HogwartsAllumni;
import org.websandbox.learning.assertj.model.House;

public class DaoRepository {

	private HogwartsAllumni harry = new HogwartsAllumni("Harry", House.GRYFFINDOR);
	private HogwartsAllumni ron = new HogwartsAllumni("Ron", House.GRYFFINDOR);
	private HogwartsAllumni snape = new HogwartsAllumni("Snape", House.SALAZAR_SLYTHERIN);

	public HogwartsAllumni findByName(String name) {
		return new HogwartsAllumni(name);
	}

	public List<HogwartsAllumni> findAllByHouse(House house) {
		switch (house) {
		case GRYFFINDOR:
			return Arrays.asList(harry, ron);
		case SALAZAR_SLYTHERIN:
			return Arrays.asList(snape);
		default:
			return new ArrayList<>();
		}
	}

	public List<HogwartsAllumni> findAll() {
		List<HogwartsAllumni> allumni = new ArrayList<>(3);
		allumni.add(harry);
		allumni.add(ron);
		allumni.add(snape);
		return allumni;
	}
}
