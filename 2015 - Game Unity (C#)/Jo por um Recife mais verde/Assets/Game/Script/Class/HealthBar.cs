using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class HealthBar : MonoBehaviour {

	public PlayerHealth playerHealth;
	private RectTransform _hpSprite;
	private Image _hpImg;
	public Color maxHealthColor = new Color (0 / 255f, 255 / 255f, 0 / 255f);
	public Color minHealthColor = new Color (255 / 255f, 0 / 255f, 0 / 255f);
	
	// Use this for initialization
	void Start () {
		this._hpSprite = GetComponent<RectTransform> ();
		this._hpImg = GetComponent<Image> ();
	}
	
	// Update is called once per frame
	void Update () {
		var healthPercent = playerHealth.currentHP / (float)playerHealth.maxHP;
		
		this._hpSprite.localScale = new Vector3 (healthPercent, 1, 1);
		this._hpImg.color = Color.Lerp (maxHealthColor, minHealthColor, healthPercent);
	}
}
