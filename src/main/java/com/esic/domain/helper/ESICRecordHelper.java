package com.esic.domain.helper;

import java.text.ParseException;

import com.esic.domain.Dependent;
import com.esic.domain.ESICDate;
import com.esic.domain.ESICRecord;

public class ESICRecordHelper {

	/**
	 * populate dependent records
	 * 
	 * @param record
	 * @throws ParseException
	 */
	public static void populateDependentList(ESICRecord record)
			throws ParseException {

		for (int i = 1; i <= 9; i++) {

			String key = "dependent_" + i + "_Name";

			String value = record.get(key);

			if (!value.isEmpty()) {

				addRecordInDependents(record, i);

			}
			// break as soon as first null name is found.
			else {
				break;
			}

		}

	}

	private static void addRecordInDependents(ESICRecord record, int dependentNo)
			throws ParseException {

		Dependent dep = new Dependent();

		String key = "dependent_" + dependentNo + "_Name";
		String value = record.get(key);
		dep.setName(value);

		key = "dependent_" + dependentNo + "_DOB";
		value = record.get(key);
		ESICDate dob = new ESICDate(value);
		dep.setDob(dob);

		key = "dependent_" + dependentNo + "_Relationship";
		value = record.get(key);
		dep.setRelationship(value);

		key = "dependent_" + dependentNo + "_residingWithHimYes";
		value = record.get(key);

		if (value.toLowerCase().equals("yes")) {
			dep.setResidingWithHim(true);
		} else {
			dep.setResidingWithHim(false);
		}

		key = "dependent_" + dependentNo + "_town";
		value = record.get(key);
		dep.setTown(value);

		key = "dependent_" + dependentNo + "_state";
		value = record.get(key);
		dep.setState(value);

		key = "dependent_" + dependentNo + "_aadharID";
		value = record.get(key);
		dep.setAadharID(value);

		record.getDependents().add(dep);
	}
}