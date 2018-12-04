using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class ObjectCatch : MonoBehaviour {
	
	private Transform _playerHand;
	public Transform meshObject;
	private Vector3 _originalSizeMesh;
	public float reductionPercentage;
	private PlayerController _player;
	private PlayerController _playerCheckObject;
	public bool isCatched { get; private set;}
	public LayerMask whatIsGround;
	public Transform groundCheck;
	public float groundRadius;
	public bool grounded;
	public float gravityForce;
	public Image imageObject;
	public bool isPulled { get; private set;}
	public float timeToSpoil;
	private float _currentTimeToSpoil;
	private float _currentTimeToSpoilInHand;


	// Use this for initialization
	void Start () {
		this.imageObject = GameObject.FindGameObjectWithTag ("GUIMushroom").GetComponent<Image>() as Image;
		this._originalSizeMesh = this.meshObject.localScale;
		this.reductionPercentage /= 100;
		this.isPulled = false;
		this._playerCheckObject = GameObject.FindGameObjectWithTag ("Player").GetComponent<PlayerController>() as PlayerController;
		HideObject ();
		ClearObject ();
	}
	
	// Update is called once per frame
	void LateUpdate () {
		grounded = Physics2D.OverlapCircle(groundCheck.position, groundRadius, whatIsGround);

		if(this._player == null && this._playerHand == null)
		{
			return;
		}

		if(!this._player.withObject)
		{
			ClearObject();
		}
		else
		{
			transform.position = this._playerHand.position;
		}
	}

	void Update()
	{

		if(!this.isCatched && !grounded)
		{
			transform.Translate(new Vector3(0, (-1 * gravityForce * Time.deltaTime), 0));
		}

		if(!this.isCatched && this.grounded && this.isPulled)
		{
			if((this._currentTimeToSpoil += Time.deltaTime) >= this.timeToSpoil * 2.0f)
			{
				Destroy(this.gameObject);
			}
		}
		else if(this.isCatched && !this.grounded && this.isPulled)
		{
			print ("Está com o Objeto...");
			print ("Tempo total: " + (this.timeToSpoil * 3.0f));
			print ("Tempo atual: " + this._currentTimeToSpoilInHand);
			if((this._currentTimeToSpoilInHand += Time.deltaTime) >= this.timeToSpoil * 3.0f)
			{
				this._player.ObjectDestroyed();
				ClearObject();
				Destroy(this.gameObject);
			}
		}
	}

	void OnTriggerEnter(Collider other)
	{
		if (this._playerCheckObject.withObject) {
			return;
		}

		if(other.tag != "PlayerHand")
		{
			return;
		}

		this._player = other.GetComponentInParent<PlayerController> ();

		this._playerHand = other.GetComponent<Transform> ();
		this._player.ObjectCatched ();
		this.isCatched = true;
		this.isPulled = true;
		//ReduceObject ();
		HideObject ();
		this.imageObject.enabled = true;
		RecursiveChangeLayer (transform, 8);
	}

	void RecursiveChangeLayer(Transform root, int layer)
	{
		root.gameObject.layer = layer;
		foreach(Transform child in root)
		{
			RecursiveChangeLayer(child, layer);
		}
	}

	public void ReduceObject()
	{
		this.meshObject.localScale = new Vector3 (this.meshObject.localScale.x * this.reductionPercentage, this.meshObject.localScale.y * this.reductionPercentage, this.meshObject.localScale.z * this.reductionPercentage);
	}

	public void NormalizeObject()
	{
		this.meshObject.localScale = this._originalSizeMesh;
	}

	public void HideObject()
	{
		MeshRenderer mesh = this.meshObject.GetComponent<MeshRenderer> ();
		mesh.enabled = false;
	}

	public void ShowObject()
	{
		MeshRenderer mesh = this.meshObject.GetComponent<MeshRenderer> ();
		mesh.enabled = true;
	}

	public void ClearObject()
	{
		this._currentTimeToSpoil = 0;
		this._currentTimeToSpoilInHand = 0;
		if (!this._playerCheckObject.withObject) {
			this.imageObject.enabled = false;
		}
		this._player = null;
		this._playerHand = null;
		this.isCatched = false;
		//NormalizeObject ();
		ShowObject ();

		RecursiveChangeLayer (transform, 11);
	}
}
