import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PhDTest {

	@Test
	void testGroupA() {
		PhD parker= new PhD(3, 1998, "Parker");
		assertEquals(parker.name(), "Parker");
		assertEquals(parker.year(), 1998);
		assertEquals(parker.month(), 3);
		assertEquals(parker.advisor1(), null);
		assertEquals(parker.advisor2(), null);
		assertEquals(parker.advisees(), 0);

		assertThrows(AssertionError.class, () -> { new PhD(3, 1998, null); });
		assertThrows(AssertionError.class, () -> { new PhD(3, 1998, ""); });
		assertThrows(AssertionError.class, () -> { new PhD(0, 1998, "Parker"); });
		assertThrows(AssertionError.class, () -> { new PhD(14, 1998, "Parker"); });
	}

	@Test
	void testGroupB() {
		PhD parker= new PhD(3, 1998, "Parker");
		PhD tyler= new PhD(6, 1990, "Tyler");
		PhD jordan= new PhD(2, 1995, "Jordan");
		parker.setAdvisor1(tyler);
		parker.setAdvisor2(jordan);
		assertEquals(parker.advisor1(), tyler);
		assertEquals(parker.advisor2(), jordan);
		assertEquals(parker.advisees(), 0);
		assertEquals(tyler.advisees(), 1);
		assertEquals(jordan.advisees(), 1);

		assertThrows(AssertionError.class, () -> { parker.setAdvisor1(null); ; });
		assertThrows(AssertionError.class, () -> { parker.setAdvisor1(jordan); ; });
		assertThrows(AssertionError.class, () -> { parker.setAdvisor2(null); ; });
		assertThrows(AssertionError.class, () -> { parker.setAdvisor2(tyler); ; });
		assertThrows(AssertionError.class, () -> { parker.setAdvisor2(jordan); ; });
		tyler.setAdvisor1(parker);
		assertThrows(AssertionError.class, () -> { tyler.setAdvisor2(parker); ; });
	}

	@Test
	void testGroupC() {
		PhD parker= new PhD(3, 1998, "Parker");
		PhD tyler= new PhD(6, 1990, "Tyler", parker);
		assertEquals(tyler.name(), "Tyler");
		assertEquals(tyler.year(), 1990);
		assertEquals(tyler.month(), 6);
		assertEquals(tyler.advisor1(), parker);
		assertEquals(tyler.advisor2(), null);
		assertEquals(tyler.advisees(), 0);
		assertEquals(parker.advisees(), 1);
		PhD jordan= new PhD(2, 1995, "Jordan", tyler, parker);
		assertEquals(jordan.name(), "Jordan");
		assertEquals(jordan.year(), 1995);
		assertEquals(jordan.month(), 2);
		assertEquals(jordan.advisor1(), tyler);
		assertEquals(jordan.advisor2(), parker);
		assertEquals(jordan.advisees(), 0);
		assertEquals(tyler.advisees(), 1);
		assertEquals(parker.advisees(), 2);

		assertThrows(AssertionError.class, () -> { new PhD(3, 1998, null, tyler); });
		assertThrows(AssertionError.class, () -> { new PhD(3, 1998, "", tyler); });
		assertThrows(AssertionError.class, () -> { new PhD(0, 1998, "Parker", tyler); });
		assertThrows(AssertionError.class, () -> { new PhD(14, 1998, "Parker", tyler); });
		assertThrows(AssertionError.class, () -> { new PhD(3, 1998, "Parker", null); });
		assertThrows(AssertionError.class, () -> { new PhD(3, 1998, null, tyler, jordan); });
		assertThrows(AssertionError.class, () -> { new PhD(3, 1998, "", tyler, jordan); });
		assertThrows(AssertionError.class, () -> { new PhD(0, 1998, "Parker", tyler, jordan); });
		assertThrows(AssertionError.class, () -> { new PhD(14, 1998, "Parker", tyler, jordan); });
		assertThrows(AssertionError.class, () -> { new PhD(3, 1998, "Parker", null, jordan); });
		assertThrows(AssertionError.class, () -> { new PhD(3, 1998, "Parker", tyler, null); });
		assertThrows(AssertionError.class, () -> { new PhD(3, 1998, "Parker", tyler, tyler); });
	}

	@Test
	void testGroupD() {
		PhD mar98= new PhD(3, 1998, "mar98");
		PhD mar98P= new PhD(3, 1998, "mar98Q");
		PhD feb98= new PhD(2, 1998, "feb98");
		PhD apr98= new PhD(4, 1998, "apr98");
		PhD mar95= new PhD(3, 1995, "mar95");
		PhD feb95= new PhD(2, 1995, "feb95");
		PhD apr95= new PhD(4, 1995, "apr95");
		PhD mar01= new PhD(3, 2001, "mar01");
		PhD feb01= new PhD(2, 2001, "feb01");
		PhD apr01= new PhD(4, 2001, "apr01");
		assertEquals(mar98.gotAfter(mar98P), false);
		assertEquals(mar98.gotAfter(feb98), true);
		assertEquals(mar98.gotAfter(apr98), false);
		assertEquals(mar98.gotAfter(mar95), true);
		assertEquals(mar98.gotAfter(feb95), true);
		assertEquals(mar98.gotAfter(apr95), true);
		assertEquals(mar98.gotAfter(mar01), false);
		assertEquals(mar98.gotAfter(feb01), false);
		assertEquals(mar98.gotAfter(apr01), false);

		PhD parker= new PhD(3, 1998, "Parker");
		PhD tyler= new PhD(6, 1990, "Tyler", parker);
		PhD jordan= new PhD(2, 1995, "Jordan", tyler, parker);
		PhD david= new PhD(2, 1960, "David");
		PhD holly= new PhD(7, 1962, "holly", parker);
		PhD renee= new PhD(12, 1931, "holly", david, parker);
		assertEquals(parker.areSiblings(parker), false);
		assertEquals(parker.areSiblings(david), false);
		assertEquals(tyler.areSiblings(holly), true);
		assertEquals(tyler.areSiblings(jordan), true);
		assertEquals(jordan.areSiblings(tyler), true);
		assertEquals(jordan.areSiblings(renee), true);
		assertEquals(parker.areSiblings(null), false);

		assertThrows(AssertionError.class, () -> { parker.gotAfter(null); });
	}
}
