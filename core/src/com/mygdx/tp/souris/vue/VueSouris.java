/*Cette classe a pour responsabilite la vue d'une souris et sa gestion */

package com.mygdx.tp.souris.vue;

import java.util.*;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.tp.souris.metier.Souris;

public class VueSouris extends VueEntite{
	private Souris souris;
	private final float vitesse;
	private Point<Float, Float> destination;
	private double dateDernierRapport;
	private double dateDernierAnniversaire;
	private double dateDerniereDestination;
	
	{
		souris = new Souris();
		vitesse = 1.0f;
		destination = Point.randomPoint();
		dateDernierRapport = System.currentTimeMillis();
		dateDernierAnniversaire = System.currentTimeMillis();
		dateDerniereDestination = System.currentTimeMillis();
	}
	
	public VueSouris() { super(); }
	
	public VueSouris(Point<Float, Float> position) {
		super(position);
	}
	
	public VueSouris(Point<Float, Float> position, Souris souris) {
		super(position);
		this.souris = souris;
	}
	
	public Collection<VueSouris> naissancePortee(Collection<Souris> nouveauxNes)
	{
		Collection<VueSouris> nouveauxNesVues = new ArrayList<VueSouris>();
		
		for (Souris nouveauNe : nouveauxNes)
		{
			VueSouris nouveauNeVue = new VueSouris(new Point<Float, Float>(position.getX(), position.getY()), nouveauNe);
			nouveauxNesVues.add(nouveauNeVue);
		}
		
		return nouveauxNesVues;
	}
	
	public Souris getSouris() { return souris; }
	public double getDateDernierRapport() { return dateDernierRapport; }
	public void setDateDernierRapport(double dateDernierRapport) { this.dateDernierRapport = dateDernierRapport; }
	
	public void render(SpriteBatch batch) {
		ShapeRenderer shapeRenderer = new ShapeRenderer();	
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(this.getCouleur().getCouleur());
		shapeRenderer.circle(this.getPosition().getX(),this.getPosition().getY(), 5);
		shapeRenderer.end();
	}
	
	public VueTombe tuerSouris() 
	{
		souris.die();
		return enterrement();
	}
	
	public VueTombe vieillirSouris()
	{
		if ((dateDernierAnniversaire - (System.currentTimeMillis()-5000)) <= 0)
		{
			dateDernierAnniversaire = System.currentTimeMillis();
			souris.vieillir();
			if (!souris.isAlive())
			{
				return enterrement();
			}
			else { return null; }
		}
		else { return null; }
	}
	
	public VueTombe enterrement()
	{
		VueTombe tombe = new VueTombe(position, couleur);
		
		return tombe;
	}
	
	public void move()
	{
		if ((dateDerniereDestination - (System.currentTimeMillis()-1000)) <= 0)
		{
			dateDerniereDestination = System.currentTimeMillis();
			destination = Point.randomPoint();
		}
		
		double angle = angle(destination);
		position.setX((float) (position.getX() + Math.cos(angle)*vitesse));
		position.setY((float) (position.getY() + Math.sin(angle)*vitesse));
	}
	
	public double angle(Point<Float, Float> destination) {
		return Math.atan2(destination.getY()-position.getY(), destination.getX()-position.getX());
	}
	
	public ArrayList<VueSouris> haveSex(VueSouris sourisPartenaire)
	{
		if (getSouris().isReproductionOk(sourisPartenaire.getSouris()))
		{
			Collection<VueSouris> nouveauxNes = new ArrayList<VueSouris>();
			
			nouveauxNes = naissancePortee(getSouris().haveSex(sourisPartenaire.getSouris()));
			dateDernierRapport = System.currentTimeMillis();
			sourisPartenaire.setDateDernierRapport(System.currentTimeMillis());
			
			return (ArrayList<VueSouris>)nouveauxNes;
		}
		else { return null; }		
	}
}
