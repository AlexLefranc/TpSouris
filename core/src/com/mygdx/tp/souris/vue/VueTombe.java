/*Cette classe a pour responsabilite la vue d'une tombe et sa gestion*/

package com.mygdx.tp.souris.vue;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class VueTombe extends VueEntite{
	private double dateEnterrement;
	private boolean detruite;
	
	{
		dateEnterrement = System.currentTimeMillis();
		detruite = false;
	}
	
	public VueTombe() { super(); }
	
	public VueTombe(Point<Float, Float> position, CouleurEntite couleur) {
		super(position, couleur);
	}

	public void render(SpriteBatch batch)
	{
		ShapeRenderer shapeRenderer = new ShapeRenderer();
		float xCroix, yCroix;
		
		xCroix = this.getPosition().getX();
		yCroix = this.getPosition().getY();
		
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(this.getCouleur().getCouleur());
		shapeRenderer.line(xCroix-5, yCroix, xCroix+5, yCroix);
		shapeRenderer.line(xCroix, yCroix+5, xCroix, yCroix-10);	
		
		shapeRenderer.end();
	}
	
	public void deterioration()
	{
		if ((dateEnterrement - (System.currentTimeMillis()-5000)) <= 0)
		{
			detruite = true;
		}
	}
	
	public boolean getEtat() { return detruite; }
}
