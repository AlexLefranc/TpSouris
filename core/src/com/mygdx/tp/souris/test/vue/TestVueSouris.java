/* Cette classe a pour responsabilite le test de la vue de souris*/

package com.mygdx.tp.souris.test.vue;

import com.mygdx.tp.souris.vue.VueSouris;
import com.mygdx.tp.souris.vue.VueTombe;

import static org.junit.Assert.*;

public class TestVueSouris {
	public void testTuerSouris()
	{
		VueSouris testVueSouris = new VueSouris();		
		while (testVueSouris.tuerSouris() == null && testVueSouris.getSouris().isAlive()) { }
		assertTrue(true);
	}
	
	public void testEnterrement()
	{
		VueSouris testVueSouris = new VueSouris();		
		while (testVueSouris.enterrement() == null ) { }
		assertTrue(true);
	}
	
	public void testHaveSex()
	{
		VueSouris souris = new VueSouris();
		VueSouris sourisPartenaire = new VueSouris();
		
	}
	
	public void testVieillir()
	{
		VueSouris souris = new VueSouris();
		while(souris.vieillirSouris() == null)
		{
			assertFalse(false);
		}
		assertTrue(true);
	}
	
}
