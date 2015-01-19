/* Cette classe a pour responsabilite le test d'une vue de tombe*/

package com.mygdx.tp.souris.test.vue;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.mygdx.tp.souris.vue.VueTombe;

public class TestVueTombe {
	public void testDeterioration()
	{
		VueTombe testTombe = new VueTombe();
		
		testTombe.deterioration();
		while(!testTombe.getEtat())
		{
			assertFalse(false);
			testTombe.deterioration();
		}
		assertTrue(true);
	}
}
