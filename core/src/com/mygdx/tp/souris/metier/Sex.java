/*Cette classe a pour responsabilite le sexe d'une souris*/

package com.mygdx.tp.souris.metier;
import java.util.Random;

public enum Sex {
	Homme,
	Femme,
	Hermaphrodite;
	
	public static Sex randomSex()
	{
		final Sex tabSex[]= values();
		final Random randomGenerator = new Random();
		// nextInt ( X ) =  [0 - X [
		return tabSex[randomGenerator.nextInt(tabSex.length)];
	}
	
	// on defini la politique de reproduciton
	public boolean reproductionPossible(final Sex autre)
	{
		if ( this== Sex.Hermaphrodite || autre == Sex.Hermaphrodite ){
			return true;
		}
		if (this != autre ){
			return true;
		}
		return false;
	}
}
