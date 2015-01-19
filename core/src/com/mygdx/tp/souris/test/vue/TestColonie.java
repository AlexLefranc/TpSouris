/* Cette classe a pour responsabilite le test d'une colonie */

package com.mygdx.tp.souris.test.vue;

import com.mygdx.tp.souris.vue.Colonie;
import com.mygdx.tp.souris.vue.VueSouris;
import static org.junit.Assert.*;

public class TestColonie {
	public void testVieillir()
	{
		Colonie testColonie = new Colonie();
		VueSouris testVueSouris = new VueSouris();
		
		testColonie.vieillir(testVueSouris);
		if (testVueSouris.getSouris().isAlive())
		{
			assertTrue(true);
		}
		else
		{
			assertFalse(false);
		}
	}
	
	public void testReproduction()
	{
		VueSouris testVueSouris = new VueSouris();
		Colonie testColonie = new Colonie();
		
		while (!testColonie.reproduction(testVueSouris))
		{
			assertFalse(false);
		}
		assertTrue(true);
	}
}
