using UnityEngine;
using System.Collections;

public class CaterpillarColliderCheck : MonoBehaviour {

	public float damagePushForce;
	private GreenCaterpillarController _greenCaterpillar;
	private Light _damageLight;
	public float maxTimeToLight;
	private float _currentTimeLight;
	public int damageEasy;
	public int damageNormal;
	public int damageHard;
	private int _currentDamage;

	// Use this for initialization
	void Start () {
		this._greenCaterpillar = GetComponentInParent<GreenCaterpillarController> ();
		//this.player = GameObject.FindGameObjectWithTag ("Player").GetComponent<PlayerHealth> ();
		this._damageLight = GetComponentInChildren<Light> ();
		this._damageLight.enabled = false;
		this._currentTimeLight = 0;
		this._currentDamage = 0;
	}

	void LateUpdate()
	{
		if(this._currentDamage > 0)
		{
			return;
		}

		switch(GameController.instance.GetDifficulty())
		{
		case EDifficulty.EASY:
		{
			this._currentDamage = this.damageEasy;
			break;
		}
		case EDifficulty.NORMAL:
		{
			this._currentDamage = this.damageNormal;
			break;
		}
		case EDifficulty.HARD:
		{
			this._currentDamage = this.damageHard;
			break;
		}
		}
	}

	// Update is called once per frame
	void Update () {
		if(this._damageLight.enabled)
		{
			if((this._currentTimeLight += Time.deltaTime) >= this.maxTimeToLight)
			{
				this._damageLight.enabled = false;
				this._currentTimeLight = 0;
			}
		}
	}

	void OnTriggerEnter(Collider other)
	{
		if(other.tag.Contains("Rigid"))
		{
			this._greenCaterpillar.FlipStart();
		}

		if(other.tag.Contains("Player"))
		{
			AudioSource.PlayClipAtPoint(this._greenCaterpillar.impactSound, transform.position, 0.5F);
			PlayerHealth playerHealth = other.GetComponent<PlayerHealth> ();
			PlayerController player = other.GetComponent<PlayerController> ();

			if(player == null || playerHealth == null)
			{
				return;
			}

			if(player.facingRight && this._greenCaterpillar.facingRight)
			{
				player.Flip();
			}
			else if(!player.facingRight && !this._greenCaterpillar.facingRight)
			{
				player.Flip();
			}

			player.AddHorizontalForce(damagePushForce * (player.facingRight ? -1 : 1));
			playerHealth.TakeDamage(this._currentDamage);
			this._damageLight.enabled = true;
		}

		if(other.tag.Contains("HealthMushroom"))
		{
			
		}
	}	
}
