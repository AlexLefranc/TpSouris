/*Cette classe a pour responsabilite le test de la souris*/

package com.mygdx.tp.souris.test.metier;
import java.util.ArrayList;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import com.mygdx.tp.souris.metier.*;

/**
 * Les tests ne doivent porter que sur ce dont à la responsabilité Souris
 * On peut éventuellement faire un test sur le sex pour vérifier que souris utilise bien son Sex 
 * mais c'est tout.
 * 
 * @author martin
 *
 */

public class TestSouris {

	public Souris getSourisWithSex(final Sex unSex)
	{
		Souris souris = new Souris ();
		while (souris.getSex() != unSex )
		{
			souris = new Souris();
		}
		return souris;
	}
	
	public Souris getSourisWithSex(final Souris parent1,final Souris parent2,final Sex unSex)
	{
		ArrayList<Souris> enfant = new ArrayList<Souris>  ( parent1.haveSex(parent2));
		while (enfant.get(0).getSex() != unSex )
		{
			enfant = new ArrayList<Souris>  ( parent1.haveSex(parent2));
		}
		return enfant.get(0);
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAliveValide() {
		Souris male = getSourisWithSex(Sex.Homme);
		Souris female = getSourisWithSex(Sex.Femme);
		
		assertTrue(male.isReproductionOk(female));
		
		male.die();
		assertFalse(male.isReproductionOk(female));
		
		male = getSourisWithSex(Sex.Homme);
		female.die();
		assertFalse(male.isReproductionOk(female));
		
		male.die();
		assertFalse(male.isReproductionOk(female));
	}
	
	
	// Redite de sex
	@Test
	public void testSexDifferent ()
	{
		final Souris male1 = getSourisWithSex(Sex.Homme);
		final Souris male2 = getSourisWithSex(Sex.Homme);
		
		final Souris female1 = getSourisWithSex(Sex.Femme);
		final Souris female2 = getSourisWithSex(Sex.Femme);
		
		assertFalse(male1.isReproductionOk(male2));
		assertFalse(female1.isReproductionOk(female2));
	
		assertTrue(male1.isReproductionOk(female1));
		assertTrue(female1.isReproductionOk(male1));
	}
	
	@Test
	public void testFamille()
	{
		// il faut faire un import, cela permet de faire un test que si une condition est vérifié.
		// Ce test par exemple n'a pas de sens si on a pas une moral de 2.
		Assume.assumeTrue(Souris.getMoral()==2);
	
		final Souris arriereGrandMere = getSourisWithSex(Sex.Femme);
		final Souris arriereGrandPere = getSourisWithSex(Sex.Homme);
		 
		final Souris leGrandPere = getSourisWithSex(arriereGrandPere,arriereGrandMere,Sex.Homme);		 
		
		
		final Souris grandMere = getSourisWithSex(Sex.Femme);
		
		final Souris mere  = getSourisWithSex(grandMere,leGrandPere,Sex.Femme);
		final Souris oncle = getSourisWithSex(grandMere,leGrandPere,Sex.Homme);
		
		final Souris pere =  getSourisWithSex(Sex.Homme);
		final Souris femmeDeLOncle =  getSourisWithSex(Sex.Femme);
		
		final Souris soeur  = getSourisWithSex(mere,pere,Sex.Femme);
		final Souris fils = getSourisWithSex(mere,pere,Sex.Homme);
		
		final Souris cousine = getSourisWithSex(oncle,femmeDeLOncle,Sex.Femme);
		
		assertFalse(fils.isReproductionOk(soeur));
		assertFalse(fils.isReproductionOk(cousine));
		assertFalse(fils.isReproductionOk(mere));
		assertFalse(fils.isReproductionOk(grandMere));
		assertFalse(fils.isReproductionOk(arriereGrandMere));
		assertTrue(fils.isReproductionOk(femmeDeLOncle)); // c'est pas cool mais ça passe.
		
		// dans l'autre sens
		assertFalse(soeur.isReproductionOk(fils));
		assertFalse(cousine.isReproductionOk(fils));
		assertFalse(mere.isReproductionOk(fils));
		assertFalse(grandMere.isReproductionOk(fils));
		assertFalse(arriereGrandMere.isReproductionOk(fils));
		assertTrue(femmeDeLOncle.isReproductionOk(fils)); 

		// on vérifie qu'on peut allez "dire bonjour" aux cousine issu de germain
		final Souris frereDuGrandPere = getSourisWithSex(arriereGrandPere,arriereGrandMere,Sex.Homme);
		final Souris uneFemme = getSourisWithSex(Sex.Femme);
		
		final Souris FilsDuFereDuGrandPere = getSourisWithSex(frereDuGrandPere,uneFemme,Sex.Homme);
		final Souris uneAutreFemme = getSourisWithSex(Sex.Femme);
		
		final Souris cousineIssuDeGermain =  getSourisWithSex(FilsDuFereDuGrandPere,uneAutreFemme,Sex.Femme);
		
		assertTrue(cousineIssuDeGermain.isReproductionOk(fils)); 
		assertTrue(fils.isReproductionOk(cousineIssuDeGermain));
		
	}

}
