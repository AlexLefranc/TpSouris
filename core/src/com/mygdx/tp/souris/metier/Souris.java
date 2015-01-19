/*Cette classe a pour responsabilite la souris */

package com.mygdx.tp.souris.metier;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;


public class Souris {
	
	private Sex sex;
	private boolean enVie = true;
	final private Collection<Souris> parents;
	final private Collection<Souris> enfants;
	final private static int MAX_ENFANTS = 10;
	final private static int RANK_PARENT = 1;
	final private static int MORAL = 2; // Moral = 1 => on touche pas aux soeurs
										// Moral = 2 => on touche pas aux cousines
										// Moral = 3 => on touche pas aux cousines issue de germain
	
	
	// code commun à tous les construteurs :
	{
		sex = Sex.randomSex();
		parents = new LinkedList<Souris>();
		enfants = new LinkedList<Souris>();
	}
	
	/** Crée une sourie sans parents**/
	public  Souris(){ }
	
	/** 
	 * Le constructeur ne devrait jamais etre appelé directement.
	 * Le constructeur est private car il ne faut pas publier une reference vers un objet qui n'est pas completement construit.
	 * Les parents doivent etre de sex different, en vie et sont interchangeables. 
	 * @param parent1 
	 * @param parent2
	 */
	private Souris(final Souris parent1,final Souris parent2){
		if ( parent1.isIllegalUnion(parent2) ){
			throw new IllegalArgumentException("That’s not how it works. That’s not how any of this works.");
		}
		if ( !parent1.isAlive() || !parent2.isAlive() ) {
			throw new  IllegalArgumentException("Pas de nécrophilie pls");
		}
		
		parents.add(parent2);
		parents.add(parent1);
	}
	
	/**
	 * Fait office de constructeur.
	 * Les parents doivent etre de sex different, en vie et sont interchangeables.
	 * @param parent1
	 * @param parent2
	 * @return Souris nouvellement crée
	 */
	private static Souris creationSouris(final Souris parent1,final Souris parent2)
	{
		Outils.assertNotNull(parent1,"Si vous voullez creer une souris sans parent passez par le constructeur sans arguements ");
		Outils.assertNotNull(parent2,"Si vous voullez creer une souris sans parent passez par le constructeur sans arguements ");
		if ( !parent1.isReproductionOk(parent2)) { throw new IllegalArgumentException("La morale ou la biologie n'approuve pas cet accouplement"); }
		
		final Souris nouvelleSouris =  new Souris( parent1,  parent2);
		parent1.addEnfant(nouvelleSouris);
		parent2.addEnfant(nouvelleSouris);
		
		return nouvelleSouris;
	}
	
	private void addEnfant(final Souris enfant){
		Outils.assertNotNull(enfant,"L'enfant ne peux pas etre null");
		if ( ! enfant.isAlive() ){
			throw new IllegalArgumentException("L'enfant doit etre en vie");
		}
		enfants.add(enfant);
	}
	

	/** 
	 * this et partenaire doivent etre en vie et etre de sex !=
	 * @param partenaire
	 * @return collection de nouvelle souris issue de la reproduction.
	 */
	public Collection<Souris> haveSex (final Souris partenaire)
	{
		final Random randomGenrator = new Random ();
		int nombreEnfant = randomGenrator.nextInt(MAX_ENFANTS);
		nombreEnfant++; // on veut au moins un enfant.
		
		final Collection<Souris> enfants = new LinkedList<Souris>();
		for (int i = 0 ; i < nombreEnfant ; i++ ){
			enfants.add(Souris.creationSouris(this, partenaire));
		}
		
		return enfants;
	}
	
	
	/**
	 *  donne une chance de mourir 
	 */
	public void vieillir() {
		final int tauxMort = 5;
		final Random randomGenerator = new Random();
		if ( randomGenerator.nextInt(100) < tauxMort )
		{
			die();
		}
	}
	
	/**
	 * Nous indique si la moral nous permet de nous reproduire
	 * @param partenaire
	 * @return
	 */
	public boolean isReproductionOk(final Souris partenaire){ 
		if ( isIllegalUnion(partenaire) )   { return false; }
		if ( ! partenaire.isAlive())   { return false; }
		if ( ! isAlive())              { return false; }
		if ( isAscendant(partenaire) ) { return false; }
		if ( isDescendant(partenaire)) { return false; }
		if ( isTooClose(partenaire))   { return false; }
		return true;
	}
	
	
	private Set<Souris> getAllAscendantAtRank(final int rank){
		if ( rank < RANK_PARENT ) { throw new IllegalArgumentException("Rank:1  <=> parent, 2 => Grands parents ... 0 ou moins n'a pas de sens"); }
		if ( rank == RANK_PARENT ) { 
			final Set<Souris> setParent = new LinkedHashSet<Souris>();
			setParent.addAll(parents);
			return setParent ;
		}
		Set<Souris> ascendant = new LinkedHashSet<Souris>();
		for (Souris parent : parents )
		{
			ascendant.addAll(parent.getAllAscendantAtRank(rank-1));
		}
		return ascendant;
 	}
	
	public Set<Souris> getAllDescendant()
	{
		Set<Souris> descendant = new LinkedHashSet<Souris>();
		descendant.addAll(enfants);
		for (Souris enfant : enfants )
		{
			descendant.addAll(enfant.getAllDescendant());
		}
		return descendant;
	}
	

	private boolean isTooClose(final Souris partenaire) {
		// On recupere tous les grands*x parents
		Set<Souris> laGenerationQuiVaBien = getAllAscendantAtRank(Souris.MORAL);
		Set<Souris> doNotFuck = new LinkedHashSet<Souris> ();
		for (Souris ascendant : laGenerationQuiVaBien ){
			doNotFuck.addAll(ascendant.getAllDescendant());
		}
		// si la souris est dans la liste des gens avec qui on peut pas, on renvoie que cette personne est trop proche de nous
		return doNotFuck.contains(partenaire);
	}

	private boolean isDescendant(final Souris partenaire) {
		if (enfants.isEmpty() ){ return false;}
		if (enfants.contains(partenaire)){return true;}
		for (Souris unEnfant : enfants )
		{
			if ( unEnfant.isDescendant(partenaire) )
			{
				return true;
			}
		}
		// si on est ni un fil, ni un descendant d'un fil, c'est qu'on est une fille !!! oupas... ><
		return false;
	}

	private boolean isAscendant(Souris partenaire) {
		return partenaire.isDescendant(this);
	}

	private boolean isIllegalUnion(final Souris other){
		return !sex.reproductionPossible(other.sex);
	}
	
	public boolean isAlive() {return enVie;}
	public void die() { enVie = false; };
	public Sex getSex() { return sex; }
	public static int getMoral() { return MORAL; }
	
}
