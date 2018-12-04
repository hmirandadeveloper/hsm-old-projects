using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class ImageTransitionToWord : MonoBehaviour {

	public string[] words;
	public string[] authors;
	public Sprite[] images;
	public Text textIntroGame;
	public Text TextAutorName;
	//public float alphaTransitionTime;

	private int _currentIndex;
	private Image _image;
	//private float _sceneTime;

	// Use this for initialization
	void Start () {
		this._image = GetComponent<Image> ();
		this._image.color = new Color (1, 1, 1, 0);
	}
	
	// Update is called once per frame
	void Update () {


		if(this.textIntroGame.text.Contains(words[_currentIndex]))
		{
			this._image.color = new Color (1, 1, 1, 1);
			//Function clear Image Color
			this._image.sprite = this.images [_currentIndex];
			this.TextAutorName.text = this.authors[_currentIndex];
			//Function show Image Color
			this._currentIndex++;

			if(this._currentIndex == this.words.Length)
			{
				this._currentIndex = this.words.Length - 1;
			}

		}
	}

	void OnGUI()
	{

	}
}
