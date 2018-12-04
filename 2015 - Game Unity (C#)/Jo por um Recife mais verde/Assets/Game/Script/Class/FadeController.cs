using UnityEngine;
using System.Collections;

public class FadeController : MonoBehaviour {

	public enum EFadeState
	{
		NONE,
		DISABLED,
		IN_COMPLETE,
		OUT_COMPLETE
	}

	public Texture2D fadeTexture;

	public bool fadeIn { get; private set;}
	public bool fadeOut { get; private set;}
	public EFadeState fadeState {get; private set;}
	private float _fadeTime;
	private float _sceneTime;
	private Color _fadeAlphaColor;

	// Use this for initialization
	void Start () {
		this.fadeIn = false;
		this.fadeOut = false;
		this.fadeState = EFadeState.NONE;
		this._fadeAlphaColor = GUI.color;
	}
	
	// Update is called once per frame
	void Update () {
		if(this.fadeIn || this.fadeOut)
		{
			this._sceneTime += Time.deltaTime;
		}
	}

	void OnGUI()
	{
		if((!this.fadeIn && !this.fadeOut) || this.fadeState == EFadeState.DISABLED)
		{
			return;
		}

		if(this.fadeState == EFadeState.NONE && this._sceneTime <= 0)
		{
			this._fadeAlphaColor.a = 1.0f;
			GUI.color = this._fadeAlphaColor;
			GUI.DrawTexture(
				new Rect(0,0,Screen.width, Screen.height),
				this.fadeTexture);
		}

		if(this.fadeIn && !this.fadeOut)
		{
			this._fadeAlphaColor.a = 1.0f - (this._sceneTime/this._fadeTime);
			GUI.color = this._fadeAlphaColor;
			GUI.DrawTexture(
				new Rect(0,0,Screen.width, Screen.height),
				this.fadeTexture);
			if(this._sceneTime >= this._fadeTime)
			{
				this._sceneTime = 0;
				this.fadeIn = false;
				this.fadeState = EFadeState.IN_COMPLETE;
			}

			return;
		}

		else if(!this.fadeIn && this.fadeOut)
		{
			this._fadeAlphaColor.a = this._sceneTime/this._fadeTime;
			GUI.color = this._fadeAlphaColor;
			GUI.DrawTexture(
				new Rect(0,0,Screen.width, Screen.height),
				this.fadeTexture);

			if(this._sceneTime >= this._fadeTime)
			{
				this._sceneTime = this._fadeTime;
				this.fadeState = EFadeState.OUT_COMPLETE;
			}

			return;
		}
	}

	public void setFadeTime(float fadeTime)
	{
		this._fadeTime = fadeTime;
	}

	public void FadeInEffect()
	{
		this.fadeIn = true;
		this.fadeOut = false;
	}

	public void FadeOutEffect()
	{
		this.fadeOut = true;
	}

	public void DisableFade()
	{
		this.fadeIn = false;
		this.fadeOut = false;
		this.fadeState = EFadeState.DISABLED;
	}

	public void CleanFade()
	{
		this._sceneTime = 0;
		this.fadeState = EFadeState.NONE;
	}
}
