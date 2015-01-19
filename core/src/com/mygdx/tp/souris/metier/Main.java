/* Cette classe a pour responsabilite du lancement du en console d'une collection de souris  */

package com.mygdx.tp.souris.metier;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;


public class Main {

	/**
	 * Un petit programme pour voir si mes souris vivent bien 
	 * @param args
	 */
	public static void main(String[] args) {
		
	
		final int nbTourDeVie = 100;
		final int nbSourisInitial = 100;
		final Collection<Souris> collSouris = new LinkedList<Souris>();
		for ( int i = 0 ;  i < nbSourisInitial ; i++ ) {
			collSouris.add(new Souris());
		}
		
		int cpt = 0;
		while (cpt<nbTourDeVie && !collSouris.isEmpty() )
		{
			cpt++;
			Souris sourisAMarier = null;
			final Collection<Souris> nouveauNee = new LinkedHashSet<Souris>();
			final Collection<Souris> sourisMorte = new  LinkedHashSet<Souris> ();
			for (Souris souris : collSouris)
			{
				if ( sourisAMarier == null )
				{
					sourisAMarier = souris;
				}
				if ( sourisAMarier.isReproductionOk(souris))
				{
					nouveauNee.addAll(sourisAMarier.haveSex(souris));
					sourisAMarier = null;
				}
				souris.vieillir();
				if ( ! souris.isAlive() )
				{
					sourisMorte.add(souris);
				}
			}
			System.out.println("nombre mort : " + sourisMorte.size() + " nombre naissance "+ nouveauNee.size() + " population : "+collSouris.size() );
			collSouris.removeAll(sourisMorte);
			collSouris.addAll(nouveauNee);
			
		}
	}

}
