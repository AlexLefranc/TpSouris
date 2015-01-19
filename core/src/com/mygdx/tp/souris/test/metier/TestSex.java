/* Cette classe a pour responsabilite le test de sexe d'une souris */

package com.mygdx.tp.souris.test.metier;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mygdx.tp.souris.metier.Sex;

public class TestSex {

	@Test
	public void testReproductionPossible() {
		final Sex HOMME = Sex.Homme;
		final Sex FEMME = Sex.Femme;
		final Sex HERMA = Sex.Hermaphrodite;
		
		assertTrue(HOMME.reproductionPossible(FEMME));
		assertTrue(HOMME.reproductionPossible(HERMA));
		
		assertTrue(HERMA.reproductionPossible(FEMME));
		assertTrue(HERMA.reproductionPossible(HERMA));
		assertTrue(HERMA.reproductionPossible(HOMME));
		
		assertTrue(FEMME.reproductionPossible(HOMME));
		assertTrue(FEMME.reproductionPossible(HERMA));
		
		assertFalse(HOMME.reproductionPossible(HOMME));
		assertFalse(FEMME.reproductionPossible(FEMME));
	
	}
	
	/**
	 *  Test naif, qui produit une boucle infi en cas d'echec
	 */
	@Test
	public void testRandomSex()
	{
		while ( Sex.Hermaphrodite == Sex.randomSex() ){};
		while ( Sex.Homme == Sex.randomSex() ){};
		while ( Sex.Femme == Sex.randomSex() ){};
		
		assertTrue(true);
	}

}
