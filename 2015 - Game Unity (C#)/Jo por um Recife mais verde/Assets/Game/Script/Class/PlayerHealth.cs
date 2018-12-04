using UnityEngine;
using System.Collections;

public class PlayerHealth : MonoBehaviour {

	public bool onLose;
	public int maxLifes;
	public int currentLife { get; private set;}
	public int maxHP;
	public int currentHP { get; private set;}
	public Transform respawnPoint;
	private Animator _playerAnimator;
	private PlayerController _playerController;


	// Use this for initialization
	void Start () {
		this._playerAnimator = GetComponentInChildren<Animator> ();
		this._playerController = GetComponent<PlayerController> ();
		this.currentHP = this.maxHP;
		this.currentLife = this.maxLifes;
		this.onLose = false;
	}
	
	// Update is called once per frame
	void Update () {
		if(onLose)
		{
			GameController.instance.GameOver();
		}
	}

	public void AddLife()
	{
		this.currentLife++;
		this.currentLife = Mathf.Clamp (this.currentLife, 0, this.maxLifes);
	}

	public void RemoveLife()
	{
		this.currentLife--;
		this.currentLife = Mathf.Clamp (this.currentLife, 0, this.maxLifes);
		if(this.currentLife == 0)
		{
			GameController.instance.GameOver();
		}
		else
		{
			this.currentHP = this.maxHP;
		}
	}

	public void AddHP(int value)
	{
		this.currentHP += value;
		this.currentHP = Mathf.Clamp (this.currentHP, 0, this.maxHP);
	}
	
	public void RemoveHP(int valueDamage)
	{
		this.currentHP -= valueDamage;
		this.currentHP = Mathf.Clamp (this.currentHP, 0, this.maxHP);

		if(this.currentHP == 0)
		{
			RemoveLife();
			this.onLose = true;
			GameController.instance.CleanCurrentStage();
			this._playerAnimator.SetTrigger("lose");
		}
	}

	public void TakeDamage(int damage)
	{
		if(this.onLose)
		{
			return;
		}

		this._playerController.disabledInput = true;
		this._playerAnimator.SetTrigger("takingDamage");
		this.RemoveHP (damage);
	}

	public void Kill()
	{
		RemoveHP (this.maxHP);
	}

	public void ReceiveHP(int hp)
	{
		AddHP (hp);
	}

	public void PlayerLoseFinish()
	{
		RespawPlayer ();
	}

	public void RespawPlayer()
	{
		transform.position = respawnPoint.transform.position;
	}
}
