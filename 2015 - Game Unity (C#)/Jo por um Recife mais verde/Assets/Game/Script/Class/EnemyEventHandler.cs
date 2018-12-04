using UnityEngine;
using System.Collections;

public class EnemyEventHandler : MonoBehaviour {

	private GreenCaterpillarController _greenCaterpillar;
	private PlayerController _player;

	// Use this for initialization
	void Start () {
		this._greenCaterpillar = GetComponentInParent<GreenCaterpillarController> ();
		this._player = GameObject.FindGameObjectWithTag ("Player").GetComponent<PlayerController> ();
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	void Attack()
	{
		this._greenCaterpillar.Attack ();
	}

	public void MoveStart ()
	{
		this._greenCaterpillar.isMoving = true;
	}

	public void MoveEnd ()
	{
		this._greenCaterpillar.isMoving = false;
	}

	public void ActionEnd ()
	{
		this._greenCaterpillar.CleanActions ();
		this._greenCaterpillar.idleTime = 2;
	}

	public void AttackGroundImpact()
	{
		this._greenCaterpillar.PlayImpactGroundSound ();
	}

	public void FlipEnd()
	{
		this._greenCaterpillar.FlipEnd ();
		this._greenCaterpillar.CleanActions ();
	}

	public void PlayerJumpAfterKnead()
	{
		this._player.StartJumpOnKnead ();
	}

	public void HasEat ()
	{
		this._greenCaterpillar.hasEat = true;
	}

	public void Eating ()
	{
		this._greenCaterpillar.hasEat = true;
	}

	public void PlayHealProcessSound()
	{
		this._greenCaterpillar.PlayHealProcessSound ();
	}

	public void PlayHealSound()
	{
		this._greenCaterpillar.PlayHealSound ();
	}

	public void HealFinish()
	{
		this._greenCaterpillar.healComplete = true;
	}

	public void PlayEatingSound()
	{
		this._greenCaterpillar.PlayEatingSound ();
	}
}
