package core;

import units.Unit;

public class Damage 
{
	public static void dealDam(int dam, Unit target)
	{
		target.setHp(target.getHp()-dam);
	}
}
