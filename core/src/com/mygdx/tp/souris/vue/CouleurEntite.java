/* Cette classe a pour responsabilite de gerer la couleur des vues de souris*/

package com.mygdx.tp.souris.vue;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

public class CouleurEntite {
	private Color couleur;
	
	public CouleurEntite() {}
	
	public CouleurEntite(Color couleur)
	{
		this.couleur = couleur;
	}
	
	public void randomColor()
	{
		Random rand = new Random();
		
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		
		this.couleur = new Color(r, g, b, 1.0f);
	}
	
	public Color getCouleur() { return couleur; }

}
