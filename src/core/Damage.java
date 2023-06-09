package core;

import units.Unit;
/**
 * unused damage class
 * @author Owen
 *
 */
public class Damage 
{
	public static void dealDam(int dam, Unit target)
	{
		target.setHp(target.getHp()-dam);
	}
}
