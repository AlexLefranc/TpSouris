/* Cette classe a pour responsabilite la position d'une entite*/

package com.mygdx.tp.souris.vue;

import java.util.Random;

import com.badlogic.gdx.Gdx;

public class Point<X extends Number,Y extends Number> {
	  private X x;
	  private Y y;
	  
	  public Point() {}
	  
	  public Point(X x,Y y) {
		  this.x = x;
		  this.y = y;
	  }
	  
	  public void setX(X x) { this.x = x; }
	  
	  public X getX() { return x; }
	  
	  public void setY(Y y) { this.y = y; }
	  
	  public Y getY(){ return y; }
	  
	  public String toString(){ return new String("Point [") + x + ", " + y +"]"; }
	  
	  public static Point<Float, Float> randomPoint()
	  {
		  float x, y;	
		  final Random randomGenerator = new Random();
		  
		  x = randomGenerator.nextFloat() * Gdx.graphics.getWidth();
		  y = randomGenerator.nextFloat() * Gdx.graphics.getHeight();
		  
		  return new Point<Float, Float>(x, y);
	  }
	}
