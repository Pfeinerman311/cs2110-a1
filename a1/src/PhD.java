/** NetId: pjf73. Time spent: 2 hours, 12 minutes. */
/** An instance maintains info about the PhD of a person. */

public class PhD {
	/** The month the PhD was awarded. An integer from 1-12, with 1 being January, etc. */
	private int month;
	/** The year the PhD was awarded. An integer with no restraints. */
	private int year;
	/** The name of the person with the PhD. A String of length > 0. */
	private String name;
	/** The first advisor of this person. A PhD type that is null if unknown. */
	private PhD advisor1;
	/** The first advisor of this person. A PhD type that is null if unknown. */
	private PhD advisor2;
	/** The total number of advisees of this person. An integer that is not user defined and is updated
	 * when this person is listed as an advisor. */
	private int advisees;

	/** Constructor: a PhD with PhD month m, PhD year y, and name n.<br>
	 * The advisors are unknown, and there are 0 advisees.<br>
	 * Precondition: n has at least 1 char and m is in 1..12 */
	public PhD(int m, int y, String n) {
		assert n != null && n.length() >= 1;
		assert m >= 1 && m <= 12;
		month= m;
		year= y;
		name= n;
		advisor1= null;
		advisor2= null;
		advisees= 0;
	}

	/** Return the name of this person. */
	public String name() {
		return name;
	}

	/** Return the year this person got their PhD. */
	public int year() {
		return year;
	}

	/** Return the month this person got their PhD. */
	public int month() {
		return month;
	}

	/** Return the first advisor of this PhD (null if unknown). */
	public PhD advisor1() {
		return advisor1;
	}

	/** Return the second advisor of this PhD (null if unknown or non-existent). */
	public PhD advisor2() {
		return advisor2;
	}

	/** Return the number of PhD advisees of this person. */
	public int advisees() {
		return advisees;
	}

	/** Make p the first advisor of this person.<br>
	 * Precondition: the first advisor is unknown and p is not null. */
	public void setAdvisor1(PhD p) {
		assert advisor1 == null && p != null;
		advisor1= p;
		p.advisees= p.advisees() + 1;
	}

	/** Make p the second advisor of this PhD. <br>
	 * Precondition: The first advisor is known, the second advisor is unknown,<br>
	 * p is not null, and p is different from the first advisor. */
	public void setAdvisor2(PhD p) {
		assert advisor1 != null && advisor2 == null;
		assert p != null && p != advisor1;
		advisor2= p;
		p.advisees= p.advisees() + 1;
	}

	/** Constructor: a PhD with PhD month m, PhD year y, name n, <br>
	 * first advisor adv1, and no second advisor.<br>
	 * Precondition: n has at least 1 char, m is in 1..12, and adv1 is not null. */
	public PhD(int m, int y, String n, PhD adv1) {
		assert n != null && n.length() >= 1;
		assert m >= 1 && m <= 12;
		assert adv1 != null;
		month= m;
		year= y;
		name= n;
		setAdvisor1(adv1);
		advisor2= null;
		advisees= 0;
	}

	/** Constructor: a PhD with PhD month m, PhD year y, name n, <br>
	 * first advisor adv1, and second advisor adv2.<br>
	 * Precondition: n has at least 1 char, m is in 1..12,<br>
	 * adv1 and adv2 are not null, and adv1 and adv2 are different. */
	public PhD(int m, int y, String n, PhD adv1, PhD adv2) {
		assert n != null && n.length() >= 1;
		assert m >= 1 && m <= 12;
		assert adv1 != null && adv2 != null;
		assert adv1 != adv2;
		month= m;
		year= y;
		name= n;
		setAdvisor1(adv1);
		setAdvisor2(adv2);
	}

	/** Return value of: this PhD got the PhD after p.<br>
	 * Precondition: p is not null. */
	public boolean gotAfter(PhD p) {
		assert p != null;
		return year > p.year() || (year == p.year() && month > p.month());
	}

	/** Return value of: p is not null and this PhD and p are intellectual siblings. */
	public boolean areSiblings(PhD p) {
		boolean notNull= p != null && this != p;
		boolean adv1Sib= advisor1 != null && (advisor1 == p.advisor1() || advisor1 == p.advisor2());
		boolean adv2Sib= advisor2 != null && (advisor2 == p.advisor1() || advisor2 == p.advisor2());
		return notNull && (adv1Sib || adv2Sib);
	}
}
