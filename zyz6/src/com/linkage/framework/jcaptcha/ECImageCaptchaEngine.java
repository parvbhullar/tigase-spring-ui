package com.linkage.framework.jcaptcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageFilter;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByFilters;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

public class ECImageCaptchaEngine extends ListImageCaptchaEngine{

	@Override
	protected void buildInitialFactories() {
		int minWordLength = 4;
		int maxWordLength = 4;
		int fontSize = 25;
		int imageWidth = 60;
		int imageHeight = 40;

		//word generator
		//字母验证
		//WordGenerator dictionnaryWords = new ComposeDictionaryWordGenerator(new FileDictionary("toddlist"));
		//数字验证
		WordGenerator dictionnaryWords = new RandomWordGenerator("0123456789");
		//word2image components
		TextPaster randomPaster = new DecoratedRandomTextPaster(minWordLength, maxWordLength,
				new RandomListColorGenerator(new Color[] { new Color(148, 0, 211), new Color(148, 0, 211),
						new Color(148, 0, 211) }), new TextDecorator[] {});
		BackgroundGenerator background = new UniColorBackgroundGenerator(imageWidth, imageHeight, new Color(135, 206, 250));
		FontGenerator font = new RandomFontGenerator(fontSize, fontSize, new Font[] {
				new Font("Dialog", Font.BOLD, fontSize), new Font("Dialog", Font.BOLD, fontSize),
				new Font("Dialog", Font.BOLD, fontSize) });

		ImageDeformation postDef = new ImageDeformationByFilters(new ImageFilter[] {});
		ImageDeformation backDef = new ImageDeformationByFilters(new ImageFilter[] {});
		ImageDeformation textDef = new ImageDeformationByFilters(new ImageFilter[] {});

		WordToImage word2image = new DeformedComposedWordToImage(font, background, randomPaster, backDef, textDef,
				postDef);
		addFactory(new GimpyFactory(dictionnaryWords, word2image));
	}
}
