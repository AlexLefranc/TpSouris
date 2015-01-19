/*Cette classe a pour responsabilite de gerer une collection d'entite*/

package com.mygdx.tp.souris.vue;

import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.tp.souris.metier.Sex;

public class Colonie implements Dessinable {
	private ArrayList<VueSouris> collSouris;
	private ArrayList<VueTombe> collTombes;
	private int nbSourisInitial;
	private final int MAX_COLONIE = 30;
	
	public Colonie()
	{
		Random rand = new Random();
		nbSourisInitial = rand.nextInt(20);
	
		collSouris = new ArrayList<VueSouris>();
		collTombes = new ArrayList<VueTombe>();
	
		for ( int i = 0 ;  i < nbSourisInitial ; i++ ) {
			collSouris.add(new VueSouris(Point.randomPoint()));
		}
	}
	
	public void render(SpriteBatch batch) {
		
		for (int i = 0; i < collSouris.size(); i++)
		{
			VueSouris souris = new VueSouris();
			souris = collSouris.get(i);
			
			souris.move();
			
			souris.render(batch);
			
			vieillir(souris);
			
			if ((souris.getSouris().getSex() == Sex.Femme || souris.getSouris().getSex() == Sex.Hermaphrodite) && collSouris.size() < MAX_COLONIE)
			{
				reproduction(souris);
			}
		}
		
		for (VueTombe tombe : collTombes)
		{
			if(!tombe.getEtat())
			{
				tombe.render(batch);
				tombe.deterioration();
			}
		}
	}
	
	public void tuerSouris(int X, int Y)
	{
		for (int i = 0; i < collSouris.size(); i++)
		{ 
			float x, y;
			VueSouris souris = new VueSouris();
			souris = collSouris.get(i);
			
			x = souris.getPosition().getX();
			y = souris.getPosition().getY();
			
			if (x >= X-10 && x <= X+10 && y >= (Gdx.graphics.getHeight()-Y)-10 && y <= (Gdx.graphics.getHeight()-Y)+10)
			{
				collTombes.add(souris.tuerSouris());
				collSouris.remove(souris);
			}
		}
	}
	
	public void creerSouris(int X, int Y)
	{
		if (collSouris.size() < MAX_COLONIE)
		{
			collSouris.add(new VueSouris(new Point<Float, Float>((float)X, (float)(Gdx.graphics.getHeight() - Y))));
		}
	}
	
	public boolean reproduction(VueSouris souris)
	{
		if ((souris.getDateDernierRapport() - (System.currentTimeMillis() - 30000) <= 0))
			{
				for (int j = 0; j < collSouris.size(); j++)
				{
					float x, y, xPartenaire, yPartenaire;
					VueSouris sourisPartenaire = new VueSouris();
					sourisPartenaire = collSouris.get(j); 
					
					x = souris.getPosition().getX();
					y = souris.getPosition().getY();
					yPartenaire = sourisPartenaire.getPosition().getY();
					xPartenaire = sourisPartenaire.getPosition().getX();
					
					if ((x <= (xPartenaire - 10.0) || x >= (xPartenaire + 10.0)) && (y <= (yPartenaire - 10.0) || y >= (yPartenaire + 10.0)))
					{
						ArrayList<VueSouris> nouveauxNes = new ArrayList<VueSouris>();
						if((nouveauxNes = souris.haveSex(sourisPartenaire)) != null)
						{
							for (int k = 0; k < nouveauxNes.size(); k++)
							{
								collSouris.add(nouveauxNes.get(k));
							}
							return true;
						}
					}
				}
			}
		return false;
	}
	
	public void vieillir(VueSouris souris)
	{
		VueTombe tombe = new VueTombe();
		if((tombe = souris.vieillirSouris()) != null)
		{
			collTombes.add(tombe);
			collSouris.remove(souris);
		}
	}
}
