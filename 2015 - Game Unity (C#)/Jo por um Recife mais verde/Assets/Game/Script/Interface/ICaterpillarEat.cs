using UnityEngine;
using System.Collections;

public interface ICaterpillarEat {

	void Eat(ObjectEat.EFood food);
	bool HasEat();
	bool IsWalk();

}
