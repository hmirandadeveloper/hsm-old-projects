using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class TextEffectController : MonoBehaviour {

	public enum EEffectType
	{
		NONE,
		TRANSPARENCY,
		WRITE
	}

	public EEffectType effectType;
	private Text textUI;
	private Outline outline;
	private float timeCount;

	//State Effect
	private bool _isFinishNoLoop;

	//Wait
	public bool isWait;
	public float secondsToWait;
	
	//Effect Transparency:
	public float minTransparency;
	public float maxTransparency;
	private float transparencyFactor;
	public float timeToRepeat;
	private Color colorTemp;

	//Effect Write:
	public string[] texts;
	public string fullText;
	private int currentTextIndex;
	private int index;
	public float writeVelocity;
	public float timeToRestart;
	public bool isRepeat;
	public bool isSoundEffect;
	public AudioSource audiosource;

	// Use this for initialization
	void Start () {
		this.index = 0;

		this.textUI = GetComponent<Text> ();
		this.timeCount = 0;
		if(this.effectType == EEffectType.TRANSPARENCY)
		{
			this.outline = GetComponent<Outline> ();
			this.textUI.text = this.fullText;
			this.colorTemp = new Color (1, 1, 1, 1);
		}

		if(this.effectType == EEffectType.WRITE)
		{
			this.writeVelocity = 1.0F / this.writeVelocity;

			if(this.texts.Length <= 0)
			{
				if(this.texts.Length > 0)
				{
					this.fullText = this.texts[0];
				}
				else if(this.textUI.text != "")
				{
					this.fullText = this.textUI.text;
				}
			}
			else
			{
				this.currentTextIndex = 0;
				this.fullText = this.texts[this.currentTextIndex];
			}

			this.textUI.text = "";
		}
	}
	
	// Update is called once per frame
	void Update () {
		if(!this.textUI.enabled)
		{
			return;
		}

		if(this.isWait)
		{
			Wait();
			return;
		}

		if(this.effectType == EEffectType.NONE)
		{
			return;
		}

		switch (this.effectType)
		{
		case EEffectType.TRANSPARENCY:
		{
			if((this.timeCount += Time.deltaTime) >= this.timeToRepeat)
			{
				
				if(this.textUI.color.a <= this.minTransparency)
				{
					this.transparencyFactor = 0.05F;
				}
				
				if(this.textUI.color.a >= this.maxTransparency)
				{
					this.transparencyFactor = -0.05F;
				}
				this.colorTemp.a = this.colorTemp.a + this.transparencyFactor;
				this.textUI.color = new Color(
					this.textUI.color.r,
					this.textUI.color.g,
					this.textUI.color.b,
					this.colorTemp.a);
				if(this.outline != null)
				{
					this.outline.effectColor = new Color(
						this.outline.effectColor.r,
						this.outline.effectColor.g,
						this.outline.effectColor.b,
						this.colorTemp.a);
				}
				this.timeCount = 0;
			}
			break;
		}

		case EEffectType.WRITE:
		{
			if(this.textUI.text.Length == this.fullText.Length  && timeCount >= 0)
			{
				if(isRepeat && timeCount >= 0)
				{
					this.fullText = texts[currentTextIndex];
					this.textUI.text = "";
					this.index = 0;
				}
				else
				{
					return;
				}
			}
			this.timeCount += Time.deltaTime;

			if(this.timeCount >= writeVelocity)
			{
				if(this.index < this.fullText.Length)
				{
					this.textUI.text += this.fullText.ToCharArray()[this.index];
					this.index++;

					if(isSoundEffect)
					{
						if(!this.audiosource.isPlaying)
						{
							this.audiosource.Play();
						}

						if(this.fullText.Length - this.textUI.text.Length > 5)
						{
							if(!this.audiosource.isPlaying)
							{
								this.audiosource.Play();
							}
						}
						else
						{
							this.audiosource.Stop();
						}
					}

					this.timeCount = (this.textUI.text.Length == this.fullText.Length && isRepeat) ? -1 * timeToRestart : 0;
				}

				if(this.textUI.text.Length == this.fullText.Length && this.texts.Length > 1)
				{

					this.isRepeat = true;

					this.currentTextIndex++;
					this.timeCount = -1 * timeToRestart;

					if(this.currentTextIndex > this.texts.Length - 1)
					{
						this.currentTextIndex = this.texts.Length - 1;
						this.isRepeat = false;
						this._isFinishNoLoop = true;
					}
				}
				else if(this.textUI.text.Length == this.fullText.Length && !this.isRepeat)
				{
					this._isFinishNoLoop = true;
				}
			}

			return;
		}

		}
	}

	public void SetTexts(string[] texts)
	{
		this.currentTextIndex = 0;
		this.textUI.text = "";

		this.texts = texts;
		this.fullText = this.texts[0];
	}

	public void SetText(string text)
	{
		this.textUI.text = "";

		this.fullText = text;
	}

	public bool IsFinish()
	{
		return this._isFinishNoLoop;
	}

	private void Wait()
	{
		this.timeCount += Time.deltaTime;
		if(this.timeCount >= this.secondsToWait)
		{
			this.isWait = false;
			this.timeCount = 0;
		}
	}
}
