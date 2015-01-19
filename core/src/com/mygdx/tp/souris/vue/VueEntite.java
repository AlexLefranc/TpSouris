/* Cette classe a pour responsabilite la vue d'une entite */

package com.mygdx.tp.souris.vue;

public abstract class VueEntite implements Dessinable{
	protected Point<Float, Float> position;
	protected CouleurEntite couleur;
	
	{
		position = new Point<Float, Float>();
		couleur = new CouleurEntite();
	}
	
	public VueEntite() {}
	
	public VueEntite(Point<Float, Float> position, CouleurEntite couleur)
	{
		this.position = position;
		this.couleur = couleur;
	}
	
	public VueEntite(Point<Float, Float> position)
	{
		this.position = position;
		this.couleur = new CouleurEntite();
		this.couleur.randomColor();
		
	}
	
	public Point<Float, Float> getPosition() { return position; }
	public CouleurEntite getCouleur() { return couleur; }
}
