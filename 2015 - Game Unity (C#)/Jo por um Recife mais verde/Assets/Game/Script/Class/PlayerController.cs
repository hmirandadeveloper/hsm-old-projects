using UnityEngine;
using System.Collections;

public class PlayerController : MonoBehaviour {
	
	public bool canPlant;
	public bool startPlant;
	public bool hasPlanted;
	public bool canRebuke;
	public bool startRebuke;
	public bool hasRebuked;
	public bool canCatchObject;
	public bool withObject;
	public bool disabledInput;

	public Transform particleEmissionPoint;
	public LayerMask whatIsGround;
	public Transform groundCheck;
	public float groundRadius;
	public bool grounded;
	public bool canRun;
	public bool canMoveFront;
	public bool facingRight{ get; private set;}
	public float maxSpeed;
	private float _timeToAdjustJump;
	private float _timeJumpCount;
	private float _speedOnAir;
	public float speedAfterJump;
	public float jumpSpeed;
	public bool isJump;
	private float _velocityFactor;
	private float _moveDirection;
	private Rigidbody _rigidbody;
	private Animator _playerAnimator;
	private int _groundedState;
	private AnimatorStateInfo _currentState;

	//Audio
	public AudioSource playerAudioSource;
	public AudioClip plantSound;
	public AudioClip runSound;
	public AudioClip jumpStartSound;
	public AudioClip jumpEndSound;
	public AudioClip walkSound;

	//ParticleEmission
	public Transform dustImpactParticlePrefab;
	public ParticleSystem dustRunRParticlePrefab;
	public ParticleSystem dustRunLParticlePrefab;

	//Android
	public bool isAndroid;

	//GUI Rebuke
	public GameObject guiRebukePrefab;
	public Transform JoRebukePanelPoint;

	// Use this for initialization
	void Start () {
		this.facingRight = true;
		this.isJump = false;
		this.canMoveFront = true;
		//this.canRun = true;
		this._rigidbody = GetComponent<Rigidbody> ();
		this._playerAnimator = GetComponentInChildren<Animator> ();
		this.groundRadius = 0.2f;
		this._speedOnAir = maxSpeed * 0.5F;
		this._timeToAdjustJump = 0.65F;
		this._groundedState = Animator.StringToHash ("Base Layer.Grounded");
		ResetPlayer ();
	}
	
	void FixedUpdate()
	{

		if(GameController.instance.paused)
		{
			return;
		}

		if(this.disabledInput)
		{
			return;
		}
		grounded = Physics2D.OverlapCircle(groundCheck.position, groundRadius, whatIsGround);
		this._playerAnimator.SetBool ("isGrounded", this.grounded);

		if(GameController.instance.wait)
		{
			return;
		}

		if(!this.canMoveFront && !this.facingRight && this._moveDirection < 0)
		{
			this._moveDirection = 0;
		}
		else if (!this.canMoveFront && this.facingRight && this._moveDirection > 0)
		{
			this._moveDirection = 0;
		}

		this._rigidbody.velocity = new Vector2(this._moveDirection * (_velocityFactor + speedAfterJump), _rigidbody.velocity.y);
		this.speedAfterJump = 0;
		this._playerAnimator.SetFloat ("hAcceleration", Mathf.Abs(this._moveDirection));
		this._playerAnimator.SetFloat ("vAcceleration", Mathf.Abs(this._rigidbody.velocity.y));
	}

	// Update is called once per frame
	void Update () {
		if(GameController.instance.wait)
		{
			return;
		}

		if(GameController.instance.paused)
		{
			return;
		}

		if(this.disabledInput)
		{
			return;
		}

		AdjustJump ();

		this._velocityFactor = this.canRun ? (this.isJump ? this._speedOnAir : this.maxSpeed) : this.maxSpeed / 2;
		float moveDirectionFactor = this.canRun ? 1 : 2;

		if (this.isAndroid) {
			this._moveDirection = JoystickAndroid.instance.axis / moveDirectionFactor;
		} 
		else 
		{
			this._moveDirection = Input.GetAxis ("Horizontal") / moveDirectionFactor;
		}
			FootStepAudio();
		this._currentState = this._playerAnimator.GetCurrentAnimatorStateInfo (0);

		if(!this.isJump && this.grounded && (this.isAndroid ? JoystickAndroid.instance.jump : Input.GetButtonDown("Jump"))
			&& this._currentState.fullPathHash == this._groundedState)
		{
			this.isJump = true;

			if(Mathf.Abs(this._moveDirection) > 0.5)
			{
				this._rigidbody.AddForce(new Vector2(0, jumpSpeed));
				AudioSource.PlayClipAtPoint(this.jumpStartSound, transform.position, 0.4F);
			}
			else
			{
				this._playerAnimator.SetTrigger("jumpIdleStart");
			}
		}

		if((this.isAndroid ? JoystickAndroid.instance.action : Input.GetButtonDown("Action")) && this.grounded)
		{
			
			if(this.withObject)
			{
				this._playerAnimator.SetTrigger("dropObject");
				this.disabledInput = true;
			}

			else if(this.canRebuke)
			{
				this.startRebuke = true;
				this.canRebuke = false;
				this._playerAnimator.SetTrigger("rebuke");
				this.disabledInput = true;
				var score = Instantiate(this.guiRebukePrefab, JoRebukePanelPoint.position, new Quaternion(0,0,0,0))  as GUIPointController;
			}
			
			else if(this.canPlant)
			{
				this.startPlant = true;
				this.canPlant = false;
				this.disabledInput = true;
				this._playerAnimator.SetTrigger("plant");
			}
			else
			{
				this._playerAnimator.SetTrigger("catchObject");
				this.disabledInput = true;
			}

		}
		
		if(this._moveDirection > 0.0f && !facingRight)
		{
			Flip();
		}
		
		else if(this._moveDirection < 0.0f && facingRight)
		{
			Flip();
		}
	}	

	public void ParticlesRunInstantiate()
	{
		if (this.facingRight && this.grounded) {
			var clone = Instantiate (this.dustRunLParticlePrefab, this.particleEmissionPoint.position, this.particleEmissionPoint.rotation) as ParticleSystem;
		}
		else
		{
			var clone = Instantiate (this.dustRunRParticlePrefab, this.particleEmissionPoint.position, this.particleEmissionPoint.rotation) as ParticleSystem;
		}
	}

	public void PlaySoundOnGroundCollider()
	{
		AudioSource.PlayClipAtPoint(this.jumpEndSound, transform.position, 0.5F);
	}

	public void ParticlesJumpImpactInstantiate()
	{
		if (this.grounded && this._moveDirection == 0) 
		{
			var clone = Instantiate (this.dustImpactParticlePrefab, this.particleEmissionPoint.position, this.particleEmissionPoint.rotation) as Transform;
		}
		else if (this.grounded)
		{
			if (this.facingRight) {
				var clone = Instantiate (this.dustRunLParticlePrefab, this.particleEmissionPoint.position, this.particleEmissionPoint.rotation) as ParticleSystem;
			}
			else
			{
				var clone = Instantiate (this.dustRunRParticlePrefab, this.particleEmissionPoint.position, this.particleEmissionPoint.rotation) as ParticleSystem;
			}
		}
	}

	public void ParticlesJumpRunImpactInstantiate()
	{
		if (this.facingRight && this.grounded) {
			var clone = Instantiate (this.dustRunLParticlePrefab, this.particleEmissionPoint.position, this.particleEmissionPoint.rotation) as ParticleSystem;
		}
		else
		{
			var clone = Instantiate (this.dustRunRParticlePrefab, this.particleEmissionPoint.position, this.particleEmissionPoint.rotation) as ParticleSystem;
		}
	}

	public void Flip()
	{
		facingRight = !facingRight;
		transform.Rotate (Vector3.up, 180.0f, Space.World);
	}

	private void AdjustJump()
	{
		if(!this.isJump)
		{
			this._timeJumpCount = 0;
			return;
		}

		this._timeJumpCount += Time.deltaTime;
		if(_timeJumpCount >= this._timeToAdjustJump)
		{
			this.isJump = false;
			this._timeJumpCount = 0;
		}

	}

	public void ObjectCatched()
	{
		this.withObject = true;
		this.canCatchObject = false;
	}

	public void ObjectDestroyed()
	{
		this.withObject = false;
		this.canCatchObject = true;
	}

	public void ResetPlayer()
	{
		this.disabledInput = false;
		this.canCatchObject = !this.withObject;
	}

	public void StartJumpIdle()
	{
		this._rigidbody.AddForce(new Vector2(0, this.jumpSpeed * 0.8F));
		AudioSource.PlayClipAtPoint(this.jumpStartSound, transform.position, 0.4F);
	}

	public void StartJumpOnKnead()
	{
		this._rigidbody.AddForce(new Vector2(0, this.jumpSpeed * 1.2F));
		AudioSource.PlayClipAtPoint(this.jumpStartSound, transform.position, 0.4F);
	}

	public void AddHorizontalForce(float force)
	{
		this._rigidbody.AddForce(new Vector2(force, 0));
	}

	void FootStepAudio()
	{
		if(this._moveDirection != 0)
		{
			if((Mathf.Abs(this._moveDirection)) > 0.5F)
			{
				this.playerAudioSource.clip = this.runSound;
			}
			else
			{
				this.playerAudioSource.clip = this.walkSound;
			}

			if(!this.playerAudioSource.isPlaying)
			{
				this.playerAudioSource.Play ();
			}
		}
		else
		{
			this.playerAudioSource.Stop ();
		}
	}

	public void correctionMoviment()
	{
		transform.Translate ((this.facingRight ? -0.1F : 0.1F), 0, 0);
	}
}
