using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class GreenCaterpillarController : MonoBehaviour, ICaterpillarEat {

	public bool enemyAwake;
	public bool started;
	public bool prepareAttack;
	public bool attacking;
	public bool eating;
	public bool hasEat;
	public bool knead;
	public bool healComplete;

	public bool isWait;
	public float idleTime;
	private float _currentIdleTime;
	private Text _mushroomCount;

	private int _mushroomConsomed;
	private int _totalMushroomToHeal;
	public int mushroomEasy;
	public int mushroomNormal;
	public int mushroomHard;
	private float _timeToRestartCountMushroom;
	private float _currentTimeToRestartCountMushroom;
	public float timeEasy;
	public float timeNormal;
	public float timeHard;

	public Transform particleEmissionPoint;
	public LayerMask whatIsGround;
	public LayerMask playerLayer;
	public Transform groundCheck;
	public float groundRadius;
	public bool grounded;
	public bool facingRight{ get; private set;}
	public float maxSpeed;
	public bool isMoving;
	public bool canWalk;
	public float jumpSpeed;
	private float _moveDirection;
	private Rigidbody _rigidbody;
	private Animator _caterpillarAnimator;
	public Transform eyePoint;
	public float fieldVisionShort;
	public float fieldVisionLong;

	public AudioSource caterpillarAudioSource;
	public AudioClip walkSound;
	public AudioClip jumpSound;
	public AudioClip impactSound;
	public AudioClip impactGroundSound;
	public AudioClip kneadSound;
	public AudioClip eatSound;
	public AudioClip healProcessSound;
	public AudioClip healSound;
	public AudioClip explosionSound;

	//ParticleEmission
	public Transform dustImpactParticlePrefab;
	public ParticleSystem dustRunRParticlePrefab;
	public ParticleSystem dustRunLParticlePrefab;
	public ParticleSystem explosionGreenParticlePrefab;
	public Transform particleImpactEmissionPoint;
	public Transform particleWalkEmissionPoint;
	public Transform particleExplosionEmissionPoint;
	public Transform butterflySpawnerLPoint;
	public Transform butterflySpawnerRPoint;

	//Butterfly
	public Transform monarchButterflyPrefab;

	// Use this for initialization
	void Start () {
		this.healComplete = false;
		this.enemyAwake = false;
		this.facingRight = false;
		this._rigidbody = GetComponent<Rigidbody> ();
		this._caterpillarAnimator = GetComponentInChildren<Animator> ();
		this.groundRadius = 0.2f;
		this._mushroomCount = GameObject.FindGameObjectWithTag ("GUIMushroomCount").GetComponent<Text>() as Text;
	}

	void LateUpdate()
	{
		if(this._totalMushroomToHeal > 0 && this._timeToRestartCountMushroom > 0)
		{
			return;
		}
		switch(GameController.instance.GetDifficulty())
		{
		case EDifficulty.EASY:
		{
			this._totalMushroomToHeal = this.mushroomEasy;
			this._timeToRestartCountMushroom = this.timeEasy;
			break;
		}
		case EDifficulty.NORMAL:
		{
			this._totalMushroomToHeal = this.mushroomNormal;
			this._timeToRestartCountMushroom = this.timeNormal;
			break;
		}
		case EDifficulty.HARD:
		{
			this._totalMushroomToHeal = this.mushroomHard;
			this._timeToRestartCountMushroom = this.timeHard;
			break;
		}
		}
	}

	// Update is called once per frame
	void Update () {

		this._mushroomCount.text = "x " + this._mushroomConsomed;

		this._moveDirection = 0;

		if(GameController.instance.paused)
		{
			this._caterpillarAnimator.SetFloat ("hAcceleration", Mathf.Abs(this._moveDirection));
			return;
		}

		this._moveDirection = (this.facingRight ? 1 : -1);

		if(this.enemyAwake)
		{
			if(this.healComplete)
			{
				if(this.facingRight)
				{
					FlipEnd();
				}
				var clone = Instantiate (this.explosionGreenParticlePrefab, this.particleExplosionEmissionPoint.position, this.particleExplosionEmissionPoint.rotation) as ParticleSystem;
				AudioSource.PlayClipAtPoint(this.explosionSound, transform.position, 1.0F);
				if(this.facingRight)
				{
					var butterfly = Instantiate (this.monarchButterflyPrefab, this.particleExplosionEmissionPoint.position, this.particleExplosionEmissionPoint.rotation) as ParticleSystem;
				}
				else
				{
					var butterfly = Instantiate (this.monarchButterflyPrefab, this.butterflySpawnerLPoint.position, this.butterflySpawnerLPoint.rotation) as ParticleSystem;
				}
				Destroy(this.gameObject);
			}
//Temp
			if(Input.GetKeyDown(KeyCode.Home))
			{
				AddMushroom();
			}

			this.isWait = this.idleTime > 0;
			grounded = Physics2D.OverlapCircle(groundCheck.position, groundRadius, whatIsGround);

			if(InAction())
			{
				return;
			}

			if(this._mushroomConsomed >= this._totalMushroomToHeal)
			{
				HealCaterpillar();
			}

			Ray ray = new Ray(this.eyePoint.transform.position, Vector3.right * this._moveDirection);

			if(this.isWait)
			{
				if((this._currentIdleTime += Time.deltaTime) <= this.idleTime)
				{
					return;
				}
				else
				{
					this._currentIdleTime = 0;
					this.idleTime = 0;
					this.canWalk = true;
				}
			}

			Debug.DrawRay(this.eyePoint.transform.position, (Vector3.right * this._moveDirection) * this.fieldVisionShort, Color.red);
			Debug.DrawRay(this.eyePoint.transform.position, (Vector3.right * this._moveDirection) * this.fieldVisionLong, Color.cyan);
			
			if(Physics.Raycast(this.eyePoint.transform.position, (Vector3.right * this._moveDirection) , this.fieldVisionShort, this.playerLayer.value))
			{
				AttackIdle();
				return;
			}
			else if(Physics.Raycast(this.eyePoint.transform.position, (Vector3.right * this._moveDirection) , this.fieldVisionLong, this.playerLayer.value))
			{
				PrepareAttack();
				return;
			}

			if(this.canWalk && this.grounded)
			{
				this._caterpillarAnimator.SetFloat ("hAcceleration", Mathf.Abs(this._moveDirection));

				if(this.isMoving)
				{
					FootStepAudio();
					this._rigidbody.velocity = new Vector2(this._moveDirection * this.maxSpeed , _rigidbody.velocity.y);
				}
			}
		}
	}	

	public void AwakeCaterpillar()
	{
		this._caterpillarAnimator.SetTrigger ("attackIdle");
		this.enemyAwake = true;
		this.attacking = true;
	}

	public void AttackIdle()
	{
		this._caterpillarAnimator.SetTrigger ("attackIdle");
		this.attacking = true;
		this.canWalk = false;
	}

	public void PrepareAttack()
	{
		this._caterpillarAnimator.SetTrigger ("prepareAttack");
		this.prepareAttack = true;
		this.canWalk = false;
	}

	public void Eat(ObjectEat.EFood food)
	{
		this._caterpillarAnimator.SetTrigger ("eat");
		AudioSource.PlayClipAtPoint(this.eatSound, transform.position, 1.0f);
		this.eating = true;
		if (food == ObjectEat.EFood.MUSHROOM) {
			this._mushroomConsomed++;
		}
		this.canWalk = false;
	}

	public bool HasEat()
	{
		return this.hasEat;
	}

	public bool IsWalk()
	{
		return this.canWalk;
	}

	public void Knead()
	{
		this._caterpillarAnimator.SetTrigger ("knead");
		AudioSource.PlayClipAtPoint(this.kneadSound, transform.position, 0.5F);
		this.knead = true;
		this.canWalk = false;
	}

	public bool InAction()
	{
		return this.prepareAttack ||
				this.attacking || 
				this.eating;
	}

	public void CleanActions()
	{
		this.prepareAttack =	
		this.attacking =
		this.eating = false;
	}

	public void Attack()
	{
		AudioSource.PlayClipAtPoint(this.jumpSound, transform.position, 0.5F);
		this._rigidbody.AddForce(new Vector2(jumpSpeed * 4.5F * this._moveDirection, jumpSpeed));
	}

	public void FlipStart()
	{
		this._caterpillarAnimator.SetTrigger ("flip");
		var clone = Instantiate (this.dustImpactParticlePrefab, this.particleWalkEmissionPoint.position, this.particleWalkEmissionPoint.rotation) as ParticleSystem;
		this.canWalk = false;
	}

	public void FlipEnd()
	{
		this.canWalk = true;
		this.facingRight = !this.facingRight;
		var clone = Instantiate (this.dustImpactParticlePrefab, this.particleWalkEmissionPoint.position, this.particleWalkEmissionPoint.rotation) as ParticleSystem;
		transform.Rotate (Vector3.up, 180.0f, Space.World);
	}

	public void AddMushroom()
	{
		this._mushroomConsomed++;
	}

	public void RemoveMushroom()
	{
		this._mushroomConsomed++;
	}

	public void ClearMushroom()
	{
		this._mushroomConsomed = 0;
	}

	void HealCaterpillar()
	{
		this.canWalk = false;
		this._caterpillarAnimator.SetTrigger ("heal");
		GameController.instance.currentStage.IsCaterpillarRescued ();
	}

	void FootStepAudio()
	{
		if(this.canWalk)
		{	
			if(!this.caterpillarAudioSource.isPlaying)
			{
				this.caterpillarAudioSource.Play ();
				if(this.facingRight)
				{
					var clone = Instantiate (this.dustRunRParticlePrefab, this.particleWalkEmissionPoint.position, this.particleWalkEmissionPoint.rotation) as ParticleSystem;
				}
				else
				{
					var clone = Instantiate (this.dustRunLParticlePrefab, this.particleWalkEmissionPoint.position, this.particleWalkEmissionPoint.rotation) as ParticleSystem;
				}
			}
		}
		else
		{
			this.caterpillarAudioSource.Stop ();
		}
	}

	public void PlayHealProcessSound()
	{
		AudioSource.PlayClipAtPoint (this.healProcessSound, transform.position, 1.0F);
	}

	public void PlayHealSound()
	{
		AudioSource.PlayClipAtPoint (this.healSound, transform.position, 0.8F);
	}

	public void PlayEatingSound()
	{
		AudioSource.PlayClipAtPoint (this.eatSound, transform.position, 0.5F);
	}

	public void PlayImpactGroundSound()
	{
		var clone = Instantiate (this.dustImpactParticlePrefab, this.particleImpactEmissionPoint.position, this.particleImpactEmissionPoint.rotation) as ParticleSystem;
		AudioSource.PlayClipAtPoint (this.impactGroundSound, transform.position, 1.0F);
	}
}
