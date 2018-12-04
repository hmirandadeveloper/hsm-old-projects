using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class ShowNameOnCollider : MonoBehaviour {

	public string objectName;
	public bool rebuke;
	private Text _text;
	public bool isObject;
	public bool onlyFaceRight;
	private ObjectCatch _object;
	private Collider _boxCollider;
	private Light _pointLight;
	private PlayerController _player;

	// Use this for initialization
	void Start () {
		this._boxCollider = GetComponent<Collider> ();
		this._text = GetComponentInChildren<Text> ();
		this._pointLight = GetComponentInChildren<Light> ();

		if (this.isObject) 
		{
			this._object = GetComponentInParent<ObjectCatch> ();
		}

		this._text.text = this.objectName;
		this._text.enabled = false;
		this._pointLight.enabled = false;
	}

	// Update is called once per frame
	void Update () {
		if(this._player == null)
		{
			return;
		}

		if (this.isObject) 
		{
			if (this._object.isCatched) {
				this._boxCollider.enabled = false;
				this._text.enabled = false;
				this._pointLight.enabled = false;
			} else {
				this._boxCollider.enabled = true;
			}
		}
	}

	void OnTriggerEnter(Collider other)
	{
		if(other.tag != "Player")
		{
			return;
		}

		this._text.enabled = true;
		this._pointLight.enabled = true;
	}

	void OnTriggerStay(Collider other)
	{
		if(other.tag != "Player")
		{
			return;
		}

		this._player = other.GetComponentInParent<PlayerController> ();

		if(this.isObject)
		{
			return;
		}

		if(this._player.startRebuke && this.rebuke)
		{
			print ("Eh rebuke... Destruir");
			Destroy(this.gameObject);
		}
		else if (this._player.startPlant && !this.rebuke)
		{
			print ("Nao eh rebuke... Destruir");
			Destroy(this.gameObject);
			
		}
		
		if(!this.onlyFaceRight)
		{
			return;
		}
		
		if(onlyFaceRight && !this._player.facingRight)
		{
			this._text.enabled = false;
			this._pointLight.enabled = false;
			return;
		}
		else if(onlyFaceRight && this._player.facingRight)
		{
			this._text.enabled = true;
			this._pointLight.enabled = true;
		}
	}

	void OnTriggerExit(Collider other)
	{
		if(other.tag != "Player")
		{
			return;
		}
		
		this._text.enabled = false;
		this._pointLight.enabled = false;
	}
}
