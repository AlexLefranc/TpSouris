/* Cette classe a pour responsabilite de gerer l'interface generale (clics et fenetre)*/

package com.mygdx.tp.souris;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.tp.souris.vue.*;

public class MyGdxTpSouris extends ApplicationAdapter {
	private SpriteBatch batch;
	private Colonie colonieSouris;
	
	@Override
	public void create () {
		colonieSouris = new Colonie();
	}
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Gdx.input.setInputProcessor(new InputAdapter () {
			public boolean touchDown (int x, int y, int pointer, int button) {
				if ( button == Input.Buttons.RIGHT )
				{
					colonieSouris.creerSouris(Gdx.input.getX(), Gdx.input.getY());
				}
				return true;
			}
		});
		
		boolean leftPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
		if ( leftPressed )
		{
			colonieSouris.tuerSouris(Gdx.input.getX(), Gdx.input.getY());
		}
		
		
		colonieSouris.render(batch);
	}
	
}