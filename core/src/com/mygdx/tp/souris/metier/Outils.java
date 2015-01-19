/*Cette classe a pour responsabilite de determiner si un objet est nul*/

package com.mygdx.tp.souris.metier;
public abstract class Outils {
	
	public static void assertNotNull(final Object obj,final String message){
		if (null == obj){
			throw new IllegalArgumentException(message);
		}
	}
	
}
