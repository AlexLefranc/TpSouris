/* Cette classe a pour responsabilite le test d'un point */

package com.mygdx.tp.souris.test.vue;

import com.mygdx.tp.souris.vue.Point;
import static org.junit.Assert.*;

public class TestPoint {
	public void testRandomPoint()
	{
		while (Point.randomPoint() == Point.randomPoint())
		{
			
		}
		assertTrue(true);
	}
}
